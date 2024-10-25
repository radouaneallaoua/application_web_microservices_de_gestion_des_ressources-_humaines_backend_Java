package com.example.employeservice.web;

import com.example.employeservice.Dtos.NotificationRequestDto;
import com.example.employeservice.Dtos.NotificationResponseDto;
import com.example.employeservice.enums.EtatNotification;
import com.example.employeservice.services.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;
    public NotificationController(NotificationService notificationService, NotificationService notificationService1) {
        this.notificationService = notificationService1;
    }

    @GetMapping("/all/{employeId}")
    public ResponseEntity<List<NotificationResponseDto>> getNotificationsOfTheEmploye(@PathVariable  String employeId) {
        return notificationService.allNotificationsForEmployeId(employeId);
    }
    @GetMapping("")
    public ResponseEntity<List<NotificationResponseDto>> getAllNotifications() {
        return notificationService.allNotifications();
    }


    @GetMapping("/all/{employeId}/etatNotification/{etatNotification}")
    public ResponseEntity<List<NotificationResponseDto>> getNotificationsOfTheEmployWithEtat(@PathVariable  String employeId, @PathVariable EtatNotification etatNotification) {
        return notificationService.allNotificationsAvecEmployeIdEtEtatNotification(employeId,etatNotification);
    }

    @GetMapping("/all/{employeId}/dateAfter")
    public ResponseEntity<List<NotificationResponseDto>> getNotificationsOfTheEmployeDateAfter(@PathVariable  String employeId, @RequestParam("dateApres")LocalDateTime date) {
        return notificationService.allNotificationsAvecEmployeIdEtDateApres(employeId,date);
    }

    @GetMapping("/all/{employeId}/dateBefore")
    public ResponseEntity<List<NotificationResponseDto>> getNotificationsOfTheEmployeDateBefore(@PathVariable  String employeId, @RequestParam("dateAvant")LocalDateTime date) {
        return notificationService.allNotificationsAvecEmployeIdEtDateAvant(employeId,date);
    }

    @PostMapping("")
    public ResponseEntity<NotificationResponseDto> addNotification(@RequestBody NotificationRequestDto notificationRequestDto) {
        return notificationService.ajouterNotification(notificationRequestDto);
    }

    @PutMapping("/{noticationId}")
    public ResponseEntity<NotificationResponseDto> updateNotification(@PathVariable Long noticationId) {
        return notificationService.updateNotification(noticationId);
    }

}
