package com.example.authservice.Dtos;

import com.example.authservice.enums.RoleName;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleRequestDto {
    private RoleName roleName;
}
