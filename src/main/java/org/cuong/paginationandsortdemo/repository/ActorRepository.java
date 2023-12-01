package org.cuong.paginationandsortdemo.repository;

import org.cuong.paginationandsortdemo.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
