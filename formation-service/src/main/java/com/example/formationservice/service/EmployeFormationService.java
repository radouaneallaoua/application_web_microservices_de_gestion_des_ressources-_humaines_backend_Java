
package com.example.formationservice.service;

import com.example.formationservice.Exception.EmployeFormationAlreadyExistsException;
import com.example.formationservice.Exception.EmployeFormationNotFoundException;
import com.example.formationservice.Exception.FormationNotFoundException;
import com.example.formationservice.Repository.EmployeFormationRepository;
import com.example.formationservice.Repository.FormationRepository;
import com.example.formationservice.clients.EmployeRestClient;
import com.example.formationservice.dtos.EmployeFormationRequestDto;
import com.example.formationservice.dtos.EmployeFormationResponseDto;
import com.example.formationservice.entities.EmployeFormation;
import com.example.formationservice.entities.EmployeFormationKey;
import com.example.formationservice.entities.Formation;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeFormationService {
    final private FormationRepository formationRepository;
    final private EmployeFormationRepository employeFormationRepository;
   final private EmployeRestClient employeRestClient;
    public EmployeFormationService(FormationRepository formationRepository, EmployeFormationRepository employeFormationRepository, EmployeRestClient employeRestClient) {
        this.formationRepository = formationRepository;
        this.employeFormationRepository = employeFormationRepository;
        this.employeRestClient = employeRestClient;
    }


    public ResponseEntity<List<EmployeFormationResponseDto>> allEmployeFormations(){
        List<EmployeFormation> employeFormations=employeFormationRepository.findAll();
        List<EmployeFormationResponseDto> list=new ArrayList<>();
        employeFormations.forEach(f->{
            f.setEmploye(employeRestClient.findEmployeById(f.getId().getEmployeId()));
            list.add(f.toDto());
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<List<EmployeFormationResponseDto>> allAvecFormationId(Long formationId){
        List<EmployeFormation> employeFormations=employeFormationRepository.findAllByFormationId(formationId);
        List<EmployeFormationResponseDto> list=new ArrayList<>();
        employeFormations.forEach(f->{
            f.setEmploye(employeRestClient.findEmployeById(f.getId().getEmployeId()));
            list.add(f.toDto());
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<List<EmployeFormationResponseDto>> allEmployeFormationAvecEvaluationSuperieure(int evaluation){
        List<EmployeFormation> employeFormations=employeFormationRepository.findAllByEvaluationIsGreaterThan(evaluation);
        List<EmployeFormationResponseDto> list=new ArrayList<>();
        employeFormations.forEach(f->{
            f.setEmploye(employeRestClient.findEmployeById(f.getId().getEmployeId()));
            list.add(f.toDto());
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    public ResponseEntity<List<EmployeFormationResponseDto>> allEmployeFormationAvecEvaluationInferieure(int evaluation){
        List<EmployeFormation> employeFormations=employeFormationRepository.findAllByEvaluationIsLessThan(evaluation);
        List<EmployeFormationResponseDto> list=new ArrayList<>();
        employeFormations.forEach(f->{
            f.setEmploye(employeRestClient.findEmployeById(f.getId().getEmployeId()));
            list.add(f.toDto());
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<EmployeFormationResponseDto> addEmployeFormation(EmployeFormationRequestDto employeFormationRequestDto){
        Formation formation=formationRepository.findById(employeFormationRequestDto.getFormationId()).orElse(null);
        if(formation==null){
            throw  new FormationNotFoundException("formation not found");
        }
        EmployeFormationKey employeFormationKey=EmployeFormationKey.builder()
                .employeId(employeFormationRequestDto.getEmployeId())
                .formationId(formation.getId())
                .build();
        EmployeFormation foundEmployeFormation=employeFormationRepository.findById(employeFormationKey).orElse(null);
        if(foundEmployeFormation!=null){
            throw new EmployeFormationAlreadyExistsException("employe est deja inscrit dans la formation");
        }
        EmployeFormation employeFormation=EmployeFormation.builder()
                .dateFin(employeFormationRequestDto.getDateFin())
                .employe(employeRestClient.findEmployeById(employeFormationRequestDto.getEmployeId()))
                .dateIntegration(employeFormationRequestDto.getDateIntegration())
                .id(employeFormationKey)
                .formation(formation)
                .build();
        EmployeFormation savedEmployeFormation=employeFormationRepository.save(employeFormation);
        return new ResponseEntity<>(savedEmployeFormation.toDto(), HttpStatus.OK);
    }


    public ResponseEntity<EmployeFormationResponseDto> addEmployeFormationEvaluation(String employeId,Long formationId, int evaluation){
        EmployeFormation employeFormation=employeFormationRepository.findById(EmployeFormationKey.builder().formationId(formationId).employeId(employeId).build()).orElse(null);
        if(employeFormation==null){
            throw  new EmployeFormationNotFoundException("employe formation not found exception");
        }
        employeFormation.setEvaluation(evaluation);
        EmployeFormation savedEmployeFormation=employeFormationRepository.save(employeFormation);
        return new ResponseEntity<>(savedEmployeFormation.toDto(), HttpStatus.OK);
    }

    public ResponseEntity<EmployeFormationResponseDto> updateEmployeFormationEvaluation(String employeId,Long formationId, int evaluation){
        EmployeFormation employeFormation=employeFormationRepository.findById(EmployeFormationKey.builder().formationId(formationId).employeId(employeId).build()).orElse(null);
        if(employeFormation==null){
            throw  new EmployeFormationNotFoundException("employe formation not found exception");
        }
        employeFormation.setEvaluation(evaluation);
        EmployeFormation savedEmployeFormation=employeFormationRepository.save(employeFormation);
        return new ResponseEntity<>(savedEmployeFormation.toDto(), HttpStatus.OK);
    }

    public ResponseEntity<String> addListEmployeToFormation(EmployeFormationRequestDto employeFormationRequestDto,List<String> employeIdList){
        Formation formation=formationRepository.findById(employeFormationRequestDto.getFormationId()).orElse(null);
        if(formation==null){
            throw  new FormationNotFoundException("formation not found");
        }
        EmployeFormationKey employeFormationKey1=EmployeFormationKey.builder()
                .employeId(employeFormationRequestDto.getEmployeId())
                .formationId(formation.getId())
                .build();
        EmployeFormation foundEmployeFormation=employeFormationRepository.findById(employeFormationKey1).orElse(null);
        if(foundEmployeFormation!=null){
            throw new EmployeFormationAlreadyExistsException("employe est deja inscrit dans la formation");
        }
        EmployeFormation employeFormation=EmployeFormation.builder()
                .dateFin(employeFormationRequestDto.getDateFin())
                .employe(employeRestClient.findEmployeById(employeFormationRequestDto.getEmployeId()))
                .dateIntegration(employeFormationRequestDto.getDateIntegration())
                .id(employeFormationKey1)
                .formation(formation)
                .build();
        employeFormationRepository.save(employeFormation);
        employeIdList.forEach(e->{
            EmployeFormationKey employeFormationKey=EmployeFormationKey.builder()
                    .employeId(e)
                    .formationId(formation.getId())
                    .build();
            EmployeFormation foundEmployeFormation2=employeFormationRepository.findById(employeFormationKey).orElse(null);
            if(foundEmployeFormation2!=null){
                throw new EmployeFormationAlreadyExistsException("employe est deja inscrit dans la formation");
            }
            EmployeFormation employeFormation2=EmployeFormation.builder()
                    .dateFin(employeFormationRequestDto.getDateFin())
                    .employe(employeRestClient.findEmployeById(e))
                    .dateIntegration(employeFormationRequestDto.getDateIntegration())
                    .id(employeFormationKey)
                    .formation(formation)
                    .build();
            employeFormationRepository.save(employeFormation2);
        });

        return new ResponseEntity<>("Tous les employes sont ajoutes a la formation", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteEmployeFormationById(EmployeFormationKey id){
        employeFormationRepository.deleteEmployeFormationById(id);
        return  ResponseEntity.ok("employe formation supprimee avec succes");
    }

}


