package com.example.paieservice.models;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notification {
    private String titre;
    private String description;
    private LocalDateTime date;
    private String employeId;

}
