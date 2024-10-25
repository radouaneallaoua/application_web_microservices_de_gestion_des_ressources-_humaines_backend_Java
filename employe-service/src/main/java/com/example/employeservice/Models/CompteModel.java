package com.example.employeservice.Models;


import com.example.employeservice.enums.EtatCompte;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompteModel {
    private String id;
    private  String email;
    private  String password;
    private LocalDateTime dateCreation;
    private EtatCompte etatCompte;
    private String employeId;
    private String token;
    private List<RoleModel> roles;

}
