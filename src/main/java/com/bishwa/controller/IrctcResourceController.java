package com.bishwa.controller;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bishwa.model.TrainTimingsModel;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class IrctcResourceController {

	@GetMapping(value="/gettrains/{startLocation}/{destination}",produces = {"application/json"})
	public ResponseEntity<?> getTrainBetween(@PathVariable String startLocation,@PathVariable String destination){
		log.debug("getTrainBetween() method execution started.");
		
		List<String> halts =  Arrays.asList("sambalpur","redhakhol","boinda","angul","talcher","dhenkanal");
		TrainTimingsModel tm = new TrainTimingsModel(10012, "rourkela", "bhubaneswar", "6.30 AM", "12.10 PM",halts); 		
		
		int haltSublistStartIndex=0;
		int haltSublistEndIndex = halts.size();
		
		if((tm.getStartLocation().equalsIgnoreCase(startLocation)||(halts.contains(startLocation.toLowerCase()))) && (tm.getDestination().equalsIgnoreCase(destination)||(halts.contains(destination.toLowerCase())))) {
			
			if(!startLocation.equalsIgnoreCase(tm.getStartLocation()))
				haltSublistStartIndex = halts.indexOf(startLocation)+1;
			if(!destination.equalsIgnoreCase(tm.getDestination()))
				haltSublistEndIndex = halts.indexOf(destination);
			halts = halts.subList(haltSublistStartIndex, haltSublistEndIndex);
			tm.setHalts(halts);
			
			return new ResponseEntity<TrainTimingsModel>(tm, HttpStatus.OK);
		}
		log.info(this.getClass().getName() + "--- getTrainBetween() method execution completed.");
		return new ResponseEntity<>("No trains found on this route.",HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/hello")
	public String welcome() {
		return "HELLO...! Good Evening.";
	}
}
