package com.arpico.ticket.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arpico.ticket.repository.SbuRepository;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/sbu")
public class sbuController {

	@Autowired
	private SbuRepository sbuRepository;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public List<String> getSbuNames() {
		return sbuRepository.findAllSbu();

	}
}
