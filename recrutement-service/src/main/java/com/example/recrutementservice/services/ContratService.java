package com.example.recrutementservice.services;
import com.example.recrutementservice.Dtos.ContratRequestDto;
import com.example.recrutementservice.Dtos.ContratResponseDto;
import com.example.recrutementservice.Exception.ContratNotFoundException;
import com.example.recrutementservice.entities.Contrat;
import com.example.recrutementservice.entities.Contrat;
import com.example.recrutementservice.repository.ContratRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ContratService {
    private final ContratRepository contratRepository;

    public ContratService(ContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }

    public ResponseEntity<List<ContratResponseDto>> getAllContrats(){
        List<Contrat> contrats=contratRepository.findAll();
        List<ContratResponseDto> dtoList=new ArrayList<>();
        contrats.forEach(o->{
            dtoList.add(o.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }




    public ResponseEntity<ContratResponseDto> addContrat(ContratRequestDto contratRequestDto){
        Contrat contrat=Contrat.builder()
                .typeContrat(contratRequestDto.getTypeContrat())
                .build();
        Contrat savedContrat=contratRepository.save(contrat);
        return  ResponseEntity.ok(savedContrat.toDto());
    }

    public ResponseEntity<ContratResponseDto> getContratById(Long contratId){
        Contrat contrat=contratRepository.findById(contratId).orElseThrow(()-> new ContratNotFoundException("contrat not found "));
        return  ResponseEntity.ok(contrat.toDto());
    }

    public ResponseEntity<ContratResponseDto> updateContrat(Long contratId, ContratRequestDto contratRequestDto){
        Contrat foundContrat=contratRepository.findById(contratId).orElse(null);
        if(foundContrat==null){
            throw new ContratNotFoundException("contrat not found");
        }
        foundContrat.setTypeContrat(contratRequestDto.getTypeContrat());
        Contrat savedContrat=contratRepository.save(foundContrat);
        return  ResponseEntity.ok(savedContrat.toDto());
    }

    public ResponseEntity<String> deleteContratById(Long contratId){
        contratRepository.deleteById(contratId);
        return ResponseEntity.ok("contrat supprimee avec succes");
    }

}
