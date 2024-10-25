package com.example.recrutementservice.services;

import com.example.recrutementservice.Dtos.EntretienRequestDto;
import com.example.recrutementservice.Dtos.EntretienResponseDto;
import com.example.recrutementservice.Exception.CondidatureNotFoundException;
import com.example.recrutementservice.Exception.EntretienNotFoundException;
import com.example.recrutementservice.entities.Condidat;
import com.example.recrutementservice.entities.Entretien;
import com.example.recrutementservice.enums.EtatEntretien;
import com.example.recrutementservice.repository.CondidatRepository;
import com.example.recrutementservice.repository.EntretienRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EntretienService {
    final private CondidatRepository condidatRepository;
    final private EntretienRepository entretienRepository;


    public EntretienService(CondidatRepository condidatRepository, EntretienRepository entretienRepository) {
        this.condidatRepository = condidatRepository;
        this.entretienRepository = entretienRepository;
    }

    public ResponseEntity<List<EntretienResponseDto>> allEntretiens(){
        List<Entretien> entretiens=entretienRepository.findAll();
        List<EntretienResponseDto> dtoList=new ArrayList<>();
        entretiens.forEach(e->{
            dtoList.add(e.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<EntretienResponseDto>> allEntretiensForCondidatId(Long condidatId){
        List<Entretien> entretiens=entretienRepository.findAllByCondidatId(condidatId);
        List<EntretienResponseDto> dtoList=new ArrayList<>();
        entretiens.forEach(e->{
            dtoList.add(e.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<EntretienResponseDto>> allEntretiensAvecEtatEntretien(EtatEntretien etatEntretien){
        List<Entretien> entretiens=entretienRepository.findAllByEtatEntretien(etatEntretien);
        List<EntretienResponseDto> dtoList=new ArrayList<>();
        entretiens.forEach(e->{
            dtoList.add(e.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<EntretienResponseDto>> allEntretiensAvecDateEntretienApres(LocalDateTime date){
        List<Entretien> entretiens=entretienRepository.findAllByDateEntretienAfter(date);
        List<EntretienResponseDto> dtoList=new ArrayList<>();
        entretiens.forEach(e->{
            dtoList.add(e.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<EntretienResponseDto>> allEntretiensAvecDateEntretienAvant(LocalDateTime date){
        List<Entretien> entretiens=entretienRepository.findAllByDateEntretienBefore(date);
        List<EntretienResponseDto> dtoList=new ArrayList<>();
        entretiens.forEach(e->{
            dtoList.add(e.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<EntretienResponseDto>> allEntretiensAvecDateEntretienEntre(LocalDateTime date1,LocalDateTime date2){
        List<Entretien> entretiens=entretienRepository.findAllByDateEntretienBetween(date1,date2);
        List<EntretienResponseDto> dtoList=new ArrayList<>();
        entretiens.forEach(e->{
            dtoList.add(e.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<EntretienResponseDto> addEntretien(EntretienRequestDto entretienRequestDto){
        Condidat condidat=condidatRepository.findById(entretienRequestDto.getCondidatId()).orElse(null);
        if(condidat==null){
            throw new CondidatureNotFoundException("condidat not found");
        }
        Entretien entretien=Entretien.builder()
                .dateEntretien(entretienRequestDto.getDateEntretien())
                .etatEntretien(EtatEntretien.PLANIFIE)
                .condidat(condidat)
                .label(entretienRequestDto.getLabel())
                .description(entretienRequestDto.getDescription())
                .build();
        Entretien savedEntretien=entretienRepository.save(entretien);
        return  ResponseEntity.ok(savedEntretien.toDto());
    }

    public ResponseEntity<EntretienResponseDto> updateEntretien(Long entretienId,EntretienRequestDto entretienRequestDto,EtatEntretien nouvelEtatEntretien){
        Entretien foundentretien=entretienRepository.findById(entretienId).orElse(null);
        if(foundentretien==null){
            throw new EntretienNotFoundException("entretien not found");
        }

        Condidat condidat=condidatRepository.findById(entretienRequestDto.getCondidatId()).orElse(null);
        if(condidat==null){
            throw new CondidatureNotFoundException("condidat not found");
        }
        foundentretien.setDateEntretien(entretienRequestDto.getDateEntretien());
        foundentretien.setCondidat(condidat);
        foundentretien.setLabel(entretienRequestDto.getLabel());
        foundentretien.setDescription(entretienRequestDto.getDescription());
        foundentretien.setEtatEntretien(nouvelEtatEntretien);
        Entretien savedEntretien=entretienRepository.save(foundentretien);
        return  ResponseEntity.ok(savedEntretien.toDto());
    }

    public  ResponseEntity<String> deleteEntretienById(Long entretienId){
        entretienRepository.deleteById(entretienId);
        return  ResponseEntity.ok("entretien supprime avec succes");
    }

}
