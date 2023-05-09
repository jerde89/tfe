package com.tfe.fournil.controller;


import com.tfe.fournil.entity.ProductCategory;
import com.tfe.fournil.repository.ProductCategoryRepository;
import com.tfe.fournil.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    CategoryService categoryService;


    @GetMapping("")
    public String showCategoryList() {
        return "category";
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductCategory>> ajaxShowdisplayCategories() {
        List<ProductCategory> categories = productCategoryRepository.findAll();
        return ResponseEntity.ok(categories);
    }

    @PostMapping(value = "")
    public ResponseEntity<ProductCategory> addCategory(@RequestBody ProductCategory category) {
        category.setUpdateAt(new Date());
        category.setCreatedAt(new Date());
        productCategoryRepository.save(category);
        return ResponseEntity.ok(category);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductCategory> modifyCategory(@RequestBody ProductCategory newCategory, @PathVariable long id) {
        ProductCategory productCategory = productCategoryRepository.findById(id)
                .map(category -> {
                    category.setName(newCategory.getName());
                    category.setDescription(newCategory.getDescription());
                    category.setUpdateAt(new Date());
                    category.setEnable(newCategory.getEnable());
                    return productCategoryRepository.save(category);
                })
                .orElseGet(() -> {
                    newCategory.setId(id);
                    return productCategoryRepository.save(newCategory);
                });
        return ResponseEntity.ok(productCategory);
    }

    @GetMapping("/nameIsUnique/{name}")
    public ResponseEntity<Boolean> checkIfCategoryIsUnique(@PathVariable("name") String name) {
        return ResponseEntity.ok(categoryService.checkIfCategoryIsUnique(name));
    }
}
