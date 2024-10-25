package com.example.recrutementservice.web;

import com.example.recrutementservice.Dtos.EntretienRequestDto;
import com.example.recrutementservice.Dtos.EntretienResponseDto;
import com.example.recrutementservice.Dtos.EvaluationRequestDto;
import com.example.recrutementservice.Dtos.EvaluationResponseDto;
import com.example.recrutementservice.enums.EtatEntretien;
import com.example.recrutementservice.services.EntretienEvaluationService;
import com.example.recrutementservice.services.EntretienService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/entretiens")
public class EntretienController {
    final  private EntretienService entretienService;
    final  private EntretienEvaluationService entretienEvaluationService;
    public EntretienController(EntretienService entretienService, EntretienEvaluationService entretienEvaluationService) {
        this.entretienService = entretienService;
        this.entretienEvaluationService = entretienEvaluationService;
    }

    @GetMapping("")
    ResponseEntity<List<EntretienResponseDto>> getAllEntretiens(){
        return entretienService.allEntretiens();
    }

    @GetMapping("/{condidatId}")
    ResponseEntity<List<EntretienResponseDto>> getAllEntretiensAvecCondidatId(@PathVariable Long condidatId){
        return entretienService.allEntretiensForCondidatId(condidatId);
    }

    @GetMapping("/etatEntretien")
    ResponseEntity<List<EntretienResponseDto>> getAllEntretiensAvecCondidatId(@RequestParam("etatEntretien")EtatEntretien etatEntretien){
        return entretienService.allEntretiensAvecEtatEntretien(etatEntretien);
    }

    @GetMapping("/date-entretien-apres")
    ResponseEntity<List<EntretienResponseDto>> allEntretiensAvecDateEntretienApres(@RequestBody LocalDateTime date){
        return entretienService.allEntretiensAvecDateEntretienApres(date);
    }

    @GetMapping("/date-entretien-avant")
    ResponseEntity<List<EntretienResponseDto>> allEntretiensAvecDateEntretienAvant(@RequestBody LocalDateTime date){
        return entretienService.allEntretiensAvecDateEntretienAvant(date);
    }

    @GetMapping("/date-entretien-entre")
    ResponseEntity<List<EntretienResponseDto>> allEntretiensAvecDateEntretienEntre(@RequestParam("date1") LocalDateTime date1,@RequestParam("date2") LocalDateTime date2){
        return entretienService.allEntretiensAvecDateEntretienEntre(date1, date2);
    }

    @PostMapping("")
    ResponseEntity<EntretienResponseDto> addEntretien(@RequestBody EntretienRequestDto entretienRequestDto){
        return entretienService.addEntretien(entretienRequestDto);
    }

    @PutMapping("/{entretienId}")
    public ResponseEntity<EntretienResponseDto> updateEntretien(@PathVariable Long entretienId,@RequestBody EntretienRequestDto entretienRequestDto, EtatEntretien etatEntretien){
        return entretienService.updateEntretien(entretienId,entretienRequestDto,etatEntretien);
    }

    @DeleteMapping("/{entretienId}")
    public ResponseEntity<String> deleteEntretien(@PathVariable Long entretienId){
        return entretienService.deleteEntretienById(entretienId);
    }

    @DeleteMapping("/entretien-evaluation/{entretienEvaluationId}")
    public ResponseEntity<String> deleteEntretienEvaluation(@PathVariable Long entretienEvaluationId){
        return entretienEvaluationService.deleteEntretienEvaluationById(entretienEvaluationId);
    }
    @PutMapping("/entretien-evaluation/{entretienEvaluationId}")
    public ResponseEntity<EvaluationResponseDto> updateEntretienEvaluation(@PathVariable Long entretienEvaluationId, @RequestBody EvaluationRequestDto evaluationRequestDto){
        return entretienEvaluationService.updateEntretienEvaluation(entretienEvaluationId,evaluationRequestDto);
    }

    @PostMapping("/entretien-evaluation")
    public ResponseEntity<EvaluationResponseDto> addEntretienEvaluation(@RequestBody EvaluationRequestDto evaluationRequestDto){
        return entretienEvaluationService.addEntretienEvaluation(evaluationRequestDto);
    }

    @GetMapping("/entretien-evaluation-sup")
    public ResponseEntity<List<EvaluationResponseDto>> allEntretiensAvecScoreSup(@RequestParam("score") int score){
        return entretienEvaluationService.allEntretiensAvecScoreSup(score);
    }

    @GetMapping("/entretien-evaluation")
    public ResponseEntity<List<EvaluationResponseDto>> allEntretiensEvaluations(){
        return entretienEvaluationService.allEntretiensEvaluations();
    }

    @GetMapping("/entretien-evaluation/{entretienEvaluationId}")
    public ResponseEntity<EvaluationResponseDto> getEvaluationForEntretienById(@PathVariable Long entretienEvaluationId){
        return entretienEvaluationService.getEvaluationForEntretienById(entretienEvaluationId);
    }

    @GetMapping("/entretien-evaluation-inf")
    public ResponseEntity<List<EvaluationResponseDto>> allEntretiensAvecScoreInf(@RequestParam("score") int score){
        return entretienEvaluationService.allEntretiensAvecScoreInf(score);
    }


}
