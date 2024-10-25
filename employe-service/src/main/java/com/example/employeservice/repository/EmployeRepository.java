package com.example.employeservice.repository;

import com.example.employeservice.entities.Employe;
import com.example.employeservice.entities.EntiteEmploye;
import com.example.employeservice.enums.EtatEmploye;
import com.example.employeservice.enums.Genre;
import com.example.employeservice.enums.SituationFamiliale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmployeRepository extends JpaRepository<Employe,String> {

    List<Employe> findAllByGenre(Genre genre);
    Employe findByEmail(String email);
    List<Employe> findAllByDateNaissanceAfter(LocalDate date);
    List<Employe> findAllByPrenomContainingIgnoreCase(String prenom);
    List<Employe> findAllByNomContainingIgnoreCase(String nom);
    List<Employe> findAllByDateNaissanceBefore(LocalDate date);
    List<Employe> findAllByAdresseContainingIgnoreCase(String adresse);
    List<Employe> findAllByAnneeExperienceGreaterThan(int anneeExperience);
    List<Employe> findAllByAnneeExperienceLessThan(int anneeExperience);
    List<Employe> findAllByEtatEmploye(EtatEmploye etat);
    List<Employe> findAllBySituationFamiliale(SituationFamiliale situation);
    List<Employe> findAllByPosteId(Long posteId);
    List<Employe> findAllByGradeId(Long gradeId);
    List<Employe> findAllByDateRecrutement(LocalDate date);
    List<Employe> findAllByDateRecrutementAfter(LocalDate date);
    List<Employe> findAllByDateRecrutementBefore(LocalDate date);
    List<Employe> findAllByDateRecrutementBetween(LocalDate date1,LocalDate date2);

    List<Employe> findAllByEntiteEmployesIsContainingAndPosteIdAndGradeIdAndEtatEmploye(
            List<EntiteEmploye> entiteEmployes, Long poste_id, Long grade_id, EtatEmploye etatEmploye
    );

    List<Employe> findAllByEntiteEmployesIsContainingAndPosteIdAndEtatEmploye(
            List<EntiteEmploye> entiteEmployes, Long poste_id,  EtatEmploye etatEmploye
    );

    List<Employe> findAllByEntiteEmployesIsContainingAndGradeIdAndEtatEmploye(
            List<EntiteEmploye> entiteEmployes, Long grade_id,  EtatEmploye etatEmploye
    );
    List<Employe> findAllByEntiteEmployesIsContainingAndEtatEmploye(
            List<EntiteEmploye> entiteEmployes, EtatEmploye etatEmploye
    );
    List<Employe> findAllByPosteIdAndGradeIdAndEtatEmploye(
            Long poste_id, Long grade_id, EtatEmploye etatEmploye
    );


}
