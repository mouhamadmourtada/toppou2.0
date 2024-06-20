package dgi.dic2.a4l0u_c0d3.toppou20.controller;

import dgi.dic2.a4l0u_c0d3.toppou20.model.User;
import dgi.dic2.a4l0u_c0d3.toppou20.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("users")
@RestController
public class UserController {


    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PatchMapping("/toggleActif")
    public ResponseEntity<User> toggleActif(@RequestParam long id){

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
