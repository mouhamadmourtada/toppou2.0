package dgi.dic2.a4l0u_c0d3.toppou20.repository;


import dgi.dic2.a4l0u_c0d3.toppou20.dto.UserWithRoleDTO;
import dgi.dic2.a4l0u_c0d3.toppou20.model.User;
import dgi.dic2.a4l0u_c0d3.toppou20.model.Role;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

//@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);
    public Optional<User> findByUsername(String username);


//    public List<User> findByRole(Role role);


    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.libelle = :roleName")
    List<User> findByRoleName(@Param("roleName") String roleName);


//    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.libelle IN :roleNames")
//    List<User> findByRoles(@Param("roleNames") List<String> roleNames);



//    @EntityGraph(attributePaths = { "roles" })
//    Optional<User> findById(Long id);


//    @Override
//    @EntityGraph(attributePaths = {"roles"})
//    List<User> findAll();

}
