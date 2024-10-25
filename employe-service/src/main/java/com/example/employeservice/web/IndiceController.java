package com.example.employeservice.web;

import com.example.employeservice.Dtos.IndiceEchelonRequestDto;
import com.example.employeservice.Dtos.IndiceEchelonResponseDto;
import com.example.employeservice.services.IndiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/indices")
public class IndiceController {
    final private IndiceService indiceService;

    public IndiceController(IndiceService indiceService) {
        this.indiceService = indiceService;
    }

    @GetMapping("")
    public ResponseEntity<List<IndiceEchelonResponseDto>> getAllIndices(){
        return  indiceService.getAllIndiceEchelons();
    }

    @GetMapping("/grade/{gradeId}")
    public ResponseEntity<List<IndiceEchelonResponseDto>> getAllIndicesAvecGradeId(@PathVariable Long gradeId){
        return  indiceService.getAllIndiceEchelonWithGradeId(gradeId);
    }

    @GetMapping("/{indiceId}")
    public ResponseEntity<IndiceEchelonResponseDto> getIndiceById(@PathVariable Long indiceId){
        return  indiceService.getIndiceEchelonById(indiceId);
    }

    @PostMapping("")
    public ResponseEntity<IndiceEchelonResponseDto> getAllIndicesAvecGradeId(@RequestBody IndiceEchelonRequestDto indiceEchelonRequestDto){
        return  indiceService.addIndiceEchelon(indiceEchelonRequestDto);
    }

    @PutMapping("/{indiceId}")
    public ResponseEntity<IndiceEchelonResponseDto> modifierIndice(@PathVariable Long indiceId, @RequestBody IndiceEchelonRequestDto indiceEchelonRequestDto){
        return  indiceService.updateIndiceEchelon(indiceId,indiceEchelonRequestDto);
    }
    @DeleteMapping("/{indiceId}")
    public ResponseEntity<String> deleteIndiceById(@PathVariable Long indiceId){
        return  indiceService.deleteIndiceById(indiceId);
    }
}
