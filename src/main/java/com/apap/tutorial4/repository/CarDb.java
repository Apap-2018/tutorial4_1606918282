package com.apap.tutorial4.repository;

import com.apap.tutorial4.model.CarModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CarDb extends JpaRepository<CarModel, Long>{
	CarModel findByType(String type);

}
