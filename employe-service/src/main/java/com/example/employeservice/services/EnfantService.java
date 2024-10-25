package com.example.employeservice.services;

import com.example.employeservice.Dtos.EnfantRequestDto;
import com.example.employeservice.Dtos.EnfantResponseDto;
import com.example.employeservice.Exception.ConjointNotFoundException;
import com.example.employeservice.Exception.EmployeNotFoundException;
import com.example.employeservice.Exception.EnfantNotFoundException;
import com.example.employeservice.entities.Conjoint;
import com.example.employeservice.entities.Enfant;
import com.example.employeservice.repository.ConjointRepository;
import com.example.employeservice.repository.EmployeRepository;
import com.example.employeservice.repository.EnfantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EnfantService {
    private final EnfantRepository enfantRepository;
    private final ConjointRepository conjointRepository;
    private final EmployeRepository employeRepository;

    public EnfantService(ConjointRepository conjointRepository, EnfantRepository enfantRepository, ConjointRepository conjointRepository1, EmployeRepository employeRepository) {
        this.enfantRepository = enfantRepository;
        this.conjointRepository = conjointRepository1;
        this.employeRepository = employeRepository;
    }

    public ResponseEntity<List<EnfantResponseDto>> getAllEnfantDeConjoint(Long conjointId){
        List<Enfant> enfants=enfantRepository.findAllByConjointId(conjointId);
        List<EnfantResponseDto> list=new ArrayList<>();
        enfants.forEach(e->{
            list.add(e.toDto());
        });
        return ResponseEntity.ok(list);
    }

    public ResponseEntity<List<EnfantResponseDto>> getAllEnfantDeEmploye(String employeId){
        List<Enfant> enfants=enfantRepository.findAllByEmployeId(employeId);
        List<EnfantResponseDto> list=new ArrayList<>();
        enfants.forEach(e->{
            list.add(e.toDto());
        });
        return ResponseEntity.ok(list);
    }

    public ResponseEntity<List<EnfantResponseDto>> getAllEnfant(){
        List<Enfant> enfants=enfantRepository.findAll();
        List<EnfantResponseDto> list=new ArrayList<>();
        enfants.forEach(e->{
            list.add(e.toDto());
        });
        return ResponseEntity.ok(list);
    }


    public ResponseEntity<EnfantResponseDto> getEnfantById(Long enfantId){
        Enfant enfant=enfantRepository.findById(enfantId).orElseThrow(()-> new EnfantNotFoundException("enfant not found"));
        return ResponseEntity.ok(enfant.toDto());
    }

    public ResponseEntity<EnfantResponseDto> updateEnfant(Long enfantId, EnfantRequestDto enfantRequestDto){
        Enfant enfant=enfantRepository.findById(enfantId).orElseThrow(()-> new EnfantNotFoundException("enfant not found"));
        enfant.setNom(enfantRequestDto.getNom());
        enfant.setPrenom(enfantRequestDto.getPrenom());
        enfant.setDateNaissance(enfantRequestDto.getDateNaissance());
        Enfant savedEnfant=enfantRepository.save(enfant);
        return ResponseEntity.ok(savedEnfant.toDto());
    }

    public ResponseEntity<EnfantResponseDto> addEnfantConjoint(EnfantRequestDto enfantRequestDto){
        Conjoint conjoint=conjointRepository.findById(enfantRequestDto.getMereId()).orElse(null);
        if(conjoint==null){
            throw new ConjointNotFoundException("conjoint not found ");
        }
        Enfant enfant=Enfant.builder()
                .conjoint(conjoint)
                .nom(enfantRequestDto.getNom())
                .employe(employeRepository.findById(enfantRequestDto.getEmployeId()).orElseThrow(()-> new EmployeNotFoundException("employe not found")))
                .dateNaissance(enfantRequestDto.getDateNaissance())
                .prenom(enfantRequestDto.getPrenom())
                .build();
        return ResponseEntity.ok(enfantRepository.save(enfant).toDto());
    }
}
