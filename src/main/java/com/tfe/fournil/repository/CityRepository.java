package com.tfe.fournil.repository;

import com.tfe.fournil.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {
}
