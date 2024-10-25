package com.example.recrutementservice.Dtos;

import com.example.recrutementservice.enums.TypeContrat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContratRequestDto {
    private TypeContrat typeContrat;

}
