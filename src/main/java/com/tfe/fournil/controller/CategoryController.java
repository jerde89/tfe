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


/**
 * The type Category controller.
 */
@Slf4j
@Controller
@RequestMapping("/category")
public class CategoryController {

    /**
     * The Product category repository.
     */
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    /**
     * The Category service.
     */
    @Autowired
    CategoryService categoryService;


    /**
     * Show category list string.
     *
     * @return the string
     */
    @GetMapping("")
    public String showCategoryList() {
        return "category";
    }

    /**
     * Ajax showdisplay categories response entity.
     *
     * @return the response entity
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<ProductCategory>> ajaxShowdisplayCategories() {
        List<ProductCategory> categories = productCategoryRepository.findAll();
        return ResponseEntity.ok(categories);
    }

    /**
     * Add category response entity.
     *
     * @param category the category
     * @return the response entity
     */
    @PostMapping(value = "")
    public ResponseEntity<ProductCategory> addCategory(@RequestBody ProductCategory category) {
        category.setUpdateAt(new Date());
        category.setCreatedAt(new Date());
        productCategoryRepository.save(category);
        return ResponseEntity.ok(category);
    }


    /**
     * Modify category response entity.
     *
     * @param newCategory the new category
     * @param id          the id
     * @return the response entity
     */
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

    /**
     * Check if category is unique response entity.
     *
     * @param name the name
     * @return the response entity
     */
    @GetMapping("/nameIsUnique/{name}")
    public ResponseEntity<Boolean> checkIfCategoryIsUnique(@PathVariable("name") String name) {
        return ResponseEntity.ok(categoryService.checkIfCategoryIsUnique(name));
    }
}
