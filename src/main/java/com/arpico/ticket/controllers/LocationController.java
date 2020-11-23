package com.arpico.ticket.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arpico.ticket.repository.LocationRepository;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/location")
public class LocationController {

	String sbucode = "";
	String sbuName = "";

	@Autowired
	private LocationRepository locationRepo;

	@RequestMapping(path = "/{sbu}", method = RequestMethod.GET)
	public List<String> getSbuNames(@PathVariable String sbu) {
		String[] arrOfStr = sbu.split("-", 2);

		System.out.println(locationRepo.findLocationBysbu(arrOfStr[0]));

		return locationRepo.findLocationBysbu(arrOfStr[0]);

	}
}
