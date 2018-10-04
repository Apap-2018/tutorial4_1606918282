package com.apap.tutorial4.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial4.model.CarModel;
import com.apap.tutorial4.model.DealerModel;


public interface CarService {
	void addCar(CarModel car);
	void deleteCar(CarModel car);
	List<CarModel> allCarDealer(DealerModel dealer);
	Optional<CarModel> getCarDetailById(Long id);
}
