package com.example.employeservice.repository;

import com.example.employeservice.entities.Employe;
import com.example.employeservice.entities.Mutuelle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MutuelleRepository extends JpaRepository<Mutuelle,Long> {
      List<Mutuelle> findAllByEmployesContains(List<Employe> employes);

}
