package com.example.absenceservice.clients;
import com.example.absenceservice.models.Employe;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "EMPLOYE-SERVICE")
public interface EmployeRestClient{
    @GetMapping("/employes/{employeId}")
    @CircuitBreaker(name = "EmployeService",fallbackMethod = "getDefaultEmploye")
    Employe findEmployeById(@PathVariable String employeId);

    default  Employe getDefaultEmploye(String employeId,Exception exception){
        return Employe.builder()
                .id(employeId)
                .nom("inconnu")
                .prenom("inconnu")
                .build();
    }


}
