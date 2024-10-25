package com.example.employeservice.web;


import com.example.employeservice.Dtos.CadreRequestDto;
import com.example.employeservice.Dtos.CadreResponseDto;
import com.example.employeservice.services.CadreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadres")
public class CadreController {
    private final CadreService cadreService;

    public CadreController(CadreService cadreService) {
        this.cadreService = cadreService;
    }

    @GetMapping("")
    public ResponseEntity<List<CadreResponseDto>> allCadres(){
        return cadreService.getAllCadre();
    }

    @GetMapping("/{corpsId}")
    public ResponseEntity<List<CadreResponseDto>> allCadresAvecCorps(@PathVariable Long corpsId){
        return cadreService.getAllCadreDeCorps(corpsId);
    }
    @DeleteMapping("/{cadreId}")
    public ResponseEntity<String> deleteCadreById(@PathVariable Long cadreId){
        return cadreService.deleteCadreById(cadreId);
    }

    @PostMapping("")
    public ResponseEntity<CadreResponseDto> addCadre(String label, Long corpsId){
        CadreRequestDto cadreRequestDto=CadreRequestDto.builder()
                .corpsId(corpsId)
                .label(label)
                .build();
        return  cadreService.addCadre(cadreRequestDto);
    }

    @GetMapping("/{cadreId}")
    public ResponseEntity<CadreResponseDto> getCadreById(@PathVariable Long cadreId){
        return  cadreService.getCadreById(cadreId);
    }

    @PutMapping("/{cadreId}")
    public ResponseEntity<CadreResponseDto> updateCadre(@PathVariable Long cadreId,String label, Long corpsId){
        CadreRequestDto cadreRequestDto=CadreRequestDto.builder()
                .corpsId(corpsId)
                .label(label)
                .build();
        return  cadreService.updateCadre(cadreId,cadreRequestDto);
    }

}

