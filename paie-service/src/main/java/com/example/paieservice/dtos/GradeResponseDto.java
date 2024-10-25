package com.example.paieservice.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GradeResponseDto {
    private Long id;
    private String label;
    private CadreResponseDto cadre;
}
