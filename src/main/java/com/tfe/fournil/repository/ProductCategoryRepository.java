package com.tfe.fournil.repository;

import com.tfe.fournil.entity.ProductCategory;
import com.tfe.fournil.entity.User;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {

    List<ProductCategory> findByName(String name);

    List<ProductCategory> findAll();

    //C. c'est du jpql
    @Query("select count(p.idProduct) from Product p where p.category.id = :id and p.enable = true and p.category.enable= true group by p.category")
    int countProductByCategory(long id);

}
