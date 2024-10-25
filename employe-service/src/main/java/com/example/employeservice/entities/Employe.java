package com.example.employeservice.entities;


import com.example.employeservice.Dtos.*;
import com.example.employeservice.Models.CompteModel;
import com.example.employeservice.enums.EtatEmploye;
import com.example.employeservice.enums.Genre;
import com.example.employeservice.enums.SituationFamiliale;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString @Builder
@AllArgsConstructor @NoArgsConstructor

public class Employe {
    @Id
    private String id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String tel;
    private String adresse;
    private String ville;
    private String CIN;
    @Column(unique = true)
    private String email;
    private String imageURL;
    private int anneeExperience;
    private LocalDate dateRecrutement;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    private EtatEmploye etatEmploye;

    @Enumerated(EnumType.STRING)
    private SituationFamiliale situationFamiliale;


    @Transient
    private CompteModel compteModel;

    @OneToMany(mappedBy = "employe",  fetch = FetchType.LAZY)
    private List<EntiteEmploye> entiteEmployes = new ArrayList<>();

    @ManyToOne
    private IndiceEchelon indiceEchelon;

    @ManyToOne
    private Poste poste;

    @ManyToOne
    private Grade grade;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "employe_mutuelles", joinColumns = @JoinColumn(name = "employe_id"), inverseJoinColumns = @JoinColumn(name="mutuelle_id"))
    private List<Mutuelle> mutuelles = new ArrayList<>();

    @OneToMany(mappedBy = "employe")
    private List<Conjoint> conjoints = new ArrayList<>();

    @OneToMany(mappedBy = "employe")
    private List<Enfant> enfants = new ArrayList<>();

    @ManyToOne
    private Region region;

    @OneToMany(mappedBy = "employe")
    private List<Notification> notifications = new ArrayList<>();

    public void addEntiteEmploye(EntiteEmploye entiteEmploye) {
        entiteEmployes.add(entiteEmploye);
        entiteEmploye.setEmploye(this);
    }

    public void removeEntiteEmploye(EntiteEmploye entiteEmploye) {
        entiteEmployes.remove(entiteEmploye);
        entiteEmploye.setEmploye(null);
    }

    public void addConjointEmploye(Conjoint conjoint) {
        conjoints.add(conjoint);
        conjoint.setEmploye(this);
    }
    public void removeConjointEmploye(Conjoint conjoint) {
        conjoints.remove(conjoint);
        conjoint.setEmploye(null);
    }

    public void addEnfantEmploye(Enfant  enfant) {
        enfants.add(enfant);
        enfant.setEmploye(this);
    }
    public void removeEnfantEmploye(Enfant  enfant) {
        enfants.remove(enfant);
        enfant.setEmploye(null);
    }

    public EmployeResponseDto toDTO(){

            List<MutuelleResponseDto> mutuelleList = new ArrayList<>();
            if (mutuelles != null) {
                mutuelles.forEach(m -> {
                    mutuelleList.add(m.toDto());
                });
            }

            List<EntiteEmployeResponseDto> entitesEmploye = new ArrayList<>();
            if (entiteEmployes != null) {
                entiteEmployes.forEach(r -> {
                    entitesEmploye.add(r.toDto());
                });
            }
            List<EnfantResponseDto> enfantsList = new ArrayList<>();
            if (enfants != null) {
                enfants.forEach(r -> {
                    enfantsList.add(r.toDto());
                });
            }
            List<ConjointResponsetDto> conjointList = new ArrayList<>();
            if (conjoints != null) {
                conjoints.forEach(c -> {
                    conjointList.add(c.toDto());
                });
            }

            return EmployeResponseDto.builder()
                    .id(id)
                    .nom(nom)
                    .prenom(prenom)
                    .dateNaissance(dateNaissance)
                    .tel(tel)
                    .adresse(adresse)
                    .ville(ville)
                    .email(email)
                    .CIN(CIN)
                    .imageURL(imageURL)
                    .anneeExperience(anneeExperience)
                    .dateRecrutement(dateRecrutement)
                    .genre(genre)
                    .etatEmploye(etatEmploye)
                    .situationFamiliale(situationFamiliale)
                    .compteModel(compteModel == null ? null : compteModel)
                    .poste(poste.toDto())
                    .grade(grade.toDto())
                    .indiceEchelon(indiceEchelon.toDto())
                    .entites(entitesEmploye)
                    .conjoints(conjointList)
                    .mutuelles(mutuelleList)
                    .enfants(enfantsList)
                    .region(region != null ? region.toDto() : null)
                    .build();
    }
}
