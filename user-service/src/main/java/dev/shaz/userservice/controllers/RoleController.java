package dev.shaz.userservice.controllers;

import dev.shaz.userservice.dtos.CreateRoleRequestDto;
import dev.shaz.userservice.models.Role;
import dev.shaz.userservice.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody CreateRoleRequestDto requestDto){
        Role role = roleService.createRole(requestDto.getName());
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
