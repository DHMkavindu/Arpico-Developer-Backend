package com.arpico.ticket.security.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpico.ticket.models.Role;
import com.arpico.ticket.models.User;
import com.arpico.ticket.repository.AdminRepository;
import com.arpico.ticket.security.services.AdminDetailsService;


@Service
public class AdminDetailsServiceImpl implements AdminDetailsService {

	@Autowired
	private AdminRepository adminRepo;

	@Override
	@Transactional
	public List<User> gelAllUsers(Role role) {
		List<User> userList = adminRepo.findByUsername(role.getId());

		return userList;
	}

}
