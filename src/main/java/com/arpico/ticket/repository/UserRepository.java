package com.arpico.ticket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arpico.ticket.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Optional<User> findByusername(String name);

	Boolean existsByName(String username);

	Boolean existsByEmail(String email);

	@Query(value = "select * from users u inner join user_roles r on u.id=r.user_id where r.role_id='1'", nativeQuery = true)
	List<User> getAllUsers();
}
