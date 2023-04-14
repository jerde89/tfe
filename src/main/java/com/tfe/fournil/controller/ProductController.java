package com.tfe.fournil.controller;

import com.google.gson.Gson;
import com.tfe.fournil.entity.Feedback;
import com.tfe.fournil.entity.Product;
import com.tfe.fournil.entity.ProductCategory;
import com.tfe.fournil.repository.ProductCategoryRepository;
import com.tfe.fournil.repository.ProductRepository;
import com.tfe.fournil.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @GetMapping("")
    public String showCategoryList(Model model)
    {
        List<ProductCategory> category =productCategoryRepository.findAll();
        model.addAttribute("categoryList", category);
        return "product";
    }



    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> ajaxShowdisplayProduct() {
        List<Product> product = productRepository.findAll();
        return ResponseEntity.ok(product);
    }

    @PostMapping(value = "")
    //@RequestBody Product product => va recevoir un objet JSON de type Product appellé product
    //Objet Product va recevoir les champ nom, desription, price, taxRate, category, ... de la js ProductJs d'une requête ajax)
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        product.setUpdateAt(new Date());
        product.setCreatedAt(new Date());
        //Je crée une variable idProductCategory qui va valoir l'id de la categorie qui a recue
        //getCategory().getIdProductCategory() => clé étrangère
        long idProductCategory = product.getCategory().getIdProductCategory();

        //je crée un objet de type ProductCategory nommé productCategoryOptional
        //productCategoryRepository.findById(idProductCategory) => il va trouvé l'id dans la table productCategory suivant l'id reçu
        //l'optinal productCategoryOptional permet de recuperer le result de la fonction findbyId dans l'optional
        Optional<ProductCategory> productCategoryOptional = productCategoryRepository.findById(idProductCategory);

//
        //si l'objet productCategoryOptional est présent, je set la categorie de l'objet product
        //Equivalent => productCategoryOptional.ifPresent(product::setCategory);
        if(productCategoryOptional.isPresent()){
            product.setCategory(productCategoryOptional.get());
        }
        productRepository.save(product);
        return ResponseEntity.ok(product);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> modifyProduct(@RequestBody Product newProduct, @PathVariable long id) {
        Product product = productRepository.findById(id)
                .map(product1 -> {
                    product1.setName(newProduct.getName());
                    product1.setDescription(newProduct.getDescription());
                    product1.setPrice(newProduct.getPrice());
                    product1.setTaxRate(newProduct.getTaxRate());
                    product1.setUpdateAt(new Date());
                    product1.setEnable(newProduct.getEnable());
                    long idProductCategory = newProduct.getCategory().getIdProductCategory();
                    Optional<ProductCategory> productCategory = productCategoryRepository.findById(idProductCategory);
                    productCategory.ifPresent(product1::setCategory);
                    return productRepository.save(product1);
                })
                .orElseGet(() -> {
                    newProduct.setIdProduct(id);
                    return productRepository.save(newProduct);
                });
        return ResponseEntity.ok(product);
    }
}
