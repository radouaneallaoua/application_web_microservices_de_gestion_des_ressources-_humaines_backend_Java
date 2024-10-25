package com.example.employeservice.entities;
import com.example.employeservice.Dtos.NotificationResponseDto;
import com.example.employeservice.enums.EtatNotification;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter @Getter @AllArgsConstructor @NoArgsConstructor @Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String contenu;
    private LocalDateTime date;
    private EtatNotification etatNotification;

    @ManyToOne
    private Employe employe;

    public NotificationResponseDto toDto(){
        return  NotificationResponseDto.builder()
                .date(date)
                .etatNotification(etatNotification)
                .contenu(contenu)
                .employeId(employe.getId())
                .id(id)
                .titre(titre)
                .build();
    }

}
