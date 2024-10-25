package com.example.absenceservice.services;

import com.example.absenceservice.Exception.AbsenceNotFoundException;
import com.example.absenceservice.clients.EmployeRestClient;
import com.example.absenceservice.dtos.AbsenceRequestDto;
import com.example.absenceservice.dtos.AbsenceResponseDto;
import com.example.absenceservice.entities.Absence;
import com.example.absenceservice.enums.Motif;
import com.example.absenceservice.repositories.AbsenceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AbsenceService {
    private final AbsenceRepository absenceRepository;
    private final EmployeRestClient employeRestClient;
    public AbsenceService(AbsenceRepository absenceRepository, EmployeRestClient employeRestClient) {
        this.absenceRepository = absenceRepository;
        this.employeRestClient = employeRestClient;
    }

    public ResponseEntity<List<AbsenceResponseDto>> getAllAbsences(){
        List<Absence> absences=absenceRepository.findAll();
        List<AbsenceResponseDto> dtoList=new ArrayList<>();
        absences.forEach(a -> {
            a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
            dtoList.add(a.toDto());

        });
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<AbsenceResponseDto>> getAllAbsencesAvecEmployeId(String employeId){
        List<Absence> absences=absenceRepository.findAllByEmployeId(employeId);
        List<AbsenceResponseDto> dtoList=new ArrayList<>();
        if(absences!=null){
            absences.forEach(a->{
                a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
                dtoList.add(a.toDto());
            });
        }
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<AbsenceResponseDto>> getAllAbsencesAvecEmployeIdEtEstJustifie(String employeId,boolean estJustifiee){
        List<Absence> absences=absenceRepository.findAllByEmployeIdAndEstJustifiee(employeId,estJustifiee);
        List<AbsenceResponseDto> dtoList=new ArrayList<>();
        if(absences!=null){
            absences.forEach(a->{
                a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
                dtoList.add(a.toDto());
            });
        }
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<AbsenceResponseDto>> getAllAbsencesAvecEmployeIdEtDateDebutEntre(String employeId, LocalDateTime date1,LocalDateTime date2){
        List<Absence> absences=absenceRepository.findAllByEmployeIdAndDateDebutBetween(employeId,date1,date2);
        List<AbsenceResponseDto> dtoList=new ArrayList<>();
        if(absences!=null){
            absences.forEach(a->{
                a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
                dtoList.add(a.toDto());
            });
        }
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<AbsenceResponseDto>> getAllAbsencesAvecEstJustifiee(boolean estJustifiee){
        List<Absence> absences=absenceRepository.findAllByEstJustifiee(estJustifiee);
        List<AbsenceResponseDto> dtoList=new ArrayList<>();
        if(absences!=null){
            absences.forEach(a->{
                a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
                dtoList.add(a.toDto());
            });
        }
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<AbsenceResponseDto>> getAllAbsencesAvecMotif(Motif motif){
        List<Absence> absences=absenceRepository.findAllByMotif(motif);
        List<AbsenceResponseDto> dtoList=new ArrayList<>();
        if(absences!=null){
            absences.forEach(a->{
                a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
                dtoList.add(a.toDto());
            });
        }
        return  ResponseEntity.ok(dtoList);
    }


    public ResponseEntity<AbsenceResponseDto> addAbsence(AbsenceRequestDto absenceRequestDto){
        Absence absence=Absence.builder()
                .motif(absenceRequestDto.getMotif())
                .dateDebut(absenceRequestDto.getDateDebut())
                .dateFin(absenceRequestDto.getDateFin())
                .dureeEnJours(absenceRequestDto.getDureeEnJours())
                .employeId(absenceRequestDto.getEmployeId())
                .estJustifiee(absenceRequestDto.isEstJustifiee())
                .employe(employeRestClient.findEmployeById(absenceRequestDto.getEmployeId()))
                .build();
        Absence savedAbsence=absenceRepository.save(absence);
        return  ResponseEntity.ok(savedAbsence.toDto());
    }

    public ResponseEntity<AbsenceResponseDto> updateAbsence(Long absenceId,AbsenceRequestDto absenceRequestDto){
        Absence absence=absenceRepository.findById(absenceId).orElse(null);
        if(absence==null){
            throw new AbsenceNotFoundException("absence n'existe pas avec id "+absenceId);
        }
        absence.setMotif(absenceRequestDto.getMotif());
        absence.setEstJustifiee(absenceRequestDto.isEstJustifiee());
        absence.setEmployeId(absenceRequestDto.getEmployeId());
        absence.setDateDebut(absenceRequestDto.getDateDebut());
        absence.setDateFin(absenceRequestDto.getDateFin());
        absence.setDureeEnJours(absenceRequestDto.getDureeEnJours());
        absence.setEmploye(employeRestClient.findEmployeById(absenceRequestDto.getEmployeId()));
        Absence savedAbsence=absenceRepository.save(absence);
        return  ResponseEntity.ok(savedAbsence.toDto());
    }

    public ResponseEntity<AbsenceResponseDto> getAbsenceById(Long absenceId){
        Absence absence=absenceRepository.findById(absenceId).orElse(null);
        if(absence==null){
            throw new AbsenceNotFoundException("absence n'existe pas avec id"+absenceId);
        }
        absence.setEmploye(employeRestClient.findEmployeById(absence.getEmployeId()));
        return  ResponseEntity.ok(absence.toDto());
    }

    public ResponseEntity<String> deleteById(Long absenceId){
        absenceRepository.deleteById(absenceId);
        return  ResponseEntity.ok("absence supprime avec id "+absenceId);
    }

    public ResponseEntity<List<AbsenceResponseDto>> getAllAbsencesAvecEmployeIdEtMotif(String employeId,Motif motif){
        List<Absence> absences=absenceRepository.findAllByEmployeIdAndMotif(employeId,motif);
        List<AbsenceResponseDto> dtoList=new ArrayList<>();
        if(absences!=null){
            absences.forEach(a->{
                a.setEmploye(employeRestClient.findEmployeById(employeId));
                dtoList.add(a.toDto());
            });
        }

        return  ResponseEntity.ok(dtoList);
    }

}
