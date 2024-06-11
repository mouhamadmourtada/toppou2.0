package dgi.dic2.a4l0u_c0d3.toppou20.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;


// Autres annotations et imports...
@Entity
@Table(name = "projet_acteurs")
@SQLDelete(sql = "UPDATE projet_acteurs SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjetActeurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    // Ajout du champ pour gérer le soft-delete
    @Column(name = "deleted", nullable = false)
    @JsonIgnore
    private boolean deleted = false;

    // Ajout des champs created_at et updated_at
    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date updatedAt;

    // Autres champs...

    // Ajout de la relation ManyToOne avec Projet
    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;

    // Ajout de la relation ManyToOne avec User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructeurs, getters, setters, méthodes de cycle de vie, etc.

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
}

