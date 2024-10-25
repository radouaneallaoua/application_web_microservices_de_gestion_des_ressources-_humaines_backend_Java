package com.example.employeservice.web;


import com.example.employeservice.Dtos.CadreResponseDto;
import com.example.employeservice.Dtos.PosteRequestDto;
import com.example.employeservice.Dtos.PosteResponseDto;
import com.example.employeservice.entities.Cadre;
import com.example.employeservice.services.PosteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/postes")
public class PosteController {
    final  private PosteService posteService;

    public PosteController(PosteService posteService) {
        this.posteService = posteService;
    }

    @GetMapping("")
    public ResponseEntity<List<PosteResponseDto>> allPostes(){
        return posteService.getAllPostes();
    }

    @PostMapping("")
    public ResponseEntity<PosteResponseDto> addPoste(@RequestBody PosteRequestDto posteRequestDto){
        return  posteService.createPoste(posteRequestDto);
    }

    @GetMapping("/{posteId}")
    public ResponseEntity<PosteResponseDto> getPosteById(@PathVariable Long posteId){
        return  posteService.getPosteById(posteId);
    }
    @DeleteMapping("/{posteId}")
    public ResponseEntity<String> deletePosteById(@PathVariable Long posteId){
        return  posteService.deletePosteById(posteId);
    }

    @PutMapping("/{posteId}")
    public ResponseEntity<PosteResponseDto> updatePoste(@PathVariable Long posteId, @RequestBody PosteRequestDto posteRequestDto){
        return  posteService.updatePoste(posteId,posteRequestDto);
    }

}
