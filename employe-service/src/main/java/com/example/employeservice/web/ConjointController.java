package com.example.employeservice.web;

import com.example.employeservice.Dtos.ConjointRequestDto;

import com.example.employeservice.Dtos.ConjointResponsetDto;
import com.example.employeservice.services.ConjointService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conjoints")
public class ConjointController {
    final  private ConjointService conjointService;

    public ConjointController(ConjointService conjointService) {
        this.conjointService = conjointService;
    }

    @GetMapping("")
    public ResponseEntity<List<ConjointResponsetDto>> getAllConjoints(){
        return  conjointService.getAllConjoints();
    }


    @PutMapping("/{conjointId}")
    public ResponseEntity<ConjointResponsetDto> modifierConjoint(@PathVariable Long conjointId,@RequestBody ConjointRequestDto conjointRequestDto){
        return  conjointService.updateEmployeConjoint(conjointId,conjointRequestDto);
    }

    @PostMapping("")
    public ResponseEntity<ConjointResponsetDto> ajouterConjoint(@RequestBody ConjointRequestDto conjointRequestDto){
        return  conjointService.addEmployeConjoint(conjointRequestDto);
    }

    @DeleteMapping("/{conjointId}")
    public ResponseEntity<String> deleteConjointById(@PathVariable Long conjointId){
        return  conjointService.deleteConjointById(conjointId);
    }



}
