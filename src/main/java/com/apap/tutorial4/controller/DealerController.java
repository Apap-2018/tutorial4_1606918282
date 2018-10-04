package com.apap.tutorial4.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.apap.tutorial4.model.*;
import com.apap.tutorial4.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DealerController {
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private CarService carService;

	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value = "/dealer/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("dealer", new DealerModel());
		return "addDealer";
	}
	
	@RequestMapping(value = "/dealer/add", method = RequestMethod.POST)
	private String addDealerSubmit(@ModelAttribute DealerModel dealer) {
		dealerService.addDealer(dealer);
		return "add";
	}
	
	@RequestMapping(value = "/dealer/update/{dealerId}", method = RequestMethod.GET)
	private String update(@PathVariable(value = "dealerId") String id, Model model) {
		DealerModel updated = dealerService.getDealerDetailById(Long.parseLong(id)).orElse(null);
		model.addAttribute("update", updated);
		return "updateDealer";
	}
	
	@RequestMapping(value = "/dealer/update/id={dealerId}", method = RequestMethod.POST)
	private String updateDealerSubmit(@PathVariable(value = "dealerId") String id, @ModelAttribute DealerModel dealer) {
		DealerModel updated = dealerService.getDealerDetailById(Long.parseLong(id)).orElse(null);
		updated.setAlamat(dealer.getAlamat());
		updated.setNoTelp(dealer.getNoTelp());
		dealerService.addDealer(updated);
		return "update";
	}
	
	@RequestMapping(value = "/dealer/delete/{dealerId}", method = RequestMethod.GET)
	private String deleteDealerSubmit(@PathVariable(value = "dealerId") String id) {
		DealerModel deleted = dealerService.getDealerDetailById(Long.parseLong(id)).orElse(null);
		dealerService.deleteDealer(deleted);
		return "delete";
	}
	
	@RequestMapping(value = "/dealer/view", method = RequestMethod.GET)
	private String getDealer(@RequestParam("dealerId") String id,Model model) {
		DealerModel dm = dealerService.getDealerDetailById(Long.parseLong(id)).orElse(null);
		
		if(dm == null) {
			model.addAttribute("error", true);
			model.addAttribute("dealerDetail",null);
			return "view-dealer";
		}
		
		model.addAttribute("dealerDetail",dm);
		Collections.sort(dm.getListCar());
		model.addAttribute("carList",dm.getListCar());
		
		return "view-dealer";	
	}
	
//	@RequestMapping(value = "/dealer/all", method = RequestMethod.GET)
//	private String getAllDealer(Model model) {
//				
//		List<DealerModel> dealers = dealerService.allDealer();
//		
//		Map<String,String> dealerCars = new HashMap<>();
//		
//		for(int i = 0 ;i < dealers.size();i++) {
//			if(!dealerCars.containsKey(dealers.get(i).getAlamat()+ " " + dealers.get(i).getNoTelp())){
//				List<CarModel> cars = carService.allCarDealer(dealers.get(i));
//				List<String> carsSpec = cars.stream().map(car -> car.toString()).collect(Collectors.toList()); 
//				dealerCars.put(dealers.get(i).getAlamat()+ " " + dealers.get(i).getNoTelp(),carsSpec.toString());
//			}
//		}
//		
//		model.addAttribute("dealerCarMap", dealerCars);
//		
//		return "view-all";
//	}
	
	@RequestMapping(value = "/dealer/all", method = RequestMethod.GET)
	private String viewAllDealer(Model model) {	
		List<DealerModel> allDealers = dealerService.allDealer();
		
		model.addAttribute("allDealers", allDealers);
		return "view-all";
	}
}
