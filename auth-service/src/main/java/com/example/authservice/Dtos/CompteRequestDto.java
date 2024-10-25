package com.example.authservice.Dtos;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompteRequestDto {
    private  String email;
    private  String password;
}
