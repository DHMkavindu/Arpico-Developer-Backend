package com.arpico.ticket.security.services;

import java.util.List;

import com.arpico.ticket.models.Role;
import com.arpico.ticket.models.User;

public interface AdminDetailsService {

	public List<User> gelAllUsers(Role role_id);

}
