package com.example.authservice.Services;

import com.example.authservice.Repositories.CompteRepository;
import com.example.authservice.entities.Compte;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CompteUserDetailsService implements UserDetailsService {

    private final CompteRepository compteRepository;

    public CompteUserDetailsService(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Compte compte = compteRepository.findCompteByEmail(username);
        if (compte == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Return UserDetails with proper authorities directly from the roles
        return new org.springframework.security.core.userdetails.User(
                compte.getEmail(),
                compte.getMotDePasse(),
                getAuthorities(compte)
        );
    }

    // Convert roles to GrantedAuthority directly
    private Collection<? extends GrantedAuthority> getAuthorities(Compte compte) {
        return compte.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().toString()))
                .collect(Collectors.toList());
    }
}
