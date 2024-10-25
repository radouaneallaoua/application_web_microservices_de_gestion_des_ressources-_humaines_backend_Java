package com.example.employeservice.services;

import com.example.employeservice.Dtos.TypeRequestDto;
import com.example.employeservice.Dtos.TypeResponseDto;
import com.example.employeservice.Exception.TypeNotFoundException;
import com.example.employeservice.entities.Type;
import com.example.employeservice.repository.TypeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TypeService {
    private final TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }


    public ResponseEntity<List<TypeResponseDto>> getAllTypes(){
        List<Type> types=typeRepository.findAll();
        List<TypeResponseDto> list=new ArrayList<>();
        types.forEach(c->{
            list.add(c.toDto());
        });
        return ResponseEntity.ok(list);
    }


    public ResponseEntity<TypeResponseDto> getTypeById(Long typeId){
        Type type=typeRepository.findById(typeId).orElseThrow(()-> new TypeNotFoundException("type not found"));
        return ResponseEntity.ok(type.toDto());
    }
    public ResponseEntity<TypeResponseDto> addType(TypeRequestDto typeRequestDto){
       Type type=Type.builder()
               .label(typeRequestDto.getLabel())
               .build();
       Type savedType=typeRepository.save(type);
        return ResponseEntity.ok(savedType.toDto());
    }

    public ResponseEntity<TypeResponseDto> updateType(Long typeId, TypeRequestDto typeRequestDto){
        Type type=typeRepository.findById(typeId).orElseThrow(()-> new TypeNotFoundException("type not found"));
        type.setLabel(typeRequestDto.getLabel());
        Type savedType=typeRepository.save(type);
        return ResponseEntity.ok(savedType.toDto());
    }

    public String deleteTypeById(Long id){
        typeRepository.deleteById(id);
        return  "type supprimer by id"+id;
    }
}
