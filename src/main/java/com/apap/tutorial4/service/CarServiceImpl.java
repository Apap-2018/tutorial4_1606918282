package com.apap.tutorial4.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.apap.tutorial4.model.CarModel;
import com.apap.tutorial4.model.DealerModel;
import com.apap.tutorial4.repository.CarDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarServiceImpl implements CarService {
	
	@Autowired
	private CarDb carDb;
	
	@Override
	public Optional<CarModel> getCarDetailById(Long id) {
		// TODO Auto-generated method stub
		return carDb.findById(id);
	}

	@Override
	public void addCar(CarModel car) {
		// TODO Auto-generated method stub
		carDb.save(car);
	}
	
	@Override
	public void deleteCar(CarModel car){
		// TODO Auto-generated method stub
		carDb.delete(car);
		
	}
	
	@Override
	public List<CarModel> allCarDealer(DealerModel dealer) {
		// TODO Auto-generated method stub
		List<CarModel> cars = carDb.findAll();
		return cars.stream().filter(car -> car.getDealer().getId() == dealer.getId()).collect(Collectors.toList());
	}

}