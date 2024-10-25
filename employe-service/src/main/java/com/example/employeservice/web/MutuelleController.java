package com.example.employeservice.web;


import com.example.employeservice.Dtos.MutuelleRequestDto;
import com.example.employeservice.Dtos.MutuelleResponseDto;
import com.example.employeservice.services.MutuelleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mutuelles")
public class MutuelleController {
    private final MutuelleService mutuelleService;

    public MutuelleController(MutuelleService mutuelleService) {
        this.mutuelleService = mutuelleService;
    }


    @GetMapping("")
    public ResponseEntity<List<MutuelleResponseDto>> allMutuelles(){
        return mutuelleService.getAllMutuelles();
    }


    @PostMapping("")
    public ResponseEntity<MutuelleResponseDto> addMutuelle(String label){
        MutuelleRequestDto mutuelleRequestDto=MutuelleRequestDto.builder()
                .label(label)
                .build();
        return  mutuelleService.addMutuelle(mutuelleRequestDto);
    }

    @GetMapping("/{mutuelleId}")
    public ResponseEntity<MutuelleResponseDto> getMutuelleById(@PathVariable Long mutuelleId){
        return  mutuelleService.getMutuelleById(mutuelleId);
    }

    @DeleteMapping("/{mutuelleId}")
    public ResponseEntity<String> deleteMutuelleById(@PathVariable Long mutuelleId){
        return  mutuelleService.deleteMutuelleById(mutuelleId);
    }

    @PutMapping("/{mutuelleId}")
    public ResponseEntity<MutuelleResponseDto> updateMutuelle(@PathVariable Long mutuelleId,String label){
        MutuelleRequestDto mutuelleRequestDto=MutuelleRequestDto.builder()
                .label(label)
                .build();
        return  mutuelleService.updateMutuelle(mutuelleId,mutuelleRequestDto);
    }

}

