package com.example.paieservice.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegionResponseDto {
    private Long id;
    private String label;

}
