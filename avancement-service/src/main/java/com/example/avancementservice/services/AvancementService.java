package com.example.avancementservice.services;

import com.example.avancementservice.clients.EmployeRestClient;
import com.example.avancementservice.dtos.AvancementRequestDto;
import com.example.avancementservice.dtos.AvancementResponseDto;
import com.example.avancementservice.entities.Avancement;
import com.example.avancementservice.enums.MotifAvancement;
import com.example.avancementservice.enums.TypeAvancement;
import com.example.avancementservice.exception.AvancementNotFoundException;
import com.example.avancementservice.models.Employe;
import com.example.avancementservice.repository.AvancementRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AvancementService {
    final private AvancementRepository avancementRepository;
    final private EmployeRestClient employeRestClient;
    public AvancementService(AvancementRepository avancementRepository, EmployeRestClient employeRestClient) {
        this.avancementRepository = avancementRepository;
        this.employeRestClient = employeRestClient;
    }

    public ResponseEntity<List<AvancementResponseDto>> AvancementsAvecEmployeId(String employeId){
        List<Avancement> avancements=avancementRepository.findAllByEmployeId(employeId);
        List<AvancementResponseDto> dtoList=new ArrayList<>();
        if(avancements!=null){
            avancements.forEach(a->{
                a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
                dtoList.add(a.toDto());
            });
        }
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<AvancementResponseDto>> toutLesAvancements(){
        List<Avancement> avancements=avancementRepository.findAll();
        List<AvancementResponseDto> dtoList=new ArrayList<>();
        avancements.forEach(a -> {
            a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
            dtoList.add(a.toDto());
        });
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<AvancementResponseDto>> AvancementsAvecGradeId(Long  gradeId){
        List<Avancement> avancements=avancementRepository.findAllByNouveauGradeId(gradeId);
        List<AvancementResponseDto> dtoList=new ArrayList<>();
        if(avancements!=null){
            avancements.forEach(a->{
                a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
                dtoList.add(a.toDto());
            });
        }
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<AvancementResponseDto>> AvancementsAvecPosteId(Long  posteId){
        List<Avancement> avancements=avancementRepository.findAllByNouveauPosteId(posteId);
        List<AvancementResponseDto> dtoList=new ArrayList<>();
        if(avancements!=null){
            avancements.forEach(a->{
                a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
                dtoList.add(a.toDto());
            });
        }
        return  ResponseEntity.ok(dtoList);
    }
    public ResponseEntity<List<AvancementResponseDto>> AvancementsAvecEntiteId(Long  entiteId){
        List<Avancement> avancements=avancementRepository.findAllByNouvelleEntiteId(entiteId);
        List<AvancementResponseDto> dtoList=new ArrayList<>();
        if(avancements!=null){
            avancements.forEach(a->{
                a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
                dtoList.add(a.toDto());
            });
        }
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<AvancementResponseDto>> AvancementsAvecIndiceEchelonId(Long  indiceEchelonId){
        List<Avancement> avancements=avancementRepository.findAllByNouveauIndiceEchelonId(indiceEchelonId);
        List<AvancementResponseDto> dtoList=new ArrayList<>();
        if(avancements!=null){
            avancements.forEach(a->{
                a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
                dtoList.add(a.toDto());
            });
        }
        return  ResponseEntity.ok(dtoList);
    }
    public ResponseEntity<List<AvancementResponseDto>> AvancementsAvecDateAvancementApres(LocalDate date){
        List<Avancement> avancements=avancementRepository.findAllByDateAvancementAfter(date);
        List<AvancementResponseDto> dtoList=new ArrayList<>();
        if(avancements!=null){
            avancements.forEach(a->{
                a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
                dtoList.add(a.toDto());
            });
        }
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<AvancementResponseDto>> AvancementsAvecDateAvancementAvant(LocalDate  date){
        List<Avancement> avancements=avancementRepository.findAllByDateAvancementBefore(date);
        List<AvancementResponseDto> dtoList=new ArrayList<>();
        if(avancements!=null){
            avancements.forEach(a->{
                a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
                dtoList.add(a.toDto());
            });
        }
        return  ResponseEntity.ok(dtoList);
    }
    public ResponseEntity<List<AvancementResponseDto>> AvancementsAvecTypeAvancement(TypeAvancement typeAvancement){
        List<Avancement> avancements=avancementRepository.findAllByTypeAvancement(typeAvancement);
        List<AvancementResponseDto> dtoList=new ArrayList<>();
        if(avancements!=null){
            avancements.forEach(a->{
                a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
                dtoList.add(a.toDto());
            });
        }
        return  ResponseEntity.ok(dtoList);
    }
    public ResponseEntity<List<AvancementResponseDto>> AvancementsAvecMotifAvancement(MotifAvancement motifAvancement){
        List<Avancement> avancements=avancementRepository.findAllByMotifAvancement(motifAvancement);
        List<AvancementResponseDto> dtoList=new ArrayList<>();
        if(avancements!=null){
            avancements.forEach(a->{
                a.setEmploye(employeRestClient.findEmployeById(a.getEmployeId()));
                dtoList.add(a.toDto());
            });
        }
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<AvancementResponseDto> ajouterAvancement(AvancementRequestDto avancementRequestDto){
        Avancement avancement=Avancement.builder()
                .dateAvancement(avancementRequestDto.getDateAvancement())
                .nouveauGradeId(avancementRequestDto.getNouveauGradeId())
                .motifAvancement(avancementRequestDto.getMotifAvancement())
                .typeAvancement(avancementRequestDto.getTypeAvancement())
                .employeId(avancementRequestDto.getEmployeId())
                .nouveauIndiceEchelonId(avancementRequestDto.getNouveauIndiceEchelonId())
                .nouvelleEntiteId(avancementRequestDto.getNouvelleEntiteId())
                .nouveauPosteId(avancementRequestDto.getPosteId())
                .build();
       Avancement savedAvancement=avancementRepository.save(avancement);
       ResponseEntity<Employe> employe=employeRestClient.updateEmployeWithGradeIdAndIndiceIdAndEntiteIdAndPosteId(avancementRequestDto.getEmployeId(),avancementRequestDto.getNouveauGradeId(),avancementRequestDto.getNouveauIndiceEchelonId(),avancementRequestDto.getNouvelleEntiteId(),avancementRequestDto.getPosteId());
        savedAvancement.setEmploye(employe.getBody());
        return  ResponseEntity.ok(savedAvancement.toDto());
    }

    public ResponseEntity<AvancementResponseDto> getAvancementById(Long avancementId){
        Avancement foundAvancement=avancementRepository.findById(avancementId).orElseThrow(()->new AvancementNotFoundException("avancement not found exception"));
        foundAvancement.setEmploye(employeRestClient.findEmployeById(foundAvancement.getEmployeId()));
        return  ResponseEntity.ok(foundAvancement.toDto());
    }

    public ResponseEntity<String> supprimerAvancement(Long avancementId){
       avancementRepository.deleteById(avancementId);

        return  ResponseEntity.ok("avancement supprime avec id"+avancementId);
    }

    public ResponseEntity<AvancementResponseDto> modifierAvancement(Long avancementId,AvancementRequestDto avancementRequestDto){
        Avancement foundAvancement=avancementRepository.findById(avancementId).orElseThrow(()-> new AvancementNotFoundException("avancement not found exception"));
        foundAvancement.setDateAvancement(avancementRequestDto.getDateAvancement());
        foundAvancement.setTypeAvancement(avancementRequestDto.getTypeAvancement());
        foundAvancement.setMotifAvancement(avancementRequestDto.getMotifAvancement());
        foundAvancement.setEmployeId(avancementRequestDto.getEmployeId());
        foundAvancement.setNouveauGradeId(avancementRequestDto.getNouveauGradeId());
        foundAvancement.setNouveauPosteId(avancementRequestDto.getPosteId());
        foundAvancement.setNouvelleEntiteId(avancementRequestDto.getNouvelleEntiteId());
        foundAvancement.setEmploye(employeRestClient.findEmployeById(avancementRequestDto.getEmployeId()));
        Avancement savedAvancement=avancementRepository.save(foundAvancement);
        return  ResponseEntity.ok(savedAvancement.toDto());
    }


}
