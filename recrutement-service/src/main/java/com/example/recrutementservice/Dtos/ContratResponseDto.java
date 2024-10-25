package com.example.recrutementservice.Dtos;

import com.example.recrutementservice.enums.TypeContrat;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContratResponseDto {
    private Long id;
    private TypeContrat typeContrat;

}
