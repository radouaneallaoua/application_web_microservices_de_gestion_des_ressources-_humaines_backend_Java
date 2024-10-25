package com.example.paieservice.web;

import com.example.paieservice.dtos.HeuresSupplementairesRequestDto;
import com.example.paieservice.dtos.HeuresSupplementairesResponseDto;
import com.example.paieservice.services.HeureSuppService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/heures-supp")
public class HeuresSuppController {
    final private HeureSuppService heureSuppService;

    public HeuresSuppController(HeureSuppService heureSuppService) {
        this.heureSuppService = heureSuppService;
    }


    @GetMapping("")
    public ResponseEntity<List<HeuresSupplementairesResponseDto>> allHeuresSupp(){
        return  heureSuppService.toutesHeuresSupp();
    }

    @PostMapping("")
    public ResponseEntity<HeuresSupplementairesResponseDto> ajouterHeuresSupp(@RequestBody HeuresSupplementairesRequestDto heuresSupplementairesRequestDto){
       return  heureSuppService.ajouterHeuresSupp(heuresSupplementairesRequestDto);
    }

    @PutMapping("/{heureSuppId}")
    public ResponseEntity<HeuresSupplementairesResponseDto> modifierHeuresSupp(@PathVariable Long heureSuppId,@RequestBody HeuresSupplementairesRequestDto heuresSupplementairesRequestDto){
        return heureSuppService.modifierHeuresSupp(heureSuppId,heuresSupplementairesRequestDto);
    }

    @DeleteMapping("/{heureSuppId}")
    public ResponseEntity<String> supprimerHeuresSupp(@PathVariable Long  heureSuppId){
        return  heureSuppService.supprimerHeuresSupp(heureSuppId);
    }

    @GetMapping("/{heureSuppId}")
    public ResponseEntity<HeuresSupplementairesResponseDto> getHeuresSuppById(@PathVariable Long heureSuppId){
        return  heureSuppService.getHeuresSuppById(heureSuppId);
    }

    @GetMapping("/employe/{employeId}")
    public ResponseEntity<List<HeuresSupplementairesResponseDto>> allHeuresSuppWithEmployeId(@PathVariable String employeId){
        return heureSuppService.toutesHeuresSuppAvecEmployeId(employeId);
    }

    @GetMapping("/employe/{employeId}/date")
    public ResponseEntity<List<HeuresSupplementairesResponseDto>> allHeuresSuppWithEmployeIdAndDate(@PathVariable String employeId, @RequestParam("date") LocalDate date){
        return  heureSuppService.toutesHeuresSuppAvecEmployeIdEtDate(employeId,date);
    }




}
