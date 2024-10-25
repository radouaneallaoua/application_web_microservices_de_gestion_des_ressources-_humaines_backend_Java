package com.example.paieservice.services;

import com.example.paieservice.clients.EmployeRestClient;
import com.example.paieservice.dtos.HeuresSupplementairesRequestDto;
import com.example.paieservice.dtos.HeuresSupplementairesResponseDto;
import com.example.paieservice.entities.HeuresSupplementaires;
import com.example.paieservice.exception.HeuresSuppNotFoundException;
import com.example.paieservice.repository.HeuresSupplRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class HeureSuppService {
    final  private HeuresSupplRepository heuresSupplRepository;
    final  private EmployeRestClient employeRestClient;
    public HeureSuppService(HeuresSupplRepository heuresSupplRepository, EmployeRestClient employeRestClient) {
        this.heuresSupplRepository = heuresSupplRepository;
        this.employeRestClient = employeRestClient;
    }

    public ResponseEntity<HeuresSupplementairesResponseDto> ajouterHeuresSupp(HeuresSupplementairesRequestDto heuresSupplementairesRequestDto){
        HeuresSupplementaires heuresSupplementaires=HeuresSupplementaires.builder()
                .date(heuresSupplementairesRequestDto.getDate())
                .nombreHeures(heuresSupplementairesRequestDto.getNombreHeures())
                .employeId(heuresSupplementairesRequestDto.getEmployeId())
                .employe(employeRestClient.findEmployeById(heuresSupplementairesRequestDto.getEmployeId()))
                .montantUnitaire(heuresSupplementairesRequestDto.getMontantUnitaire())
                .build();
        HeuresSupplementaires savedHeuresSupp=heuresSupplRepository.save(heuresSupplementaires);
        return ResponseEntity.ok(savedHeuresSupp.toDto());
    }

    public ResponseEntity<HeuresSupplementairesResponseDto> modifierHeuresSupp(Long heureSuppId,HeuresSupplementairesRequestDto heuresSupplementairesRequestDto){
        HeuresSupplementaires foundHeures=heuresSupplRepository.findById(heureSuppId).orElseThrow(()-> new HeuresSuppNotFoundException("heures supp not found exception"));
        foundHeures.setNombreHeures(heuresSupplementairesRequestDto.getNombreHeures());
        foundHeures.setEmployeId(heuresSupplementairesRequestDto.getEmployeId());
        foundHeures.setDate(heuresSupplementairesRequestDto.getDate());
        foundHeures.setMontantUnitaire(heuresSupplementairesRequestDto.getMontantUnitaire());
        foundHeures.setEmploye(employeRestClient.findEmployeById(heuresSupplementairesRequestDto.getEmployeId()));
        HeuresSupplementaires savedHeuresSupp=heuresSupplRepository.save(foundHeures);
        return ResponseEntity.ok(savedHeuresSupp.toDto());
    }

    public ResponseEntity<String> supprimerHeuresSupp(Long heureSuppId){
        heuresSupplRepository.deleteById(heureSuppId);
        return ResponseEntity.ok("heures supp supprimees avec id"+heureSuppId);
    }

    public ResponseEntity<List<HeuresSupplementairesResponseDto>> toutesHeuresSuppAvecEmployeIdEtDate(String employeId, LocalDate date){
        List<HeuresSupplementaires> heuresSupplementaires=heuresSupplRepository.findAllByEmployeIdAndDate(employeId, date);
        List<HeuresSupplementairesResponseDto> dtos=new ArrayList<>();
        if(heuresSupplementaires!=null){
            heuresSupplementaires.forEach(h->{
                h.setEmploye(employeRestClient.findEmployeById(h.getEmployeId()));
                dtos.add(h.toDto());
            });
        }
        return  ResponseEntity.ok(dtos);
    }

    public ResponseEntity<List<HeuresSupplementairesResponseDto>> toutesHeuresSuppAvecEmployeId(String employeId){
        List<HeuresSupplementaires> heuresSupplementaires=heuresSupplRepository.findAllByEmployeId(employeId);
        List<HeuresSupplementairesResponseDto> dtos=new ArrayList<>();
        if(heuresSupplementaires!=null){
            heuresSupplementaires.forEach(h->{
                h.setEmploye(employeRestClient.findEmployeById(h.getEmployeId()));
                dtos.add(h.toDto());
            });
        }
        return  ResponseEntity.ok(dtos);
    }

    public ResponseEntity<List<HeuresSupplementairesResponseDto>> toutesHeuresSupp(){
        List<HeuresSupplementaires> heuresSupplementaires=heuresSupplRepository.findAll();
        List<HeuresSupplementairesResponseDto> dtos=new ArrayList<>();
        heuresSupplementaires.forEach(h -> {
            h.setEmploye(employeRestClient.findEmployeById(h.getEmployeId()));
            dtos.add(h.toDto());
        });
        return  ResponseEntity.ok(dtos);
    }

    public ResponseEntity<HeuresSupplementairesResponseDto> getHeuresSuppById(Long heureSuppId) {
        HeuresSupplementaires heuresSupplementaires=heuresSupplRepository.findById(heureSuppId).orElseThrow(()-> new HeuresSuppNotFoundException("Heures supp not found"));
        heuresSupplementaires.setEmploye(employeRestClient.findEmployeById(heuresSupplementaires.getEmployeId()));
        return  ResponseEntity.ok(heuresSupplementaires.toDto());
    }
}
