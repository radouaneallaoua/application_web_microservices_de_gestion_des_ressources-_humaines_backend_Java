package com.example.paieservice.models;

import com.example.paieservice.enums.RoleName;
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
