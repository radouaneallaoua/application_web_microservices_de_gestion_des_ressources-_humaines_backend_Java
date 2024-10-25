package com.example.employeservice.Dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubEntiteRequestDto {
    private String name;
    private Long typeId;
    private Long entiteMere;
}
