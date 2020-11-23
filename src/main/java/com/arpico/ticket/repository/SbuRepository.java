package com.arpico.ticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arpico.ticket.models.Sbu;

@Repository
public interface SbuRepository extends JpaRepository<Sbu, Integer> {

	@Query(value = "select sbu from sbu", nativeQuery = true)
	List<String> findAllSbu();
}
