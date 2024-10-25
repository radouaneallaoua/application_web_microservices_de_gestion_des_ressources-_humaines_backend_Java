package com.example.employeservice.services;
import com.example.employeservice.Dtos.IndiceEchelonRequestDto;
import com.example.employeservice.Dtos.IndiceEchelonResponseDto;
import com.example.employeservice.Exception.GradeNotFoundException;
import com.example.employeservice.Exception.IndiceEchelonNotFoundException;
import com.example.employeservice.entities.IndiceEchelon;
import com.example.employeservice.repository.GradeRepository;
import com.example.employeservice.repository.IndiceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class IndiceService {
    private final IndiceRepository indiceRepository;
    private final GradeRepository gradeRepository;

    public IndiceService(IndiceRepository indiceRepository, GradeRepository gradeRepository) {
        this.indiceRepository = indiceRepository;
        this.gradeRepository = gradeRepository;
    }


    public ResponseEntity<List<IndiceEchelonResponseDto>> getAllIndiceEchelons(){
        List<IndiceEchelon> indiceEchelons=indiceRepository.findAll();
        List<IndiceEchelonResponseDto> list=new ArrayList<>();
        indiceEchelons.forEach(c->{
            list.add(c.toDto());
        });
        return ResponseEntity.ok(list);
    }

    public ResponseEntity<List<IndiceEchelonResponseDto>> getAllIndiceEchelonWithGradeId(Long gradeId){
        List<IndiceEchelon> indiceEchelons=indiceRepository.findAllByGradeId(gradeId);
        List<IndiceEchelonResponseDto> list=new ArrayList<>();
        indiceEchelons.forEach(c->{
            list.add(c.toDto());
        });
        return ResponseEntity.ok(list);
    }


    public ResponseEntity<IndiceEchelonResponseDto> getIndiceEchelonById(Long indiceEchelonId){
        IndiceEchelon indiceEchelon=indiceRepository.findById(indiceEchelonId).orElseThrow(()-> new IndiceEchelonNotFoundException("indiceEchelon not found"));
        return ResponseEntity.ok(indiceEchelon.toDto());
    }
    public ResponseEntity<IndiceEchelonResponseDto> addIndiceEchelon(IndiceEchelonRequestDto indiceEchelonRequestDto){
       IndiceEchelon indiceEchelon=IndiceEchelon.builder()
               .grade(gradeRepository.findById(indiceEchelonRequestDto.getGradeId()).orElseThrow(()-> new GradeNotFoundException("grade not found")))
               .indice(indiceEchelonRequestDto.getIndice())
               .echelon(indiceEchelonRequestDto.getEchelon())
               .build();
       IndiceEchelon savedIndiceEchelon=indiceRepository.save(indiceEchelon);
        return ResponseEntity.ok(savedIndiceEchelon.toDto());
    }

    public ResponseEntity<IndiceEchelonResponseDto> updateIndiceEchelon(Long indiceEchelonId, IndiceEchelonRequestDto indiceEchelonRequestDto){
        IndiceEchelon indiceEchelon=indiceRepository.findById(indiceEchelonId).orElseThrow(()-> new IndiceEchelonNotFoundException("indiceEchelon not fount"));
        indiceEchelon.setEchelon(indiceEchelonRequestDto.getEchelon());
        indiceEchelon.setIndice(indiceEchelonRequestDto.getIndice());
        indiceEchelon.setGrade(gradeRepository.findById(indiceEchelonRequestDto.getGradeId()).orElseThrow(()-> new GradeNotFoundException("grade not found")));
        IndiceEchelon savedIndiceEchelon=indiceRepository.save(indiceEchelon);
        return ResponseEntity.ok(savedIndiceEchelon.toDto());
    }

    public ResponseEntity<String> deleteIndiceById(Long indiceId){
        indiceRepository.deleteById(indiceId);
        return  ResponseEntity.ok("indice echelon supprime avec id"+indiceId);
    }
}
