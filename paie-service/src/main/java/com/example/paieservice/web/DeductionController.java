package com.example.paieservice.web;

import com.example.paieservice.dtos.DeductionRequestDto;
import com.example.paieservice.dtos.DeductionResponseDto;
import com.example.paieservice.enums.TypeDeduction;
import com.example.paieservice.services.DeductionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/deductions")
public class DeductionController {
    final private DeductionService deductionService;

    public DeductionController(DeductionService deductionService) {
        this.deductionService = deductionService;
    }

    @GetMapping("")
    public ResponseEntity<List<DeductionResponseDto>> allDeductions(){
        return  deductionService.allDeductions();
    }

    @PostMapping("")
    public ResponseEntity<DeductionResponseDto> ajouterDeduction(@RequestBody DeductionRequestDto deductionRequestDto){
       return  deductionService.ajouterDeduction(deductionRequestDto);
    }

    @PutMapping("/{deductionId}")
    public ResponseEntity<DeductionResponseDto> modifierDeduction(@PathVariable Long deductionId,@ModelAttribute DeductionRequestDto deductionRequestDto){
        return  deductionService.modifierDeduction(deductionId,deductionRequestDto);
    }

    @DeleteMapping("/{deductionId}")
    public ResponseEntity<String> supprimerDeduction(@PathVariable Long  deductionId){
        return  deductionService.deleteDeductionWithId(deductionId);
    }

    @GetMapping("/{deductionId}")
    public ResponseEntity<DeductionResponseDto> getDeductionById(@PathVariable Long deductionId){
        return  deductionService.getDeductionById(deductionId);
    }
    @GetMapping("/employe-deductions/{employeId}")
    public ResponseEntity<List<DeductionResponseDto>> allDeductionsWithEmployeId(@PathVariable String employeId){
        return  deductionService.toutDeductionsAvecEmployeId(employeId);
    }

    @GetMapping("/employe-deductions/{employeId}/type-deduction")
    public ResponseEntity<List<DeductionResponseDto>> allDeductionsWithEmployeIdAndTypeDeduction(@PathVariable String employeId, @RequestParam("type deduction") TypeDeduction typeDeduction){
        return  deductionService.toutDeductionsAvecEmployeIdEtTypeDeduction(employeId,typeDeduction);
    }

    @GetMapping("/employe-deductions/{employeId}/date-fin-avant")
    public ResponseEntity<List<DeductionResponseDto>> allDeductionsWithEmployeIdAndDateFinBefore(@PathVariable String employeId, @RequestParam("date") LocalDate date){
        return  deductionService.toutDeductionsAvecEmployeIdEtDateFinAvant(employeId,date);
    }

    @GetMapping("/employe-deductions/{employeId}/date-entre")
    public ResponseEntity<List<DeductionResponseDto>> allDeductionsWithEmployeIdAndDateDebutAfterAndDateFinBefore(@PathVariable String employeId, @RequestParam("date debut") LocalDate date1,@RequestParam("date fin") LocalDate date2){
        return  deductionService.toutDeductionsAvecEmployeIdEtDateDebutApresEtDateFinAvant(employeId,date1,date2);
    }


}
