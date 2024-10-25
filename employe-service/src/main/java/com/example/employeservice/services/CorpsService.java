package com.example.employeservice.services;
import com.example.employeservice.Dtos.CorpsRequestDto;
import com.example.employeservice.Dtos.CorpsResponseDto;
import com.example.employeservice.Exception.CategorieNotFoundException;
import com.example.employeservice.Exception.CorpsNotFoundException;
import com.example.employeservice.entities.Corps;
import com.example.employeservice.repository.CategorieRepository;
import com.example.employeservice.repository.CorpsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CorpsService {
    private final CorpsRepository corpsRepository;
    private final CategorieRepository categorieRepository;

    public CorpsService(CorpsRepository corpsRepository, CategorieRepository categorieRepository) {
        this.corpsRepository = corpsRepository;
        this.categorieRepository = categorieRepository;
    }


    public ResponseEntity<List<CorpsResponseDto>> getAllCorpsDeCategorie(Long categorieId){
        List<Corps> corps=corpsRepository.findAllByCategorieId(categorieId);
        List<CorpsResponseDto> list=new ArrayList<>();
        corps.forEach(c->{
            list.add(c.toDto());
        });
        return ResponseEntity.ok(list);
    }

    public ResponseEntity<List<CorpsResponseDto>> getAllCorps(){
        List<Corps> corps=corpsRepository.findAll();
        List<CorpsResponseDto> list=new ArrayList<>();
        corps.forEach(c->{
            list.add(c.toDto());
        });
        return ResponseEntity.ok(list);
    }


    public ResponseEntity<CorpsResponseDto> getCorpsById(Long corpsId){
        Corps corps=corpsRepository.findById(corpsId).orElseThrow(()-> new CorpsNotFoundException("corps not found"));
        return ResponseEntity.ok(corps.toDto());
    }

    public ResponseEntity<CorpsResponseDto> updateCorps(Long corpsId, CorpsRequestDto corpsRequestDto){
        Corps corps=corpsRepository.findById(corpsId).orElseThrow(()-> new CorpsNotFoundException("corps not found"));
        corps.setCategorie(categorieRepository.findById(corpsRequestDto.getCategorieId()).orElseThrow(()-> new CategorieNotFoundException("categorie not found")));
        corps.setLabel(corpsRequestDto.getLabel());

        Corps savedCorps=corpsRepository.save(corps);
        return ResponseEntity.ok(savedCorps.toDto());
    }

    public ResponseEntity<CorpsResponseDto> addCorps(CorpsRequestDto corpsRequestDto){
        Corps corps=Corps.builder()
                .label(corpsRequestDto.getLabel())
                .categorie(categorieRepository.findById(corpsRequestDto.getCategorieId()).orElseThrow(()-> new CategorieNotFoundException("categorie not found")))
                .build();
        Corps savedCorps=corpsRepository.save(corps);
        return ResponseEntity.ok(savedCorps.toDto());
    }

    public ResponseEntity<String> deleteCorpsById(Long corpsId) {
        corpsRepository.deleteById(corpsId);
        return  ResponseEntity.ok("corps supprime avec succes");
    }
}
