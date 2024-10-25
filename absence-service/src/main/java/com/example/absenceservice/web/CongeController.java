package com.example.absenceservice.web;

import com.example.absenceservice.dtos.CongeRequestDto;
import com.example.absenceservice.dtos.CongeResponseDto;
import com.example.absenceservice.enums.EtatConge;
import com.example.absenceservice.enums.TypeConge;
import com.example.absenceservice.services.CongeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/conges")
public class CongeController {
    final private CongeService congeService;

    public CongeController(CongeService congeService) {
        this.congeService = congeService;
    }

    @GetMapping("/{congeId}")
    public ResponseEntity<CongeResponseDto> getCongeById(@PathVariable Long congeId){
        return  congeService.getCongeById(congeId);
    }

    @PostMapping("")
    public ResponseEntity<CongeResponseDto> addConge(@RequestBody CongeRequestDto congeRequestDto){
        return  congeService.addConge(congeRequestDto);
    }

    @PutMapping("/{congeId}")
    public ResponseEntity<CongeResponseDto> updateConge(@PathVariable Long congeId,@RequestBody CongeRequestDto congeRequestDto){
        return  congeService.updateConge(congeId,congeRequestDto);
    }

    @DeleteMapping("/{congeId}")
    public ResponseEntity<String> deleteConge(@PathVariable Long congeId){
        return  congeService.deleteById(congeId);
    }


    @GetMapping("")
    public ResponseEntity<List<CongeResponseDto>> allConges(){
        return  congeService.getAllConges();
    }

    @GetMapping("/dateDebutAvant")
    public ResponseEntity<List<CongeResponseDto>> allCongesAvecDateDebutAvant(@RequestParam  LocalDate date){
        return  congeService.getAllCongesAvecDateDebutAvant(date);
    }

    @GetMapping("/dateFinApres")
    public ResponseEntity<List<CongeResponseDto>> allCongesAvecDateFinApres(@RequestParam  LocalDate date){
        return  congeService.getAllCongesAvecDateFinApres(date);
    }

    @GetMapping("/dateDebut")
    public ResponseEntity<List<CongeResponseDto>> allCongesAvecDateDebut( @RequestParam LocalDate date){
        return  congeService.getAllCongesAvecDateDebut(date);
    }

    @GetMapping("/dateFin")
    public ResponseEntity<List<CongeResponseDto>> allCongesAvecDateFin(@RequestParam LocalDate date){
        return  congeService.getAllCongesAvecDateFin(date);
    }
    @GetMapping("/dateFinEntre")
    public ResponseEntity<List<CongeResponseDto>> allCongesAvecDateFinEntre(@RequestParam("date1")  LocalDate date1,@RequestParam("date2")  LocalDate date2){
        return  congeService.getAllCongesAvecDateFinEntre(date1, date2);
    }

    @GetMapping("/dureeInferieure")
    public ResponseEntity<List<CongeResponseDto>> allCongesAvecDureeInf(@RequestParam("duree")  int duree){
        return  congeService.getAllCongesAvecDureeEnJoursInferieure(duree);
    }

    @GetMapping("/dureeSuperieure")
    public ResponseEntity<List<CongeResponseDto>> allCongesAvecDureeSup(@RequestParam("duree")  int duree){
        return  congeService.getAllCongesAvecDureeEnJoursSuperieure(duree);
    }

    @GetMapping("/etatConge")
    public ResponseEntity<List<CongeResponseDto>> allCongesAvecEtatConge(@RequestParam("etatConge") EtatConge etatConge){
        return  congeService.getAllCongesAvecEtatConge(etatConge);
    }

    @GetMapping("/employe/{employeId}")
    public ResponseEntity<List<CongeResponseDto>> allCongeOfEmploye(@PathVariable  String employeId){
        return  congeService.getAllCongesAvecEmployeId(employeId);
    }


    @GetMapping("/employe/{employeId}/typeConge")
    public ResponseEntity<List<CongeResponseDto>> allCongeOfEmployeWithType(@PathVariable  String employeId, @RequestParam TypeConge typeConge){
        return  congeService.getAllCongesAvecEmployeIdEtTypeConge(employeId,typeConge);
    }



}
