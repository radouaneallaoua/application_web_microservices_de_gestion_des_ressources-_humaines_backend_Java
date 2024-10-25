package com.example.avancementservice.models;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    private Long gradeId;
    private String label;
}
