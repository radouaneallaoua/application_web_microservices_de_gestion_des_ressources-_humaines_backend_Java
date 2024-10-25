package com.example.formationservice.web;

import com.example.formationservice.dtos.CompetenceRequestDto;
import com.example.formationservice.dtos.CompetenceResponseDto;
import com.example.formationservice.service.CompetenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competences")
public class CompetenceController {
   final private CompetenceService competenceService;

    public CompetenceController(CompetenceService competenceService) {
        this.competenceService = competenceService;
    }

    @GetMapping("")
    public ResponseEntity<List<CompetenceResponseDto>> allCompetences(){
        return  competenceService.allCompetences();
    }


    @PostMapping("")
    public ResponseEntity<CompetenceResponseDto> addCompetence(@ModelAttribute CompetenceRequestDto competenceRequestDto){
        return  competenceService.addCompetence(competenceRequestDto);
    }

    @GetMapping("/{competenceId}")
    public ResponseEntity<CompetenceResponseDto> getCompetenceById(@PathVariable Long  competenceId){
        return  competenceService.getCompetenceById(competenceId);
    }

    @PutMapping("/{competenceId}")
    public ResponseEntity<CompetenceResponseDto> updateCompetence(@PathVariable Long competenceId,@ModelAttribute CompetenceRequestDto competenceRequestDto){
        return  competenceService.updateCompetence(competenceId,competenceRequestDto);
    }

    @DeleteMapping("/{competenceId}")
    public void deleteCompetenceById(@PathVariable Long competenceId){
          competenceService.deleteCompetenceById(competenceId);
    }

}
