package com.example.formationservice.models;

import com.example.formationservice.enums.RoleName;
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
