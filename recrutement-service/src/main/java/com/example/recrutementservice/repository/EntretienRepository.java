package com.example.recrutementservice.repository;


import com.example.recrutementservice.entities.Entretien;
import com.example.recrutementservice.enums.EtatEntretien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EntretienRepository extends JpaRepository<Entretien,Long> {
    List<Entretien> findAllByCondidatId(Long condidatId);
    List<Entretien> findAllByEtatEntretien(EtatEntretien etatEntretien);
    List<Entretien> findAllByDateEntretienAfter(LocalDateTime date);
    List<Entretien> findAllByDateEntretienBefore(LocalDateTime date);
    List<Entretien> findAllByDateEntretienBetween(LocalDateTime date1,LocalDateTime date2);

}
