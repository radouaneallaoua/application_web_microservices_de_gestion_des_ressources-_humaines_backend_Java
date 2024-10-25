package com.example.paieservice.services;

import com.example.paieservice.clients.EmployeRestClient;
import com.example.paieservice.dtos.*;
import com.example.paieservice.entities.HistoriqueDesPaies;
import com.example.paieservice.enums.SituationFamiliale;
import com.example.paieservice.enums.TypePaiement;
import com.example.paieservice.exception.PaieNotFoundException;

import com.example.paieservice.models.Employe;
import com.example.paieservice.models.Notification;
import com.example.paieservice.models.NotificationResponseDto;
import com.example.paieservice.repository.HistoriquePaiesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class HistoriquesPaiesService {
    final  private HistoriquePaiesRepository historiquePaiesRepository;
    final  private EmployeRestClient employeRestClient;


    public HistoriquesPaiesService(HistoriquePaiesRepository historiquePaiesRepository, EmployeRestClient employeRestClient) {
        this.historiquePaiesRepository = historiquePaiesRepository;
        this.employeRestClient = employeRestClient;
    }

    public ResponseEntity<HistoriqueDesPaiesResponseDto>  getPaieById(String paieId){

        HistoriqueDesPaies historiqueDesPaies=historiquePaiesRepository.findById(paieId).orElseThrow(()-> new PaieNotFoundException("paie not found with id "+paieId));
        historiqueDesPaies.setEmploye(employeRestClient.findEmployeById(historiqueDesPaies.getEmployeId()));
        return ResponseEntity.ok(historiqueDesPaies.toDto());
    }

    public ResponseEntity<HistoriqueDesPaiesResponseDto>  ajouterPaie(HistoriqueDesPaiesRequestDto historiqueDesPaiesRequestDto) throws IOException {
        Path path = Paths.get(System.getProperty("user.home"), "gestion-RH", "recus_paiement");
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        String fileName = UUID.randomUUID().toString();
        String fileExtension = historiqueDesPaiesRequestDto.getRecu().getOriginalFilename().substring(historiqueDesPaiesRequestDto.getRecu().getOriginalFilename().lastIndexOf("."));
        Path recuURL = Paths.get(System.getProperty("user.home"), "gestion-RH", "recus_paiement", fileName + fileExtension);
        Files.copy(historiqueDesPaiesRequestDto.getRecu().getInputStream(), recuURL);

        HistoriqueDesPaies historiqueDesPaies=HistoriqueDesPaies.builder()
                .id(UUID.randomUUID().toString())
                .montant(historiqueDesPaiesRequestDto.getMontant())
                .employeId(historiqueDesPaiesRequestDto.getEmployeId())
                .datePaiement(historiqueDesPaiesRequestDto.getDatePaiement())
                .typePaiement(historiqueDesPaiesRequestDto.getTypePaiement())
                .recu(recuURL.toUri().toString())
                .employe(employeRestClient.findEmployeById(historiqueDesPaiesRequestDto.getEmployeId()))
                .build();
        HistoriqueDesPaies savedHistorique=historiquePaiesRepository.save(historiqueDesPaies);
        Notification notification= Notification.builder()
                .titre("votre paiement de ce mois est passe")
                .date(LocalDateTime.now())
                .description("le paiement de ce mois est effectue par le responsable-RH")
                .employeId(historiqueDesPaiesRequestDto.getEmployeId())
                .build();
        ResponseEntity<NotificationResponseDto> notificationResponseDto=employeRestClient.addNotification(notification);

        return ResponseEntity.ok(savedHistorique.toDto());
    }


    public ResponseEntity<HistoriqueDesPaiesResponseDto>  modifierPaie(String paieId,HistoriqueDesPaiesRequestDto historiqueDesPaiesRequestDto) throws IOException {
        HistoriqueDesPaies foundPaie=historiquePaiesRepository.findById(paieId).orElseThrow(()-> new PaieNotFoundException("paie not found exception"));
        Path oldRecuURL=Paths.get(System.getProperty("user.home"), "gestion-RH", "recus_paiement",foundPaie.getRecu());
        Files.delete(oldRecuURL);
        Path path = Paths.get(System.getProperty("user.home"), "gestion-RH", "recus_paiement");
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        String fileName = UUID.randomUUID().toString();
        String fileExtension = historiqueDesPaiesRequestDto.getRecu().getOriginalFilename().substring(historiqueDesPaiesRequestDto.getRecu().getOriginalFilename().lastIndexOf("."));
        Path recuURL = Paths.get(System.getProperty("user.home"), "gestion-RH", "recus_paiement", fileName + fileExtension);
        Files.copy(historiqueDesPaiesRequestDto.getRecu().getInputStream(), recuURL);
        foundPaie.setDatePaiement(historiqueDesPaiesRequestDto.getDatePaiement());
        foundPaie.setRecu(recuURL.toUri().toString());
        foundPaie.setMontant(historiqueDesPaiesRequestDto.getMontant());
        foundPaie.setEmployeId(historiqueDesPaiesRequestDto.getEmployeId());
        foundPaie.setEmploye(employeRestClient.findEmployeById(historiqueDesPaiesRequestDto.getEmployeId()));
        HistoriqueDesPaies savedHistorique=historiquePaiesRepository.save(foundPaie);
        return ResponseEntity.ok(savedHistorique.toDto());
    }

    public ResponseEntity<String> supprimerPaie(String paieId){
        historiquePaiesRepository.deleteById(paieId);
        return  ResponseEntity.ok("paie supprimee avec id"+paieId);
    }

    public ResponseEntity<List<HistoriqueDesPaiesResponseDto>> toutHistoriques(){
        List<HistoriqueDesPaies> historiqueDesPaies= historiquePaiesRepository.findAll();
        List<HistoriqueDesPaiesResponseDto> dtos=new ArrayList<>();
        historiqueDesPaies.forEach(h -> {
            h.setEmploye(employeRestClient.findEmployeById(h.getEmployeId()));
            dtos.add(h.toDto());
        });
        return  ResponseEntity.ok(dtos);
    }

    public ResponseEntity<List<HistoriqueDesPaiesResponseDto>> toutHistoriquesAvecEmployeId(String employeId){
        List<HistoriqueDesPaies> historiqueDesPaies= historiquePaiesRepository.findAllByEmployeId(employeId);
        List<HistoriqueDesPaiesResponseDto> dtos=new ArrayList<>();
        if(historiqueDesPaies!=null){
            historiqueDesPaies.forEach(h->{
                h.setEmploye(employeRestClient.findEmployeById(h.getEmployeId()));
                dtos.add(h.toDto());
            });
        }
        return  ResponseEntity.ok(dtos);
    }
    public ResponseEntity<List<HistoriqueDesPaiesResponseDto>> toutHistoriquesAvecDatePaiementApres(LocalDateTime date){
        List<HistoriqueDesPaies> historiqueDesPaies= historiquePaiesRepository.findAllByDatePaiementAfter(date);
        List<HistoriqueDesPaiesResponseDto> dtos=new ArrayList<>();
        if(historiqueDesPaies!=null){
            historiqueDesPaies.forEach(h->{
                h.setEmploye(employeRestClient.findEmployeById(h.getEmployeId()));
                dtos.add(h.toDto());
            });
        }
        return  ResponseEntity.ok(dtos);
    }

    public ResponseEntity<List<HistoriqueDesPaiesResponseDto>> toutHistoriquesAvecDatePaiementAvant(LocalDateTime date){
        List<HistoriqueDesPaies> historiqueDesPaies= historiquePaiesRepository.findAllByDatePaiementBefore(date);
        List<HistoriqueDesPaiesResponseDto> dtos=new ArrayList<>();
        if(historiqueDesPaies!=null){
            historiqueDesPaies.forEach(h->{
                h.setEmploye(employeRestClient.findEmployeById(h.getEmployeId()));
                dtos.add(h.toDto());
            });
        }
        return  ResponseEntity.ok(dtos);
    }

    public ResponseEntity<List<HistoriqueDesPaiesResponseDto>> toutHistoriquesAvecTypePaiement(TypePaiement typePaiement){
        List<HistoriqueDesPaies> historiqueDesPaies= historiquePaiesRepository.findAllByTypePaiement(typePaiement);
        List<HistoriqueDesPaiesResponseDto> dtos=new ArrayList<>();
        if(historiqueDesPaies!=null){
            historiqueDesPaies.forEach(h->{
                h.setEmploye(employeRestClient.findEmployeById(h.getEmployeId()));
                dtos.add(h.toDto());
            });
        }
        return  ResponseEntity.ok(dtos);
    }

    public String getZone(String region) {
        return switch (region) {
            case "Région de Rabat-Salé-Kénitra", "Région de Casablanca-Settat" -> "Zone A";
            case "Région de Fès-Meknès", "Région de Marrakech-Safi", "Région de Tanger-Tétouan-Al Hoceïma",
                 "Région de l'Oriental" -> "Zone B";
            case "Région de Béni Mellal-Khénifra", "Région de Drâa-Tafilalet", "Région de Souss-Massa",
                 "Région de Guelmim-Oued Noun", "Région de Laâyoune-Sakia El Hamra", "Région de Dakhla-Oued Ed Dahab" ->
                    "Zone C";
            default -> "Unknown Region";
        };
    }

    public ResponseEntity<String> calculerSalaire(String employeId) {
        Employe employe = employeRestClient.findEmployeById(employeId);
        int indice = employe.getIndiceEchelon().getIndice();
        int echelon = employe.getIndiceEchelon().getEchelon();
        GradeResponseDto grade = employe.getGrade();
        RegionResponseDto region = employe.getRegion();
        double tauxIdemnite = getTauxIndemnite(region.getLabel());


        double salaireDeBase = 0;

        if (indice > 150) {
            salaireDeBase += 100 * 98.85;
            salaireDeBase += 50 * 79.62;
            salaireDeBase += (indice - 150) * 50.92;
        } else if (indice > 100) {
            salaireDeBase += 100 * 98.85;
            salaireDeBase += (indice - 100) * 79.62;
        } else {
            salaireDeBase += indice * 98.85;
        }

        double indemniteDeResidence = salaireDeBase * tauxIdemnite;
        final double[] allocationsFamiliales = {0};
        double indemniteStatutaires = 0;

        double indemniteDeTechnicite = 0;
        double indemniteDeSujetion = 0;
        double indemniteDeEncadrement = 0;
        double indemniteAdministrative = 0;
        String label = grade.getLabel();

        if (label.equals("administrateur 3e grade")) {
            if (echelon >= 1 && echelon <= 5) {
                indemniteAdministrative = 3914;
                indemniteDeSujetion = 1450;
            } else if (echelon >= 6) {
                indemniteAdministrative = 3979;
                indemniteDeSujetion = 1450;
                indemniteDeEncadrement = 883;
            }
        } else if (label.equals("administrateur 2e grade")) {
            if (echelon >= 1 && echelon <= 5) {
                indemniteAdministrative = 5557;
                indemniteDeSujetion = 1450;
                indemniteDeEncadrement = 1565;
            } else if (echelon >= 6) {
                indemniteAdministrative = 6267;
                indemniteDeSujetion = 1450;
                indemniteDeEncadrement = 4650;
            }
        } else if (label.equals("administrateur 1er grade")) {
            indemniteAdministrative = 7096;
            indemniteDeSujetion = 1450;
            indemniteDeEncadrement = 7850;
        } else if (label.equals("Ingénieurs d'application - premier grade")) {
            indemniteDeTechnicite = 3867;
            indemniteDeSujetion = 1600;
            indemniteDeEncadrement = 0;
        } else if (label.equals("Ingénieurs d'application-grade principal")) {
            indemniteDeTechnicite = 4040;
            indemniteDeSujetion = 1600;
            indemniteDeEncadrement = 1790;
        } else if (label.equals("Ingénieurs d'Etat et architectes-premier grade")) {
            indemniteDeTechnicite = 6092;
            indemniteDeSujetion = 1600;
            indemniteDeEncadrement = 3200;
        } else if (label.equals("Ingénieurs d'Etat et architectes-grade principal")) {
            indemniteDeTechnicite = 6422;
            indemniteDeSujetion = 1600;
            indemniteDeEncadrement = 6770;
        } else if (label.equals("Ingénieurs en chefs et Architectes en chef-premier grade")) {
            indemniteDeTechnicite = 7650;
            indemniteDeSujetion = 1600;
            indemniteDeEncadrement = 8980;
        } else if (label.equals("Ingénieurs en chefs et Architectes en chef-grade principal")) {
            indemniteDeTechnicite = 11188;
            indemniteDeSujetion = 1600;
            indemniteDeEncadrement = 14357;
        }else if (label.equals("Technicien 4e grade")) {
            indemniteDeTechnicite = 3687;
            indemniteDeSujetion = 305;

        } else if (label.equals("Technicien 3e grade")) {
            indemniteDeTechnicite = 3971;
            indemniteDeSujetion = 305;

        } else if (label.equals("Technicien 2e grade")) {
            if (echelon >= 1 && echelon <= 5) {
                indemniteDeTechnicite = 4364;
                indemniteDeSujetion = 1000;

            } else if (echelon >= 6) {
                indemniteDeTechnicite = 4417;
                indemniteDeSujetion = 1000;
                indemniteDeEncadrement = 700;
            }
        }  else if (label.equals("Technicien 1e grade")) {
            if (echelon >= 1 && echelon <= 5) {
                indemniteDeTechnicite = 5880;
                indemniteDeSujetion = 1000;
                indemniteDeEncadrement=950;
            } else if (echelon >= 6 && echelon<=10) {
                indemniteDeTechnicite = 7264;
                indemniteDeSujetion = 1000;
                indemniteDeEncadrement = 3600;
            }else if (echelon >= 11 && echelon<=13) {
                indemniteDeTechnicite = 7713;
                indemniteDeSujetion = 1000;
                indemniteDeEncadrement = 3600;
            }
        }

        indemniteStatutaires+=indemniteAdministrative+indemniteDeEncadrement+indemniteDeTechnicite+indemniteDeSujetion;
        double salaireImposableMensuelBrut = salaireDeBase
                + indemniteDeResidence
                + indemniteStatutaires
                - allocationsFamiliales[0];
        double CMR = salaireImposableMensuelBrut * 0.1;
        double cotisationMutuelles = 0;
        double mutuelleSC = Math.min(salaireImposableMensuelBrut * 0.025, 400);
        double mutuelleAMO = 400;
        cotisationMutuelles += mutuelleAMO + mutuelleSC;

        double taxeProfession = Math.min((salaireImposableMensuelBrut * 0.2), 2333.34);
        double retenus = CMR + cotisationMutuelles + taxeProfession;
        double salaireImposableMensuelNet = salaireImposableMensuelBrut - retenus;
        double salaireImposableAnnuelNet = salaireImposableMensuelNet * 12;
        double IGRAnnuelBrut = 0;

        if (salaireImposableAnnuelNet > 150000) {
            IGRAnnuelBrut = salaireImposableAnnuelNet * 0.4 - 18440;
        } else if (salaireImposableAnnuelNet > 60000) {
            IGRAnnuelBrut = salaireImposableAnnuelNet * 0.38 - 15440;
        } else if (salaireImposableAnnuelNet > 50000) {
            IGRAnnuelBrut = salaireImposableAnnuelNet * 0.34 - 13040;
        } else if (salaireImposableAnnuelNet > 40000) {
            IGRAnnuelBrut = salaireImposableAnnuelNet * 0.24 - 8040;
        } else if (salaireImposableAnnuelNet > 28000) {
            IGRAnnuelBrut = salaireImposableAnnuelNet * 0.12 - 3240;
        } else {
            IGRAnnuelBrut = 0;
        }

        double IGRMensuelBrut = IGRAnnuelBrut / 12;
        int nombreFemmeEtEnfants = 0;

        SituationFamiliale situationFamiliale = employe.getSituationFamiliale();
        if (situationFamiliale == SituationFamiliale.MARIE) {
            if (!employe.getConjoints().isEmpty()) {
                nombreFemmeEtEnfants += employe.getConjoints().size();
            }
            if (!employe.getEnfants().isEmpty()) {
                nombreFemmeEtEnfants += employe.getEnfants().size();
                List<EnfantResponseDto> enfantResponseDtoList = employe.getEnfants();
                final int[] sommeEnfantPasse = {0};
                enfantResponseDtoList.forEach(e -> {
                    LocalDate dateNaissance = e.getDateNaissance();
                    Period period = Period.between(LocalDate.now(), dateNaissance);
                    if (period.getYears() < 21) {
                        if (sommeEnfantPasse[0] <= 6) {
                            allocationsFamiliales[0] += sommeEnfantPasse[0] <= 3 ? 300 : 36;
                            sommeEnfantPasse[0]++;
                        }
                    }
                });
            }
        }

        double IGRMensuelNet = IGRMensuelBrut - 30 * nombreFemmeEtEnfants;
        double salaireNetMensuel=salaireImposableMensuelNet-IGRMensuelNet;
        return ResponseEntity.ok("le salaire net monsuel d'employe est :"+salaireNetMensuel);
    }

    public  double getTauxIndemnite(String region) {
        switch (region) {
            case "Casablanca-Settat":
            case "Rabat-Salé-Kénitra":
            case "Tanger-Tétouan-Al Hoceïma":
                return 35.0/100;

            case "Marrakech-Safi":
            case "Fès-Meknès":
            case "L'Oriental":
            case "Souss-Massa":
                return 30.0/100;

            case "Béni Mellal-Khénifra":
            case "Drâa-Tafilalet":
            case "Guelmim-Oued Noun":
            case "Laâyoune-Sakia El Hamra":
            case "Dakhla-Oued Ed-Dahab":
                return 25.0/100;
        }
        return 0;
    }

    public ResponseEntity<byte[]> getRecu(String paieId) throws IOException {
        HistoriqueDesPaies historiqueDesPaies=historiquePaiesRepository.findById(paieId).orElseThrow(()-> new PaieNotFoundException("paie not found"));
        String recu=historiqueDesPaies.getRecu();
        return ResponseEntity.ok(Files.readAllBytes(Path.of(URI.create(recu))));
    }
}
