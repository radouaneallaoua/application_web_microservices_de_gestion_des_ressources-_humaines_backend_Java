package com.example.authservice.Dtos;

import com.example.authservice.enums.EtatCompte;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<RoleResponseDto> roles;

}
