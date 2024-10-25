package com.example.employeservice.Dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TypeResponseDto {
    private Long id;
    private String label;

}
