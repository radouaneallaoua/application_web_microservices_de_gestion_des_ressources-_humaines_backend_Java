package com.example.employeservice.services;

import com.example.employeservice.Dtos.GradeRequestDto;
import com.example.employeservice.Dtos.GradeResponseDto;
import com.example.employeservice.Exception.CadreNotFoundException;
import com.example.employeservice.Exception.GradeNotFoundException;
import com.example.employeservice.entities.Grade;
import com.example.employeservice.repository.CadreRepository;
import com.example.employeservice.repository.GradeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GradeService {
    private final GradeRepository gradeRepository;
    private final CadreRepository cadreRepository;

    public GradeService(GradeRepository gradeRepository, CadreRepository cadreRepository) {
        this.gradeRepository = gradeRepository;
        this.cadreRepository = cadreRepository;
    }


    public ResponseEntity<List<GradeResponseDto>> getAllGradeDeCadre(Long cadreId){
        List<Grade> grades=gradeRepository.findAllByCadreId(cadreId);
        List<GradeResponseDto> list=new ArrayList<>();
        grades.forEach(c->{
            list.add(c.toDto());
        });
        return ResponseEntity.ok(list);
    }

    public ResponseEntity<List<GradeResponseDto>> getAllGrade(){
        List<Grade> grades=gradeRepository.findAll();
        List<GradeResponseDto> list=new ArrayList<>();
        grades.forEach(c->{
            list.add(c.toDto());
        });
        return ResponseEntity.ok(list);
    }


    public ResponseEntity<GradeResponseDto> getGradeById(Long gradeId){
        Grade grade=gradeRepository.findById(gradeId).orElseThrow(()-> new GradeNotFoundException("grade not found"));
        return ResponseEntity.ok(grade.toDto());
    }


    public ResponseEntity<GradeResponseDto> updateGrade(Long gradeId, GradeRequestDto gradeRequestDto){
        Grade grade=gradeRepository.findById(gradeId).orElseThrow(()-> new GradeNotFoundException("grade not found"));
        grade.setLabel(gradeRequestDto.getLabel());

        Grade savedGrade=gradeRepository.save(grade);
        return ResponseEntity.ok(savedGrade.toDto());
    }

    public ResponseEntity<GradeResponseDto> addGrade(GradeRequestDto gradeRequestDto){
        Grade grade=Grade.builder()
                .cadre(cadreRepository.findById(gradeRequestDto.getCadreId()).orElseThrow(()->new CadreNotFoundException("cadre not found")))
                .label(gradeRequestDto.getLabel())
                .build();

        Grade savedGrade=gradeRepository.save(grade);
        return ResponseEntity.ok(savedGrade.toDto());
    }

    public ResponseEntity<String> deleteGradeById(Long gradeId) {
        gradeRepository.deleteById(gradeId);
        return ResponseEntity.ok("grade supprime avec succes");
    }
}
