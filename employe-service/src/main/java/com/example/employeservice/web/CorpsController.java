package com.example.employeservice.web;


import com.example.employeservice.Dtos.CorpsRequestDto;
import com.example.employeservice.Dtos.CorpsResponseDto;
import com.example.employeservice.services.CorpsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/corps")
public class CorpsController {
    private CorpsService corpsService;

    public CorpsController(CorpsService corpsService) {
        this.corpsService = corpsService;
    }

    @GetMapping("")
    public ResponseEntity<List<CorpsResponseDto>> allCorpss(){
        return corpsService.getAllCorps();
    }

    @GetMapping("/all/{categorieId}")
    public ResponseEntity<List<CorpsResponseDto>> allCorpsAvecCategorie(@PathVariable Long categorieId){
        return corpsService.getAllCorpsDeCategorie(categorieId);
    }

    @PostMapping("")
    public ResponseEntity<CorpsResponseDto> addCorps(@RequestBody CorpsRequestDto corpsRequestDto){
        return  corpsService.addCorps(corpsRequestDto);
    }
    @DeleteMapping("/{corpsId}")
    public ResponseEntity<String> deleteCorpsById(@PathVariable Long corpsId){
        return corpsService.deleteCorpsById(corpsId);
    }

    @GetMapping("/{corpsId}")
    public ResponseEntity<CorpsResponseDto> getCorpsById(@PathVariable Long corpsId){
        return  corpsService.getCorpsById(corpsId);
    }

    @PutMapping("/{corpsId}")
    public ResponseEntity<CorpsResponseDto> updateCorps(@PathVariable Long corpsId, @RequestBody CorpsRequestDto corpsRequestDto){
        return  corpsService.updateCorps(corpsId,corpsRequestDto);
    }

}
