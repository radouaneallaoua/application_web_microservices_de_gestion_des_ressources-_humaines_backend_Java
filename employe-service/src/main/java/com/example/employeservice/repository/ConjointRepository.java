package com.example.employeservice.repository;


import com.example.employeservice.entities.Conjoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConjointRepository extends JpaRepository<Conjoint,Long> {
    List<Conjoint> findAllByEmployeId(String employeId);
}
