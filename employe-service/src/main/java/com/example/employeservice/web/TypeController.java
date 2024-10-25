package com.example.employeservice.web;

import com.example.employeservice.Dtos.TypeRequestDto;
import com.example.employeservice.Dtos.TypeResponseDto;
import com.example.employeservice.services.TypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/types")
public class TypeController {
    final private TypeService typeService;


    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("")
    public ResponseEntity<List<TypeResponseDto>> getAllTypes(){
        return  typeService.getAllTypes();
    }

    @PostMapping("")
    public ResponseEntity<TypeResponseDto> addType(@RequestParam("label") String label){
        TypeRequestDto typeRequestDto=TypeRequestDto.builder()
                .label(label)
                .build();
        return typeService.addType(typeRequestDto);

    }

    @PutMapping("/{typeId}")
    public ResponseEntity<TypeResponseDto>updateType(@PathVariable Long typeId, @RequestParam("label") String label){
        TypeRequestDto typeRequestDto=TypeRequestDto.builder()
                .label(label)
                .build();
        return typeService.updateType(typeId,typeRequestDto);

    }

    @DeleteMapping("/{typeId}")
    public ResponseEntity<String>deleteTypeById(@PathVariable Long typeId){
        return ResponseEntity.ok(typeService.deleteTypeById(typeId));

    }
}
