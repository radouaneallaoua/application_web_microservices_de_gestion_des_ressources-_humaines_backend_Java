package com.example.recrutementservice.web;

import com.example.recrutementservice.Dtos.CondidatRequestDto;
import com.example.recrutementservice.Dtos.CondidatResponseDto;
import com.example.recrutementservice.enums.Genre;
import com.example.recrutementservice.services.CondidatService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/condidatures")
public class CondidatController {
    private  final CondidatService condidatService;

    public CondidatController(CondidatService condidatService) {
        this.condidatService = condidatService;
    }

    @GetMapping("")
    public ResponseEntity<List<CondidatResponseDto>> getAllCondidats() {
       return condidatService.getAllCondidats();
    }
    @GetMapping("/{offreId}/ville")
    public ResponseEntity<List<CondidatResponseDto>> getAllCondidatsAvecOffreIdAvecVille(@PathVariable Long offreId, String ville) {
        return condidatService.getAllCondidatsByOffreIdAndVille(offreId,ville);
    }


    @GetMapping("/offre/{offreId}")
    public ResponseEntity<List<CondidatResponseDto>> getAllCondidatsAvecOffreId(@PathVariable Long offreId) {
        return condidatService.getAllCondidatsOfOffreId(offreId);
    }
    @GetMapping("/{offreId}/ville/date-condidature-avant")
    public ResponseEntity<List<CondidatResponseDto>> getAllCondidatsByOffreIdDateCondidatureBeforeAndVille(@PathVariable Long offreId,@RequestParam("date") LocalDateTime date,@RequestParam("ville") String ville){
        return condidatService.getAllCondidatsByOffreIdDateCondidatureBeforeAndVille(offreId,date,ville);
    }

    @GetMapping("/{offreId}/date-condidature-entre")
    public ResponseEntity<List<CondidatResponseDto>> getAllCondidatsWithOffreIdAndDateCondidatureBetween(@PathVariable Long offreId,@RequestParam("date1") LocalDateTime date1,@RequestParam("date2") LocalDateTime date2){
        return condidatService.getAllCondidatsWithOffreIdAndDateCondidatureBetween(offreId,date1,date2);
    }

    @GetMapping("/{offreId}/date-condidature-apres")
    public ResponseEntity<List<CondidatResponseDto>> getAllCondidatsWithOffreIdAndDateCondidatureAfter(@PathVariable Long offreId,@RequestParam() LocalDateTime date){
        return condidatService.getAllCondidatsWithOffreIdAndDateCondidatureAfter(offreId,date);
    }

    @GetMapping("/{offreId}/date-condidature-avant")
    public ResponseEntity<List<CondidatResponseDto>> getAllCondidatsWithOffreIdAndDateCondidatureBefore(@PathVariable Long offreId,@RequestParam() LocalDateTime date){
        return condidatService.getAllCondidatsWithOffreIdAndDateCondidatureBefore(offreId,date);
    }

    @GetMapping("/{condidatureId}")
    public ResponseEntity<CondidatResponseDto> getCondidatureById(@PathVariable Long condidatureId){
        return condidatService.getCondidatById(condidatureId);
    }

    @DeleteMapping("/{condidatureId}")
    public ResponseEntity<String> deleteCondidatureById(@PathVariable Long condidatureId){
        return condidatService.deleteCondidatById(condidatureId);
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CondidatResponseDto> addCondidature( @ModelAttribute CondidatRequestDto condidatRequestDto) throws IOException {
        return  condidatService.addCondidature(condidatRequestDto);
    }

    @GetMapping("/{offreId}/ville/date-condidature-apres")
    public ResponseEntity<List<CondidatResponseDto>> getAllCondidatsByOffreIdDateCondidatureAfterAndVille(@PathVariable Long offreId,@RequestParam("date") LocalDateTime date,@RequestParam("ville") String ville){
        return condidatService.getAllCondidatsByOffreIdDateCondidatureAfterAndVille(offreId,date,ville);
    }
    @GetMapping("/{offreId}/ville/date-condidature-entre")
    public ResponseEntity<List<CondidatResponseDto>> getAllCondidatsByOffreIdDateCondidatureBetweenAndVille(@PathVariable Long offreId,@RequestParam("date1")LocalDateTime date1,@RequestParam("date2") LocalDateTime date2,String ville){
        return condidatService.getAllCondidatsByOffreIdDateCondidatureBetweenAndVille(offreId,date1,date2,ville);
    }

    @GetMapping("/{offreId}/avec-email-condidature")
    public ResponseEntity<?> getAllCondidatsByOffreIdEtEmailCondidat(@PathVariable  Long offreId,@RequestBody String email){
        return condidatService.getAllCondidatsByOffreIdEtEmailCondidat(offreId,email);
    }
    @GetMapping("/{offreId}/genre")
    public ResponseEntity<?> getAllCondidatsByOffreIdEtGenre(@PathVariable  Long offreId, @RequestBody Genre genre){
        return condidatService.getAllCondidatsByOffreIdEtGenre(offreId,genre);
    }
    @GetMapping("/{offreId}/adresse-contient")
    public ResponseEntity<?> getAllCondidatsByOffreIdEtAdresse(@PathVariable  Long offreId, @RequestBody String adresse){
        return condidatService.getAllCondidatsByOffreIdAdresseContaining(offreId,adresse);
    }
    @GetMapping(value = "/CV/{condidatId}",produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> getCondidatCV(@PathVariable Long condidatId) throws IOException {
        return condidatService.getCV(condidatId);
    }

}
