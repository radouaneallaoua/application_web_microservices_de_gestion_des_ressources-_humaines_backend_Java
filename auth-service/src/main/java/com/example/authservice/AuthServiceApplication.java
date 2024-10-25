package com.example.authservice;

import com.example.authservice.Dtos.CompteRequestDto;
import com.example.authservice.Dtos.RoleRequestDto;
import com.example.authservice.Dtos.RoleResponseDto;
import com.example.authservice.Repositories.CompteRepository;
import com.example.authservice.Repositories.RoleRepository;
import com.example.authservice.Services.CompteService;
import com.example.authservice.Services.RoleService;
import com.example.authservice.entities.Compte;
import com.example.authservice.enums.EtatCompte;
import com.example.authservice.enums.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AuthServiceApplication {



    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }
//    @Bean
//    @Autowired
//    public CommandLineRunner commandLineRunner(CompteRepository compteRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder){
//        return args -> {
//            Compte compte=Compte.builder()
//                    .id(UUID.randomUUID().toString())
//                    .dateCreation(LocalDateTime.now())
//                    .etatCompte(EtatCompte.ACTIF)
//                    .email("responsable@gmail.com")
//                    .motDePasse(passwordEncoder.encode("responsable"))
//                    .employeId("e2a4cb1c-9a92-4af3-a8c5-5d2fa91387bc")
//                    .roles(List.of(roleRepository.findById(Long.valueOf(2)).get()))
//                    .build();
//            compteRepository.save(compte);
//        };
//    }

}
