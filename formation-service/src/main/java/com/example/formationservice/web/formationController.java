package com.example.formationservice.web;

import com.example.formationservice.dtos.*;

import com.example.formationservice.entities.EmployeFormationKey;
import com.example.formationservice.entities.EntiteFormationKey;
import com.example.formationservice.service.EmployeFormationService;
import com.example.formationservice.service.EntiteFormationService;
import com.example.formationservice.service.FormationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/formations")
public class formationController {
    final private FormationService formationService;
    final private EmployeFormationService employeFormationService;
    private final EntiteFormationService entiteFormationService;

    public formationController(FormationService formationService, EmployeFormationService employeFormationService,EntiteFormationService entiteFormationService1) {
        this.formationService = formationService;
        this.employeFormationService = employeFormationService;
        this.entiteFormationService = entiteFormationService1;
    }

    @GetMapping("")
    public ResponseEntity<List<FormationResponseDto>> allFormations(){
        return formationService.allFormations();
    }

    @PostMapping("/employe-formation")
    public ResponseEntity<EmployeFormationResponseDto> addEmployeFormation(@RequestBody EmployeFormationRequestDto employeFormationRequestDto){
        return employeFormationService.addEmployeFormation(employeFormationRequestDto);
    }

    @PostMapping("/employe-formation/add-evaluation/{employeId}/{formationId}")
    public ResponseEntity<EmployeFormationResponseDto> addEmployeFormationEvaluation(@PathVariable String employeId,@PathVariable Long formationId,@RequestParam(name = "evaluation") int evaluation){
        return employeFormationService.addEmployeFormationEvaluation(employeId, formationId, evaluation);
    }

    @PutMapping("/employe-formation/add-evaluation/{employeId}/{formationId}")
    public ResponseEntity<EmployeFormationResponseDto> updateEmployeFormationEvaluation(@PathVariable String employeId,@PathVariable Long formationId,@RequestParam(name = "evaluation") int evaluation){
        return employeFormationService.updateEmployeFormationEvaluation(employeId, formationId, evaluation);
    }

    @PostMapping("/add-all-entites-to-formation")
    public ResponseEntity<?> addAllEntitesToFormation(@RequestBody FormationRequestDto formationRequestDto){
        return entiteFormationService.addAllEntitesToFormation(formationRequestDto);
    }

    @PostMapping("/add-list-employe-to-formation")
    public ResponseEntity<String> addListEmployeToFormation(@RequestBody EmployeFormationRequestDto employeFormationRequestDto,@RequestBody List<String> employeIds ){
        return employeFormationService.addListEmployeToFormation(employeFormationRequestDto,employeIds);
    }


    @GetMapping("/employe-formations")
    public ResponseEntity<List<EmployeFormationResponseDto>> allEmployesFormations(){
        return employeFormationService.allEmployeFormations();
    }


    @GetMapping("/employe-formations/{formationId}")
    public ResponseEntity<List<EmployeFormationResponseDto>> allEmployesFormationsAvecFormationId(@PathVariable Long formationId){
        return employeFormationService.allAvecFormationId(formationId);
    }

    @GetMapping("/evaluation-sup")
    public ResponseEntity<List<EmployeFormationResponseDto>> allEmployesFormationsAvecEvalSup(@RequestParam int evaluation){
        return employeFormationService.allEmployeFormationAvecEvaluationSuperieure(evaluation);
    }

    @GetMapping("/evaluation-ing")
    public ResponseEntity<List<EmployeFormationResponseDto>> allEmployesFormationsAvecEvalInf(@RequestParam int evaluation){
        return employeFormationService.allEmployeFormationAvecEvaluationInferieure(evaluation);
    }



    @GetMapping("/competence/{competenceId}")
    public ResponseEntity<List<FormationResponseDto>> allFormationsAvecCompetence(@PathVariable Long competenceId){
        return formationService.allFormationsAvecCompetence(competenceId);
    }



    @GetMapping("/dateDebutAvant")
    public ResponseEntity<List<FormationResponseDto>> allFormationsAvecDateDebutAvant(@RequestParam LocalDateTime date){
        return formationService.allFormationsAvecDateDebutAvant(date);
    }

    @GetMapping("/dateDebutApres")
    public ResponseEntity<List<FormationResponseDto>> allFormationsAvecDateDebutApres(@RequestParam LocalDateTime date){
        return formationService.allFormationsAvecDateDebutApres(date);
    }

    @GetMapping("/dateFinAvant")
    public ResponseEntity<List<FormationResponseDto>> allFormationsAvecDateFinAvant(@RequestParam LocalDateTime date){
        return formationService.allFormationsAvecDateFinAvant(date);
    }

