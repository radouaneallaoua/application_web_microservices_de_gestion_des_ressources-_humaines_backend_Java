package com.example.avancementservice.clients;


import com.example.avancementservice.models.Employe;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "EMPLOYE-SERVICE")
public interface EmployeRestClient {

    @GetMapping("/employes/{employeId}")
    Employe findEmployeById(@PathVariable String employeId);

    @PutMapping("/employes/{employeId}/update-from-avancement")
    ResponseEntity<Employe> updateEmployeWithGradeIdAndIndiceIdAndEntiteIdAndPosteId(@PathVariable String employeId,
                                                                            @RequestParam(name = "nouveauGradeId") Long gradeId,
                                                                            @RequestParam(name = "nouvelIndiceEchelonId") Long indiceEchelonId,
                                                                            @RequestParam(name = "nouvelleEntiteId") Long entiteId,
                                                                            @RequestParam(name = "nouveauPosteId") Long posteId);
}
