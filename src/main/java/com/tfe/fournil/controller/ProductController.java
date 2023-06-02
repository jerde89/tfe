package com.tfe.fournil.controller;

import com.tfe.fournil.entity.Product;
import com.tfe.fournil.entity.ProductCategory;
import com.tfe.fournil.entity.ProductVersion;
import com.tfe.fournil.repository.ProductCategoryRepository;
import com.tfe.fournil.repository.ProductRepository;
import com.tfe.fournil.repository.ProductVersionRepository;
import com.tfe.fournil.service.FileStorageService;
import com.tfe.fournil.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @GetMapping("")
    public String showCategoryList(Model model) {
        List<ProductCategory> category = productCategoryRepository.findAll();
        model.addAttribute("categoryList", category);
        return "product";
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> ajaxShowdisplayProduct() {
        List<Product> product = productRepository.findAll();
        return ResponseEntity.ok(product);
    }

    @GetMapping("/findByCategories")
    public ResponseEntity<List<Product>> productListByCategories(@RequestParam(value = "categories", required = false) List<Long> categorieIds) {
        if (categorieIds != null && !categorieIds.isEmpty()) {
            return ResponseEntity.ok(productRepository.findAllByCategoryIdIn(categorieIds));
        } else {
            return ResponseEntity.ok(productRepository.findAll());
        }
    }

    @PostMapping(value = "")
    //@RequestBody Product product => va recevoir un objet JSON de type Product appellé product
    //Objet Product va recevoir les champ nom, desription, price, taxRate, category, ... de la js ProductJs d'une requête ajax)
//    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
    public ResponseEntity<Product> addProduct(@RequestParam("name") String name,
                                              @RequestParam("description") String description,
                                              @RequestParam("category") long category,
                                              @RequestParam(value = "file", required = false) MultipartFile file,
                                              @RequestParam("price") float price,
                                              @RequestParam("tax_rate") int tax_rate,
                                              @RequestParam("enable") boolean enable) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setEnable(enable);
        product.setTaxRate(tax_rate);
        ProductCategory productCategory = productCategoryRepository.findById(category).orElseThrow();
        if (productCategory != null) {
            product.setCategory(productCategory);
        }
        String message;
        String url;

        try {
            if (!file.isEmpty()) {
                url = fileStorageService.save(file, file.getOriginalFilename());
                product.setImg(url);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                log.info(message + "URL : " + url);
            } else {
                log.info("coucou m'chou");
                product.setImg("istockphoto-1341411204-612x612.jpg");
            }

        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            log.error(message);
        }

        productRepository.save(product);
        productService.updateProductVersion(product, price, tax_rate);
        return ResponseEntity.ok(product);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> modifyProduct(@RequestParam("name") String name,
                                                 @RequestParam("description") String description,
                                                 @RequestParam("category") long category,
                                                 @RequestParam(value = "file", required = false) MultipartFile file,
                                                 @RequestParam("price") float price,
                                                 @RequestParam("tax_rate") int taxRate,
                                                 @RequestParam("enable") boolean enable,
                                                 @PathVariable long id) {

        Product product = productService.updateProduct(id, name, description, category, price, taxRate, enable);
        return ResponseEntity.ok(product);

    }
}
