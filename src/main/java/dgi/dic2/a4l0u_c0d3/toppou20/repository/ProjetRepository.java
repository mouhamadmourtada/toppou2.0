package dgi.dic2.a4l0u_c0d3.toppou20.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import dgi.dic2.a4l0u_c0d3.toppou20.model.Projet;

public interface ProjetRepository extends JpaRepository<Projet, Long> {
    
}
