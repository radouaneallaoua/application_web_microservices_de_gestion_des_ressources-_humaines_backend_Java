package com.example.employeservice.repository;


import com.example.employeservice.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade,Long> {
    List<Grade> findAllByCadreId(Long cadreId);
}
