package com.example.recrutementservice.Dtos;


import com.example.recrutementservice.enums.Genre;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CondidatRequestDto {
    private String nom;
    private String prenom;
    private String tel;
    private String adresse;
    private String ville;
    private String email;
    private String motivation;
    private Genre genre;
    private MultipartFile CV;
    private Long offreId;
}
