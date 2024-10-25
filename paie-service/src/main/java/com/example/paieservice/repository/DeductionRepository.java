package com.example.paieservice.repository;

import com.example.paieservice.entities.Deduction;
import com.example.paieservice.enums.TypeDeduction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DeductionRepository extends JpaRepository<Deduction,Long> {
    List<Deduction> findAllByEmployeIdAndTypeDeduction(String employeId, TypeDeduction typeDeduction);
    List<Deduction> findAllByEmployeIdAndDateFinBefore(String employeId, LocalDate date);
    List<Deduction> findAllByEmployeId(String employeId);
    List<Deduction> findAllByEmployeIdAndDateDebutAfterAndDateFinBefore(String employeId,LocalDate date1,LocalDate date2);
}
