package dgi.dic2.a4l0u_c0d3.toppou20.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDTO {
    private String title;
    private String description;
    private String category;
    private Date creationDate;
    private Date dueDate;
    private String priority;
    private String status;
    private Date updatedDate;

}