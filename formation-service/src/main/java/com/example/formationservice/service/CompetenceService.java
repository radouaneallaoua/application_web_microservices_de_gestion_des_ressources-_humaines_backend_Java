package com.example.formationservice.service;

import com.example.formationservice.Repository.CompetenceRepository;
import com.example.formationservice.Repository.FormationRepository;
import com.example.formationservice.dtos.CompetenceRequestDto;
import com.example.formationservice.dtos.CompetenceResponseDto;
import com.example.formationservice.entities.Competence;
import jakarta.ws.rs.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CompetenceService {
    final private FormationRepository formationRepository;
    final private CompetenceRepository competenceRepository;

    public CompetenceService(FormationRepository formationRepository, CompetenceRepository competenceRepository) {
        this.formationRepository = formationRepository;
        this.competenceRepository = competenceRepository;
    }


    public ResponseEntity<List<CompetenceResponseDto>> allCompetences(){
        List<Competence> competences=competenceRepository.findAll();
        List<CompetenceResponseDto> list=new ArrayList<>();
        competences.forEach(s->{
            list.add(s.toDto());
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }




    public ResponseEntity<CompetenceResponseDto> getCompetenceById(Long competenceId){
        Competence competence=competenceRepository.findById(competenceId).orElse(null);
        if(competence==null){
            throw new NotFoundException("competence not found");
        }
        return ResponseEntity.ok(competence.toDto());
    }

    public ResponseEntity<CompetenceResponseDto> updateCompetence(Long competenceId, CompetenceRequestDto competenceRequestDto){
        Competence competence=competenceRepository.findById(competenceId).orElse(null);
        if(competence==null){
            throw new NotFoundException("formation not found");
        }
        competence.setLabel(competenceRequestDto.getLabel());
        competence.setDescription(competenceRequestDto.getDescription());
        Competence savedCompetence=competenceRepository.save(competence);
        return ResponseEntity.ok(savedCompetence.toDto());
    }

    public ResponseEntity<CompetenceResponseDto> addCompetence(CompetenceRequestDto competenceRequestDto){
        Competence competence=new Competence();
        competence.setLabel(competenceRequestDto.getLabel());
        competence.setDescription(competenceRequestDto.getDescription());
        Competence savedCompetence=competenceRepository.save(competence);
        return ResponseEntity.ok(savedCompetence.toDto());
    }


    public void deleteCompetenceById(Long competenceId){
        competenceRepository.deleteById(competenceId);
    }

}
