package com.example.authservice.Clients;


import com.example.authservice.Dtos.CompteResponseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "EMPLOYE-SERVICE")
public interface EmployeFeignClient {
    @GetMapping("/employes/email/{email}")
    @CircuitBreaker(name = "employeService",fallbackMethod = "getDefaultEmail")
    String findByEmail(@PathVariable String email); //RETURN THE EMPLOYE ID

    @PutMapping("/employes/{employeId}/setCompte")
    @CircuitBreaker(name = "employeService",fallbackMethod = "getDefaultMessage")
    String updateEmployeCompte(@PathVariable String employeId, @RequestBody CompteResponseDto compteResponseDto); //RETURN THE EMPLOYE ID

    default String getDefaultEmail(Exception exception,String email){
        return "not found";
    }
    default String getDefaultMessage(Exception exception,String employeId){
        return "failed to update compte employe from the authService";
    }
}
