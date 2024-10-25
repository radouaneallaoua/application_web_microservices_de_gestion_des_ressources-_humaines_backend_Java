package com.example.employeservice.Dtos;

import com.example.employeservice.enums.EtatEmploye;
import com.example.employeservice.enums.Genre;
import com.example.employeservice.enums.SituationFamiliale;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeRequestDto {
    private String nom;
    private String prenom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateNaissance;

    private String tel;
    private String adresse;
    private String ville;
    private String email;
    private String CIN;
    private int anneeExperience;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateRecrutement;
    private Genre genre;
    private EtatEmploye etatEmploye;
    private SituationFamiliale situationFamiliale;
    private Long posteId;
    private Long entiteId;
    private Long gradeId;
    private Long indiceEchelonId;
    private List<Long> mutuellesId=new ArrayList<>();
    private MultipartFile file;
    private Long regionId;


}
