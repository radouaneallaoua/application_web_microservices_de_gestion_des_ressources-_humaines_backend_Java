package com.example.paieservice.dtos;

import com.example.paieservice.enums.TypePaiement;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Builder
@Getter @Setter
public class HistoriqueDesPaiesRequestDto {
    private double montant;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime datePaiement;
    private TypePaiement typePaiement;
    private MultipartFile recu;
    private String employeId;
}
