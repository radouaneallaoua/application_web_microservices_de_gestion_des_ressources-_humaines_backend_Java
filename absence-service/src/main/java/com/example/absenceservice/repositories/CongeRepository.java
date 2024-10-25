package com.example.absenceservice.repositories;

import com.example.absenceservice.entities.Conge;
import com.example.absenceservice.enums.EtatConge;
import com.example.absenceservice.enums.TypeConge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CongeRepository extends JpaRepository<Conge,Long> {
    List<Conge> findAllByEmployeId(String employeId);
    List<Conge> findAllByEmployeIdAndTypeConge(String employeId,TypeConge typeConge);
    List<Conge> findAllByTypeConge(TypeConge typeConge);
    List<Conge> findAllByEtatConge(EtatConge etatConge);
    List<Conge> findAllByDateDebut(LocalDate date);
    List<Conge> findAllByDateDebutBefore(LocalDate date);
    List<Conge> findAllByDateFinBetween(LocalDate date1,LocalDate date2);
    List<Conge> findAllByDateFin(LocalDate date);
    List<Conge> findAllByDateFinAfter(LocalDate date);
    List<Conge> findAllByDureeEnJoursGreaterThanEqual(int duree);
    List<Conge> findAllByDureeEnJoursLessThanEqual(int duree);


}
