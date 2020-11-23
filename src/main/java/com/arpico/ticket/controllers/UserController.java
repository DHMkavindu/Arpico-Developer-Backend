package com.arpico.ticket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arpico.ticket.security.services.impl.UserDetailsServiceImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@RequestMapping(path = "/getUsers", method = RequestMethod.GET)
	public ResponseEntity<?> getAllUser() {
		try {
			return new ResponseEntity<Object>(userDetailsService.getAllUsers(), HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<Object>(userDetailsService.getAllUsers(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
