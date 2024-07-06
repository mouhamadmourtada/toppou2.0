package dgi.dic2.a4l0u_c0d3.toppou20.controller;



import dgi.dic2.a4l0u_c0d3.toppou20.dto.*;
import dgi.dic2.a4l0u_c0d3.toppou20.exception.BadCredentialsException;
import dgi.dic2.a4l0u_c0d3.toppou20.model.Role;
import dgi.dic2.a4l0u_c0d3.toppou20.model.User;
import dgi.dic2.a4l0u_c0d3.toppou20.repository.RoleRepository;
import dgi.dic2.a4l0u_c0d3.toppou20.service.JwtService;
import dgi.dic2.a4l0u_c0d3.toppou20.repository.UserRepository;
//import dgi.dic2.a4l0u_c0d3.toppou20.repository.

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class AuthController {

    private JwtService jwtService;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthController(JwtService jwtService,
                          AuthenticationManager authenticationManager,
                          RoleRepository roleRepository,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder
    ) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("api/auth/login")
    public String nouyoo(){
        return "login mdof";
    }

    @PostMapping("api/auth/login")
    public AuthResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequestDTO.getUsername(),
                            authRequestDTO.getPassword()
                    )
            );
            if(authentication.isAuthenticated()){
                String token = jwtService.generateToken(authRequestDTO.getUsername());
                User user = userRepository.findByUsername(authRequestDTO.getUsername()).get();
            return new AuthResponseDTO(token, user, 200);
            } else {
                throw new UsernameNotFoundException("invalid user request..!!");
            }
        } catch (AuthenticationException e) {
//            e.printStackTrace();
            throw new BadCredentialsException("invalid user request..!!");

//            return JwtResponseDTO.builder().accessToken("problème yaa ngay démarrer fin").build();
        }
    }


    @PostMapping("api/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        // Vérifier si l'utilisateur existe déjà
        if (userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is already taken.");
        }

        // Créer un nouvel utilisateur
        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
//        user.setPassword(passwordEncoder.encode("passer"));
        user.setEmail(registerRequestDTO.getEmail());
        user.setPrenom(registerRequestDTO.getPrenom());
        user.setNom(registerRequestDTO.getNom());
        user.setDateNaissance(registerRequestDTO.getDateNaissance());

//
        user.setAdresse(registerRequestDTO.getAdresse());
        user.setGrade(registerRequestDTO.getGrade());
        user.setLieuNaissance(registerRequestDTO.getLieuNaissance());
        user.setStatus(registerRequestDTO.getStatus());
        user.setTelephone(registerRequestDTO.getTelephone());
        user.setTitre(registerRequestDTO.getTitre());
        user.setDeleted(false); // Par défaut, l'utilisateur n'est pas supprimé
        user.setIsConfirmed(false);

        // Ajouter des rôles par défaut (si nécessaire)

        List<String> roleLibelles = registerRequestDTO.getRoles();

// Récupérer les rôles à partir des libellés
        List<Role> roles = roleRepository.findByLibelleIn(roleLibelles);

        System.out.println(roles);
        // Vérifier si tous les rôles ont été trouvés
        if (roles.size() != roleLibelles.size()) {
            throw new RuntimeException("Error: One or more roles are not found.");
        }
//
//        // Associer les rôles à l'utilisateur
        user.setRoles(new HashSet<>(roles));

        // Sauvegarder l'utilisateur dans la base de données
        userRepository.save(user);

        // Générer un token JWT pour le nouvel utilisateur
        String token = jwtService.generateToken(user.getUsername());
        // Retourner le token JWT dans la réponse
        return ResponseEntity.ok(new RegisterResponseDTO(token, user));
    }
    
}
