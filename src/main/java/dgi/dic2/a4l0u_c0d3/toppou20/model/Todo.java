package dgi.dic2.a4l0u_c0d3.toppou20.model;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
 import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//  cette classe est une entité pout todo, Todo, doit avoir un id, un titre, une description, une date de création, une date de mise à jour, une date d'échéance, un statut (en cours, terminé, annulé), une priorité (faible, moyenne, haute), une catégorie (travail, personnel, loisir, etc.),

 @Entity
 @Table (name = "todo"
//         ,uniqueConstraints = {
//         @UniqueConstraint(name = "test_todo_unique", columnNames = "email")
//    }
 )
 public class Todo {
//      met le nécessaire pour le model Todo
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(
             name = "title",
//             columnDefinition = "TEXT"
//             unique = true

             nullable = false
     )
     private String title;

     @Column(name = "description")
     private String description;

     @Column(name = "creation_date")
     private Date creationDate;

     @Column(name = "update_date")
     private Date updateDate;

     @Column(name = "due_date")
     private Date dueDate;

     @Column(name = "status")
     private String status;

     @Column(name = "priority")
     private String priority;

     @Column(name = "category")
     private String category;

     public Todo() {
     }

     public Todo(Long id, String title, String description, Date creationDate, Date updateDate, Date dueDate, String status, String priority, String category) {
         this.id = id;
         this.title = title;
         this.description = description;
         this.creationDate = creationDate;
         this.updateDate = updateDate;
         this.dueDate = dueDate;
         this.status = status;
         this.priority = priority;
         this.category = category;
     }



     public Todo(String title, String description, Date creationDate, Date updateDate, Date dueDate, String status, String priority, String category) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.dueDate = dueDate;
        this.status = status;
        this.priority = priority;
        this.category = category;
    }

     public Long getId() {
         return id;
     }

     public String getTitle() {
         return title;
     }

     public String getDescription() {
         return description;
     }

     public Date getCreationDate() {
         return creationDate;
     }

     public Date getUpdateDate() {
         return updateDate;
     }

     public Date getDueDate() {
         return dueDate;
     }

     public String getStatus() {
         return status;
     }

     public String getPriority() {
         return priority;
     }

     public String getCategory() {
         return category;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public void setTitle(String title) {
         this.title = title;
     }

     public void setDescription(String description) {
         this.description = description;
     }

     public void setCreationDate(Date creationDate) {
         this.creationDate = creationDate;
     }

     public void setUpdateDate(Date updateDate) {
         this.updateDate = updateDate;
     }

     public void setDueDate(Date dueDate) {
         this.dueDate = dueDate;
     }

     public void setStatus(String status) {
         this.status = status;
     }

     public void setPriority(String priority) {
         this.priority = priority;
     }

     public void setCategory(String category) {
         this.category = category;
     }

     @Override
     public String toString() {
         return "Todo{" +
                 "id=" + id +
                 ", title='" + title + '\'' +
                 ", description='" + description + '\'' +
                 ", creationDate=" + creationDate +
                 ", updateDate=" + updateDate +
                 ", dueDate=" + dueDate +
                 ", status='" + status + '\'' +
                 ", priority='" + priority + '\'' +
                 ", category='" + category + '\'' +
                 '}';
     }

//      ajouter les méthodes equals et hashcode
     @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (!(o instanceof Todo todo)) return false;
         return Objects.equals(id, todo.id) &&
                 Objects.equals(title, todo.title) &&
                 Objects.equals(description, todo.description) &&
                 Objects.equals(creationDate, todo.creationDate) &&
                 Objects.equals(updateDate, todo.updateDate) &&
                 Objects.equals(dueDate, todo.dueDate) &&
                 Objects.equals(status, todo.status) &&
                 Objects.equals(priority, todo.priority) &&
                 Objects.equals(category, todo.category);
     }

     @Override
     public int hashCode() {
         return Objects.hash(id, title, description, creationDate, updateDate, dueDate, status, priority, category);
     }

  

    
 }
