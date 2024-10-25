package com.example.formationservice.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Entite {
    private Long id;
    private String name;
    private Long typeId;
    private Long entiteMereId;
}
