package com.example.employeservice.web;
import com.example.employeservice.Dtos.ConjointRequestDto;
import com.example.employeservice.Dtos.ConjointResponsetDto;
import com.example.employeservice.Dtos.EmployeRequestDto;
import com.example.employeservice.Dtos.EmployeResponseDto;

import com.example.employeservice.Models.CompteModel;
import com.example.employeservice.enums.EtatEmploye;

import com.example.employeservice.enums.SituationFamiliale;
import com.example.employeservice.services.ConjointService;
import com.example.employeservice.services.EmployeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employes")
public class EmployeController {
    private final EmployeService employeService;
    private final ConjointService conjointService;

    public EmployeController(EmployeService employeService,ConjointService conjointService) {
        this.employeService = employeService;
        this.conjointService = conjointService;
    }

    @GetMapping("")
    public ResponseEntity<List<EmployeResponseDto>> getAllEmployes(){
        return  employeService.getAllEmploye();
    }

    @GetMapping("/email/{email}")
    public String getEmployeIdByEmail(@PathVariable String email){
        return  employeService.getEmployeIdWithEmail(email);
    }
    @PutMapping("/{employeId}/setCompte")
    public ResponseEntity<String> setCompte(@PathVariable String employeId,@RequestBody CompteModel compteModel){
        return employeService.setCompte(employeId,compteModel);
    }

    @GetMapping("/{employeId}")
    public EmployeResponseDto getEmployeById(@PathVariable String employeId){
        return  employeService.getEmployeById(employeId);
    }

    @DeleteMapping("/{employeId}")
    public ResponseEntity<String> deleteEmployeById(@PathVariable String employeId){
        return  employeService.deleteEmployeById(employeId);
    }

    @PostMapping(value = "",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EmployeResponseDto> saveEmploye(@ModelAttribute EmployeRequestDto employeRequestDto) throws IOException {
        return  employeService.saveEmploye(employeRequestDto);
    }

    @PutMapping(value = "/{employeId}/update-profile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EmployeResponseDto> updateProfile(@PathVariable String employeId, @RequestParam(name = "email",required = false)String email, @RequestParam(name = "tel",required = false)String tel, @RequestParam(name = "adresse",required = false)String adresse, @RequestParam(name = "regionId",required = false)Long regionId, @RequestParam(name = "situationFamiliale",required = false) SituationFamiliale situationFamiliale, @RequestParam(name = "image",required = false) MultipartFile image) throws IOException {
        return employeService.updateEmployeWithImageAndEmailAndTelAndAdresseAndRegionAndSituationFamiliale(employeId, email, tel, adresse, regionId, situationFamiliale, image);
    }

    @PutMapping(value="/{employeId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EmployeResponseDto> updateEmploye(@PathVariable String employeId,@ModelAttribute EmployeRequestDto employeRequestDto) throws IOException {
        return  employeService.updateEmploye(employeId,employeRequestDto);
    }

    @PutMapping("/{employeId}/update-from-avancement")
    public ResponseEntity<EmployeResponseDto> updateEmployeWithGradeIdAndIndiceIdAndEntiteIdAndPosteId(@PathVariable  String employeId,
                                                                                                       @RequestParam(name = "nouveauGradeId") Long gradeId,
                                                                                                       @RequestParam(name = "nouvelIndiceEchelonId") Long indiceEchelonId,
                                                                                                       @RequestParam(name = "nouvelleEntiteId") Long entiteId,
                                                                                                       @RequestParam(name = "nouveauPosteId") Long posteId){
        return  employeService.updateEmployeWithGradeIdAndIndiceIdAndEntiteIdAndPosteId(employeId,gradeId,indiceEchelonId,entiteId,posteId);
    }

    @GetMapping("/all/etat/{etat}")
    public ResponseEntity<List<EmployeResponseDto>> getAllEmployesWithEtat(@PathVariable EtatEmploye etat){
        return  employeService.getEmployesWithStatus(etat);
    }
    @GetMapping("all/filtrerByNom")
    public ResponseEntity<List<EmployeResponseDto>> getAllEmployesWithNom(@RequestBody String nom){
        return  employeService.getAllEmployesWithNom(nom);
    }
    @GetMapping("all/filterByFirstName")
    public ResponseEntity<List<EmployeResponseDto>> getAllEmployesWithPrenom(@RequestBody String prenom){
        return  employeService.getAllEmployesWithPrenom(prenom);
    }
    @GetMapping("all/withGrade/{gradeId}")
    public ResponseEntity<List<EmployeResponseDto>> getAllEmployesWithGrade(@PathVariable Long gradeId){
        return  employeService.getEmployesWithGradeId(gradeId);
    }
    @GetMapping("all/withPoste/{posteId}")
    public ResponseEntity<List<EmployeResponseDto>> getAllEmployesWithPoste(@PathVariable Long posteId) {
        return employeService.getEmployesWithPosteId(posteId);
    }
    @GetMapping("all/dateRecrutement")
    public ResponseEntity<List<EmployeResponseDto>> getAllEmployesWithDateRecrutement(@RequestBody LocalDate dateRecrutement) {
        return employeService.getAllEmployesWithDateRecrutement(dateRecrutement);
    }

    @GetMapping("all/dateRecrutementApres")
    public ResponseEntity<List<EmployeResponseDto>> getAllEmployesWithDateRecrutementAfter(@RequestBody LocalDate dateRecrutement) {
        return employeService.getAllEmployesWithDateRecrutementAfter(dateRecrutement);
    }
    @GetMapping("all/dateRecrutementAvant")
    public ResponseEntity<List<EmployeResponseDto>> getAllEmployesWithDateRecrutementBefore(@RequestBody LocalDate dateRecrutement) {
        return employeService.getAllEmployesWithDateRecrutementBefore(dateRecrutement);
    }

    @GetMapping("all/dateRecrutementEntre")
    public ResponseEntity<List<EmployeResponseDto>> getAllEmployesWithDateRecrutementBetween(@RequestParam("date1") LocalDate date1, @RequestParam("date2") LocalDate date2) {
        return employeService.getAllEmployesWithDateRecrutementBetween(date1,date2);
    }

    @GetMapping("{employeId}/conjoints")
    public ResponseEntity<List<ConjointResponsetDto>> getEmployeConjoints(@PathVariable String employeId){
        return  conjointService.getAllConjointDeEmploye(employeId);
    }

    @PostMapping("/add-conjoint")
    public ResponseEntity<ConjointResponsetDto> addEmployeConjoint(@ModelAttribute ConjointRequestDto conjointRequestDto
   ){
        return conjointService.addEmployeConjoint(conjointRequestDto);
    }

    @GetMapping(value = "/image-profile/{employeId}",produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> getEmployeImageProfile(@PathVariable String employeId) throws IOException {
        return employeService.getImageProfile(employeId);
    }



}
