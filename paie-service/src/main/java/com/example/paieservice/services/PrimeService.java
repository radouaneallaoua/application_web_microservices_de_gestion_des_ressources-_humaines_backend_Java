package com.example.paieservice.services;

import com.example.paieservice.clients.EmployeRestClient;
import com.example.paieservice.dtos.PrimeRequestDto;
import com.example.paieservice.dtos.PrimeResponseDto;
import com.example.paieservice.entities.Prime;
import com.example.paieservice.enums.TypePrime;
import com.example.paieservice.exception.PrimeNotFoundException;
import com.example.paieservice.repository.PrimeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PrimeService {
    final private PrimeRepository primeRepository;
    final  private EmployeRestClient employeRestClient;
    public PrimeService(PrimeRepository primeRepository, EmployeRestClient employeRestClient) {
        this.primeRepository = primeRepository;
        this.employeRestClient = employeRestClient;
    }

    public ResponseEntity<PrimeResponseDto> ajouterPrime(PrimeRequestDto primeRequestDto){
        Prime prime=Prime.builder()
                .typePrime(primeRequestDto.getTypePrime())
                .dateDebut(primeRequestDto.getDateDebut())
                .dateFin(primeRequestDto.getDateFin())
                .montant(primeRequestDto.getMontant())
                .description(primeRequestDto.getDescription())
                .employeId(primeRequestDto.getEmployeId())
                .employe(employeRestClient.findEmployeById(primeRequestDto.getEmployeId()))
                .build();
        Prime savedPrime=primeRepository.save(prime);
        return  ResponseEntity.ok(savedPrime.toDto());

    }

    public ResponseEntity<PrimeResponseDto> modifierPrime(Long primeId,PrimeRequestDto primeRequestDto){
        Prime foundPrime=primeRepository.findById(primeId).orElseThrow(()-> new PrimeNotFoundException("prime not found with id"+primeId));
        foundPrime.setTypePrime(primeRequestDto.getTypePrime());
        foundPrime.setDateDebut(primeRequestDto.getDateDebut());
        foundPrime.setMontant(primeRequestDto.getMontant());
        foundPrime.setDescription(primeRequestDto.getDescription());
        foundPrime.setEmployeId(primeRequestDto.getEmployeId());
        foundPrime.setEmploye(employeRestClient.findEmployeById(primeRequestDto.getEmployeId()));
        Prime savedPrime=primeRepository.save(foundPrime);
        return  ResponseEntity.ok(savedPrime.toDto());

    }

    public ResponseEntity<PrimeResponseDto> getPrimeById(Long primeId){
        Prime prime=primeRepository.findById(primeId).orElseThrow(()-> new PrimeNotFoundException("prime nout found exception"));
        prime.setEmploye(employeRestClient.findEmployeById(prime.getEmployeId()));
        return  ResponseEntity.ok(prime.toDto());
    }

    public ResponseEntity<String> supprimerPrimeWithId(Long primeId){
        primeRepository.deleteById(primeId);
        return  ResponseEntity.ok("prime supprime avec id"+primeId);
    }

    public ResponseEntity<List<PrimeResponseDto>> toutPrimesAvecEmployeIdEtTypePrime(String employeId, TypePrime typePrime){
        List<Prime> primes=primeRepository.findAllByEmployeIdAndTypePrime(employeId, typePrime);
        List<PrimeResponseDto> dtos=new ArrayList<>();
        if(primes!=null){
            primes.forEach(p->{
                p.setEmploye(employeRestClient.findEmployeById(p.getEmployeId()));
                dtos.add(p.toDto());
            });
        }
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<List<PrimeResponseDto>> toutPrimesAvecEmployeId(String employeId){
        List<Prime> primes=primeRepository.findAllByEmployeId(employeId);
        List<PrimeResponseDto> dtos=new ArrayList<>();
        if(primes!=null){
            primes.forEach(p->{
                p.setEmploye(employeRestClient.findEmployeById(p.getEmployeId()));
                dtos.add(p.toDto());
            });
        }
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<List<PrimeResponseDto>> toutPrimesAvecEmployeIdEtDateFinAvant(String employeId, LocalDate date){
        List<Prime> primes=primeRepository.findAllByEmployeIdAndDateFinBefore(employeId, date);
        List<PrimeResponseDto> dtos=new ArrayList<>();
        if(primes!=null){
            primes.forEach(p->{
                p.setEmploye(employeRestClient.findEmployeById(p.getEmployeId()));
                dtos.add(p.toDto());
            });
        }
        return ResponseEntity.ok(dtos);
    }
    public ResponseEntity<List<PrimeResponseDto>> allPrimes(){
        List<Prime> primes=primeRepository.findAll();
        List<PrimeResponseDto> dtos=new ArrayList<>();
        primes.forEach(p -> {
            p.setEmploye(employeRestClient.findEmployeById(p.getEmployeId()));
            dtos.add(p.toDto());
        });
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<List<PrimeResponseDto>> toutPrimesAvecEmployeIdEtDateDebutApresEtDateFinAvant(String employeId,LocalDate date1,LocalDate date2){
        List<Prime> primes=primeRepository.findAllByEmployeIdAndDateDebutAfterAndDateFinBefore(employeId,date1,date2);
        List<PrimeResponseDto> dtos=new ArrayList<>();
        if(primes!=null){
            primes.forEach(p->{
                p.setEmploye(employeRestClient.findEmployeById(p.getEmployeId()));
                dtos.add(p.toDto());
            });
        }
        return ResponseEntity.ok(dtos);
    }


}
