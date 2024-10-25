package com.example.employeservice.repository;

import com.example.employeservice.entities.IndiceEchelon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IndiceRepository extends JpaRepository<IndiceEchelon,Long> {
    List<IndiceEchelon> findAllByGradeId(Long gradeId);
}
