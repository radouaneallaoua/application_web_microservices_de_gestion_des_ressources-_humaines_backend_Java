package com.example.paieservice.dtos;

import com.example.paieservice.models.Employe;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleResponseDto {
    private Long id;
    private String label;
    private List<Employe> employeResponseDtos;

}
