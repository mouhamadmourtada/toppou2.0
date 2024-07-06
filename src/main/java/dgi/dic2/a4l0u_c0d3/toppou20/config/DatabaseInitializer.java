package dgi.dic2.a4l0u_c0d3.toppou20.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.github.javafaker.Faker;

import java.util.*;

import dgi.dic2.a4l0u_c0d3.toppou20.model.User;
import dgi.dic2.a4l0u_c0d3.toppou20.model.Role;

import dgi.dic2.a4l0u_c0d3.toppou20.repository.UserRepository;
import dgi.dic2.a4l0u_c0d3.toppou20.repository.RoleRepository;

@Component
public class DatabaseInitializer implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (checkIfInitialDataInserted()) {
            insertInitialData();
//            markInitialDataAsInserted();
        }
    }

    private boolean checkIfInitialDataInserted() {
//        return false
        long count = userRepository.count();
        System.out.println("voici la valeur de count" + (count == 0));
        return count == 0;
    }


    private void insertInitialData() {
        Faker faker = new Faker(new Locale("fr"));

        // Créer les rôles avec leurs descriptions
        roleRepository.saveAll(Arrays.asList(
                new Role("chercheur", "Rôle pour les chercheurs"),
                new Role("stagiaire", "Rôle pour les stagiaires"),
                new Role("bailleur", "Rôle pour les bailleurs"),
                new Role("admin", "Rôle pour les administrateurs"),
                new Role("comptable", "Rôle pour les comptables"),
                new Role("superadmin", "Rôle pour les super administrateurs")
        ));

        // Créer 100 utilisateurs avec des données aléatoires et assigner des rôles aléatoirement
        for (int i = 1; i <= 100; i++) {
            User user = new User();
            user.setEmail(faker.internet().emailAddress());
            user.setUsername(faker.name().username());
            user.setPassword("passer");
            user.setNom(faker.name().lastName());
            user.setPrenom(faker.name().firstName());
            user.setDateNaissance(faker.date().birthday());
            user.setLieuNaissance(faker.address().city());
            user.setAdresse(faker.address().fullAddress());
            user.setTelephone(faker.phoneNumber().phoneNumber());
            user.setTitre(faker.job().title());
            user.setStatus(faker.random().nextBoolean() ? "Actif" : "Inactif");
            user.setGrade(faker.job().position());

            userRepository.save(user);

            // Assigner des rôles aléatoirement à chaque utilisateur
            assignRandomRolesToUser(user);
        }
    }

    private void assignRandomRolesToUser(User user) {
        Iterable<Role> availableRoles = roleRepository.findAll();
        List<Role> rolesList = new ArrayList<>();
        availableRoles.forEach(rolesList::add);

        Role randomRole = rolesList.get(new Random().nextInt(rolesList.size()));
        user.getRoles().add(randomRole);
        userRepository.save(user);
    }



    private void markInitialDataAsInserted() {
        // Marquer les données initiales comme étant insérées
    }
}
