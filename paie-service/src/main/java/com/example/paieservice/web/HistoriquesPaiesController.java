package com.example.paieservice.web;

import com.example.paieservice.dtos.HistoriqueDesPaiesRequestDto;
import com.example.paieservice.dtos.HistoriqueDesPaiesResponseDto;
import com.example.paieservice.enums.TypePaiement;
import com.example.paieservice.services.HistoriquesPaiesService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/paies")
public class HistoriquesPaiesController {
    final private  HistoriquesPaiesService historiquesPaiesService;

    public HistoriquesPaiesController(HistoriquesPaiesService historiquesPaiesService) {
        this.historiquesPaiesService = historiquesPaiesService;
    }

    @GetMapping("")
    public ResponseEntity<List<HistoriqueDesPaiesResponseDto>> allHistoriques(){
        return historiquesPaiesService.toutHistoriques();
    }
    @GetMapping("/calculer-salaire/{employeId}")
    public ResponseEntity<String> calculerSalaire(@PathVariable String employeId){
        return  historiquesPaiesService.calculerSalaire(employeId);
    }

    @GetMapping("/employe/{employeId}")
    public ResponseEntity<List<HistoriqueDesPaiesResponseDto>> allHistoriquesOfEmployeId(@PathVariable String employeId){
        return historiquesPaiesService.toutHistoriquesAvecEmployeId(employeId);
    }

    @GetMapping("/date-paiement-apres")
    public ResponseEntity<List<HistoriqueDesPaiesResponseDto>> allHistoriquesWithDatePaiementApres(@RequestParam LocalDateTime date){
        return historiquesPaiesService.toutHistoriquesAvecDatePaiementApres(date);
    }

    @GetMapping("/date-paiement-avant")
    public ResponseEntity<List<HistoriqueDesPaiesResponseDto>> toutHistoriquesAvecDatePaiementAvant(@RequestParam LocalDateTime date){
        return historiquesPaiesService.toutHistoriquesAvecDatePaiementAvant(date);
    }

    @GetMapping("/type-paiement")
    public ResponseEntity<List<HistoriqueDesPaiesResponseDto>> toutHistoriquesAvecTypePaiement(@RequestParam TypePaiement typePaiement){
        return historiquesPaiesService.toutHistoriquesAvecTypePaiement(typePaiement);
    }

    @GetMapping("/{paieId}")
    public ResponseEntity<HistoriqueDesPaiesResponseDto> getPaieById(@PathVariable String paieId){
        return historiquesPaiesService.getPaieById(paieId);
    }

    @PostMapping(value = "",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HistoriqueDesPaiesResponseDto> ajouterHistorique(
           @ModelAttribute HistoriqueDesPaiesRequestDto historiqueDesPaiesRequestDto) throws IOException {
        return historiquesPaiesService.ajouterPaie(historiqueDesPaiesRequestDto);
    }

    @PutMapping(value = "/{paieId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HistoriqueDesPaiesResponseDto> modifierHistorique(
            @PathVariable String paieId,
            @ModelAttribute HistoriqueDesPaiesRequestDto historiqueDesPaiesRequestDto) throws IOException {
        return historiquesPaiesService.modifierPaie(paieId,historiqueDesPaiesRequestDto);
    }

    @DeleteMapping(value = "/{paieId}")
    public ResponseEntity<String> supprimerHistorique(@PathVariable String paieId) {
        return historiquesPaiesService.supprimerPaie(paieId);
    }
    @GetMapping(value = "/recu/{paieId}",produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> getRecu(@PathVariable String paieId) throws IOException {
        return historiquesPaiesService.getRecu(paieId);
    }
}
