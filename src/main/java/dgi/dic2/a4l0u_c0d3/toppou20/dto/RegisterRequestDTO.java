// package com.example.toppou.dto;
package dgi.dic2.a4l0u_c0d3.toppou20.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequestDTO {
    private String username;
    private String password;
    private String email;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String adresse;
    private String telephone;
    private String grade;
    private String titre;
    private String lieuNaissance;
    private String status;
    private List<String> roles;



    //    private
//    private boolean isConfirmed;

}
