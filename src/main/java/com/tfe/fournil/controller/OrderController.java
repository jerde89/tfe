package com.tfe.fournil.controller;

import com.tfe.fournil.entity.Product;
import com.tfe.fournil.entity.ProductCategory;
import com.tfe.fournil.repository.ProductCategoryRepository;
import com.tfe.fournil.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @GetMapping("")
    public String showordder(Model model) {

        List<Product> productList = productRepository.findAll().stream()
                .filter(Product::getEnable)
                .collect(Collectors.toList());;
        model.addAttribute("productList", productList);

        List<ProductCategory> categoryList = productCategoryRepository.findAll().stream()
                .filter(ProductCategory::getEnable)
                .collect(Collectors.toList());
        model.addAttribute("categoryList", categoryList);
        //
        for (ProductCategory productCategory: categoryList
              ) {
            int countCategory = 0;
            try {
                countCategory = productCategoryRepository.countProductByCategory(productCategory.getId());
                //si pas de produits liés à une catégorie, il indique 0
            }catch (Exception exception){
                log.info("Catégorie sans produit " + productCategory.getName());
            }
            productCategory.setCountProduct(countCategory);
        }

        return "order";
    }

}
