package com.example.avancementservice.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategorieResponseDto {
    private Long id;
    private String name;
}
