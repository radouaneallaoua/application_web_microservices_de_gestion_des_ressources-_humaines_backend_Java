package com.example.employeservice.repository;



import com.example.employeservice.entities.Corps;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CorpsRepository extends JpaRepository<Corps,Long> {
    List<Corps> findAllByCategorieId(Long categorieId);
}
