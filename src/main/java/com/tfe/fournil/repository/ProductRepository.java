package com.tfe.fournil.repository;

import com.tfe.fournil.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Product repository.
 */
public interface ProductRepository extends JpaRepository<Product,Long> {
    /**
     * Find all by category id in list.
     *
     * @param categoryId the category id
     * @return the list
     */
    List<Product> findAllByCategoryIdIn(List<Long> categoryId);

}
