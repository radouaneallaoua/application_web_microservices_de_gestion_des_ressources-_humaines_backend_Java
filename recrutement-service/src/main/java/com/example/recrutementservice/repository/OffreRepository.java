package com.example.recrutementservice.repository;


import com.example.recrutementservice.entities.Contrat;
import com.example.recrutementservice.entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OffreRepository extends JpaRepository<Offre,Long> {
    List<Offre> findAllByDatePublicationBefore(LocalDate datePublication);
    List<Offre> findAllByDatePublicationAfter(LocalDate datePublication);
    List<Offre> findAllByDatePublicationBetween(LocalDate datePublication, LocalDate datePublication2);
    List<Offre> findAllByEstExpiree(boolean estExpiree);
    List<Offre> findAllByDateLimiteCondidatureAfter(LocalDate dateLimiteCondidature);
    List<Offre> findAllByDateLimiteCondidatureBefore(LocalDate dateLimiteCondidature);
    List<Offre> findAllByVilleContainingIgnoreCase(String ville);
    List<Offre> findAllByVilleContainingIgnoreCaseAndDateLimiteCondidatureBeforeAndPosteContainingIgnoreCaseAndNombreDePostesIsGreaterThan(String villeRecherchee,LocalDate dateLimite,String posteRecherche,int nombreDePosteMin);
    List<Offre> findAllByVilleContainingIgnoreCaseAndDateLimiteCondidatureBeforeAndPosteContainingIgnoreCaseAndNombreDePostesIsGreaterThanAndTypeContrats(String ville, LocalDate dateLimiteCondidature, String poste, int nombreDePostes, List<Contrat> typeContrats);
    List<Offre> findAllByTypeContrats(List<Contrat> typeContrats);
    List<Offre> findAllByVilleContainingIgnoreCaseAndDateLimiteCondidatureBeforeAndPosteContainingIgnoreCase(String villeRecherchee,LocalDate dateLimite,String posteRecherche);
    List<Offre> findAllByVilleContainingIgnoreCaseAndDateLimiteCondidatureBefore(String villeRecherchee,LocalDate dateLimite);
    List<Offre> findAllByPosteContainingIgnoreCaseAndDateLimiteCondidatureBefore(String posteRecherchee,LocalDate dateLimite);
    List<Offre> findAllByVilleContainingIgnoreCaseAndPosteContainingIgnoreCase(String villeRecherchee,String  posteRecherche);
    List<Offre> findAllByNombreDePostesIsGreaterThan(int nombre);
    List<Offre> findAllByNombreDePostesIsLessThan(int nombre);
    List<Offre> findAllByPosteContainingIgnoreCase(String posteName);

}
