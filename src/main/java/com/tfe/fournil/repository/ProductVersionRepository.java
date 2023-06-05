package com.tfe.fournil.repository;

import com.tfe.fournil.entity.Product;
import com.tfe.fournil.entity.ProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Product version repository.
 */
public interface ProductVersionRepository extends JpaRepository<ProductVersion,Long> {
    List<ProductVersion> findAll();

}
