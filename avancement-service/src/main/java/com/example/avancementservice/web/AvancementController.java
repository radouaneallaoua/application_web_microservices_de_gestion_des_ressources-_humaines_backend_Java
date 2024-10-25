package com.example.avancementservice.web;

import com.example.avancementservice.dtos.AvancementRequestDto;
import com.example.avancementservice.dtos.AvancementResponseDto;
import com.example.avancementservice.enums.MotifAvancement;
import com.example.avancementservice.enums.TypeAvancement;
import com.example.avancementservice.services.AvancementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/avancements")
public class AvancementController {
    final private AvancementService avancementService;

    public AvancementController(AvancementService avancementService) {
        this.avancementService = avancementService;
    }

    @GetMapping("")
    public ResponseEntity<List<AvancementResponseDto>> toutLesAvancements(){
        return avancementService.toutLesAvancements();
    }

    @GetMapping("/{avancementId}")
    public ResponseEntity<AvancementResponseDto> getAvancementById(@PathVariable Long avancementId){
        return avancementService.getAvancementById(avancementId);
    }

    @GetMapping("/employe/{employeId}")
    public ResponseEntity<List<AvancementResponseDto>> toutLesAvancementsAvecEmployeId(@PathVariable String employeId){
        return avancementService.AvancementsAvecEmployeId(employeId);
    }

    @PostMapping("")
    public ResponseEntity<AvancementResponseDto> ajouterAvancement(@RequestBody AvancementRequestDto avancementRequestDto){
        return  avancementService.ajouterAvancement(avancementRequestDto);
    }

    @PutMapping("/{avancementId}")
    public ResponseEntity<AvancementResponseDto> modifierAvancement(@PathVariable Long avancementId,@RequestBody AvancementRequestDto avancementRequestDto){
        return  avancementService.modifierAvancement(avancementId,avancementRequestDto);
    }

    @DeleteMapping("/{avancementId}")
    public ResponseEntity<String> supprimerAvancement(@PathVariable Long avancementId){
        return  avancementService.supprimerAvancement(avancementId);
    }


    @GetMapping("/grade/{gradeId}")
    public ResponseEntity<List<AvancementResponseDto>> toutLesAvancementsAvecGradeId(@PathVariable Long gradeId){
        return avancementService.AvancementsAvecGradeId(gradeId);
    }

    @GetMapping("/poste/{posteId}")
    public ResponseEntity<List<AvancementResponseDto>> toutLesAvancementsAvecPosteId(@PathVariable Long posteId){
        return avancementService.AvancementsAvecPosteId(posteId);
    }

    @GetMapping("/entite/{entiteId}")
    public ResponseEntity<List<AvancementResponseDto>> toutLesAvancementsAvecEntiteId(@PathVariable Long entiteId){
        return avancementService.AvancementsAvecEntiteId(entiteId);
    }

    @GetMapping("/indice/{indiceId}")
    public ResponseEntity<List<AvancementResponseDto>> toutLesAvancementsAvecIndiceId(@PathVariable Long indiceId){
        return avancementService.AvancementsAvecIndiceEchelonId(indiceId);
    }

    @GetMapping("/motifAvancement")
    public ResponseEntity<List<AvancementResponseDto>> toutLesAvancementsAvecMotif(@RequestParam("motif avancement") MotifAvancement motifAvancement){
        return avancementService.AvancementsAvecMotifAvancement(motifAvancement);
    }

    @GetMapping("/typeAvancement")
    public ResponseEntity<List<AvancementResponseDto>> toutLesAvancementsAvecType(@RequestParam("type avancement") TypeAvancement typeAvancement){
        return avancementService.AvancementsAvecTypeAvancement(typeAvancement);
    }

    @GetMapping("/dateAvancementApres")
    public ResponseEntity<List<AvancementResponseDto>> toutLesAvancementsAvecDateAvancementApres(@RequestParam("date") LocalDate date){
        return avancementService.AvancementsAvecDateAvancementApres(date);
    }

    @GetMapping("/dateAvancementAvant")
    public ResponseEntity<List<AvancementResponseDto>> toutLesAvancementsAvecDateAvancementAvant(@RequestParam("date") LocalDate date){
        return avancementService.AvancementsAvecDateAvancementAvant(date);
    }


}
