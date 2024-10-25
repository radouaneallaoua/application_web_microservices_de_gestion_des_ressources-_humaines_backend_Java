package com.example.recrutementservice.services;

import com.example.recrutementservice.Dtos.EvaluationRequestDto;
import com.example.recrutementservice.Dtos.EvaluationResponseDto;
import com.example.recrutementservice.Exception.EntretienEvaluationNotFoundException;
import com.example.recrutementservice.Exception.EntretienNotFoundException;
import com.example.recrutementservice.entities.Entretien;
import com.example.recrutementservice.entities.EntretienEvaluation;
import com.example.recrutementservice.repository.EntretienEvaluationRepository;
import com.example.recrutementservice.repository.EntretienRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EntretienEvaluationService {
    final private EntretienRepository entretienRepository;
    final private EntretienEvaluationRepository entretienEvaluationRepository;

    public EntretienEvaluationService(EntretienRepository entretienRepository, EntretienEvaluationRepository entretienEvaluationRepository) {
        this.entretienRepository = entretienRepository;
        this.entretienEvaluationRepository = entretienEvaluationRepository;
    }

    public ResponseEntity<List<EvaluationResponseDto>> allEntretiensEvaluations(){
        List<EntretienEvaluation> entretienEvaluations=entretienEvaluationRepository.findAll();
        List<EvaluationResponseDto> dtoList=new ArrayList<>();
        entretienEvaluations.forEach(e->{
            dtoList.add(e.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<EvaluationResponseDto> getEvaluationForEntretienById(Long entretienId){
        EntretienEvaluation entretienEvaluation=entretienEvaluationRepository.findEntretienEvaluationByEntretienId(entretienId);
        if(entretienEvaluation==null){
            throw new EntretienEvaluationNotFoundException("evaluation not found for entretien with id"+entretienId);
        }

        return ResponseEntity.ok(entretienEvaluation.toDto());
    }
    public ResponseEntity<List<EvaluationResponseDto>> allEntretiensAvecScoreSup(int score){
        List<EntretienEvaluation> entretienEvaluations=entretienEvaluationRepository.findAllByScoreIsGreaterThan(score);
        List<EvaluationResponseDto> dtoList=new ArrayList<>();
        entretienEvaluations.forEach(e->{
            dtoList.add(e.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<EvaluationResponseDto>> allEntretiensAvecScoreInf(int score){
        List<EntretienEvaluation> entretienEvaluations=entretienEvaluationRepository.findAllByScoreIsLessThan(score);
        List<EvaluationResponseDto> dtoList=new ArrayList<>();
        entretienEvaluations.forEach(e->{
            dtoList.add(e.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }




    public ResponseEntity<EvaluationResponseDto> addEntretienEvaluation(EvaluationRequestDto evaluationRequestDto){
        Entretien entretien=entretienRepository.findById(evaluationRequestDto.getEntretienId()).orElse(null);
        if(entretien==null){
            throw new EntretienNotFoundException("entretien not found");
        }
        EntretienEvaluation entretienEvaluation=EntretienEvaluation.builder()
                .entretien(entretien)
                .avis(evaluationRequestDto.getAvis())
                .score(evaluationRequestDto.getScore())
                .build();
        EntretienEvaluation savedEntretienEvaluation=entretienEvaluationRepository.save(entretienEvaluation);
        return  ResponseEntity.ok(savedEntretienEvaluation.toDto());
    }

    public ResponseEntity<EvaluationResponseDto> updateEntretienEvaluation(Long evaluationId,EvaluationRequestDto evaluationRequestDto){
        EntretienEvaluation entretienEvaluation=entretienEvaluationRepository.findById(evaluationId).orElse(null);
        if(entretienEvaluation==null){
            throw new EntretienEvaluationNotFoundException("evaluation entretien not found");
        }
        Entretien entretien=entretienRepository.findById(evaluationRequestDto.getEntretienId()).orElse(null);
        if(entretien==null){
            throw new EntretienNotFoundException("entretien not found");
        }
        entretienEvaluation.setEntretien(entretien);
        entretienEvaluation.setScore(evaluationRequestDto.getScore());
        entretienEvaluation.setAvis(evaluationRequestDto.getAvis());
        entretienEvaluation.setScore(evaluationRequestDto.getScore());
        EntretienEvaluation savedEntretienEvaluation=entretienEvaluationRepository.save(entretienEvaluation);
        return  ResponseEntity.ok(savedEntretienEvaluation.toDto());
    }

    public  ResponseEntity<String> deleteEntretienEvaluationById(Long entretienEvaluationId){
        entretienRepository.deleteById(entretienEvaluationId);
        return  ResponseEntity.ok("entretien evaluation supprime avec succes");
    }

}
