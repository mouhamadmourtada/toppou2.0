// package com.example.toppou.dto;
package dgi.dic2.a4l0u_c0d3.toppou20.dto;

import dgi.dic2.a4l0u_c0d3.toppou20.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
//@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserWithRoleDTO {
    private Long id;
    private String username;
//    private String password;
    private Set<Role> roles;

    public UserWithRoleDTO(Long id, String username, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}