package com.example.avancementservice.models;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Entite {
    private Long entiteId;
    private String name;
    private Long typeId;
    private Long entiteMereId;
}
