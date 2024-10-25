package com.example.formationservice.clients;


import com.example.formationservice.dtos.EntiteEmployeResponseDto;
import com.example.formationservice.models.Employe;
import com.example.formationservice.models.Entite;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "EMPLOYE-SERVICE")
public interface EmployeRestClient{
    @GetMapping("/employes/{employeId}")
    @CircuitBreaker(name = "employeService",fallbackMethod = "getDefaultEmploye")
    Employe findEmployeById(@PathVariable String employeId);

    default  Employe getDefaultEmploye(String employeId,Exception exception){
        return Employe.builder()
                .id(employeId)
                .nom("inconnu")
                .prenom("inconnu")
                .build();
    }

    @GetMapping("/entites/{entiteId}")
    Entite findEntiteById(@PathVariable Long entiteId);
    @GetMapping("/entites")
    List<Entite> getAllEntites();
    @GetMapping("/entites/employe/{employeId}/entite-actuelle")
    ResponseEntity<EntiteEmployeResponseDto> findEmployeEntiteActuelleById(@PathVariable String employeId);

    @GetMapping("entites/entite-employe/{entiteId}")
    ResponseEntity<List<EntiteEmployeResponseDto>> getAllEntitesEmployesWithEntiteId(@PathVariable Long entiteId);
}
