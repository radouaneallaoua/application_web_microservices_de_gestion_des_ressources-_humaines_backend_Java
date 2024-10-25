package com.example.employeservice.repository;


import com.example.employeservice.entities.Enfant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EnfantRepository extends JpaRepository<Enfant,Long> {
    List<Enfant> findAllByConjointId(Long conjointId);
    List<Enfant> findAllByEmployeId(String employeId);
    List<Enfant> findAllByDateNaissanceBefore(LocalDate date);
    List<Enfant> findAllByDateNaissanceAfter(LocalDate date);
}
