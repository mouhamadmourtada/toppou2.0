package dgi.dic2.a4l0u_c0d3.toppou20.projection;


import dgi.dic2.a4l0u_c0d3.toppou20.model.Todo;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "noCategory", types = {Todo.class})
public interface NoCategoryTodo {
    String getTitle();
    String getDescription();
//    String getCategory();
    Date getCreationDate();

    /*
    private String title;
    private String description;
    private String category;
    private Date creationDate;
    private Date dueDate;
    private String priority;
    private String status;
    private Date updatedDate;

     */
}
