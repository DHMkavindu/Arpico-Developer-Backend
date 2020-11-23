package com.arpico.ticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arpico.ticket.models.Locations;

@Repository
public interface LocationRepository extends JpaRepository<Locations, Integer> {

	@Query(value = "select location from locations where sbu =:sbu ", nativeQuery = true)
	List<String> findLocationBysbu(@Param("sbu") String sbu);

}
