package com.example.formationservice.Repository;

import com.example.formationservice.entities.EmployeFormation;
import com.example.formationservice.entities.EmployeFormationKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeFormationRepository extends JpaRepository<EmployeFormation, EmployeFormationKey> {
    List<EmployeFormation> findAllByFormationId(Long formationId);

    List<EmployeFormation> findAllByEvaluationIsLessThan(int evaluation);
    List<EmployeFormation> findAllByEvaluationIsGreaterThan(int evaluation);
    void deleteEmployeFormationById(EmployeFormationKey id);
}


