package com.example.paieservice.web;

import com.example.paieservice.dtos.PrimeRequestDto;
import com.example.paieservice.dtos.PrimeResponseDto;
import com.example.paieservice.enums.TypePrime;
import com.example.paieservice.services.PrimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/primes")
public class PrimeController {
    final private PrimeService primeService;

    public PrimeController(PrimeService primeService) {
        this.primeService = primeService;
    }

    @GetMapping("")
    public ResponseEntity<List<PrimeResponseDto>> allPrimes(){
        return  primeService.allPrimes();
    }

    @PostMapping("")
    public ResponseEntity<PrimeResponseDto> ajouterPrime(@RequestBody PrimeRequestDto primeRequestDto){
       return  primeService.ajouterPrime(primeRequestDto);
    }

    @PutMapping("/{primeId}")
    public ResponseEntity<PrimeResponseDto> modifierPrime(@PathVariable Long primeId,@RequestBody PrimeRequestDto primeRequestDto){
        return  primeService.modifierPrime(primeId,primeRequestDto);
    }

    @DeleteMapping("/{primeId}")
    public ResponseEntity<String> supprimerPrime(@PathVariable Long  primeId){
        return  primeService.supprimerPrimeWithId(primeId);
    }

    @GetMapping("/{primeId}")
    public ResponseEntity<PrimeResponseDto> getPrimeById(@PathVariable Long primeId){
        return  primeService.getPrimeById(primeId);
    }
    @GetMapping("/employe-primes/{employeId}")
    public ResponseEntity<List<PrimeResponseDto>> allPrimesWithEmployeId(@PathVariable String employeId){
        return  primeService.toutPrimesAvecEmployeId(employeId);
    }

    @GetMapping("/employe-primes/{employeId}/type-prime")
    public ResponseEntity<List<PrimeResponseDto>> allPrimesWithEmployeIdAndTypePrime(@PathVariable String employeId, @RequestParam("type prime") TypePrime typePrime){
        return  primeService.toutPrimesAvecEmployeIdEtTypePrime(employeId,typePrime);
    }

    @GetMapping("/employe-primes/{employeId}/date-fin-avant")
    public ResponseEntity<List<PrimeResponseDto>> allPrimesWithEmployeIdAndDateFinBefore(@PathVariable String employeId, @RequestParam("date") LocalDate date){
        return  primeService.toutPrimesAvecEmployeIdEtDateFinAvant(employeId,date);
    }

    @GetMapping("/employe-primes/{employeId}/date-entre")
    public ResponseEntity<List<PrimeResponseDto>> allPrimesWithEmployeIdAndDateDebutAfterAndDateFinBefore(@PathVariable String employeId, @RequestParam("date debut") LocalDate date1,@RequestParam("date fin") LocalDate date2){
        return  primeService.toutPrimesAvecEmployeIdEtDateDebutApresEtDateFinAvant(employeId,date1,date2);
    }


}
