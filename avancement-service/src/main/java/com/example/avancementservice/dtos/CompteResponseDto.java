package com.example.avancementservice.dtos;


import com.example.avancementservice.enums.EtatCompte;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompteResponseDto {
    private String id;
    private  String email;
    private  String password;
    private LocalDateTime dateCreation;
    private EtatCompte etatCompte;
    private String employeId;
    private String token;


}
