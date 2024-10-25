package com.example.avancementservice.models;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Poste {
    private Long posteId;
    private  String label;
    private String description;
}
