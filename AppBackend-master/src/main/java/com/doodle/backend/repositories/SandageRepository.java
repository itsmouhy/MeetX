package com.doodle.backend.repositories;

import com.doodle.backend.entities.Sandage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SandageRepository extends JpaRepository<Sandage,Long> {

}