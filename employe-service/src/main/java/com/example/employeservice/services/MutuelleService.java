package com.example.employeservice.services;

import com.example.employeservice.Dtos.MutuelleRequestDto;
import com.example.employeservice.Dtos.MutuelleResponseDto;
import com.example.employeservice.Exception.MutuelleNotFoundException;
import com.example.employeservice.entities.Mutuelle;
import com.example.employeservice.repository.MutuelleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MutuelleService {
    private final MutuelleRepository mutuelleRepository;

    public MutuelleService(MutuelleRepository mutuelleRepository) {
        this.mutuelleRepository = mutuelleRepository;
    }


    public ResponseEntity<List<MutuelleResponseDto>> getAllMutuelles(){
        List<Mutuelle> mutuelles=mutuelleRepository.findAll();
        List<MutuelleResponseDto> list=new ArrayList<>();
        mutuelles.forEach(c->{
            list.add(c.toDto());
        });
        return ResponseEntity.ok(list);
    }


    public ResponseEntity<MutuelleResponseDto> getMutuelleById(Long mutuelleId){
        Mutuelle mutuelle=mutuelleRepository.findById(mutuelleId).orElseThrow(()-> new MutuelleNotFoundException("mutuelle not found"));
        return ResponseEntity.ok(mutuelle.toDto());
    }
    public ResponseEntity<MutuelleResponseDto> addMutuelle(MutuelleRequestDto mutuelleRequestDto){
       Mutuelle mutuelle=Mutuelle.builder()
               .label(mutuelleRequestDto.getLabel())
               .build();
       Mutuelle savedMutuelle=mutuelleRepository.save(mutuelle);
        return ResponseEntity.ok(savedMutuelle.toDto());
    }

    public ResponseEntity<MutuelleResponseDto> updateMutuelle(Long mutuelleId, MutuelleRequestDto mutuelleRequestDto){
        Mutuelle mutuelle=mutuelleRepository.findById(mutuelleId).orElseThrow(()-> new MutuelleNotFoundException("mutuelle not found"));
        mutuelle.setLabel(mutuelleRequestDto.getLabel());
        Mutuelle savedMutuelle=mutuelleRepository.save(mutuelle);
        return ResponseEntity.ok(savedMutuelle.toDto());
    }

    public ResponseEntity<String> deleteMutuelleById(Long mutuelleId) {
        mutuelleRepository.deleteById(mutuelleId);
        return  ResponseEntity.ok("mutuelle supprimee avec succes");
    }
}
