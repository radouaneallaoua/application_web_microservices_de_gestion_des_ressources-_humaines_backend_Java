package com.example.employeservice.Dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EntiteResponseDto {
    private Long id;
    private String name;
    private Long typeId;
    private Long entiteMereId;
}
