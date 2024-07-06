package dgi.dic2.a4l0u_c0d3.toppou20.repository;

import org.springframework.data.repository.CrudRepository;
import dgi.dic2.a4l0u_c0d3.toppou20.model.File;

import java.util.List;

public interface FileRepository extends CrudRepository<File, Long>{
    List<File> findByEntityIdAndEntityType(Long entityId, String entityType);
    
}
