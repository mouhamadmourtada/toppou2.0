package dgi.dic2.a4l0u_c0d3.toppou20.repository;

import dgi.dic2.a4l0u_c0d3.toppou20.model.Todo;
import dgi.dic2.a4l0u_c0d3.toppou20.model.CompleteTodo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



//@RepositoryRestResource(
//        excerptProjection = CompleteTodo.class,
//        collectionResourceRel = "todos",
//        path = "persons"
//
//)
public interface AnotherRepository extends CrudRepository<Todo, Long> {
}
