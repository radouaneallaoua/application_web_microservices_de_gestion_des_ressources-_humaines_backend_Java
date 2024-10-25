package com.example.employeservice.web;


import com.example.employeservice.Dtos.CategorieRequestDto;
import com.example.employeservice.Dtos.CategorieResponseDto;
import com.example.employeservice.services.CategorieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")

public class CategorieController {
    final private CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping("")
    public ResponseEntity<List<CategorieResponseDto>> allCategories(){
        return  categorieService.allCategories();
    }


    @PostMapping("")
    public ResponseEntity<CategorieResponseDto> addCategorie(String name){
        CategorieRequestDto categorieRequestDto=CategorieRequestDto.builder().name(name).build();
        return  categorieService.addCategorie(categorieRequestDto);
    }

    @GetMapping("/{categorieId}")
    public ResponseEntity<CategorieResponseDto> getCategorieById(@PathVariable Long  categorieId){
        return  categorieService.getCategorieById(categorieId);
    }

    @PutMapping("/{categorieId}")
    public ResponseEntity<CategorieResponseDto> updateCategorie(@PathVariable Long categorieId,String name){
        CategorieRequestDto categorieRequestDto=CategorieRequestDto.builder().name(name).build();
        return  categorieService.updateCategorie(categorieId,categorieRequestDto);
    }

    @DeleteMapping("/{categorieId}")
    public ResponseEntity<String> deleteCategorieById(@PathVariable Long categorieId){
          return categorieService.deleteCategorieById(categorieId);
    }

}
