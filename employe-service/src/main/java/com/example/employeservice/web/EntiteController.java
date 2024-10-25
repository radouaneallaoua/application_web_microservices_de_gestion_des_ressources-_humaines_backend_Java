package com.example.employeservice.web;

import com.example.employeservice.Dtos.*;
import com.example.employeservice.entities.EntiteEmployeKey;
import com.example.employeservice.services.EntiteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/entites")
public class EntiteController {
    private final EntiteService entiteService;

    public EntiteController(EntiteService entiteService) {
        this.entiteService =entiteService;
    }

    @GetMapping("")
    public ResponseEntity<List<EntiteResponseDto>> getAllEntites(){
        return  entiteService.getAllEntites();
    }

    @GetMapping("/{entiteId}")
    public ResponseEntity<EntiteResponseDto> getEntiteById(@PathVariable Long entiteId){
        return  entiteService.getEntiteById(entiteId);
    }

    @GetMapping("/{entiteId}/sous-entites")
    public ResponseEntity<List<EntiteResponseDto>> getAllSubEntitesOfTheEntiteId(@PathVariable Long entiteId){
        return  entiteService.getAllEntitesAvecEntiteMereId(entiteId);
    }




    @GetMapping("/all-avec-type/{typeId}")
    public ResponseEntity<List<EntiteResponseDto>> getAllEntitesAvecTypeId(@PathVariable Long typeId){
        return  entiteService.getAllEntitesAvecType(typeId);
    }

    @GetMapping("/entite-employe")
    public ResponseEntity<List<EntiteEmployeResponseDto>> getAllEntitesEmployes(){
        return  entiteService.getAllEntitesEmployes();
    }
    @GetMapping("/entite-employe/date-debut-apres")
    public ResponseEntity<List<EntiteEmployeResponseDto>> getAllEntitesEmployesWithDateDebutApres(@RequestParam LocalDate date){
        return  entiteService.getAllEntiteEmployeAvecDateDebutApres(date);
    }

    @GetMapping("/entite-employe/{entiteId}")
    public ResponseEntity<List<EntiteEmployeResponseDto>> getAllEntitesEmployesWithEntiteId(@PathVariable Long entiteId){
        return  entiteService.getAllEntiteEmployeAvecEntiteId(entiteId);
    }

    @GetMapping("/employe/{employeId}")
    public ResponseEntity<List<EntiteEmployeResponseDto>> getAllEntitesEmployesWithEmployeId(@PathVariable String employeId){
        return  entiteService.getAllEntiteEmployeAvecEmployeId(employeId);
    }

    @GetMapping("/employe/{employeId}/entite-actuelle")
    public ResponseEntity<EntiteEmployeResponseDto> getEntiteActuelleAvecEmployeId(@PathVariable String employeId){
        return  entiteService.getEmployeEntiteActuelle(employeId);
    }

    @PostMapping("/entite-employe")
    public ResponseEntity<EntiteEmployeResponseDto> addEntiteEmploye(@RequestBody EntiteEmployeRequestDto entiteEmployeRequestDto){
        return  entiteService.addEntiteEmploye(entiteEmployeRequestDto);
    }

    @PutMapping("/entite-employe/")
    public ResponseEntity<EntiteEmployeResponseDto> updateEntiteEmploye(@RequestParam("id") EntiteEmployeKey id,@RequestBody EntiteEmployeRequestDto entiteEmployeRequestDto ){
        return  entiteService.updateEntiteEmploye(id,entiteEmployeRequestDto);
    }

    @DeleteMapping("/entite-employe/")
    public ResponseEntity<String> deleteEntiteEmploye(@RequestParam("id") EntiteEmployeKey id ){
        return  entiteService.deleteEntiteEmployeById(id);
    }

    @PostMapping("")
    public ResponseEntity<EntiteResponseDto> addEntite(@RequestBody EntiteRequestDto entiteRequestDto){
        return  entiteService.addEntite(entiteRequestDto);
    }

    @PostMapping("/{entiteId}/ajouter-sous-entite")
    public ResponseEntity<EntiteResponseDto> addSousEntite(@PathVariable Long entiteId, @RequestBody EntiteRequestDto entiteRequestDto){
        return  entiteService.addSubEntite(entiteId,entiteRequestDto);
    }

    @PutMapping("/{entiteId}")
    public ResponseEntity<EntiteResponseDto> updateEntite(Long entiteId,@RequestBody EntiteRequestDto entiteRequestDto) {
        return  entiteService.updateEntiteById(entiteId,entiteRequestDto);
    }

    @PutMapping("/sub-entite/{entiteId}")
    public ResponseEntity<EntiteResponseDto> updateSubEntite(@PathVariable  Long entiteId,@RequestParam("label") String label,@RequestParam("type") Long typeId,@RequestParam("entiteMereId") Long entiteMereId) {
        EntiteRequestDto entiteRequestDto=EntiteRequestDto.builder()
                .name(label)
                .typeId(typeId)
                .build();
        return  entiteService.updateSubEntiteById(entiteId,entiteRequestDto,entiteMereId);
    }

    @DeleteMapping("/{entiteId}")
    public void deleteEntiteById(@PathVariable Long entiteId){
        entiteService.deleteEntiteById(entiteId);
    }

}
