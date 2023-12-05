package org.cuong.paginationandsortdemo.repository;

import org.cuong.paginationandsortdemo.entity.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {

    @Query("select a from Actor a " +
            "where a.firstName like concat('%', :query, '%') ")
    Page<Actor> searchActorByName(@Param("query") String query, Pageable pageable);

    Page<Actor> findAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName, Pageable pageable);
}
