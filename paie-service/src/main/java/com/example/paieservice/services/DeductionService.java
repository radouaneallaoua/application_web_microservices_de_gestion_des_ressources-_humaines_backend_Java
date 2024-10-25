package com.example.paieservice.services;

import com.example.paieservice.clients.EmployeRestClient;
import com.example.paieservice.dtos.DeductionRequestDto;
import com.example.paieservice.dtos.DeductionResponseDto;
import com.example.paieservice.dtos.PrimeResponseDto;
import com.example.paieservice.entities.Deduction;
import com.example.paieservice.entities.Prime;
import com.example.paieservice.enums.TypeDeduction;
import com.example.paieservice.exception.DeductionNotFoundException;
import com.example.paieservice.repository.DeductionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DeductionService {
    final private DeductionRepository deductionRepository;
    final  private EmployeRestClient employeRestClient;
    public DeductionService(DeductionRepository deductionRepository, EmployeRestClient employeRestClient) {
        this.deductionRepository = deductionRepository;
        this.employeRestClient = employeRestClient;
    }
    
    public ResponseEntity<DeductionResponseDto> ajouterDeduction(DeductionRequestDto deductionRequestDto){
        Deduction deduction=Deduction.builder()
                .typeDeduction(deductionRequestDto.getTypeDeduction())
                .dateDebut(deductionRequestDto.getDateDebut())
                .dateFin(deductionRequestDto.getDateFin())
                .montant(deductionRequestDto.getMontant())
                .description(deductionRequestDto.getDescription())
                .employeId(deductionRequestDto.getEmployeId())
                .employe(employeRestClient.findEmployeById(deductionRequestDto.getEmployeId()))
                .build();
        Deduction savedDeduction=deductionRepository.save(deduction);
        return  ResponseEntity.ok(savedDeduction.toDto());
                
    }
    
    public ResponseEntity<DeductionResponseDto> modifierDeduction(Long deductionId,DeductionRequestDto deductionRequestDto){
        Deduction foundDeduction=deductionRepository.findById(deductionId).orElseThrow(()-> new DeductionNotFoundException("deduction not found with id"+deductionId));
        foundDeduction.setTypeDeduction(deductionRequestDto.getTypeDeduction());
        foundDeduction.setDateDebut(deductionRequestDto.getDateDebut());
        foundDeduction.setMontant(deductionRequestDto.getMontant());
        foundDeduction.setDescription(deductionRequestDto.getDescription());
        foundDeduction.setEmployeId(deductionRequestDto.getEmployeId());
        foundDeduction.setEmploye(employeRestClient.findEmployeById(deductionRequestDto.getEmployeId()));
        Deduction savedDeduction=deductionRepository.save(foundDeduction);
        return  ResponseEntity.ok(savedDeduction.toDto());

    }
    
    public ResponseEntity<DeductionResponseDto> getDeductionById(Long deductionId){
        Deduction deduction=deductionRepository.findById(deductionId).orElseThrow(()-> new DeductionNotFoundException("deduction not found exception"));
        return  ResponseEntity.ok(deduction.toDto());
    }
    
    public ResponseEntity<String> deleteDeductionWithId(Long deductionId){
        deductionRepository.deleteById(deductionId);
        return  ResponseEntity.ok("deduction supprimee avec id "+deductionId);
    }
    
    public ResponseEntity<List<DeductionResponseDto>> toutDeductionsAvecEmployeIdEtTypeDeduction(String employeId, TypeDeduction typeDeduction){
        List<Deduction> deductions=deductionRepository.findAllByEmployeIdAndTypeDeduction(employeId, typeDeduction);
        List<DeductionResponseDto> dtos=new ArrayList<>();
        if(deductions!=null){
            deductions.forEach(p->{
                p.setEmploye(employeRestClient.findEmployeById(p.getEmployeId()));
                dtos.add(p.toDto());
            });
        }
        return ResponseEntity.ok(dtos);
    }


    public ResponseEntity<List<DeductionResponseDto>> allDeductions(){
        List<Deduction> deductions=deductionRepository.findAll();
        List<DeductionResponseDto> dtos=new ArrayList<>();
        deductions.forEach(p -> {
            p.setEmploye(employeRestClient.findEmployeById(p.getEmployeId()));
            dtos.add(p.toDto());
        });
        return ResponseEntity.ok(dtos);
    }
    public ResponseEntity<List<DeductionResponseDto>> toutDeductionsAvecEmployeId(String employeId){
        List<Deduction> deductions=deductionRepository.findAllByEmployeId(employeId);
        List<DeductionResponseDto> dtos=new ArrayList<>();
        if(deductions!=null){
            deductions.forEach(p->{
                p.setEmploye(employeRestClient.findEmployeById(p.getEmployeId()));
                dtos.add(p.toDto());
            });
        }
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<List<DeductionResponseDto>> toutDeductionsAvecEmployeIdEtDateFinAvant(String employeId, LocalDate date){
        List<Deduction> deductions=deductionRepository.findAllByEmployeIdAndDateFinBefore(employeId, date);
        List<DeductionResponseDto> dtos=new ArrayList<>();
        if(deductions!=null){
            deductions.forEach(p->{
                p.setEmploye(employeRestClient.findEmployeById(p.getEmployeId()));
                dtos.add(p.toDto());
            });
        }
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<List<DeductionResponseDto>> toutDeductionsAvecEmployeIdEtDateDebutApresEtDateFinAvant(String employeId,LocalDate date1,LocalDate date2){
        List<Deduction> deductions=deductionRepository.findAllByEmployeIdAndDateDebutAfterAndDateFinBefore(employeId,date1,date2);
        List<DeductionResponseDto> dtos=new ArrayList<>();
        if(deductions!=null){
            deductions.forEach(p->{
                p.setEmploye(employeRestClient.findEmployeById(p.getEmployeId()));
                dtos.add(p.toDto());
            });
        }
        return ResponseEntity.ok(dtos);
    }
    
    
}
