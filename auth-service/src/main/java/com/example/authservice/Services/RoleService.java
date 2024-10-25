package com.example.authservice.Services;

import com.example.authservice.Clients.EmployeFeignClient;
import com.example.authservice.Dtos.CompteRequestDto;
import com.example.authservice.Dtos.CompteResponseDto;
import com.example.authservice.Dtos.RoleRequestDto;
import com.example.authservice.Dtos.RoleResponseDto;
import com.example.authservice.Repositories.CompteRepository;
import com.example.authservice.Repositories.RoleRepository;
import com.example.authservice.entities.Compte;
import com.example.authservice.entities.UserRole;
import com.example.authservice.enums.EtatCompte;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class RoleService {
    final private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public ResponseEntity<List<RoleResponseDto>> getRolesList(){
       List<UserRole> roles = roleRepository.findAll();
       List<RoleResponseDto> roleResponseDtos = new ArrayList<>();
       roles.forEach(r->{
           roleResponseDtos.add(r.toDto());
       });
        return new ResponseEntity<>(roleResponseDtos, HttpStatus.OK);
    }

    public ResponseEntity<RoleResponseDto> create(RoleRequestDto roleRequestDto){
        UserRole userRole= UserRole.builder()
                .roleName(roleRequestDto.getRoleName())
                .build();
        UserRole savedUserRole=roleRepository.save(userRole);
        return new ResponseEntity<>(savedUserRole.toDto(), HttpStatus.OK);
    }


}
