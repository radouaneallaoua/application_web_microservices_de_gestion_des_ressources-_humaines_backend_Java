package com.example.employeservice.services;
import com.example.employeservice.Dtos.*;
import com.example.employeservice.Exception.EmployeNotFoundException;
import com.example.employeservice.Exception.EntiteEmployeNotFoundException;
import com.example.employeservice.Exception.EntiteNotFoundException;
import com.example.employeservice.Exception.TypeNotFoundException;
import com.example.employeservice.entities.Employe;
import com.example.employeservice.entities.Entite;
import com.example.employeservice.entities.EntiteEmploye;
import com.example.employeservice.entities.EntiteEmployeKey;
import com.example.employeservice.repository.EmployeRepository;
import com.example.employeservice.repository.EntiteEmployeRepository;
import com.example.employeservice.repository.EntiteRepository;
import com.example.employeservice.repository.TypeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EntiteService {
    private final EntiteRepository entiteRepository;
    private final TypeRepository typeRepository;
    private final EntiteEmployeRepository entiteEmployeRepository;
    private final EmployeRepository employeRepository;

    public EntiteService(EntiteRepository entiteRepository, TypeRepository typeRepository, EntiteEmployeRepository entiteEmployeRepository, EmployeRepository employeRepository) {
        this.entiteRepository = entiteRepository;
        this.typeRepository = typeRepository;
        this.entiteEmployeRepository = entiteEmployeRepository;
        this.employeRepository = employeRepository;
    }

    public ResponseEntity<List<EntiteResponseDto>> getAllEntites(){
        List<Entite> entiteList=entiteRepository.findAll();
        List<EntiteResponseDto> entiteResponseDtos=new ArrayList<>();
        entiteList.forEach(e->{
            entiteResponseDtos.add(e.toDto());
        });
        return ResponseEntity.ok(entiteResponseDtos);
    }
    public ResponseEntity<List<EntiteResponseDto>> getAllEntitesAvecType(Long typeId){
        List<Entite> entiteList=entiteRepository.findAllByTypeId(typeId);
        List<EntiteResponseDto> entiteResponseDtos=new ArrayList<>();
        entiteList.forEach(e->{
            entiteResponseDtos.add(e.toDto());
        });
        return ResponseEntity.ok(entiteResponseDtos);
    }


    public ResponseEntity<List<EntiteResponseDto>> getAllEntitesAvecEntiteMereId(Long entiteMereId){
        List<Entite> entiteList=entiteRepository.findAllByEntiteMereId(entiteMereId);
        List<EntiteResponseDto> entiteResponseDtos=new ArrayList<>();
        entiteList.forEach(e->{
            entiteResponseDtos.add(e.toDto());
        });
        return ResponseEntity.ok(entiteResponseDtos);
    }

    public ResponseEntity<List<EntiteEmployeResponseDto>> getAllEntitesEmployes(){
        List<EntiteEmploye> entiteList=entiteEmployeRepository.findAll();
        List<EntiteEmployeResponseDto> entiteResponseDtos=new ArrayList<>();
        entiteList.forEach(e->{
            entiteResponseDtos.add(e.toDto());
        });
        return ResponseEntity.ok(entiteResponseDtos);
    }

    public ResponseEntity<EntiteEmployeResponseDto> addEntiteEmploye(EntiteEmployeRequestDto entiteEmployeRequestDto){
        EntiteEmploye entiteEmploye=EntiteEmploye.builder()
                .employe(employeRepository.findById(entiteEmployeRequestDto.getEmployeId()).orElseThrow(()-> new EmployeNotFoundException("employe not found")))
                .entite(entiteRepository.findById(entiteEmployeRequestDto.getEntiteId()).orElseThrow(()-> new EntiteNotFoundException("entite not found")))
                .dateDebut(entiteEmployeRequestDto.getDateDebut())
                .dateFin(entiteEmployeRequestDto.getDateFin())
                .id(EntiteEmployeKey.builder().employeId(entiteEmployeRequestDto.getEmployeId()).entiteId(entiteEmployeRequestDto.getEntiteId()).build())
                .build();

        return ResponseEntity.ok(entiteEmployeRepository.save(entiteEmploye).toDto());
    }

    public ResponseEntity<EntiteEmployeResponseDto> updateEntiteEmploye(EntiteEmployeKey id, EntiteEmployeRequestDto entiteEmployeRequestDto){
        EntiteEmploye entiteEmploye=entiteEmployeRepository.findById(id).orElseThrow(()->new EntiteEmployeNotFoundException("entite employe not found"));
        entiteEmploye.setEmploye(employeRepository.findById(entiteEmployeRequestDto.getEmployeId()).orElseThrow(()-> new EmployeNotFoundException("employe not found")));
        entiteEmploye.setEntite(entiteRepository.findById(entiteEmployeRequestDto.getEntiteId()).orElseThrow(()-> new EntiteNotFoundException("entite not found")));
        entiteEmploye.setDateDebut(entiteEmployeRequestDto.getDateDebut());
        entiteEmploye.setDateFin(entiteEmployeRequestDto.getDateFin());
        return ResponseEntity.ok(entiteEmployeRepository.save(entiteEmploye).toDto());
    }

    public ResponseEntity<String> deleteEntiteEmployeById(EntiteEmployeKey id){
        entiteEmployeRepository.deleteById(id);
        return  ResponseEntity.ok("entite employe supprimee");
    }

    public ResponseEntity<List<EntiteEmployeResponseDto>> getAllEntiteEmployeAvecEntiteId(Long entiteId){
        List<EntiteEmploye> entiteList=entiteEmployeRepository.findAllByEntiteId(entiteId);
        List<EntiteEmployeResponseDto> entiteResponseDtos=new ArrayList<>();
        entiteList.forEach(e->{
            entiteResponseDtos.add(e.toDto());
        });
        return ResponseEntity.ok(entiteResponseDtos);
    }

    public ResponseEntity<List<EntiteEmployeResponseDto>> getAllEntiteEmployeAvecEmployeId(String employeId){
        List<EntiteEmploye> entiteList=entiteEmployeRepository.findAllByEmployeId(employeId);
        List<EntiteEmployeResponseDto> entiteResponseDtos=new ArrayList<>();
        entiteList.forEach(e->{
            entiteResponseDtos.add(e.toDto());
        });
        return ResponseEntity.ok(entiteResponseDtos);
    }

    public ResponseEntity<EntiteEmployeResponseDto> getEmployeEntiteActuelle(String employeId){
        List<EntiteEmploye> entiteList=entiteEmployeRepository.findAllByEmployeId(employeId);
        List<EntiteEmployeResponseDto> entiteResponseDtos=new ArrayList<>();
        entiteList.forEach(e->{
            entiteResponseDtos.add(e.toDto());
        });
        return ResponseEntity.ok(entiteResponseDtos.get(entiteResponseDtos.size()-1));
    }

    public ResponseEntity<List<EntiteEmployeResponseDto>> getAllEntiteEmployeAvecDateDebutApres(LocalDate dateDebut){
        List<EntiteEmploye> entiteList=entiteEmployeRepository.findAllByDateDebutAfter(dateDebut);
        List<EntiteEmployeResponseDto> entiteResponseDtos=new ArrayList<>();
        entiteList.forEach(e->{
            entiteResponseDtos.add(e.toDto());
        });
        return ResponseEntity.ok(entiteResponseDtos);
    }


    public ResponseEntity<EntiteResponseDto> getEntiteById(Long entiteId){
       Entite entite=entiteRepository.findById(entiteId).orElseThrow(()-> new EntiteNotFoundException("entite not found"));
        return ResponseEntity.ok(entite.toDto());
    }

    public ResponseEntity<EntiteResponseDto> updateEntiteById(Long entiteId, EntiteRequestDto entiteRequestDto){
        Entite entite=entiteRepository.findById(entiteId).orElseThrow(()-> new EntiteNotFoundException("entite not found"));
        entite.setLabel(entiteRequestDto.getName());
        entite.setType(typeRepository.findById(entiteRequestDto.getTypeId()).orElseThrow(()-> new TypeNotFoundException("type not found")));
        Entite savedEntite=entiteRepository.save(entite);
        return ResponseEntity.ok(savedEntite.toDto());
    }
    public ResponseEntity<EntiteResponseDto> updateSubEntiteById(Long entiteId, EntiteRequestDto entiteRequestDto,Long entiteMereId){
        Entite entite=entiteRepository.findById(entiteId).orElseThrow(()-> new EntiteNotFoundException("entite not found"));
        entite.setLabel(entiteRequestDto.getName());
        entite.setType(typeRepository.findById(entiteRequestDto.getTypeId()).orElseThrow(()-> new TypeNotFoundException("type not found")));
        entite.setEntiteMere(entiteRepository.findById(entiteMereId).orElseThrow(()-> new EntiteNotFoundException("entite not found")));
        Entite savedEntite=entiteRepository.save(entite);
        return ResponseEntity.ok(savedEntite.toDto());
    }

    public ResponseEntity<EntiteResponseDto> addEntite(EntiteRequestDto entiteRequestDto){
        Entite entite=Entite.builder()
                .type(typeRepository.findById(entiteRequestDto.getTypeId()).orElseThrow(()-> new TypeNotFoundException("type not found")))
                .label(entiteRequestDto.getName())
                .entiteMere(null)
                .build();
        Entite savedEntite=entiteRepository.save(entite);
        return ResponseEntity.ok(savedEntite.toDto());
    }

    public ResponseEntity<EntiteResponseDto> addSubEntite(Long entiteMereId,EntiteRequestDto entiteRequestDto){
        Entite entite=Entite.builder()
                .entiteMere(entiteRepository.findById(entiteMereId).orElseThrow(()-> new EntiteNotFoundException("entite not found")))
                .type(typeRepository.findById(entiteRequestDto.getTypeId()).orElseThrow(()-> new TypeNotFoundException("type not found")))
                .label(entiteRequestDto.getName())
                .build();
        Entite savedEntite=entiteRepository.save(entite);
        return ResponseEntity.ok(savedEntite.toDto());
    }

    public void deleteEntiteById(Long entiteId){
        entiteRepository.deleteById(entiteId);
    }



}
