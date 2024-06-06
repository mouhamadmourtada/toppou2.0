package dgi.dic2.a4l0u_c0d3.toppou20.repository;


import dgi.dic2.a4l0u_c0d3.toppou20.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository <User, Long> {
    public User findByEmail(String email);
    public Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.libelle = :roleName")
    List<User> findByRoleName(@Param("roleName") String roleName);


    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.libelle IN :roleNames")
    List<User> findByRoles(@Param("roleNames") List<String> roleNames);
}
