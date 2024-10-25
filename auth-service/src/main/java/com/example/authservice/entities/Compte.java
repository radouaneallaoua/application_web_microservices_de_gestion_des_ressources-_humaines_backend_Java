package com.example.authservice.entities;


import com.example.authservice.Dtos.CompteResponseDto;
import com.example.authservice.Dtos.RoleResponseDto;
import com.example.authservice.enums.EtatCompte;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Compte {
    @Id
    private String id;
    private String email;
    private String motDePasse;
    private LocalDateTime dateCreation;
    private String token;
    private String employeId;
    @Enumerated(EnumType.STRING)
    private EtatCompte etatCompte;

    @ManyToMany()
    @JoinTable(name = "compte-role",joinColumns = @JoinColumn(name="compte_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<UserRole> roles;


    public CompteResponseDto toDto(){
        List<RoleResponseDto> roleResponseDtos=new ArrayList<>();
        if(roles!=null){
            roles.forEach(r->{
                roleResponseDtos.add(r.toDto());
            });
        }
        return  CompteResponseDto.builder()
                .dateCreation(dateCreation)
                .email(email)
                .token(token)
                .employeId(employeId)
                .roles(roleResponseDtos)
                .etatCompte(etatCompte)
                .password(motDePasse)
                .id(id)
                .build();
    }
}
