package com.example.avancementservice.repository;

import com.example.avancementservice.entities.Avancement;
import com.example.avancementservice.enums.MotifAvancement;
import com.example.avancementservice.enums.TypeAvancement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AvancementRepository extends JpaRepository<Avancement,Long> {
    List<Avancement> findAllByEmployeId(String employeId);
    List<Avancement> findAllByDateAvancementBefore(LocalDate date);
    List<Avancement> findAllByDateAvancementAfter(LocalDate date);
    List<Avancement> findAllByNouvelleEntiteId(Long entiteId);
    List<Avancement> findAllByNouveauGradeId(Long gradeId);
    List<Avancement> findAllByNouveauPosteId(Long posteId);
    List<Avancement> findAllByMotifAvancement(MotifAvancement motifAvancement);
    List<Avancement> findAllByTypeAvancement(TypeAvancement typeAvancement);
    List<Avancement> findAllByNouveauIndiceEchelonId(Long indiceEchelonId);
}
