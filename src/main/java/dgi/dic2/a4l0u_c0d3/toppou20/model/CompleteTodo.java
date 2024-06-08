package dgi.dic2.a4l0u_c0d3.toppou20.model;


import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "completeTodo", types = {Todo.class})
public interface CompleteTodo {
//    String getTitle();
    String getDescription();

    String getCategory();
    Date getCreationDate();
    String getStatus();
    String getPriority();
    Date getDueDate();
//    Date getUpdatedDate();

}

