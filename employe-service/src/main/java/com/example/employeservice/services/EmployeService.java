package com.example.employeservice.services;
import com.example.employeservice.Dtos.*;
import com.example.employeservice.Exception.*;
import com.example.employeservice.Models.CompteModel;
import com.example.employeservice.entities.*;
import com.example.employeservice.enums.EtatEmploye;
import com.example.employeservice.enums.Genre;
import com.example.employeservice.enums.SituationFamiliale;
import com.example.employeservice.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.management.relation.RoleNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmployeService {
    private final EmployeRepository employeRepository;
    private final PosteRepository posteRepository;
    private final GradeRepository gradeRepository;
    private final MutuelleRepository mutuelleRepository;
    private final IndiceRepository indiceRepository;
    private final EnfantRepository enfantRepository;
    private final EntiteService entiteService;
    private final EntiteEmployeRepository entiteEmployeRepository;
    private final EntiteRepository entiteRepository;
    private final ConjointRepository conjointRepository;
    private final RegionRepository regionRepository;

    public EmployeService(EmployeRepository employeRepository, PosteRepository posteRepository, GradeRepository gradeRepository, MutuelleRepository mutuelleRepository, IndiceRepository indiceRepository, EnfantRepository enfantRepository, EntiteService entiteService, EntiteEmployeRepository entiteEmployeRepository, EntiteRepository entiteRepository, ConjointRepository conjointRepository, RegionRepository regionRepository) {
        this.employeRepository = employeRepository;
        this.posteRepository = posteRepository;
        this.gradeRepository = gradeRepository;
        this.mutuelleRepository = mutuelleRepository;
        this.indiceRepository = indiceRepository;
        this.enfantRepository = enfantRepository;
        this.entiteService = entiteService;
        this.entiteEmployeRepository = entiteEmployeRepository;
        this.entiteRepository = entiteRepository;
        this.conjointRepository = conjointRepository;
        this.regionRepository = regionRepository;
    }

    public ResponseEntity<String> deleteEmployeById(String employeId){
        Employe employe=employeRepository.findById(employeId).orElseThrow(()->new EmployeNotFoundException("Employe not found exception"));
        List<EntiteEmploye> entiteEmployes=entiteEmployeRepository.findAllByEmployeId(employeId);
        entiteEmployes.forEach(employe::removeEntiteEmploye);
        System.out.println("Entites deleted");
        System.out.println(employe.getEntiteEmployes());
        List<Conjoint> conjointList=conjointRepository.findAllByEmployeId(employeId);
        conjointList.forEach(employe::removeConjointEmploye);

        List<Enfant> enfantList=enfantRepository.findAllByEmployeId(employeId);
        enfantList.forEach(employe::removeEnfantEmploye);
        employeRepository.deleteById(employeId);
        return  ResponseEntity.ok("employe supprime avec succes");
    }

    public String getEmployeIdWithEmail(String email){
        Employe employe=employeRepository.findByEmail(email);
        if(employe==null){
            return "email not found";
        }
        return employe.getId();
    }

    public EmployeResponseDto getEmployeById(String employeId){
        Employe employe=employeRepository.findById(employeId).orElseThrow(()-> new EmployeNotFoundException(("employe not found")));
        employe.setEntiteEmployes(entiteEmployeRepository.findAllByEmployeId(employeId));
        employe.setConjoints(conjointRepository.findAllByEmployeId(employeId));
        employe.setEnfants(enfantRepository.findAllByEmployeId(employeId));
        return  employe.toDTO();
    }

    public ResponseEntity<EmployeResponseDto> updateEmployeWithGradeIdAndIndiceIdAndEntiteIdAndPosteId(String employeId,Long gradeId,Long indiceId,Long entiteId,Long posteId){
        Employe employe=employeRepository.findById(employeId).orElseThrow(()->new EmployeNotFoundException("employe not found"));
        Grade grade=gradeRepository.findById(gradeId).orElseThrow(()-> new GradeNotFoundException("grade not found"));
        IndiceEchelon indice=indiceRepository.findById(indiceId).orElseThrow(()-> new IndiceEchelonNotFoundException("indice not found"));
        Poste poste=posteRepository.findById(posteId).orElseThrow(()-> new PosteNotFoundException("poste not found"));
        employe.setGrade(grade);
        employe.setPoste(poste);
        employe.setIndiceEchelon(indice);
        if(employe.getEntiteEmployes().get(employe.getEntiteEmployes().size()-1).getId().getEntiteId()!=entiteId){
            entiteService.addEntiteEmploye(EntiteEmployeRequestDto.builder().employeId(employeId).entiteId(entiteId).dateDebut(LocalDate.now()).build());
        }
        return ResponseEntity.ok(employeRepository.save(employe).toDTO());
    }
    public ResponseEntity<EmployeResponseDto> updateEmployeWithImageAndEmailAndTelAndAdresseAndRegionAndSituationFamiliale(String employeId,String email,String tel,String adresse,Long regionId,SituationFamiliale situationFamiliale, MultipartFile image) throws IOException {
        Employe employe=employeRepository.findById(employeId).orElseThrow(()->new EmployeNotFoundException("employe not found"));
        if(email!=null){
            employe.setEmail(email);
        }
        if(tel!=null){
            employe.setTel(tel);
        }
        if(adresse!=null){
            employe.setAdresse(adresse);
        }
        if(email!=null){
            employe.setEmail(email);
        }
        if(regionId!=null){
            employe.setRegion(regionRepository.findById(regionId).orElseThrow(()-> new RegionNotFoundException("region not found")));
        }
        if(situationFamiliale!=null){
            employe.setSituationFamiliale(situationFamiliale);
        }
        if(image!=null){
            String oldFileName = employe.getImageURL();
            String name=oldFileName.substring(oldFileName.lastIndexOf("/")+1);
            Path path=Paths.get(System.getProperty("user.home"), "gestion-RH", "employes_images",name);
            Files.delete(path);
            String newFileName=UUID.randomUUID().toString();
            String newfileExtension = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
            Path newImageURL = Paths.get(System.getProperty("user.home"), "gestion-RH", "employes_images", newFileName + newfileExtension);
            Files.copy(image.getInputStream(), newImageURL);
            employe.setImageURL(newImageURL.toUri().toString());
        }
        return ResponseEntity.ok(employeRepository.save(employe).toDTO());
    }

    public ResponseEntity<EmployeResponseDto> saveEmploye(EmployeRequestDto employeRequestDto) throws IOException {
        Path path = Paths.get(System.getProperty("user.home"), "gestion-RH", "employes_images");
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        String fileName = UUID.randomUUID().toString();
        String fileExtension = employeRequestDto.getFile().getOriginalFilename().substring(employeRequestDto.getFile().getOriginalFilename().lastIndexOf("."));
        Path imageURL = Paths.get(System.getProperty("user.home"), "gestion-RH", "employes_images", fileName + fileExtension);
        Files.copy(employeRequestDto.getFile().getInputStream(), imageURL);

        List<Mutuelle> mutuelleList=new ArrayList<>();
        if(employeRequestDto.getMutuellesId()!=null) {
            employeRequestDto.getMutuellesId().forEach(id -> {
                Mutuelle mutuelle = mutuelleRepository.findById(id).orElseThrow(() -> new MutuelleNotFoundException("mutuelle not found exception"));
                mutuelleList.add(mutuelle);
            });
        }


        Employe employe = Employe.builder()
                .id(UUID.randomUUID().toString())
                .nom(employeRequestDto.getNom())
                .indiceEchelon(indiceRepository.findById(employeRequestDto.getIndiceEchelonId()).orElseThrow(()-> new IndiceEchelonNotFoundException("indice not found")))
                .adresse(employeRequestDto.getAdresse())
                .etatEmploye(employeRequestDto.getEtatEmploye())
                .dateNaissance(employeRequestDto.getDateNaissance())
                .anneeExperience(employeRequestDto.getAnneeExperience())
                .poste(posteRepository.findById(employeRequestDto.getPosteId()).orElseThrow(()-> new PosteNotFoundException("poste not found")))
                .grade(gradeRepository.findById(employeRequestDto.getGradeId()).orElseThrow(()-> new GradeNotFoundException("grade not found")))
                .dateRecrutement(employeRequestDto.getDateRecrutement())
                .prenom(employeRequestDto.getPrenom())
                .imageURL(imageURL.toUri().toString())
                .situationFamiliale(employeRequestDto.getSituationFamiliale())
                .ville(employeRequestDto.getVille())
                .genre(employeRequestDto.getGenre())
                .mutuelles(mutuelleList)
                .region(regionRepository.findById(employeRequestDto.getRegionId()).orElse(null))
                .tel(employeRequestDto.getTel())
                .email(employeRequestDto.getEmail())
                .CIN(employeRequestDto.getCIN())
                .build();
        Employe savedEmploye=employeRepository.save(employe);
        EntiteEmploye entiteEmploye=EntiteEmploye.builder()
                .entite(entiteRepository.findById(employeRequestDto.getEntiteId()).orElse(null))
                .dateDebut(LocalDate.now())
                .dateFin(null)
                .id(EntiteEmployeKey.builder().entiteId(employeRequestDto.getEntiteId()).employeId(savedEmploye.getId()).build())
                .employe(employeRepository.findById(savedEmploye.getId()).orElse(null))
                .build();
        EntiteEmploye e=entiteEmployeRepository.save(entiteEmploye);
        savedEmploye.setEntiteEmployes(List.of(e));
        return  ResponseEntity.ok(savedEmploye.toDTO());
    }
    public ResponseEntity<EmployeResponseDto> updateEmploye(String employeId,EmployeRequestDto employeRequestDto) throws IOException {
        Employe employe=employeRepository.findById(employeId).orElse(null);
        if(employe==null){
            throw new EmployeNotFoundException("employe n'existe pas avec id "+employeId);
        }
        String oldFileName = employe.getImageURL();
        Path path=Paths.get(System.getProperty("user.home"), "gestion-RH", "employes_images", oldFileName);
        Files.delete(path);
        String newFileName=UUID.randomUUID().toString();
        String newfileExtension = employeRequestDto.getFile().getOriginalFilename().substring(employeRequestDto.getFile().getOriginalFilename().lastIndexOf("."));
        Path newImageURL = Paths.get(System.getProperty("user.home"), "gestion-RH", "employes_images", newFileName + newfileExtension);
        Files.copy(employeRequestDto.getFile().getInputStream(), newImageURL);

        List<Mutuelle> mutuelleList=new ArrayList<>();
        if(employeRequestDto.getMutuellesId()!=null) {
            employeRequestDto.getMutuellesId().forEach(id -> {
                Mutuelle mutuelle = mutuelleRepository.findById(id).orElseThrow(() -> new MutuelleNotFoundException("mutuelle not found exception"));
                mutuelleList.add(mutuelle);
            });
        }
        employe.setNom(employeRequestDto.getNom());
        employe.setIndiceEchelon(indiceRepository.findById(employeRequestDto.getIndiceEchelonId()).orElseThrow(()-> new IndiceEchelonNotFoundException("indice not found")));
        employe.setAdresse(employeRequestDto.getAdresse());
        employe.setEtatEmploye(employeRequestDto.getEtatEmploye());
        employe.setDateNaissance(employeRequestDto.getDateNaissance());
        employe.setAnneeExperience(employeRequestDto.getAnneeExperience());
        employe.setPoste(posteRepository.findById(employeRequestDto.getPosteId()).orElseThrow(()-> new PosteNotFoundException("poste not found")));
        employe.setGrade(gradeRepository.findById(employeRequestDto.getGradeId()).orElseThrow(()-> new GradeNotFoundException("grade not found")));
        employe.setDateRecrutement(employeRequestDto.getDateRecrutement());
        employe.setPrenom(employeRequestDto.getPrenom());
        employe.setImageURL(newImageURL.toUri().toString());
        employe.setVille(employeRequestDto.getVille());
        employe.setGenre(employeRequestDto.getGenre());
        employe.setMutuelles(mutuelleList);
        employe.setCIN(employeRequestDto.getCIN());
        employe.setTel(employeRequestDto.getTel());
        Employe savedEmploye=employeRepository.save(employe);
        return  ResponseEntity.ok(savedEmploye.toDTO());
    }
    public ResponseEntity<List<EmployeResponseDto>> getAllEmploye(){
        List<Employe> employes=employeRepository.findAll();
        List<EmployeResponseDto> employeResponseDtos = new ArrayList<>();
        employes.forEach(e->{
            e.setEntiteEmployes(entiteEmployeRepository.findAllByEmployeId(e.getId()));
            e.setConjoints(conjointRepository.findAllByEmployeId(e.getId()));
            e.setEnfants(enfantRepository.findAllByEmployeId(e.getId()));
            employeResponseDtos.add(e.toDTO());
        });
        return  ResponseEntity.ok(employeResponseDtos);
    }
    public ResponseEntity<List<EmployeResponseDto>> recherche1(List<EntiteEmploye> entiteEmployes,Long posteId,Long gradeId,EtatEmploye etatEmploye){
        List<Employe> employes=employeRepository.findAllByEntiteEmployesIsContainingAndPosteIdAndGradeIdAndEtatEmploye(entiteEmployes,posteId,gradeId,etatEmploye);
        List<EmployeResponseDto> employeResponseDtos = new ArrayList<>();
        employes.forEach(e->{
            e.setEntiteEmployes(entiteEmployeRepository.findAllByEmployeId(e.getId()));
            e.setConjoints(conjointRepository.findAllByEmployeId(e.getId()));
            e.setEnfants(enfantRepository.findAllByEmployeId(e.getId()));
            employeResponseDtos.add(e.toDTO());
        });
        return  ResponseEntity.ok(employeResponseDtos);
    }
    public ResponseEntity<List<EmployeResponseDto>> recherche2(List<EntiteEmploye> entiteEmployes,Long posteId,EtatEmploye etatEmploye){
        List<Employe> employes=employeRepository.findAllByEntiteEmployesIsContainingAndPosteIdAndEtatEmploye(entiteEmployes,posteId,etatEmploye);
        List<EmployeResponseDto> employeResponseDtos = new ArrayList<>();
        employes.forEach(e->{
            e.setEntiteEmployes(entiteEmployeRepository.findAllByEmployeId(e.getId()));
            e.setConjoints(conjointRepository.findAllByEmployeId(e.getId()));
            e.setEnfants(enfantRepository.findAllByEmployeId(e.getId()));
            employeResponseDtos.add(e.toDTO());
        });
        return  ResponseEntity.ok(employeResponseDtos);
    }
    public ResponseEntity<List<EmployeResponseDto>> recherche3(List<EntiteEmploye> entiteEmployes,Long gradeId,EtatEmploye etatEmploye){
        List<Employe> employes=employeRepository.findAllByEntiteEmployesIsContainingAndGradeIdAndEtatEmploye(entiteEmployes,gradeId,etatEmploye);
        List<EmployeResponseDto> employeResponseDtos = new ArrayList<>();
        employes.forEach(e->{
            e.setEntiteEmployes(entiteEmployeRepository.findAllByEmployeId(e.getId()));
            e.setConjoints(conjointRepository.findAllByEmployeId(e.getId()));
            e.setEnfants(enfantRepository.findAllByEmployeId(e.getId()));
            employeResponseDtos.add(e.toDTO());
        });
        return  ResponseEntity.ok(employeResponseDtos);
    }
    public ResponseEntity<List<EmployeResponseDto>> recherche4(List<EntiteEmploye> entiteEmployes,EtatEmploye etatEmploye){
        List<Employe> employes=employeRepository.findAllByEntiteEmployesIsContainingAndEtatEmploye(entiteEmployes,etatEmploye);
        List<EmployeResponseDto> employeResponseDtos = new ArrayList<>();
        employes.forEach(e->{
            e.setEntiteEmployes(entiteEmployeRepository.findAllByEmployeId(e.getId()));
            e.setConjoints(conjointRepository.findAllByEmployeId(e.getId()));
            e.setEnfants(enfantRepository.findAllByEmployeId(e.getId()));
            employeResponseDtos.add(e.toDTO());
        });
        return  ResponseEntity.ok(employeResponseDtos);
    }
    public ResponseEntity<List<EmployeResponseDto>> recherche5(Long posteId,Long gradeId,EtatEmploye etatEmploye){
        List<Employe> employes=employeRepository.findAllByPosteIdAndGradeIdAndEtatEmploye(posteId,gradeId,etatEmploye);
        List<EmployeResponseDto> employeResponseDtos = new ArrayList<>();
        employes.forEach(e->{
            e.setEntiteEmployes(entiteEmployeRepository.findAllByEmployeId(e.getId()));
            e.setConjoints(conjointRepository.findAllByEmployeId(e.getId()));
            e.setEnfants(enfantRepository.findAllByEmployeId(e.getId()));
            employeResponseDtos.add(e.toDTO());
        });
        return  ResponseEntity.ok(employeResponseDtos);
    }

    public ResponseEntity<List<EmployeResponseDto>> getAllEmployesWithDateRecrutement(LocalDate dateRecrutement){
        List<Employe> employes=employeRepository.findAllByDateRecrutement(dateRecrutement);
        List<EmployeResponseDto> employeResponseDtos = new ArrayList<>();
        employes.forEach(e->{
            employeResponseDtos.add(e.toDTO());
        });
        return  ResponseEntity.ok(employeResponseDtos);
    }
    public ResponseEntity<List<EmployeResponseDto>> getAllEmployesWithDateRecrutementAfter(LocalDate dateRecrutement){
        List<Employe> employes=employeRepository.findAllByDateRecrutementAfter(dateRecrutement);
        List<EmployeResponseDto> employeResponseDtos = new ArrayList<>();
        employes.forEach(e->{
            employeResponseDtos.add(e.toDTO());
        });
        return  ResponseEntity.ok(employeResponseDtos);
    }
    public ResponseEntity<List<EmployeResponseDto>> getAllEmployesWithDateRecrutementBefore(LocalDate dateRecrutement){
        List<Employe> employes=employeRepository.findAllByDateRecrutementBefore(dateRecrutement);
        List<EmployeResponseDto> employeResponseDtos = new ArrayList<>();
        employes.forEach(e->{
            employeResponseDtos.add(e.toDTO());
        });
        return  ResponseEntity.ok(employeResponseDtos);
    }
    public ResponseEntity<List<EmployeResponseDto>> getAllEmployesWithPrenom(String prenom){
        List<Employe> employes=employeRepository.findAllByPrenomContainingIgnoreCase(prenom);
        List<EmployeResponseDto> employeResponseDtos = new ArrayList<>();
        employes.forEach(e->{
            employeResponseDtos.add(e.toDTO());
        });
        return  ResponseEntity.ok(employeResponseDtos);
    }
    public ResponseEntity<List<EmployeResponseDto>> getAllEmployesWithNom(String nom){
        List<Employe> employes=employeRepository.findAllByNomContainingIgnoreCase(nom);
        List<EmployeResponseDto> employeResponseDtos = new ArrayList<>();
        employes.forEach(e->{
            employeResponseDtos.add(e.toDTO());
        });
        return  ResponseEntity.ok(employeResponseDtos);
    }
    public ResponseEntity<List<EmployeResponseDto>> getAllEmployesWithDateRecrutementBetween(LocalDate date1,LocalDate date2){
        List<Employe> employes=employeRepository.findAllByDateRecrutementBetween(date1,date2);
        List<EmployeResponseDto> employeResponseDtos = new ArrayList<>();
        employes.forEach(e->{
            employeResponseDtos.add(e.toDTO());
        });
        return  ResponseEntity.ok(employeResponseDtos);
    }

    public ResponseEntity<List<EmployeResponseDto>> getEmployesWithStatus(EtatEmploye etatEmploye){
        List<Employe> employes=employeRepository.findAllByEtatEmploye(etatEmploye);
        List<EmployeResponseDto> employeResponseDtos = new ArrayList<>();
        employes.forEach(e->{
            employeResponseDtos.add(e.toDTO());
        });
        return  ResponseEntity.ok(employeResponseDtos);
    }

    public ResponseEntity<List<EmployeResponseDto>> getEmployesWithGender(Genre genre){
        List<Employe> employes=employeRepository.findAllByGenre(genre);
        List<EmployeResponseDto> employeResponseDtos = new ArrayList<>();
        employes.forEach(e->{
            employeResponseDtos.add(e.toDTO());
        });
        return  ResponseEntity.ok(employeResponseDtos);
    }
    public ResponseEntity<List<EmployeResponseDto>> getEmployesWithDateNaissanceAfter(LocalDate date){
        List<Employe> employes=employeRepository.findAllByDateNaissanceAfter(date);
        List<EmployeResponseDto> employeResponseDtos = new ArrayList<>();
        employes.forEach(e->{
            employeResponseDtos.add(e.toDTO());
        });
        return  ResponseEntity.ok(employeResponseDtos);
    }

    public ResponseEntity<List<EmployeResponseDto>> getEmployesWithDateNaissanceBefore(LocalDate date){
        List<Employe> employes=employeRepository.findAllByDateNaissanceBefore(date);
        List<EmployeResponseDto> employeResponseDtos = new ArrayList<>();
        employes.forEach(e->{
            employeResponseDtos.add(e.toDTO());
        });
        return  ResponseEntity.ok(employeResponseDtos);
    }

    public ResponseEntity<List<EmployeResponseDto>> getEmployesWithAddressContains(String address){
        List<Employe> employes=employeRepository.findAllByAdresseContainingIgnoreCase(address);
        List<EmployeResponseDto> employeResponseDtos = new ArrayList<>();
        employes.forEach(e->{

            employeResponseDtos.add(e.toDTO());
        });
        return  ResponseEntity.ok(employeResponseDtos);
    }

    public ResponseEntity<List<EmployeResponseDto>> getEmployesWithNombreAnneeExperienceInf(int n){
        List<Employe> employes=employeRepository.findAllByAnneeExperienceLessThan(n);
        List<EmployeResponseDto> employeResponseDtos = new ArrayList<>();
        employes.forEach(e->{
            employeResponseDtos.add(e.toDTO());
        });
        return  ResponseEntity.ok(employeResponseDtos);
    }

    public ResponseEntity<List<EmployeResponseDto>> getEmployesWithNombreAnneeExperienceSup(int n){
        List<Employe> employes=employeRepository.findAllByAnneeExperienceGreaterThan(n);
        List<EmployeResponseDto> employeResponseDtos = new ArrayList<>();
        employes.forEach(e->{

            employeResponseDtos.add(e.toDTO());
        });
        return  ResponseEntity.ok(employeResponseDtos);
    }

    public ResponseEntity<List<EmployeResponseDto>> getEmployesWithSituationFamiliale(SituationFamiliale situationFamiliale){
        List<Employe> employes=employeRepository.findAllBySituationFamiliale(situationFamiliale);
        List<EmployeResponseDto> employeResponseDtos = new ArrayList<>();
        employes.forEach(e->{

            employeResponseDtos.add(e.toDTO());
        });
        return  ResponseEntity.ok(employeResponseDtos);
    }

    public ResponseEntity<List<EmployeResponseDto>> getEmployesWithPosteId(Long posteId){
        List<Employe> employes=employeRepository.findAllByPosteId(posteId);
        List<EmployeResponseDto> employeResponseDtos = new ArrayList<>();
        employes.forEach(e->{

            employeResponseDtos.add(e.toDTO());
        });
        return  ResponseEntity.ok(employeResponseDtos);
    }
    public ResponseEntity<List<EmployeResponseDto>> getEmployesWithGradeId(Long gradeId){
        List<Employe> employes=employeRepository.findAllByGradeId(gradeId);
        List<EmployeResponseDto> employeResponseDtos = new ArrayList<>();
        employes.forEach(e->{

            employeResponseDtos.add(e.toDTO());
        });
        return  ResponseEntity.ok(employeResponseDtos);
    }






    public ResponseEntity<byte[]> getImageProfile(String employeId) throws IOException {
        Employe employe=employeRepository.findById(employeId).orElseThrow(()-> new EmployeNotFoundException("employe not found"));
        String fileName=employe.getImageURL();
        return ResponseEntity.ok(Files.readAllBytes(Path.of(URI.create(fileName))));
    }

    public ResponseEntity<String> setCompte(String employeId, CompteModel compteModel) {
        Employe employe=employeRepository.findById(employeId).get();
        employe.setCompteModel(compteModel);
        employeRepository.save(employe);
        return ResponseEntity.ok("Employe compte updated");
    }
}
