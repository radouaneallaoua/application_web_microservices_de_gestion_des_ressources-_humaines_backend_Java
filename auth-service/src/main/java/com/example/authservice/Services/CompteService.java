package com.example.authservice.Services;

import com.example.authservice.Clients.EmployeFeignClient;
import com.example.authservice.Dtos.CompteRequestDto;
import com.example.authservice.Dtos.CompteResponseDto;
import com.example.authservice.Repositories.CompteRepository;
import com.example.authservice.Repositories.RoleRepository;
import com.example.authservice.entities.Compte;
import com.example.authservice.enums.EtatCompte;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CompteService {
    final private CompteRepository compteRepository;
    final private EmployeFeignClient employeFeignClient;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public CompteService(CompteRepository compteRepository, EmployeFeignClient employeFeignClient, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.compteRepository = compteRepository;
        this.employeFeignClient = employeFeignClient;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> login(String email,String password){
        Compte compte=compteRepository.findCompteByEmailAndMotDePasse(email, password);
        if(compte==null){
            return new ResponseEntity<>("incorrect credentials", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(compte.toDto(), HttpStatus.OK);
    }
    public ResponseEntity<List<CompteResponseDto>> getAllComptes(){
        List<Compte> comptes=compteRepository.findAll();
        List<CompteResponseDto> compteResponseDtos=new ArrayList<>();
        comptes.forEach(c->{
            compteResponseDtos.add(c.toDto());
        });
        return ResponseEntity.ok(compteResponseDtos);
    }

    public String getCompteEmployeId(String email){
        Compte compte=compteRepository.findCompteByEmail(email);
        return compte.getEmployeId();
    }
    public ResponseEntity<CompteResponseDto> create(CompteRequestDto compteRequestDto){
        String employeIdOrEmailNotFound=employeFeignClient.findByEmail(compteRequestDto.getEmail());
        if(employeIdOrEmailNotFound.equals("email not found")){
            throw new RuntimeException("email not found");
        }
        Compte compte=Compte.builder()
                .id(UUID.randomUUID().toString())
                .dateCreation(LocalDateTime.now())
                .etatCompte(EtatCompte.ACTIF)
                .email(compteRequestDto.getEmail())
                .motDePasse(passwordEncoder.encode(compteRequestDto.getPassword()))
                .employeId(employeIdOrEmailNotFound)
                .roles(List.of(roleRepository.findById(Long.valueOf(3)).get()))
                .build();
        Compte savedCompte=compteRepository.save(compte);
        return new ResponseEntity<>(savedCompte.toDto(), HttpStatus.CREATED);
    }


}
