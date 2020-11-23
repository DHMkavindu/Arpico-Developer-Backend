package com.arpico.ticket.security.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arpico.ticket.models.User;
import com.arpico.ticket.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}

	public List<User> getAllUsers() {
		List<User> userList = userRepository.getAllUsers();
		return userList;
	}

	public UserDetails loadUserByname(String name) throws Exception {
		User user = userRepository.findByUsername(name)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with -> username or email : " + name));

		return UserDetailsImpl.build(user);
	}

}
