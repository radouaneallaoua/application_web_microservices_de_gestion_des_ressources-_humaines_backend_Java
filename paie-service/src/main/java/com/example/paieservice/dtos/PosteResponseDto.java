package com.example.paieservice.dtos;

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
