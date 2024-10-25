package com.example.paieservice.repository;

import com.example.paieservice.entities.HistoriqueDesPaies;
import com.example.paieservice.enums.TypePaiement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface HistoriquePaiesRepository extends JpaRepository<HistoriqueDesPaies,String> {
    List<HistoriqueDesPaies> findAllByEmployeId(String employeId);
    List<HistoriqueDesPaies> findAllByDatePaiementAfter(LocalDateTime date);
    List<HistoriqueDesPaies> findAllByDatePaiementBefore(LocalDateTime date);
    List<HistoriqueDesPaies> findAllByTypePaiement(TypePaiement typePaiement);

}