    @GetMapping("/dateFinApres")
    public ResponseEntity<List<FormationResponseDto>> allFormationsAvecDateFinApres(@RequestParam LocalDateTime date){
        return formationService.allFormationsAvecDateFinApres(date);
    }

    @GetMapping("/avecDureeSuperieure")
    public ResponseEntity<List<FormationResponseDto>> allFormationsAvecDureeSup(@RequestParam int duree){
        return formationService.allFormationsAvecDureeSup(duree);
    }

    @GetMapping("/avecDureeInferieure")
    public ResponseEntity<List<FormationResponseDto>> allFormationsAvecDureeInf(@RequestParam int duree){
        return formationService.allFormationsAvecDureeInf(duree);
    }

    @GetMapping("/avecDureeEntre")
    public ResponseEntity<List<FormationResponseDto>> allFormationsAvecDureeEntre(@RequestParam("duree1") int duree1,@RequestParam("duree2") int duree2){
        return formationService.allFormationsAvecDureeEntre(duree1,duree2);
    }

   @PostMapping("")
   public ResponseEntity<FormationResponseDto> addFormation(@RequestBody FormationRequestDto formationRequestDto){
       return formationService.addFormation(formationRequestDto);
   }


    @GetMapping("/{formationId}")
    public ResponseEntity<FormationResponseDto> getFormationById(@PathVariable Long formationId){
        return formationService.getFormationById(formationId);
    }

    @PostMapping("/entite-formation")
    public ResponseEntity<EntiteFormationResponseDto> addEntiteFormation(@RequestBody EntiteFormationRequestDto entiteFormationRequestDto){
        return  entiteFormationService.addEntiteFormation(entiteFormationRequestDto);
    }

    @PutMapping("/entite-formation/{formationId}/{entiteId}")
    public ResponseEntity<EntiteFormationResponseDto> updateEntiteFormation(@PathVariable Long formationId,@PathVariable Long entiteId,@RequestBody EntiteFormationRequestDto entiteFormationRequestDto){
        return  entiteFormationService.updateEntiteFormation(EntiteFormationKey.builder().entiteId(entiteId).formationId(formationId).build(), entiteFormationRequestDto);
    }

    @DeleteMapping("/entite-formation/{formationId}/{entiteId}")
    public ResponseEntity<String> deleteEntiteFormation(@PathVariable Long formationId,@PathVariable Long entiteId){
        return  entiteFormationService.deleteEntiteFormationById(EntiteFormationKey.builder().entiteId(entiteId).formationId(formationId).build());
    }

    @GetMapping("/entite-formation/{formationId}/date-ajout-entites-avant")
    public ResponseEntity<List<EntiteFormationResponseDto>> allEntiteFormationAvecFormationIdDateAjoutBefore(@PathVariable Long formationId,@RequestParam LocalDateTime date){
        return  entiteFormationService.allEntiteFormationAvecFormationIdDateAjoutBefore(formationId,date);
    }

    @GetMapping("/entite-formation/{formationId}/date-ajout-entites-apres")
    public ResponseEntity<List<EntiteFormationResponseDto>> allEntiteFormationAvecFormationIdDateAjoutAfter(@PathVariable Long formationId,@RequestParam LocalDateTime date){
        return  entiteFormationService.allEntiteFormationAvecFormationIdDateAjoutAfter(formationId,date);
    }

    @GetMapping("/entite-formation/{formationId}")
    public ResponseEntity<List<EntiteFormationResponseDto>> allAvecFormationId(@PathVariable Long formationId){
        return  entiteFormationService.allAvecFormationId(formationId);
    }




    @GetMapping("/entite-formation/")
    public ResponseEntity<List<EntiteFormationResponseDto>> allEntiteFormations(){
        return  entiteFormationService.allEntiteFormations();
    }


    @PutMapping("/{formationId}")
    public ResponseEntity<FormationResponseDto> updateFormation(@PathVariable Long formationId,@RequestBody FormationRequestDto formationRequestDto){
        return formationService.updateFormation(formationId,formationRequestDto);
    }

    @DeleteMapping("/{formationId}")
    public ResponseEntity<String> deleteFormation(@PathVariable Long formationId){
         return formationService.deleteFormationById(formationId);
    }

    @DeleteMapping("/employe-formation/{employeId}/{formationId}")
    public void deleteEmployeFormation(@PathVariable String employeId, @PathVariable Long formationId){
        EmployeFormationKey employeFormationKey=EmployeFormationKey.builder()
                .employeId(employeId)
                .formationId(formationId)
                .build();
        employeFormationService.deleteEmployeFormationById(employeFormationKey);
    }







}
