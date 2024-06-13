package dgi.dic2.a4l0u_c0d3.toppou20.repository;


import dgi.dic2.a4l0u_c0d3.toppou20.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository <Role, Long> {
    public Optional<Role> findByLibelle(String libelle);

//    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.libelle = :roleName")
//    List<User> findByRoleName(@Param("roleName") String roleName);
//
//
//    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.libelle IN :roleNames")
//    List<User> findByRoles(@Param("roleNames") List<String> roleNames);
}
