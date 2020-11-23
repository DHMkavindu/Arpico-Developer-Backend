package com.arpico.ticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arpico.ticket.models.User;

@Repository
public interface AdminRepository extends JpaRepository<User, Integer> {

	List<User> findByUsername(int role_id);

}