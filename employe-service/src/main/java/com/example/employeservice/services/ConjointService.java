package com.example.employeservice.services;

import com.example.employeservice.Dtos.ConjointRequestDto;
import com.example.employeservice.Dtos.ConjointResponsetDto;
import com.example.employeservice.Exception.ConjointNotFoundException;
import com.example.employeservice.Exception.EmployeNotFoundException;
import com.example.employeservice.entities.Conjoint;
import com.example.employeservice.entities.Employe;
import com.example.employeservice.repository.ConjointRepository;
import com.example.employeservice.repository.EmployeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ConjointService {
    private final ConjointRepository conjointRepository;
    private final  EmployeRepository employeRepository;
    public ConjointService(ConjointRepository conjointRepository, EmployeRepository employeRepository) {
        this.conjointRepository = conjointRepository;
        this.employeRepository = employeRepository;
    }

    public ResponseEntity<List<ConjointResponsetDto>> getAllConjointDeEmploye(String employeId){
        List<Conjoint> conjointList=conjointRepository.findAllByEmployeId(employeId);
        List<ConjointResponsetDto> list=new ArrayList<>();
        conjointList.forEach(c->{
            list.add(c.toDto());
        });
        return ResponseEntity.ok(list);
    }

    public ResponseEntity<List<ConjointResponsetDto>> getAllConjoints(){
        List<Conjoint> conjointList=conjointRepository.findAll();
        List<ConjointResponsetDto> list=new ArrayList<>();
        conjointList.forEach(c->{
            list.add(c.toDto());
        });
        return ResponseEntity.ok(list);
    }

    public ResponseEntity<ConjointResponsetDto> getConjointById(Long conjointId){
        Conjoint conjoint=conjointRepository.findById(conjointId).orElseThrow(()-> new ConjointNotFoundException("conjoint not found"));
        return ResponseEntity.ok(conjoint.toDto());
    }

    public ResponseEntity<String> deleteConjointById(Long conjointId){
        conjointRepository.deleteById(conjointId);
        return ResponseEntity.ok("conjoint supprime avec succes");
    }

    public ResponseEntity<ConjointResponsetDto> addEmployeConjoint(ConjointRequestDto conjointRequestDto){
        Employe employe=employeRepository.findById(conjointRequestDto.getEmployeId()).orElse(null);
        if(employe==null){
            throw new EmployeNotFoundException("employe not found");
        }
        Conjoint conjoint=Conjoint.builder()
                .employe(employe)
                .nom(conjointRequestDto.getNom())
                .dateNaissance(conjointRequestDto.getDateNaissance())
                .prenom(conjointRequestDto.getPrenom())
                .build();
        return ResponseEntity.ok(conjointRepository.save(conjoint).toDto());
    }

    public ResponseEntity<ConjointResponsetDto> updateEmployeConjoint(Long conjointId,ConjointRequestDto conjointRequestDto){
        Employe employe=employeRepository.findById(conjointRequestDto.getEmployeId()).orElse(null);
        Conjoint conjoint=conjointRepository.findById(conjointId).orElseThrow(()-> new ConjointNotFoundException("conjoint not found "));
        if(employe==null){
            throw new EmployeNotFoundException("employe not found");
        }
        conjoint.setEmploye(employe);
        conjoint.setNom(conjointRequestDto.getNom());
        conjoint.setPrenom(conjointRequestDto.getPrenom());
        conjoint.setDateNaissance(conjointRequestDto.getDateNaissance());
        return ResponseEntity.ok(conjointRepository.save(conjoint).toDto());
    }
}
