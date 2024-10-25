package com.example.employeservice.repository;


import com.example.employeservice.entities.Poste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosteRepository extends JpaRepository<Poste,Long> {

}
