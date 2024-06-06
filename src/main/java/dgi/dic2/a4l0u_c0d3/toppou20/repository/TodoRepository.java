package dgi.dic2.a4l0u_c0d3.toppou20.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dgi.dic2.a4l0u_c0d3.toppou20.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByTitle(String title);

}
