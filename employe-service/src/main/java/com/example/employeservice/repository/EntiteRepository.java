package com.example.employeservice.repository;


import com.example.employeservice.entities.Entite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntiteRepository extends JpaRepository<Entite,Long> {
    List<Entite> findAllByTypeId(Long typeId);
    List<Entite> findAllByEntiteMereId(Long entiteMereId);

}
