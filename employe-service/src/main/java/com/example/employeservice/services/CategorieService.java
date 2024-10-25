package com.example.employeservice.services;
import com.example.employeservice.Dtos.CategorieRequestDto;
import com.example.employeservice.Dtos.CategorieResponseDto;
import com.example.employeservice.Exception.CategorieNotFoundException;
import com.example.employeservice.entities.Categorie;
import com.example.employeservice.repository.CategorieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategorieService {
    private final CategorieRepository categorieRepository;

    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public ResponseEntity<List<CategorieResponseDto>> allCategories(){
        List<Categorie> categories=categorieRepository.findAll();
        List<CategorieResponseDto> categorieResponseDtos=new ArrayList<>();
        categories.forEach(c->{
            categorieResponseDtos.add(c.toDto());
        });
        return  ResponseEntity.ok(categorieResponseDtos);
    }
    public ResponseEntity<CategorieResponseDto> updateCategorie(Long categorieId, CategorieRequestDto categorieRequestDto){
        Categorie categorie=categorieRepository.findById(categorieId).orElseThrow(()-> new CategorieNotFoundException("categorie not found"));
        categorie.setName(categorieRequestDto.getName());
        Categorie savedCategorie=categorieRepository.save(categorie);
        return  ResponseEntity.ok(savedCategorie.toDto());
    }

    public ResponseEntity<CategorieResponseDto> addCategorie(CategorieRequestDto categorieRequestDto){
        Categorie categorie=Categorie.builder()
                .name(categorieRequestDto.getName())
                .name(categorieRequestDto.getName())
                .build();
        Categorie savedCategorie=categorieRepository.save(categorie);
        return  ResponseEntity.ok(savedCategorie.toDto());
    }

    public ResponseEntity<CategorieResponseDto> getCategorieById(Long categorieId){
        Categorie categorie=categorieRepository.findById(categorieId).orElseThrow(()->new CategorieNotFoundException("categorie n'existe pas"));
        return  ResponseEntity.ok(categorie.toDto());
    }


    public ResponseEntity<String> deleteCategorieById(Long categorieId){
        categorieRepository.deleteById(categorieId);
        return ResponseEntity.ok("categorie est supprimée avec succés");
    }
}
