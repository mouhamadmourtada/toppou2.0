package dgi.dic2.a4l0u_c0d3.toppou20.controller;

import dgi.dic2.a4l0u_c0d3.toppou20.dto.GetChefProjetResponse;
import dgi.dic2.a4l0u_c0d3.toppou20.dto.PasswordResetRequest;
import dgi.dic2.a4l0u_c0d3.toppou20.dto.PasswordResetResponse;
import dgi.dic2.a4l0u_c0d3.toppou20.model.Role;
import dgi.dic2.a4l0u_c0d3.toppou20.model.User;
import dgi.dic2.a4l0u_c0d3.toppou20.repository.RoleRepository;
import dgi.dic2.a4l0u_c0d3.toppou20.repository.UserRepository;
import dgi.dic2.a4l0u_c0d3.toppou20.service.MailService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;

@Validated
@RequestMapping("users")
@RestController
public class UserController {


    private final RoleRepository roleRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private MailService mailService;

    public UserController(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            MailService mailService,

            RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    @PatchMapping("/{id}/reset")
    public ResponseEntity<PasswordResetResponse> reset(@PathVariable Long id, @Valid @RequestBody PasswordResetRequest request) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.notFound().build(); // Retourne une réponse 404 Not Found
        }

        User realUser = optionalUser.get();

        realUser.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(realUser);
        mailService.sendMail(realUser.getEmail(), "Mot de passe changé", "votre mot de passe a été changé. voici le nouveau mot de passe "+request.getPassword());
        return ResponseEntity.ok(new PasswordResetResponse("Password reset successful"));

    }


    @GetMapping("/chefProjet")
    public GetChefProjetResponse getChefProjet() {
        List<User> users = userRepository.findByRoleName("chef_projet");
        return new GetChefProjetResponse(users);
    }

    @PatchMapping("/{id}/toggleActif")
    public ResponseEntity<User> toggleActif(@PathVariable long id){

        Optional<User> gotUser = userRepository.findById(id);

        if (!gotUser.isPresent()) {
            return ResponseEntity.notFound().build(); // Retourne une réponse 404 Not Found
        }

        User realUser = gotUser.get();
        realUser.setActif(!realUser.getActif());
        userRepository.save(realUser);

        return ResponseEntity.ok(realUser);
    }
}
