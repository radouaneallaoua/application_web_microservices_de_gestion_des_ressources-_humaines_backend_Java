package com.example.paieservice.repository;

import com.example.paieservice.entities.Prime;
import com.example.paieservice.enums.TypePrime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrimeRepository extends JpaRepository<Prime,Long> {
    List<Prime> findAllByEmployeIdAndTypePrime(String employeId, TypePrime typePrime);
    List<Prime> findAllByEmployeIdAndDateFinBefore(String employeId, LocalDate date);
    List<Prime> findAllByEmployeId(String employeId);
    List<Prime> findAllByEmployeIdAndDateDebutAfterAndDateFinBefore(String employeId,LocalDate date1,LocalDate date2);
}
