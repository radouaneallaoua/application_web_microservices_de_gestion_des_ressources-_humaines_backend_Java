package com.example.recrutementservice.services;


import com.example.recrutementservice.Dtos.CondidatRequestDto;
import com.example.recrutementservice.Dtos.CondidatResponseDto;
import com.example.recrutementservice.Exception.CondidatureNotFoundException;
import com.example.recrutementservice.Exception.OffreNotFoundException;
import com.example.recrutementservice.entities.Condidat;
import com.example.recrutementservice.entities.Offre;
import com.example.recrutementservice.enums.Genre;
import com.example.recrutementservice.repository.CondidatRepository;
import com.example.recrutementservice.repository.OffreRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
public class CondidatService {
    final private CondidatRepository condidatRepository;
    final private OffreRepository offreRepository;
    public CondidatService(CondidatRepository condidatRepository, OffreRepository offreRepository) {
        this.condidatRepository = condidatRepository;
        this.offreRepository = offreRepository;
    }
    public ResponseEntity<List<CondidatResponseDto>> getAllCondidats(){
        List<Condidat> condidats=condidatRepository.findAll();
        List<CondidatResponseDto> dtoList=new ArrayList<>();
        condidats.forEach(c->{
            dtoList.add(c.toDto());
        });
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<?> getAllCondidatsByOffreIdEtEmailCondidat(Long offreId,String email){
        Condidat condidat=condidatRepository.findCondidatByOffreIdAndEmail(offreId,email);
       if(condidat==null){
           return ResponseEntity.ok("condidat n'existe pas ");
       }
        return  ResponseEntity.ok(condidat.toDto());
    }

    public ResponseEntity<List<CondidatResponseDto>> getAllCondidatsByOffreIdAndVille(Long offreId,String ville){
        List<Condidat> condidats=condidatRepository.findAllByOffreIdAndVille(offreId,ville);
        List<CondidatResponseDto> dtoList=new ArrayList<>();
        condidats.forEach(c->{
            dtoList.add(c.toDto());
        });
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<CondidatResponseDto>> getAllCondidatsByOffreIdDateCondidatureBeforeAndVille(Long offreId,LocalDateTime date,String ville){
        List<Condidat> condidats=condidatRepository.findAllByOffreIdAndDateCondidatureAfterAndVille(offreId,date,ville);
        List<CondidatResponseDto> dtoList=new ArrayList<>();
        condidats.forEach(c->{
            dtoList.add(c.toDto());
        });
        return  ResponseEntity.ok(dtoList);
    }


    public ResponseEntity<List<CondidatResponseDto>> getAllCondidatsByOffreIdDateCondidatureAfterAndVille(Long offreId,LocalDateTime date,String ville){
        List<Condidat> condidats=condidatRepository.findAllByOffreIdAndDateCondidatureBeforeAndVille(offreId,date,ville);
        List<CondidatResponseDto> dtoList=new ArrayList<>();
        condidats.forEach(c->{
            dtoList.add(c.toDto());
        });
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<CondidatResponseDto>> getAllCondidatsByOffreIdDateCondidatureBetweenAndVille(Long offreId,LocalDateTime date1,LocalDateTime date2,String ville){
        List<Condidat> condidats=condidatRepository.findAllByOffreIdAndDateCondidatureBetweenAndVille(offreId,date1,date2,ville);
        List<CondidatResponseDto> dtoList=new ArrayList<>();
        condidats.forEach(c->{
            dtoList.add(c.toDto());
        });
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<CondidatResponseDto>> getAllCondidatsByOffreIdEtGenre(Long offreId,Genre genre){
        List<Condidat> condidats=condidatRepository.findAllByOffreIdAndGenre(offreId, genre);
        List<CondidatResponseDto> dtoList=new ArrayList<>();
        condidats.forEach(c->{
            dtoList.add(c.toDto());
        });
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<CondidatResponseDto>> getAllCondidatsByOffreIdAdresseContaining(Long offreId,String adresse){
        List<Condidat> condidats=condidatRepository.findAllByOffreIdAndAdresseContainingIgnoreCase(offreId, adresse);
        List<CondidatResponseDto> dtoList=new ArrayList<>();
        condidats.forEach(c->{
            dtoList.add(c.toDto());
        });
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<CondidatResponseDto>> getAllCondidatsOfOffreId(Long offreId){
        List<Condidat> condidats=condidatRepository.findAllByOffreId(offreId);
        List<CondidatResponseDto> dtoList=new ArrayList<>();
        condidats.forEach(c->{
            dtoList.add(c.toDto());
        });
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<CondidatResponseDto>> getAllCondidatsWithOffreIdAndDateCondidatureBetween(Long offreId,LocalDateTime date1,LocalDateTime date2){
        List<Condidat> condidats=condidatRepository.findAllByOffreIdAndDateCondidatureBetween(offreId,date1,date2);
        List<CondidatResponseDto> dtoList=new ArrayList<>();
        condidats.forEach(c->{
            dtoList.add(c.toDto());
        });
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<CondidatResponseDto>> getAllCondidatsWithOffreIdAndDateCondidatureAfter(Long offreId,LocalDateTime date){
        List<Condidat> condidats=condidatRepository.findAllByOffreIdAndDateCondidatureAfter(offreId, date);
        List<CondidatResponseDto> dtoList=new ArrayList<>();
        condidats.forEach(c->{
            dtoList.add(c.toDto());
        });
        return  ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<CondidatResponseDto>> getAllCondidatsWithOffreIdAndDateCondidatureBefore(Long offreId,LocalDateTime date){
        List<Condidat> condidats=condidatRepository.findAllByOffreIdAndDateCondidatureBefore(offreId, date);
        List<CondidatResponseDto> dtoList=new ArrayList<>();
        condidats.forEach(c->{
            dtoList.add(c.toDto());
        });
        return  ResponseEntity.ok(dtoList);
    }


    public ResponseEntity<CondidatResponseDto> getCondidatById(Long condidatId){
        Condidat condidat=condidatRepository.findById(condidatId).orElse(null);
        if(condidat==null){
            throw new CondidatureNotFoundException("condidature not found");
        }
        return  ResponseEntity.ok(condidat.toDto());
    }

    public ResponseEntity<String> deleteCondidatById(Long condidatId){
        condidatRepository.deleteById(condidatId);
        return  ResponseEntity.ok("condidat est supprime avec succes");
    }

    public ResponseEntity<CondidatResponseDto> addCondidature(CondidatRequestDto condidatRequestDto) throws IOException {
        Offre offre=offreRepository.findById(condidatRequestDto.getOffreId()).orElseThrow(()-> new OffreNotFoundException("offre not found"));
        Path path= Paths.get(System.getProperty("user.home"),"app-gestion-RH","recrutement_CVs");
        String fileName= UUID.randomUUID().toString();

        Path filePath = null;
        if(condidatRequestDto.getCV()!=null) {
            String fileExtension = condidatRequestDto.getCV().getOriginalFilename().substring(condidatRequestDto.getCV().getOriginalFilename().lastIndexOf("."));
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            filePath = Paths.get(System.getProperty("user.home"), "app-gestion-RH", "recrutement_CVs", fileName + fileExtension);
            Files.copy(condidatRequestDto.getCV().getInputStream(), filePath);
        }
        Condidat condidat=Condidat.builder()
                .CV(filePath.toUri().toString())
                .nom(condidatRequestDto.getNom())
                .offre(offre)
                .tel(condidatRequestDto.getTel())
                .prenom(condidatRequestDto.getPrenom())
                .email(condidatRequestDto.getEmail())
                .motivation(condidatRequestDto.getMotivation())
                .adresse(condidatRequestDto.getAdresse())
                .dateCondidature(LocalDateTime.now())
                .genre(condidatRequestDto.getGenre())
                .ville(condidatRequestDto.getVille())
                .build();
        Condidat savedCondidat=condidatRepository.save(condidat);
        return  ResponseEntity.ok(savedCondidat.toDto());
    }


    public ResponseEntity<byte[]> getCV(Long condidatId) throws IOException {
        Condidat condidat=condidatRepository.findById(condidatId).orElseThrow(()-> new CondidatureNotFoundException("condidature not found"));
        String cv=condidat.getCV();
        return ResponseEntity.ok(Files.readAllBytes(Path.of(URI.create(cv))));

    }
}
