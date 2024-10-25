package com.example.employeservice.Dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PosteResponseDto {
    private Long id;
    private String label;
    private String description;

}
