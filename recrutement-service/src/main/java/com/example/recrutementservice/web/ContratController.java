package com.example.recrutementservice.web;

import com.example.recrutementservice.Dtos.ContratRequestDto;
import com.example.recrutementservice.Dtos.ContratResponseDto;
import com.example.recrutementservice.repository.ContratRepository;
import com.example.recrutementservice.services.ContratService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contrats")

public class ContratController {
    final  private ContratService contratService;

    public ContratController(ContratService contratService) {
        this.contratService = contratService;
    }


    @PostMapping("")
    public ResponseEntity<ContratResponseDto> addContrat(@RequestBody ContratRequestDto contratRequestDto){
        return contratService.addContrat(contratRequestDto);
    }

    @GetMapping("")
    public ResponseEntity<List<ContratResponseDto>> getAllContrats(){
        return contratService.getAllContrats();
    }

    @PutMapping("/{contratId}")
    public ResponseEntity<ContratResponseDto> updateContrat(@PathVariable Long contratId,@RequestBody ContratRequestDto contratRequestDto){
        return contratService.updateContrat(contratId, contratRequestDto);
    }

    @DeleteMapping("/{contratId}")
    public ResponseEntity<String> deleteContrat(@PathVariable Long contratId){
        return contratService.deleteContratById(contratId);
    }
}
