package com.arpico.ticket.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arpico.ticket.models.Job;
import com.arpico.ticket.models.User;


@Repository
public interface JobRepository extends JpaRepository<Job, String> {

	Job findByUser(User user);

	@Query(value = "SELECT * FROM jobs WHERE id = ?1 and status = ?2 ORDER BY cre_dt DESC", nativeQuery = true)
	List<Job> findByUserAndStatus(User user_id, char status);

	@Query(value = "SELECT * FROM jobs WHERE id = ?1 and cre_dt =?2 ORDER BY cre_dt DESC", nativeQuery = true)
	List<Job> findByUserAndStatusAndCre_dt(User user_id, LocalDate cre_dt);

	@Query(value = "SELECT * FROM jobs WHERE status = ?1 ORDER BY cre_dt DESC", nativeQuery = true)
	List<Job> findByStatus(char status);

	// List<Job> findByUserContainingOrStatusContainingAllIgnoreCase(User
	// user_id, char status);

	List<Job> findByDepartmentAndSbu(String department, String sbu);

	@Query(value = "SELECT * FROM jobs WHERE id=?1 and cre_dt=?2 ORDER BY cre_dt DESC", nativeQuery = true)
	List<Job> findByUserAndDate(Long user_id, String cre_dt);
}