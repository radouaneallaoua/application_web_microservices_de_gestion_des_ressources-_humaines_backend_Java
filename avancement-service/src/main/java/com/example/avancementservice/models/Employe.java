package com.example.avancementservice.models;


import com.example.avancementservice.dtos.*;
import com.example.avancementservice.enums.EtatEmploye;
import com.example.avancementservice.enums.Genre;
import com.example.avancementservice.enums.SituationFamiliale;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employe {
    private String id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String tel;
    private String adresse;
    private String ville;
    private String CIN;
    private String email;
    private String imageURL;
    private int anneeExperience;
    private LocalDate dateRecrutement;
    private Genre genre;
    private EtatEmploye etatEmploye;
    private SituationFamiliale situationFamiliale;
    private CompteModel compteModel;
    private PosteResponseDto poste;
    private GradeResponseDto grade;
    private RegionResponseDto region;
    private IndiceEchelonResponseDto indiceEchelon;
    private List<EntiteEmployeResponseDto> entites;
    private List<ConjointResponsetDto> conjoints;
    private List<EnfantResponseDto> enfants;
    private List<MutuelleResponseDto> mutuelles;


}
