package com.tfe.fournil.service;

import com.tfe.fournil.entity.ProductCategory;
import com.tfe.fournil.entity.User;
import com.tfe.fournil.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    public boolean checkIfCategoryIsUnique(String name) {
        List<ProductCategory> categoryList = productCategoryRepository.findByName(name);
        return CollectionUtils.isEmpty(categoryList);
    }
}