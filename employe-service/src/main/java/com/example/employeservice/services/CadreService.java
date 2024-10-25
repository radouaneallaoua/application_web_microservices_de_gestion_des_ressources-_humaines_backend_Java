package com.example.employeservice.services;
import com.example.employeservice.Dtos.CadreRequestDto;
import com.example.employeservice.Dtos.CadreResponseDto;
import com.example.employeservice.Exception.CadreNotFoundException;
import com.example.employeservice.Exception.CorpsNotFoundException;
import com.example.employeservice.entities.Cadre;
import com.example.employeservice.repository.CadreRepository;
import com.example.employeservice.repository.CorpsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CadreService {
    private final CadreRepository cadreRepository;
    private final CorpsRepository corpsRepository;

    public CadreService(CadreRepository cadreRepository, CorpsRepository corpsRepository) {
        this.cadreRepository = cadreRepository;
        this.corpsRepository = corpsRepository;
    }


    public ResponseEntity<List<CadreResponseDto>> getAllCadreDeCorps(Long corpsId){
        List<Cadre> cadres=cadreRepository.findAllByCorpsId(corpsId);
        List<CadreResponseDto> list=new ArrayList<>();
        cadres.forEach(c->{
            list.add(c.toDto());
        });
        return ResponseEntity.ok(list);
    }

    public ResponseEntity<List<CadreResponseDto>> getAllCadre(){
        List<Cadre> cadres=cadreRepository.findAll();
        List<CadreResponseDto> list=new ArrayList<>();
        cadres.forEach(c->{
            list.add(c.toDto());
        });
        return ResponseEntity.ok(list);
    }


    public ResponseEntity<CadreResponseDto> getCadreById(Long cadreId){
        Cadre cadre=cadreRepository.findById(cadreId).orElseThrow(()-> new CadreNotFoundException("cadre not fount"));
        return ResponseEntity.ok(cadre.toDto());
    }
    public ResponseEntity<CadreResponseDto> addCadre(CadreRequestDto cadreRequestDto){
       Cadre cadre=Cadre.builder()
               .corps(corpsRepository.findById(cadreRequestDto.getCorpsId()).orElseThrow(()-> new CorpsNotFoundException("corps not found")))
               .label(cadreRequestDto.getLabel())
               .build();
       Cadre savedCadre=cadreRepository.save(cadre);
        return ResponseEntity.ok(savedCadre.toDto());
    }

    public ResponseEntity<CadreResponseDto> updateCadre(Long cadreId, CadreRequestDto cadreRequestDto){
        Cadre cadre=cadreRepository.findById(cadreId).orElseThrow(()-> new CadreNotFoundException("cadre not fount"));
        cadre.setLabel(cadreRequestDto.getLabel());
        Cadre savedCadre=cadreRepository.save(cadre);
        return ResponseEntity.ok(savedCadre.toDto());
    }

    public ResponseEntity<String> deleteCadreById(Long cadreId) {
        cadreRepository.deleteById(cadreId);
        return  ResponseEntity.ok("cadre supprime avce succes");
    }
}
