package com.example.employeservice.Dtos;

import jakarta.annotation.Nullable;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EntiteRequestDto {
    private String name;
    private Long typeId;
}
