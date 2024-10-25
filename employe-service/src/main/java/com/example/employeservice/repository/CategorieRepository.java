package com.example.employeservice.repository;


import com.example.employeservice.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie,Long> {

}
