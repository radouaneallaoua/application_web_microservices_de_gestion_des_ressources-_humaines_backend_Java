package com.example.formationservice.service;

import com.example.formationservice.Exception.CompetenceNotFoundException;
import com.example.formationservice.Exception.FormationNotFoundException;
import com.example.formationservice.Repository.CompetenceRepository;
import com.example.formationservice.Repository.FormationRepository;
import com.example.formationservice.dtos.FormationRequestDto;
import com.example.formationservice.dtos.FormationResponseDto;
import com.example.formationservice.entities.Competence;
import com.example.formationservice.entities.Formation;
import jakarta.ws.rs.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FormationService {
    final private FormationRepository formationRepository;
    final private CompetenceRepository competenceRepository;

    public FormationService(FormationRepository formationRepository, CompetenceRepository competenceRepository) {
        this.formationRepository = formationRepository;
        this.competenceRepository = competenceRepository;
    }

    public ResponseEntity<List<FormationResponseDto>> allFormations(){
        List<Formation> formations=formationRepository.findAll();
        List<FormationResponseDto> list=new ArrayList<>();
        formations.forEach(f->{
            list.add(f.toDto());
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    public ResponseEntity<List<FormationResponseDto>> allFormationsAvecCompetence(Long competenceId){
        List<Formation> formations=formationRepository.findAllByCompetenceId(competenceId);
        List<FormationResponseDto> list=new ArrayList<>();
        formations.forEach(f->{
            list.add(f.toDto());
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    public ResponseEntity<List<FormationResponseDto>> allFormationsAvecDateDebutAvant(LocalDateTime date){
        List<Formation> formations=formationRepository.findAllByDateDebutBefore(date);
        List<FormationResponseDto> list=new ArrayList<>();
        formations.forEach(f->{
            list.add(f.toDto());
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    public ResponseEntity<List<FormationResponseDto>> allFormationsAvecDateDebutApres(LocalDateTime date){
        List<Formation> formations=formationRepository.findAllByDateDebutAfter(date);
        List<FormationResponseDto> list=new ArrayList<>();
        formations.forEach(f->{
            list.add(f.toDto());
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    public ResponseEntity<List<FormationResponseDto>> allFormationsAvecDateFinAvant(LocalDateTime date){
        List<Formation> formations=formationRepository.findAllByDateFinBefore(date);
        List<FormationResponseDto> list=new ArrayList<>();
        formations.forEach(f->{
            list.add(f.toDto());
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<List<FormationResponseDto>> allFormationsAvecDateFinApres(LocalDateTime date){
        List<Formation> formations=formationRepository.findAllByDateFinAfter(date);
        List<FormationResponseDto> list=new ArrayList<>();
        formations.forEach(f->{
            list.add(f.toDto());
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<List<FormationResponseDto>> allFormationsAvecDureeSup(int duree){
        List<Formation> formations=formationRepository.findAllByDureeEnJoursIsGreaterThan(duree);
        List<FormationResponseDto> list=new ArrayList<>();
        formations.forEach(f->{
            list.add(f.toDto());
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<List<FormationResponseDto>> allFormationsAvecDureeInf(int duree){
        List<Formation> formations=formationRepository.findAllByDureeEnJoursIsLessThan(duree);
        List<FormationResponseDto> list=new ArrayList<>();
        formations.forEach(f->{
            list.add(f.toDto());
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<List<FormationResponseDto>> allFormationsAvecDureeEntre(int duree1,int duree2){
        List<Formation> formations=formationRepository.findAllByDureeEnJoursIsBetween(duree1,duree2);
        List<FormationResponseDto> list=new ArrayList<>();
        formations.forEach(f->{
            list.add(f.toDto());
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<FormationResponseDto> getFormationById(Long formationId){
        Formation formation=formationRepository.findById(formationId).orElse(null);
        if(formation==null){
            throw new NotFoundException("formation not found");
        }
        return ResponseEntity.ok(formation.toDto());
    }

    public ResponseEntity<FormationResponseDto> updateFormation(Long formationId, FormationRequestDto formationRequestDto){
        Formation formation=formationRepository.findById(formationId).orElse(null);
        if(formation==null){
            throw new FormationNotFoundException("formation not found");
        }
        Competence competence=competenceRepository.findById(formationRequestDto.getCompetenceId()).orElse(null);
        if(competence==null){
            throw new CompetenceNotFoundException("competence n'existe pas avec id"+formationRequestDto.getCompetenceId());
        }
        formation.setLabel(formationRequestDto.getLabel());
        formation.setDescription(formationRequestDto.getDescription());
        formation.setPrestataireFormation(formationRequestDto.getPrestataireFormation());
        formation.setDateDebut(formationRequestDto.getDateDebut());
        formation.setDateFin(formationRequestDto.getDateFin());
        formation.setDureeEnJours(formationRequestDto.getDureeEnJours());
        formation.setCompetence(competence);
        Formation savedFormation=formationRepository.save(formation);
        return ResponseEntity.ok(savedFormation.toDto());
    }

    public ResponseEntity<FormationResponseDto> addFormation(FormationRequestDto formationRequestDto){
        Formation formation=new Formation();
        Competence competence=competenceRepository.findById(formationRequestDto.getCompetenceId()).orElse(null);
        if(competence==null){
            throw new CompetenceNotFoundException("competence n'existe pas avec id"+formationRequestDto.getCompetenceId());
        }
        formation.setLabel(formationRequestDto.getLabel());
        formation.setDescription(formationRequestDto.getDescription());
        formation.setDateDebut(formationRequestDto.getDateDebut());
        formation.setDateFin(formationRequestDto.getDateFin());
        formation.setPrestataireFormation(formationRequestDto.getPrestataireFormation());
        formation.setDureeEnJours(formationRequestDto.getDureeEnJours());
        formation.setCompetence(competence);
       Formation savedFormation=formationRepository.save(formation);
        return ResponseEntity.ok(savedFormation.toDto());
    }


    public ResponseEntity<String> deleteFormationById(Long formationId){
        formationRepository.deleteById(formationId);
        return  ResponseEntity.ok("formation supprimee avec id "+formationId);
    }

}
