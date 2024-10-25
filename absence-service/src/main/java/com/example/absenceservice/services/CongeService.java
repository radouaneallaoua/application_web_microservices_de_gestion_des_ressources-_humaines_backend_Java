package com.example.absenceservice.services;

import com.example.absenceservice.Exception.CongeNotFoundException;
import com.example.absenceservice.clients.EmployeRestClient;
import com.example.absenceservice.dtos.CongeRequestDto;
import com.example.absenceservice.dtos.CongeResponseDto;
import com.example.absenceservice.entities.Conge;
import com.example.absenceservice.enums.EtatConge;
import com.example.absenceservice.enums.TypeConge;
import com.example.absenceservice.repositories.CongeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CongeService {
    final private CongeRepository congeRepository;
    final  private EmployeRestClient employeRestClient;
    public CongeService(CongeRepository congeRepository, EmployeRestClient employeRestClient) {
        this.congeRepository = congeRepository;
        this.employeRestClient = employeRestClient;
    }

    public ResponseEntity<CongeResponseDto> addConge(CongeRequestDto congeRequestDto){
        Conge conge=Conge.builder()
                .etatConge(congeRequestDto.getEtatConge())
                .dateDebut(congeRequestDto.getDateDebut())
                .dateFin(congeRequestDto.getDateFin())
                .employe(employeRestClient.findEmployeById(congeRequestDto.getEmployeId()))
                .typeConge(congeRequestDto.getTypeConge())
                .dureeEnJours(congeRequestDto.getDureeEnJours())
                .employeId(congeRequestDto.getEmployeId())
                .build();
        Conge savedConge=congeRepository.save(conge);
        savedConge.setEmploye(employeRestClient.findEmployeById(congeRequestDto.getEmployeId()));
        return  ResponseEntity.ok(savedConge.toDto());
    }

    public ResponseEntity<CongeResponseDto> getCongeById(Long congeId){
        Conge conge=congeRepository.findById(congeId).orElse(null);
        if(conge==null){
            throw new CongeNotFoundException("conge n'existe pas avec id"+congeId);
        }
        conge.setEmploye(employeRestClient.findEmployeById(conge.getEmployeId()));
        return  ResponseEntity.ok(conge.toDto());
    }

    public ResponseEntity<String> deleteById(Long congeId){
        congeRepository.deleteById(congeId);
        return  ResponseEntity.ok("conge supprime avec id "+congeId);
    }


    public ResponseEntity<CongeResponseDto> updateConge(Long congeId,CongeRequestDto congeRequestDto){
        Conge conge=congeRepository.findById(congeId).orElse(null);
        if(conge==null){
            throw new CongeNotFoundException("conge n'existe pas avec id "+congeId);
        }
        conge.setEtatConge(congeRequestDto.getEtatConge());
        conge.setTypeConge(congeRequestDto.getTypeConge());
        conge.setEmployeId(congeRequestDto.getEmployeId());
        conge.setDateDebut(congeRequestDto.getDateDebut());
        conge.setTypeConge(congeRequestDto.getTypeConge());
        conge.setDateFin(congeRequestDto.getDateFin());
        conge.setEmploye(employeRestClient.findEmployeById(congeRequestDto.getEmployeId()));
        Conge savedConge=congeRepository.save(conge);
        return  ResponseEntity.ok(savedConge.toDto());
    }

    public ResponseEntity<List<CongeResponseDto>> getAllConges(){
        List<Conge> conges=congeRepository.findAll();
        List<CongeResponseDto> dtoList=new ArrayList<>();
        conges.forEach(a->{
            a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
            dtoList.add(a.toDto());
        });
        return  ResponseEntity.ok(dtoList);
    }


    public ResponseEntity<List<CongeResponseDto>> getAllCongesAvecEmployeId(String employeId){
        List<Conge> conges=congeRepository.findAllByEmployeId(employeId);
        List<CongeResponseDto> dtoList=new ArrayList<>();
        if(conges!=null){
            conges.forEach(a->{
                a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
                dtoList.add(a.toDto());
            });
        }
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<CongeResponseDto>> getAllCongesAvecDateDebut(LocalDate date){
        List<Conge> conges=congeRepository.findAllByDateDebut(date);
        List<CongeResponseDto> dtoList=new ArrayList<>();
        if(conges!=null){
            conges.forEach(a->{
                a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
                dtoList.add(a.toDto());
            });
        }
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<CongeResponseDto>> getAllCongesAvecDateFin(LocalDate date){
        List<Conge> conges=congeRepository.findAllByDateFin(date);
        List<CongeResponseDto> dtoList=new ArrayList<>();
        if(conges!=null){
            conges.forEach(a->{
                a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
                dtoList.add(a.toDto());
            });
        }
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<CongeResponseDto>> getAllCongesAvecDateDebutAvant(LocalDate date){
        List<Conge> conges=congeRepository.findAllByDateDebutBefore(date);
        List<CongeResponseDto> dtoList=new ArrayList<>();
        if(conges!=null){
            conges.forEach(a->{
                a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
                dtoList.add(a.toDto());
            });
        }
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<CongeResponseDto>> getAllCongesAvecDateFinEntre(LocalDate date1,LocalDate date2){
        List<Conge> conges=congeRepository.findAllByDateFinBetween(date1,date2);
        List<CongeResponseDto> dtoList=new ArrayList<>();
        if(conges!=null){
            conges.forEach(a->{
                a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
                dtoList.add(a.toDto());
            });
        }
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<CongeResponseDto>> getAllCongesAvecDureeEnJoursSuperieure(int duree){
        List<Conge> conges=congeRepository.findAllByDureeEnJoursGreaterThanEqual(duree);
        List<CongeResponseDto> dtoList=new ArrayList<>();
        if(conges!=null){
            conges.forEach(a->{
                a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
                dtoList.add(a.toDto());
            });
        }
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<CongeResponseDto>> getAllCongesAvecDureeEnJoursInferieure(int duree){
        List<Conge> conges=congeRepository.findAllByDureeEnJoursLessThanEqual(duree);
        List<CongeResponseDto> dtoList=new ArrayList<>();
        if(conges!=null){
            conges.forEach(a->{
                a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
                dtoList.add(a.toDto());
            });
        }
        return  ResponseEntity.ok(dtoList);
    }
    public ResponseEntity<List<CongeResponseDto>> getAllCongesAvecDateFinApres(LocalDate date){
        List<Conge> conges=congeRepository.findAllByDateFinAfter(date);
        List<CongeResponseDto> dtoList=new ArrayList<>();
        conges.forEach(a->{
            a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
            dtoList.add(a.toDto());
        });
        return  ResponseEntity.ok(dtoList);
    }


    public ResponseEntity<List<CongeResponseDto>> getAllCongesAvecEtatConge(EtatConge etatConge){
        List<Conge> conges=congeRepository.findAllByEtatConge(etatConge);
        List<CongeResponseDto> dtoList=new ArrayList<>();
        if(conges!=null){
            conges.forEach(a->{
                a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
                dtoList.add(a.toDto());
            });
        }
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<CongeResponseDto>> getAllCongesAvecEmployeIdEtTypeConge(String employeId, TypeConge typeConge){
        List<Conge> conges=congeRepository.findAllByEmployeIdAndTypeConge(employeId, typeConge);
        List<CongeResponseDto> dtoList=new ArrayList<>();
        if(conges!=null){
            conges.forEach(a->{
                a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
                dtoList.add(a.toDto());
            });
        }
        return  ResponseEntity.ok(dtoList);
    }


}
