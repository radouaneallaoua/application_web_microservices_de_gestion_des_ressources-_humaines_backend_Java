package com.example.absenceservice.repositories;

import com.example.absenceservice.entities.Absence;
import com.example.absenceservice.enums.Motif;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AbsenceRepository extends JpaRepository<Absence,Long> {
    List<Absence> findAllByEmployeId(String employeId);
    List<Absence> findAllByEmployeIdAndEstJustifiee(String employeId,boolean estJustifie);
    List<Absence> findAllByEmployeIdAndDateDebutBetween(String employeId,LocalDateTime date1,LocalDateTime date2);
    List<Absence> findAllByEstJustifiee(boolean estJustifie);
    List<Absence> findAllByMotif(Motif motif);
    List<Absence> findAllByEmployeIdAndMotif(String employeId,Motif motif);
}
