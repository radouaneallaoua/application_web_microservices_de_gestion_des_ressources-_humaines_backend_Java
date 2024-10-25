package com.example.absenceservice.models;

import com.example.absenceservice.enums.RoleName;
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
