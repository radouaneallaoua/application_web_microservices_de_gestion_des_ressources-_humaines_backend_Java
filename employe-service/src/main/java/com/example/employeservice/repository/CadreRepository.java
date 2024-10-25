package com.example.employeservice.repository;

import com.example.employeservice.entities.Cadre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CadreRepository extends JpaRepository<Cadre,Long> {
    List<Cadre> findAllByCorpsId(Long corpsId);
}
