package com.example.formationservice.Repository;

import com.example.formationservice.entities.EntiteFormation;
import com.example.formationservice.entities.EntiteFormationKey;
import com.example.formationservice.models.Entite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EntiteFormationRepository extends JpaRepository<EntiteFormation, EntiteFormationKey> {
    List<EntiteFormation> findAllByFormationId(Long formationId);
    List<EntiteFormation> findAllByFormationIdAndDateAjoutBefore(Long formationId, LocalDateTime date);
    List<EntiteFormation> findAllByFormationIdAndDateAjoutAfter(Long formationId, LocalDateTime date);
}
