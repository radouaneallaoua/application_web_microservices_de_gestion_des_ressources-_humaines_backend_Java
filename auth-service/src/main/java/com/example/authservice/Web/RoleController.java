package com.example.authservice.Web;

import com.example.authservice.Dtos.RoleRequestDto;
import com.example.authservice.Dtos.RoleResponseDto;

import com.example.authservice.Services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    final  private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }



    @GetMapping("")
    public ResponseEntity<List<RoleResponseDto>> getRolesList(){
        return roleService.getRolesList();
    }

    @PostMapping("/create")
    public ResponseEntity<RoleResponseDto> create(@RequestBody RoleRequestDto roleRequestDto){
        return roleService.create(roleRequestDto);
    }

}
