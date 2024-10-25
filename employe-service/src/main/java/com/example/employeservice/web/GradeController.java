package com.example.employeservice.web;


import com.example.employeservice.Dtos.GradeRequestDto;
import com.example.employeservice.Dtos.GradeResponseDto;
import com.example.employeservice.Dtos.GradeResponseDto;
import com.example.employeservice.services.GradeService;
import com.example.employeservice.services.GradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {
     final private GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }


    @GetMapping("")
    public ResponseEntity<List<GradeResponseDto>> allGrades(){
        return gradeService.getAllGrade();
    }

    @GetMapping("/all/{cadreId}")
    public ResponseEntity<List<GradeResponseDto>> allGradeAvecCadreId(@PathVariable Long cadreId){
        return gradeService.getAllGradeDeCadre(cadreId);
    }

    @PostMapping("")
    public ResponseEntity<GradeResponseDto> addGrade(@RequestBody GradeRequestDto gradeRequestDto){
        return  gradeService.addGrade(gradeRequestDto);
    }

    @GetMapping("/{gradeId}")
    public ResponseEntity<GradeResponseDto> getGradeById(@PathVariable Long gradeId){
        return  gradeService.getGradeById(gradeId);
    }

    @DeleteMapping("/{gradeId}")
    public ResponseEntity<String> deleteGradeById(@PathVariable Long gradeId){
        return  gradeService.deleteGradeById(gradeId);
    }

    @PutMapping("/{gradeId}")
    public ResponseEntity<GradeResponseDto> updateGrade(@PathVariable Long gradeId, @RequestBody GradeRequestDto gradeRequestDto){
        return  gradeService.updateGrade(gradeId,gradeRequestDto);
    }

}
