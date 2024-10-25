package com.example.employeservice.services;

import com.example.employeservice.Dtos.NotificationRequestDto;
import com.example.employeservice.Dtos.NotificationResponseDto;
import com.example.employeservice.Exception.NotificationNotFoundException;
import com.example.employeservice.entities.Employe;
import com.example.employeservice.entities.Notification;
import com.example.employeservice.enums.EtatNotification;
import com.example.employeservice.repository.EmployeRepository;
import com.example.employeservice.repository.NotificationRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final EmployeRepository employeRepository;
    public NotificationService(NotificationRepository notificationRepository, EmployeRepository employeRepository) {
        this.notificationRepository = notificationRepository;
        this.employeRepository = employeRepository;
    }



    public ResponseEntity<NotificationResponseDto> ajouterNotification(NotificationRequestDto notificationRequestDto){
        Employe employe=employeRepository.findById(notificationRequestDto.getEmployeId()).orElse(null);
        if(employe==null){
            throw new NotFoundException("employe n'existe pas");
        }
        Notification notification=Notification.builder()
                .etatNotification(EtatNotification.NONLUE)
                .date(notificationRequestDto.getDate())
                .contenu(notificationRequestDto.getDescription())
                .titre(notificationRequestDto.getTitre())
                .employe(employe)
                .build();
        Notification savedNotification=notificationRepository.save(notification);
        return ResponseEntity.ok(savedNotification.toDto());
    }

    public ResponseEntity<NotificationResponseDto> updateNotification(Long notificationId){
        Notification notification=notificationRepository.findById(notificationId).orElseThrow(()-> new NotificationNotFoundException("notification not found"));
        notification.setEtatNotification(EtatNotification.LUE);
        Notification savedNotification=notificationRepository.save(notification);
        return ResponseEntity.ok(savedNotification.toDto());
    }

    public ResponseEntity<List<NotificationResponseDto>> allNotificationsForEmployeId(String employeId){
        List<Notification> notifications=notificationRepository.findAllByEmployeIdOrderByDateDesc(employeId);
        List<NotificationResponseDto> list=new ArrayList<>();
        if(notifications!=null){
            notifications.forEach(n->{
                list.add(n.toDto());
            });
        }

        return ResponseEntity.ok(list);
    }
    public ResponseEntity<List<NotificationResponseDto>> allNotifications(){
        List<Notification> notifications=notificationRepository.findAll();
        List<NotificationResponseDto> list=new ArrayList<>();
        notifications.forEach(n -> {
            list.add(n.toDto());
        });

        return ResponseEntity.ok(list);
    }

    public ResponseEntity<List<NotificationResponseDto>> allNotificationsAvecEmployeIdEtEtatNotification(String employeId, EtatNotification etatNotification){
        List<Notification> notifications=notificationRepository.findAllByEmployeIdAndEtatNotification(employeId,etatNotification);
        List<NotificationResponseDto> list=new ArrayList<>();
        if(notifications!=null){
            notifications.forEach(n->{
                list.add(n.toDto());
            });
        }

        return ResponseEntity.ok(list);
    }


    public ResponseEntity<List<NotificationResponseDto>> allNotificationsAvecEmployeIdEtDateApres(String employeId, LocalDateTime date){
        List<Notification> notifications=notificationRepository.findAllByEmployeIdAndDateAfter(employeId,date);
        List<NotificationResponseDto> list=new ArrayList<>();
        if(notifications!=null){
            notifications.forEach(n->{
                list.add(n.toDto());
            });
        }

        return ResponseEntity.ok(list);
    }

    public ResponseEntity<List<NotificationResponseDto>> allNotificationsAvecEmployeIdEtDateAvant(String employeId, LocalDateTime date){
        List<Notification> notifications=notificationRepository.findAllByEmployeIdAndDateBefore(employeId,date);
        List<NotificationResponseDto> list=new ArrayList<>();
        if(notifications!=null){
            notifications.forEach(n->{
                list.add(n.toDto());
            });
        }

        return ResponseEntity.ok(list);
    }



}
