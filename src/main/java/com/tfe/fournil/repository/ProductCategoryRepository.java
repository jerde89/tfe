package com.tfe.fournil.repository;

import com.tfe.fournil.entity.ProductCategory;
import com.tfe.fournil.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {

    List<ProductCategory> findByName(String name);

    List<ProductCategory> findAll();

}
