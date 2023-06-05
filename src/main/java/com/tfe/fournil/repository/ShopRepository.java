package com.tfe.fournil.repository;

import com.tfe.fournil.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Shop repository.
 */
public interface ShopRepository extends JpaRepository<Shop,Long> {
}
