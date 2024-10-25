package com.example.recrutementservice.web;

import com.example.recrutementservice.Dtos.OffreRequestDto;
import com.example.recrutementservice.Dtos.OffreResponseDto;
import com.example.recrutementservice.services.OffreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/offres")
public class OffreController {
    private final OffreService offreService;

    public OffreController(OffreService offreService) {
        this.offreService = offreService;
    }

    @GetMapping("")
    public ResponseEntity<List<OffreResponseDto>> getAllOffres(){
        return offreService.getAllOffres();
    }

    @GetMapping("/date-publication-apres")
    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecDatePublicationApres(LocalDate date){
        return offreService.getAllOffresAvecDatePublicationApres(date);
    }

    @GetMapping("/date-publication-avant")
    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecDatePublicationAvant( LocalDate date){
        return offreService.getAllOffresAvecDatePublicationAvant(date);
    }
    @GetMapping("/contrat")
    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecContrat( @RequestParam("contrat") Long contratId){
        return offreService.getAllOffresAvecDateContrat(contratId);
    }

    @GetMapping("/date-publication-entre")
    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecDatePublicationEntre(@RequestParam("date1") LocalDate date1,@RequestParam("date2") LocalDate date2){
        return offreService.getAllOffresAvecDatePublicationEntre(date1,date2);
    }

    @GetMapping("/estExpiree")
    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecEstExpiree(@RequestParam("estExpiree") boolean estExpiree){
        return offreService.getAllOffresAvecEstExpiree(estExpiree);
    }

    @GetMapping("/poste-contient-titre")
    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecPosteContaining(@RequestParam("posteRecherche") String posteRecherche){
        return offreService.getAllOffresAvecPosteContaining(posteRecherche);
    }
    @GetMapping("/recherche-ville-poste-date-condidature-avant-nombre-poste-min")
    public ResponseEntity<List<OffreResponseDto>> getAllOffresRecherche1(@RequestParam("posteRecherche") String posteRecherche,
                                                                                  @RequestParam("villeRecherchee") String villeRecherchee,
                                                                                  @RequestParam("dateCondidatureAvant") LocalDate dateCondidatureAvant,
                                                                                              @RequestParam("nombreDePostes") int nombreDePostesMin
                                                                                  ){
        return offreService.getAllOffresAvecVilleDateLimiteCondidatureAvantEtPosteContientEtNombreDePostesSuperieur(villeRecherchee,dateCondidatureAvant,posteRecherche,nombreDePostesMin);
    }

    @GetMapping("/recherche-ville-poste-date-condidature-avant-nombre-poste-min-type-contrat")
    public ResponseEntity<List<OffreResponseDto>> getAllOffresRecherche10(@RequestParam("posteRecherche") String posteRecherche,
                                                                         @RequestParam("villeRecherchee") String villeRecherchee,
                                                                         @RequestParam("dateCondidatureAvant") LocalDate dateCondidatureAvant,
                                                                         @RequestParam("nombreDePostes") int nombreDePostesMax,
                                                                         @RequestParam("typeContratId") Long contratId


    ){
        return offreService.getAllOffresAvecVilleDateLimiteCondidatureAvantEtPosteContientEtNombreDePostesSuperieurEtTypeContrats(villeRecherchee,dateCondidatureAvant,posteRecherche,nombreDePostesMax,List.of(contratId));
    }

    @GetMapping("/recherche-ville-poste-date-condidature-avant")
    public ResponseEntity<List<OffreResponseDto>> getAllOffresRecherche2(@RequestParam("posteRecherche") String posteRecherche,
                                                                                  @RequestParam("villeRecherchee") String villeRecherchee,
                                                                                  @RequestParam("dateCondidatureAvant") LocalDate dateCondidatureAvant

    ){
        return offreService.getAllOffresAvecVilleDateLimiteCondidatureAvantEtPosteContient(villeRecherchee,dateCondidatureAvant,posteRecherche);
    }
    @GetMapping("/recherche-ville-poste")
    public ResponseEntity<List<OffreResponseDto>> getAllOffresRecherche3(@RequestParam("posteRecherche") String posteRecherche,
                                                                         @RequestParam("villeRecherchee") String villeRecherchee


                                                                         ){
        return offreService.getAllOffresAvecPosteEtVille(villeRecherchee,posteRecherche);
    }
    @GetMapping("/recherche-ville-date-limite-condidature-avant")
    public ResponseEntity<List<OffreResponseDto>> getAllOffresRecherche4(@RequestParam("dateLimiteCondidature") LocalDate dateLimiteCondidature,
                                                                         @RequestParam("villeRecherchee") String villeRecherchee


    ){
        return offreService.getAllOffresAvecVilleDateLimiteCondidatureAvant(villeRecherchee,dateLimiteCondidature);
    }
    @GetMapping("/recherche-poste-date-limite-condidature-avant")
    public ResponseEntity<List<OffreResponseDto>> getAllOffresRecherche5(@RequestParam("dateLimiteCondidature") LocalDate dateLimiteCondidature,
                                                                         @RequestParam("posteRecherche") String posteRecherchee


    ){
        return offreService.getAllOffresAvecPosteDateLimiteCondidatureAvant(posteRecherchee,dateLimiteCondidature);
    }



    @GetMapping("/ville")
    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecVille(@RequestParam("villeRecherchee") String villeRecherchee){
        return offreService.getAllOffresAvecVille(villeRecherchee);
    }

    @GetMapping("/date-limite-condidature-apres")
    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecDateLimiteCondidatureApres(@RequestParam("dateLimiteCondidatureApres") LocalDate date1){
        return offreService.getAllOffresAvecDateLimiteCondidatureApres(date1);
    }

    @GetMapping("/date-limite-condidature-avant")
    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecDateLimiteCondidatureAvant(@RequestParam("dateLimiteCondidatureAvant")LocalDate date2){
        return offreService.getAllOffresAvecDateLimiteCondidatureAvant(date2);
    }

    @GetMapping("/nombre-postes-sup")
    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecNombreDePostesSup(@RequestParam("nombreDePostesSup") int nombre){
        return offreService.getAllOffresAvecNombreDePostesSup(nombre);
    }

    @GetMapping("/nombre-postes-inf")
    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecNombreDePostesInf(@RequestParam("nombreDePostesInf")  int nombre){
        return offreService.getAllOffresAvecNombreDePostesInf(nombre);
    }


    @GetMapping("/{offreId}")
    public ResponseEntity<OffreResponseDto> getOffreById(@PathVariable Long offreId){
        return offreService.getOffreById(offreId);
    }
    @PostMapping("")
    public ResponseEntity<OffreResponseDto> addOffre(@RequestBody OffreRequestDto offreRequestDto){
        return offreService.addOffre(offreRequestDto);
    }

    @PutMapping("/{offreId}")
    public ResponseEntity<OffreResponseDto> updateOffre(@PathVariable Long offreId,@RequestBody OffreRequestDto offreRequestDto,boolean estExpiree){
        return offreService.updateOffre(offreId,offreRequestDto,estExpiree);
    }

    @DeleteMapping("/{offreId}")
    public ResponseEntity<String> deleteOffreById(@PathVariable Long offreId){
        return  offreService.deleteOffreById(offreId);
    }




}
