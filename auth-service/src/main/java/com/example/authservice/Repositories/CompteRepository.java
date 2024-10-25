package com.example.authservice.Repositories;

import com.example.authservice.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends JpaRepository<Compte,String> {
    Compte findCompteByEmailAndMotDePasse(String email,String motDePasse);
   Compte findCompteByEmail(String email);
}
