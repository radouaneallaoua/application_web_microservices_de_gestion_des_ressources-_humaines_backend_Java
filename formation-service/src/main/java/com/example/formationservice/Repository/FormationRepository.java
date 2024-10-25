package com.example.formationservice.Repository;

import com.example.formationservice.entities.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FormationRepository extends JpaRepository<Formation,Long> {
    List<Formation> findAllByDateFinBefore(LocalDateTime date);
    List<Formation> findAllByDateDebutAfter(LocalDateTime date);
    List<Formation> findAllByDateFinAfter(LocalDateTime date);
    List<Formation> findAllByDateDebutBefore(LocalDateTime date);
    List<Formation> findAllByDureeEnJoursIsBetween(int d1,int d2);
    List<Formation> findAllByDureeEnJoursIsLessThan(int d);
    List<Formation> findAllByDureeEnJoursIsGreaterThan(int d);
    List<Formation> findAllByCompetenceId(Long competenceId);


}
