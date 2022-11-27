package com.tfe.fournil.controller;


import com.tfe.fournil.entity.ProductCategory;
import com.tfe.fournil.repository.ProductCategoryRepository;
import com.tfe.fournil.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Controller
@RequestMapping("/managementCategoryProduct")
public class ManagementCategoryProductController {

    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    ProductCategoryRepository productCategoryRepository;



    @GetMapping("")
    public String showCategoryList()
    {
        return "managementCategoryProduct";
    }

    @GetMapping("/displayCategories")
    public ResponseEntity<List<ProductCategory>> ajaxShowdisplayCategories()
    {
        List<ProductCategory> categories = productCategoryRepository.findAll();
        return ResponseEntity.ok(categories);
    }

    @PostMapping(value="/addCategory")
    public String addCategory(Model model, @Valid ProductCategory  category, BindingResult bindingResult, HttpSession session)
    {
        session.removeAttribute("errors");
        session.removeAttribute("success");
        if(bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            session.setAttribute("errors", errors);
        }else{
            productCategoryRepository.save(category);
            session.setAttribute("success", "Votre catégorie a été enregistré");
        }
        return "redirect:/managementCategoryProduct";
    }


    @PutMapping(value="/modifyCategory/{id}")
    public ResponseEntity<ProductCategory> modifyCategory(@RequestBody ProductCategory newCategory, @PathVariable long id)
    {
        ProductCategory productCategory = productCategoryRepository.findById(id)
                .map(category -> {
                    category.setName(newCategory.getName());
                    category.setDescription(newCategory.getDescription());
                    category.setUpdateAt(new Date());
                    category.setEnable(newCategory.getEnable());
                    return productCategoryRepository.save(category);
                })
                .orElseGet(() -> {
                    newCategory.setIdProductCategory(id);
                    return productCategoryRepository.save(newCategory);
                });
        return ResponseEntity.ok(productCategory);
    }
}
