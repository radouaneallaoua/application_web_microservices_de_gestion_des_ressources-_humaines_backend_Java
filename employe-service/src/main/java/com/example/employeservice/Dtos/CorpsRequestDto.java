package com.example.employeservice.Dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CorpsRequestDto {
    private String label;
    private Long categorieId;
}
