package com.example.paieservice.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CorpsResponseDto {
    private Long id;
    private String label;
    private CategorieResponseDto categorie;
}
