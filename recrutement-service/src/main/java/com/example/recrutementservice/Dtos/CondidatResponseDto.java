package com.example.recrutementservice.Dtos;
import com.example.recrutementservice.enums.Genre;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CondidatResponseDto {
    private  Long id;
    private String nom;
    private String prenom;
    private String tel;
    private String adresse;
    private String ville;
    private String email;
    private String motivation;
    private String CV;
    private Genre genre;
    private Long offreId;
    private LocalDateTime dateCondidature;
    private List<EntretienResponseDto> entretiens;
}
