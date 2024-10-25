package com.example.paieservice.clients;


import com.example.paieservice.models.Employe;
import com.example.paieservice.models.Notification;
import com.example.paieservice.models.NotificationResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "EMPLOYE-SERVICE")
public interface EmployeRestClient {
    @GetMapping("/employes/{employeId}")

    Employe findEmployeById(@PathVariable String employeId);

    @PostMapping("/notifications")
    ResponseEntity<NotificationResponseDto> addNotification(@RequestBody  Notification notification);
}
