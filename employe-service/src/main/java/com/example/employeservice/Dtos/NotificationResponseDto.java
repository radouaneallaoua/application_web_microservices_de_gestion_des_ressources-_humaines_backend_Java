package com.example.employeservice.Dtos;

import com.example.employeservice.enums.EtatNotification;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotificationResponseDto {
    private Long id;
    private String titre;
    private String contenu;
    private LocalDateTime date;
    private String employeId;
    private EtatNotification etatNotification;

}
