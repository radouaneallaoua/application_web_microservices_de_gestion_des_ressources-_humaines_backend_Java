package com.example.recrutementservice.services;
import com.example.recrutementservice.Dtos.OffreRequestDto;
import com.example.recrutementservice.Dtos.OffreResponseDto;
import com.example.recrutementservice.Exception.ContratNotFoundException;
import com.example.recrutementservice.Exception.OffreNotFoundException;
import com.example.recrutementservice.entities.Contrat;
import com.example.recrutementservice.entities.Offre;
import com.example.recrutementservice.repository.ContratRepository;
import com.example.recrutementservice.repository.OffreRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OffreService {
    private final OffreRepository offreRepository;
    private final ContratRepository contratRepository;

    public OffreService(OffreRepository offreRepository, ContratRepository contratRepository) {
        this.offreRepository = offreRepository;
        this.contratRepository = contratRepository;
    }

    public ResponseEntity<List<OffreResponseDto>> getAllOffres(){
        List<Offre> offres=offreRepository.findAll();
        List<OffreResponseDto> dtoList=new ArrayList<>();
        offres.forEach(o->{
            dtoList.add(o.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecDatePublicationApres(LocalDate date){
        List<Offre> offres=offreRepository.findAllByDatePublicationAfter(date);
        List<OffreResponseDto> dtoList=new ArrayList<>();
        offres.forEach(o->{
            dtoList.add(o.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }
    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecDateContrat(Long contratId){
        Contrat contrat=contratRepository.findById(contratId).orElseThrow(()-> new ContratNotFoundException("contrat not found"));
        List<Offre> offres=offreRepository.findAllByTypeContrats(List.of(contrat));
        List<OffreResponseDto> dtoList=new ArrayList<>();
        offres.forEach(o->{
            dtoList.add(o.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }
    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecDateLimiteCondidatureApres(LocalDate date){
        List<Offre> offres=offreRepository.findAllByDateLimiteCondidatureAfter(date);
        List<OffreResponseDto> dtoList=new ArrayList<>();
        offres.forEach(o->{
            dtoList.add(o.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }
    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecDateLimiteCondidatureAvant(LocalDate date){
        List<Offre> offres=offreRepository.findAllByDateLimiteCondidatureBefore(date);
        List<OffreResponseDto> dtoList=new ArrayList<>();
        offres.forEach(o->{
            dtoList.add(o.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecVille(String ville){
        List<Offre> offres=offreRepository.findAllByVilleContainingIgnoreCase(ville);
        List<OffreResponseDto> dtoList=new ArrayList<>();
        offres.forEach(o->{
            dtoList.add(o.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecNombreDePostesSup(int nombre){
        List<Offre> offres=offreRepository.findAllByNombreDePostesIsGreaterThan(nombre);
        List<OffreResponseDto> dtoList=new ArrayList<>();
        offres.forEach(o->{
            dtoList.add(o.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecNombreDePostesInf(int nombre){
        List<Offre> offres=offreRepository.findAllByNombreDePostesIsLessThan(nombre);
        List<OffreResponseDto> dtoList=new ArrayList<>();
        offres.forEach(o->{
            dtoList.add(o.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecDatePublicationAvant(LocalDate date){
        List<Offre> offres=offreRepository.findAllByDatePublicationBefore(date);
        List<OffreResponseDto> dtoList=new ArrayList<>();
        offres.forEach(o->{
            dtoList.add(o.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }
    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecDatePublicationEntre(LocalDate date1,LocalDate date2){
        List<Offre> offres=offreRepository.findAllByDatePublicationBetween(date1,date2);
        List<OffreResponseDto> dtoList=new ArrayList<>();
        offres.forEach(o->{
            dtoList.add(o.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }
    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecEstExpiree(boolean estExpiree){
        List<Offre> offres=offreRepository.findAllByEstExpiree(estExpiree);
        List<OffreResponseDto> dtoList=new ArrayList<>();
        offres.forEach(o->{
            dtoList.add(o.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }
    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecPosteContaining(String posteName){
        List<Offre> offres=offreRepository.findAllByPosteContainingIgnoreCase(posteName);
        List<OffreResponseDto> dtoList=new ArrayList<>();
        offres.forEach(o->{
            dtoList.add(o.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecVilleDateLimiteCondidatureAvantEtPosteContientEtNombreDePostesSuperieur(String villeRecherchee,LocalDate dateLimite,String posteRecherche,int nombreDePosteMin){
        List<Offre> offres=offreRepository.findAllByVilleContainingIgnoreCaseAndDateLimiteCondidatureBeforeAndPosteContainingIgnoreCaseAndNombreDePostesIsGreaterThan(villeRecherchee,dateLimite,posteRecherche,nombreDePosteMin);
        List<OffreResponseDto> dtoList=new ArrayList<>();
        offres.forEach(o->{
            dtoList.add(o.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecVilleDateLimiteCondidatureAvantEtPosteContientEtNombreDePostesSuperieurEtTypeContrats(String villeRecherchee,LocalDate dateLimite,String posteRecherche,int nombreDePosteMin,List<Long> contratIds){
        List<Contrat> contrats=new ArrayList<>();
        contratIds.forEach(c->{
            Contrat contrat=contratRepository.findById(c).orElseThrow(()-> new ContratNotFoundException("contrat not found"));
            contrats.add(contrat);
        });
        List<Offre> offres=offreRepository.findAllByVilleContainingIgnoreCaseAndDateLimiteCondidatureBeforeAndPosteContainingIgnoreCaseAndNombreDePostesIsGreaterThanAndTypeContrats(villeRecherchee,dateLimite,posteRecherche,nombreDePosteMin,contrats);
        List<OffreResponseDto> dtoList=new ArrayList<>();
        offres.forEach(o->{
            dtoList.add(o.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecVilleDateLimiteCondidatureAvantEtPosteContient(String villeRecherchee,LocalDate dateLimite,String posteRecherche){
        List<Offre> offres=offreRepository.findAllByVilleContainingIgnoreCaseAndDateLimiteCondidatureBeforeAndPosteContainingIgnoreCase(villeRecherchee,dateLimite,posteRecherche);
        List<OffreResponseDto> dtoList=new ArrayList<>();
        offres.forEach(o->{
            dtoList.add(o.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecVilleDateLimiteCondidatureAvant(String villeRecherchee,LocalDate dateLimite){
        List<Offre> offres=offreRepository.findAllByVilleContainingIgnoreCaseAndDateLimiteCondidatureBefore(villeRecherchee,dateLimite);
        List<OffreResponseDto> dtoList=new ArrayList<>();
        offres.forEach(o->{
            dtoList.add(o.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecPosteDateLimiteCondidatureAvant(String posteRecherche,LocalDate dateLimite){
        List<Offre> offres=offreRepository.findAllByPosteContainingIgnoreCaseAndDateLimiteCondidatureBefore(posteRecherche,dateLimite);
        List<OffreResponseDto> dtoList=new ArrayList<>();
        offres.forEach(o->{
            dtoList.add(o.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<OffreResponseDto>> getAllOffresAvecPosteEtVille(String villeRecherchee,String posteRecherche){
        List<Offre> offres=offreRepository.findAllByVilleContainingIgnoreCaseAndPosteContainingIgnoreCase(villeRecherchee,posteRecherche);
        List<OffreResponseDto> dtoList=new ArrayList<>();
        offres.forEach(o->{
            dtoList.add(o.toDto());
        });
        return ResponseEntity.ok(dtoList);
    }
    public ResponseEntity<OffreResponseDto> addOffre(OffreRequestDto offreRequestDto){
        List<Contrat> contrats=new ArrayList<>();
        offreRequestDto.getContratIds().forEach(o->{
            Contrat contrat=contratRepository.findById(o).orElseThrow(()-> new ContratNotFoundException("contrat not found"));
            contrats.add(contrat);
        });
        Offre offre=Offre.builder()
                .poste(offreRequestDto.getPoste())
                .datePublication(LocalDate.now())
                .titre(offreRequestDto.getTitre())
                .description(offreRequestDto.getDescription())
                .dateLimiteCondidature(offreRequestDto.getDateLimiteCondidature())
                .nombreDePostes(offreRequestDto.getNombreDePostes())
                .ville(offreRequestDto.getVille())
                .typeContrats(contrats)
                .estExpiree(false)
                .build();
        Offre savedOffre=offreRepository.save(offre);
        return  ResponseEntity.ok(savedOffre.toDto());
    }

    public ResponseEntity<OffreResponseDto> getOffreById(Long offreId){
        Offre offre=offreRepository.findById(offreId).orElseThrow(()-> new OffreNotFoundException("offre not found "));
        return  ResponseEntity.ok(offre.toDto());
    }

    public ResponseEntity<OffreResponseDto> updateOffre(Long offreId, OffreRequestDto offreRequestDto,boolean estExpiree){
        Offre foundOffre=offreRepository.findById(offreId).orElse(null);
        if(foundOffre==null){
            throw new OffreNotFoundException("offre not found");
        }
        List<Contrat> contrats=new ArrayList<>();
        offreRequestDto.getContratIds().forEach(id->{
            contrats.add(contratRepository.findById(id).get());
        });
        foundOffre.setPoste(offreRequestDto.getPoste());
        foundOffre.setTitre(offreRequestDto.getTitre());
        foundOffre.setEstExpiree(estExpiree);
        foundOffre.setDateLimiteCondidature(offreRequestDto.getDateLimiteCondidature());
        foundOffre.setNombreDePostes(offreRequestDto.getNombreDePostes());
        foundOffre.setVille(offreRequestDto.getVille());
        foundOffre.setTypeContrats(contrats);
        foundOffre.setDescription(offreRequestDto.getDescription());
        Offre savedOffre=offreRepository.save(foundOffre);
        return  ResponseEntity.ok(savedOffre.toDto());
    }

    public ResponseEntity<String> deleteOffreById(Long offreId){
        offreRepository.deleteById(offreId);
        return ResponseEntity.ok("offre supprimee avec succes");
    }

}
