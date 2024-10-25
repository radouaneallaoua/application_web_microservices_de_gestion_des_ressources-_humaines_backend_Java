package com.example.authservice.Web;

import com.example.authservice.Dtos.CompteRequestDto;
import com.example.authservice.Dtos.CompteResponseDto;
import com.example.authservice.Services.CompteService;
import com.example.authservice.entities.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    final  private CompteService compteService;

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthController(CompteService compteService) {
        this.compteService = compteService;
    }

    @GetMapping("")
    public ResponseEntity<List<CompteResponseDto>> getAllComptes(){
        return compteService.getAllComptes();
    }

    @PostMapping("/create")
    public ResponseEntity<CompteResponseDto> create(@RequestBody CompteRequestDto compteRequestDto){
        return compteService.create(compteRequestDto);
    }

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody CompteRequestDto compteRequestDto){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(compteRequestDto.getEmail(),compteRequestDto.getPassword()));
        Instant instant=Instant.now();
        String scope= authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
        String employeId=compteService.getCompteEmployeId(compteRequestDto.getEmail());
        JwtClaimsSet jwtClaimsSet= JwtClaimsSet.builder()
                .issuedAt(instant)
                .expiresAt(instant.plus(60, ChronoUnit.DAYS))
                .subject(compteRequestDto.getEmail()+"::"+employeId)
                .claim("scope",scope)
                .build();

        JwtEncoderParameters jwtEncoderParameters=JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS512).build(),
                jwtClaimsSet
        );

        String jwt=jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
        return Map.of("access-token",jwt);
    }

    @GetMapping("/profile")
    public Authentication authentication(Authentication authentication){
        return authentication;
    }
}
