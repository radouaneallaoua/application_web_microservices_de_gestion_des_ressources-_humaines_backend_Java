package com.example.authservice.entities;
import com.example.authservice.Dtos.RoleResponseDto;
import com.example.authservice.enums.RoleName;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @ManyToMany()
    @JoinTable(name = "compte-role",joinColumns = @JoinColumn(name="role_id"),inverseJoinColumns = @JoinColumn(name="compte_id"))
    private List<Compte> comptes;

    public RoleResponseDto toDto(){
        return  RoleResponseDto.builder()
                .roleName(roleName)
                .id(id)
                .build();
    }
}

