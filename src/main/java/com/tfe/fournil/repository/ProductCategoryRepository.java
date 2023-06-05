package com.tfe.fournil.repository;

import com.tfe.fournil.entity.ProductCategory;
import com.tfe.fournil.entity.User;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * The interface Product category repository.
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {

    /**
     * Find by name list.
     *
     * @param name the name
     * @return the list
     */
    List<ProductCategory> findByName(String name);

    List<ProductCategory> findAll();

    /**
     * Count product by category int.
     *
     * @param id the id
     * @return the int
     */
//C. c'est du jpql
    @Query("select count(p.idProduct) from Product p where p.category.id = :id and p.enable = true and p.category.enable= true group by p.category")
    int countProductByCategory(long id);

}
