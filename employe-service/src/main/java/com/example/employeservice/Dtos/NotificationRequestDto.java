package com.example.employeservice.Dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotificationRequestDto {
    private String titre;
    private String description;
    private LocalDateTime date;
    private String employeId;

}
