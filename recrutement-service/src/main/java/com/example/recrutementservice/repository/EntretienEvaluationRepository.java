package com.example.recrutementservice.repository;


import com.example.recrutementservice.entities.EntretienEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntretienEvaluationRepository extends JpaRepository<EntretienEvaluation,Long> {
    EntretienEvaluation findEntretienEvaluationByEntretienId(Long entretienId);
    List<EntretienEvaluation> findAllByScoreIsGreaterThan(int score);
    List<EntretienEvaluation> findAllByScoreIsLessThan(int score);

}
