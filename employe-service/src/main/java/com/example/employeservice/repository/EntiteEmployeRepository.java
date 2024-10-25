package com.example.employeservice.repository;


import com.example.employeservice.entities.EntiteEmploye;
import com.example.employeservice.entities.EntiteEmployeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EntiteEmployeRepository extends JpaRepository<EntiteEmploye, EntiteEmployeKey> {
    List<EntiteEmploye> findAllByEntiteId(Long entiteId);
    List<EntiteEmploye> findAllByEmployeId(String employeId);
    List<EntiteEmploye> findAllByDateDebutAfter(LocalDate date);
}
