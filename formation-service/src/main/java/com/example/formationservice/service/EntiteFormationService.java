
package com.example.formationservice.service;

import com.example.formationservice.Exception.EntiteFormationNotFoundException;
import com.example.formationservice.Exception.FormationNotFoundException;
import com.example.formationservice.Repository.EntiteFormationRepository;
import com.example.formationservice.Repository.FormationRepository;
import com.example.formationservice.clients.EmployeRestClient;
import com.example.formationservice.dtos.*;
import com.example.formationservice.entities.EntiteFormation;
import com.example.formationservice.entities.EntiteFormationKey;
import com.example.formationservice.entities.Formation;
import com.example.formationservice.models.Entite;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EntiteFormationService {
   final  private FormationRepository formationRepository;
   final private EntiteFormationRepository entiteFormationRepository;
   final private EmployeRestClient employeRestClient;
   final  private EmployeFormationService employeFormationService;
   final private FormationService formationService;
    public EntiteFormationService(FormationRepository formationRepository, EntiteFormationRepository entiteFormationRepository, EmployeRestClient employeRestClient, EmployeFormationService employeFormationService, FormationService formationService) {
        this.formationRepository = formationRepository;
        this.entiteFormationRepository = entiteFormationRepository;
        this.employeRestClient = employeRestClient;
        this.employeFormationService = employeFormationService;
        this.formationService = formationService;
    }


    public ResponseEntity<List<EntiteFormationResponseDto>> allEntiteFormations(){
        List<EntiteFormation> entiteFormations=entiteFormationRepository.findAll();
        List<EntiteFormationResponseDto> list=new ArrayList<>();
        entiteFormations.forEach(f->{
            f.setEntite(employeRestClient.findEntiteById(f.getId().getEntiteId()));
            list.add(f.toDto());
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<List<EntiteFormationResponseDto>> allAvecFormationId(Long formationId){
        List<EntiteFormation> entiteFormations=entiteFormationRepository.findAllByFormationId(formationId);
        List<EntiteFormationResponseDto> list=new ArrayList<>();
        entiteFormations.forEach(f->{
            f.setEntite(employeRestClient.findEntiteById(f.getId().getEntiteId()));
            list.add(f.toDto());
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<List<EntiteFormationResponseDto>> allEntiteFormationAvecFormationIdDateAjoutAfter(Long formationId,LocalDateTime date){
        List<EntiteFormation> entiteFormations=entiteFormationRepository.findAllByFormationIdAndDateAjoutAfter(formationId,date);
        List<EntiteFormationResponseDto> list=new ArrayList<>();
        entiteFormations.forEach(f->{
            f.setEntite(employeRestClient.findEntiteById(f.getId().getEntiteId()));
            list.add(f.toDto());
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<List<EntiteFormationResponseDto>> allEntiteFormationAvecFormationIdDateAjoutBefore(Long formationId,LocalDateTime date){
        List<EntiteFormation> entiteFormations=entiteFormationRepository.findAllByFormationIdAndDateAjoutBefore(formationId,date);
        List<EntiteFormationResponseDto> list=new ArrayList<>();
        entiteFormations.forEach(f->{
            f.setEntite(employeRestClient.findEntiteById(f.getId().getEntiteId()));
            list.add(f.toDto());
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    public ResponseEntity<EntiteFormationResponseDto> addEntiteFormation(EntiteFormationRequestDto entiteFormationRequestDto){
        Formation formation=formationRepository.findById(entiteFormationRequestDto.getFormationId()).orElse(null);
        if(formation==null){
            throw  new FormationNotFoundException("formation not found");
        }
        EntiteFormationKey entiteFormationKey=EntiteFormationKey.builder()
                .entiteId(entiteFormationRequestDto.getEntiteId())
                .formationId(formation.getId())
                .build();
        EntiteFormation entiteFormation=EntiteFormation.builder()
                .id(entiteFormationKey)
                .formation(formation)
                .entite(employeRestClient.findEntiteById(entiteFormationKey.getEntiteId()))
                .dateAjout(entiteFormationRequestDto.getDateAjout())
                .dateFin(entiteFormationRequestDto.getDateFin())
                .build();
        EntiteFormation savedEntiteFormation=entiteFormationRepository.save(entiteFormation);
        ResponseEntity<List<EntiteEmployeResponseDto>> listResponseEntity=employeRestClient.getAllEntitesEmployesWithEntiteId(entiteFormationRequestDto.getEntiteId());
        List<EntiteEmployeResponseDto> entites=listResponseEntity.getBody();
        if(entites!=null) {
            entites.forEach(e -> {
               ResponseEntity<EntiteEmployeResponseDto> response=employeRestClient.findEmployeEntiteActuelleById(e.getEmployeId());
               EntiteEmployeResponseDto  entiteActuelleDeEmploye=response.getBody();
                assert entiteActuelleDeEmploye != null;
                if(entiteActuelleDeEmploye.getEntiteId()==entiteFormationRequestDto.getEntiteId()){
                   employeFormationService.addEmployeFormation(EmployeFormationRequestDto.builder().formationId(entiteFormationRequestDto.getFormationId()).employeId(e.getEmployeId()).dateIntegration(LocalDateTime.now()).build());
               }
            });
        }
        return new ResponseEntity<>(savedEntiteFormation.toDto(), HttpStatus.OK);
    }

    public ResponseEntity<?> addAllEntitesToFormation(FormationRequestDto formationRequestDto){
        ResponseEntity<FormationResponseDto> savedFormation= formationService.addFormation(formationRequestDto);
        Formation formation=formationRepository.findById(savedFormation.getBody().getId()).orElse(null);
        if(formation==null){
            throw  new FormationNotFoundException("formation not found");
        }
        List<Entite> allEntites=employeRestClient.getAllEntites();
        allEntites.forEach(e->{
            EntiteFormationKey entiteFormationKey=EntiteFormationKey.builder()
                    .entiteId(e.getId())
                    .formationId(formation.getId())
                    .build();
            EntiteFormation entiteFormation=EntiteFormation.builder()
                    .id(entiteFormationKey)
                    .formation(formation)
                    .entite(e)
                    .dateAjout(formationRequestDto.getDateDebut())
                    .dateFin(formationRequestDto.getDateFin())
                    .build();
            EntiteFormation savedEntiteFormation=entiteFormationRepository.save(entiteFormation);

            ResponseEntity<List<EntiteEmployeResponseDto>> listResponseEntity=employeRestClient.getAllEntitesEmployesWithEntiteId(e.getId());
            List<EntiteEmployeResponseDto> entites=listResponseEntity.getBody();
            if(entites!=null) {
                entites.forEach(item -> {
                    ResponseEntity<EntiteEmployeResponseDto> response=employeRestClient.findEmployeEntiteActuelleById(item.getEmployeId());
                    EntiteEmployeResponseDto  entiteActuelleDeEmploye=response.getBody();
                    assert entiteActuelleDeEmploye != null;
                    if(entiteActuelleDeEmploye.getEntiteId()==e.getId()){
                        employeFormationService.addEmployeFormation(EmployeFormationRequestDto.builder().formationId(savedFormation.getBody().getId()).employeId(item.getEmployeId()).dateIntegration(formationRequestDto.getDateDebut()).dateFin(formationRequestDto.getDateFin()).build());
                    }
                });
            }
        });
        return new ResponseEntity<>("All entites sont ajoutees", HttpStatus.OK);
    }

    public ResponseEntity<EntiteFormationResponseDto> updateEntiteFormation(EntiteFormationKey id,EntiteFormationRequestDto entiteFormationRequestDto){
       EntiteFormation entiteFormation=entiteFormationRepository.findById(id).orElse(null);
       if(entiteFormation==null){
           throw new EntiteFormationNotFoundException("entite formation n'existe pas");
       }
        Formation formation=formationRepository.findById(entiteFormationRequestDto.getFormationId()).orElse(null);
        if(formation==null){
            throw  new FormationNotFoundException("formation not found");
        }
       entiteFormation.setFormation(formation);
       entiteFormation.setEntite(employeRestClient.findEntiteById(id.getEntiteId()));
        EntiteFormation savedEntiteFormation=entiteFormationRepository.save(entiteFormation);
        return new ResponseEntity<>(savedEntiteFormation.toDto(), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteEntiteFormationById(EntiteFormationKey id){
        entiteFormationRepository.deleteById(id);

        return  ResponseEntity.ok("entite formation supprimee avec succes");
    }

}


