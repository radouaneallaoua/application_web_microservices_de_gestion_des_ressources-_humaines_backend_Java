package com.example.paieservice.repository;

import com.example.paieservice.entities.HeuresSupplementaires;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HeuresSupplRepository extends JpaRepository<HeuresSupplementaires,Long> {
    List<HeuresSupplementaires> findAllByEmployeIdAndDate(String employeId, LocalDate date);
    List<HeuresSupplementaires> findAllByEmployeId(String employeId);
}
