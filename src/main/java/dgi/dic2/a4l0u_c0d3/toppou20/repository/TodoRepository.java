package dgi.dic2.a4l0u_c0d3.toppou20.repository;

import java.util.List;

import dgi.dic2.a4l0u_c0d3.toppou20.model.NoCategoryTodo;
import org.springframework.data.jpa.repository.JpaRepository;

import dgi.dic2.a4l0u_c0d3.toppou20.model.Todo;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(excerptProjection = NoAddresses.class)
@RepositoryRestResource(
        excerptProjection = NoCategoryTodo.class,
        path = "todos",
        collectionResourceRel = "todos"
)
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByTitle(String title);

}
