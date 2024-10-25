package com.example.employeservice.services;

import com.example.employeservice.Dtos.PosteResponseDto;
import com.example.employeservice.Dtos.PosteResponseDto;
import com.example.employeservice.Dtos.PosteRequestDto;
import com.example.employeservice.Dtos.PosteResponseDto;
import com.example.employeservice.Exception.PosteNotFoundException;
import com.example.employeservice.Exception.PosteNotFoundException;
import com.example.employeservice.entities.Poste;
import com.example.employeservice.entities.Poste;
import com.example.employeservice.entities.Poste;
import com.example.employeservice.repository.PosteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PosteService {
    private final PosteRepository posteRepository;

    public PosteService(PosteRepository posteRepository) {
        this.posteRepository = posteRepository;
    }

    public ResponseEntity<PosteResponseDto> createPoste(PosteRequestDto posteRequestDto){
        Poste poste=Poste.builder()
                .description(posteRequestDto.getDescription())
                .label(posteRequestDto.getLabel())
                .build();
        Poste savedPoste=posteRepository.save(poste);
        return  ResponseEntity.ok(savedPoste.toDto());
    }

    public ResponseEntity<PosteResponseDto> updatePoste(Long posteId, PosteRequestDto posteRequestDto){
        Poste poste=posteRepository.findById(posteId).orElseThrow(()-> new PosteNotFoundException("poste not found"));
        poste.setDescription(posteRequestDto.getDescription());
        poste.setLabel(posteRequestDto.getLabel());

        Poste savedPoste=posteRepository.save(poste);
        return  ResponseEntity.ok(savedPoste.toDto());
    }

    public ResponseEntity<List<PosteResponseDto>> getAllPostes(){
        List<Poste> postes=posteRepository.findAll();
        List<PosteResponseDto> list=new ArrayList<>();
        postes.forEach(c->{
            list.add(c.toDto());
        });
        return ResponseEntity.ok(list);
    }
    public ResponseEntity<PosteResponseDto> getPosteById(Long posteId){
        Poste poste=posteRepository.findById(posteId).orElseThrow(()-> new PosteNotFoundException("poste not found"));
        return ResponseEntity.ok(poste.toDto());
    }

    public ResponseEntity<String> deletePosteById(Long posteId){
        posteRepository.deleteById(posteId);
        return  ResponseEntity.ok("poste supprime avec succes");
    }
}
