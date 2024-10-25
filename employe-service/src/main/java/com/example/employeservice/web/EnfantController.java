package com.example.employeservice.web;

import com.example.employeservice.Dtos.EnfantRequestDto;
import com.example.employeservice.Dtos.EnfantResponseDto;
import com.example.employeservice.services.EnfantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enfants")
public class EnfantController {
    final  private EnfantService enfantService;

    public EnfantController(EnfantService enfantService) {
        this.enfantService = enfantService;
    }

    @GetMapping("")
    public ResponseEntity<List<EnfantResponseDto>> getAllEnfants(){
        return  enfantService.getAllEnfant();
    }

    @GetMapping("/{enfantId}")
    public ResponseEntity<EnfantResponseDto> getEnfantById(@PathVariable Long enfantId){
        return  enfantService.getEnfantById(enfantId);
    }

    @PutMapping("/{enfantId}")
    public ResponseEntity<EnfantResponseDto> modifierEnfant(@PathVariable Long enfantId,@RequestBody EnfantRequestDto enfantRequestDto){
        return  enfantService.updateEnfant(enfantId,enfantRequestDto);
    }

    @PostMapping("")
    public ResponseEntity<EnfantResponseDto> ajouterEnfant(@RequestBody EnfantRequestDto enfantRequestDto){
        return  enfantService.addEnfantConjoint(enfantRequestDto);
    }

    @GetMapping("/conjoint/{conjointId}")
    public ResponseEntity<List<EnfantResponseDto>> getEnfantByConjointId(@PathVariable Long conjointId){
        return  enfantService.getAllEnfantDeConjoint(conjointId);
    }

    @GetMapping("/employe/{employeId}")
    public ResponseEntity<List<EnfantResponseDto>> getEnfantByConjointId(@PathVariable String employeId){
        return  enfantService.getAllEnfantDeEmploye(employeId);
    }



}
