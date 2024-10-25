package com.example.recrutementservice.repository;



import com.example.recrutementservice.entities.Condidat;
import com.example.recrutementservice.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CondidatRepository extends JpaRepository<Condidat,Long> {
    List<Condidat> findAllByOffreId(Long offreId);
    List<Condidat> findAllByOffreIdAndVille(Long offreId,String ville);
    List<Condidat> findAllByOffreIdAndDateCondidatureAfter(Long offreId,LocalDateTime date);
    List<Condidat> findAllByOffreIdAndDateCondidatureBefore(Long offreId,LocalDateTime date);
    List<Condidat> findAllByOffreIdAndDateCondidatureBetween(Long offreId,LocalDateTime date1,LocalDateTime date2);
    Condidat findCondidatByOffreIdAndEmail(Long offreId,String email);
    List<Condidat> findAllByOffreIdAndGenre(Long offreId,Genre genre);
    List<Condidat> findAllByOffreIdAndAdresseContainingIgnoreCase(Long offreId,String adresse);
    List<Condidat> findAllByOffreIdAndDateCondidatureBeforeAndVille(Long offreId,LocalDateTime date,String ville);
    List<Condidat> findAllByOffreIdAndDateCondidatureAfterAndVille(Long offreId,LocalDateTime date,String ville);
    List<Condidat> findAllByOffreIdAndDateCondidatureBetweenAndVille(Long offreId,LocalDateTime date1,LocalDateTime date2,String ville);

}
