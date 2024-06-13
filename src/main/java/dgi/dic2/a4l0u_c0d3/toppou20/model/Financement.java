package dgi.dic2.a4l0u_c0d3.toppou20.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// @Data c'est pour quoi ?
// @Data est un projet Lombok qui génère automatiquement les méthodes equals, hashCode, toString, les getters et les setters pour tous les champs de la classe.
@AllArgsConstructor
@NoArgsConstructor
@Builder
// @Builder c'est pour quoi ?
// @Builder est un projet Lombok qui génère automatiquement un constructeur qui initialise toutes les propriétés requises et un constructeur qui initialise toutes les propriétés.


@Entity
@Table(name = "financements")
@SQLDelete(sql = "UPDATE financements SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Financement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "date_signature", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateSignature;

    @Column(name = "date_obtention_effective", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateObtentionEffective;

    @Column(name = "montant", nullable = false)
    private long montant;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "objectif", columnDefinition = "text")
    private String objectif;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date updatedAt;

    @Column(name = "deleted", nullable = false)
    @JsonIgnore
    private boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "projet_id", nullable = false)
    private Projet projet;

    @ManyToMany
    @JoinTable(
            name = "financement_bailleurs",
            joinColumns = @JoinColumn(name = "financement_id"),
            inverseJoinColumns = @JoinColumn(name = "bailleur_id")
    )
    private Set<User> bailleurs = new HashSet<>();

    // Constructors, getters and setters

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

//    public Financement() {
//    }

    public Financement(Date dateSignature, Date dateObtentionEffective, long montant, Projet projet) {
        this.dateSignature = dateSignature;
        this.dateObtentionEffective = dateObtentionEffective;
        this.montant = montant;
        this.projet = projet;
    }

}
