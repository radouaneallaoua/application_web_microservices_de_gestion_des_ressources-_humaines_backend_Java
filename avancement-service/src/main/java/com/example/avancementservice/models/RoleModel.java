package com.example.avancementservice.models;

import com.example.avancementservice.enums.RoleName;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleModel {
    private Long id;
    private RoleName roleName;
}
