package com.tfe.fournil.repository;

import com.tfe.fournil.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByCategoryIdIn(List<Long> categoryId);

}
