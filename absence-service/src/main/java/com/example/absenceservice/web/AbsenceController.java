package com.example.absenceservice.web;

import com.example.absenceservice.dtos.AbsenceRequestDto;
import com.example.absenceservice.dtos.AbsenceResponseDto;

import com.example.absenceservice.enums.Motif;
import com.example.absenceservice.services.AbsenceService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/absences")
public class AbsenceController {
    final private AbsenceService absenceService;

    public AbsenceController(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }

    @GetMapping("/{absenceId}")
    public ResponseEntity<AbsenceResponseDto> getAbsenceById(@PathVariable Long absenceId){
        return  absenceService.getAbsenceById(absenceId);
    }

    @PostMapping("")
    public ResponseEntity<AbsenceResponseDto> addAbsence(@RequestBody AbsenceRequestDto absenceRequestDto){
        return  absenceService.addAbsence(absenceRequestDto);
    }

    @PutMapping("/{absenceId}")
    public ResponseEntity<AbsenceResponseDto> updateAbsence(@PathVariable Long absenceId,@RequestBody AbsenceRequestDto absenceRequestDto){
        return  absenceService.updateAbsence(absenceId,absenceRequestDto);
    }

    @DeleteMapping("/{absenceId}")
    public ResponseEntity<String> deleteAbsenceById(@PathVariable Long absenceId){
        return  absenceService.deleteById(absenceId);
    }


    @GetMapping("")
    public ResponseEntity<List<AbsenceResponseDto>> allAbsences(){
        return  absenceService.getAllAbsences();
    }

    @GetMapping("/employe/{employeId}")
    public ResponseEntity<List<AbsenceResponseDto>> allAbsencesOfEmployeId(@PathVariable String employeId){
        return  absenceService.getAllAbsencesAvecEmployeId(employeId);
    }

    @GetMapping("/employe/{employeId}/motif")
    public ResponseEntity<List<AbsenceResponseDto>> allAbsencesOfEmployeIdWithMotif(@PathVariable String employeId,@RequestParam("motif") Motif motif){
        return  absenceService.getAllAbsencesAvecEmployeIdEtMotif(employeId,motif);
    }


    @GetMapping("/employe/{employeId}/dateDebutEntre")
    public ResponseEntity<List<AbsenceResponseDto>> allAbsencesAvecDateDebutAvant(@PathVariable String employeId, @RequestParam("date1") LocalDateTime date1, @RequestParam("date2") LocalDateTime date2){
        return  absenceService.getAllAbsencesAvecEmployeIdEtDateDebutEntre(employeId,date1,date2);
    }

    @GetMapping("/all/estJustifiee")
    public ResponseEntity<List<AbsenceResponseDto>> allAbsencesWithEstJustifiee(@RequestParam("estJustifiee")  boolean estJustifiee){
        return  absenceService.getAllAbsencesAvecEstJustifiee(estJustifiee);
    }

    @GetMapping("/all/motif")
    public ResponseEntity<List<AbsenceResponseDto>> allAbsencesWithEstMotif(@RequestParam("motif") Motif motif){
        return  absenceService.getAllAbsencesAvecMotif(motif);
    }




}
