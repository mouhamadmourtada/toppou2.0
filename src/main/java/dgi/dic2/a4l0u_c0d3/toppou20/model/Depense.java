package dgi.dic2.a4l0u_c0d3.toppou20.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;

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
@Table(name = "depenses")
@SQLDelete(sql = "UPDATE depenses SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Depense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "justification", nullable = false)
    private String justification;

    @Column(name = "montant", nullable = false)
    private long montant;

    @Column(name = "date_depense", nullable = false)
    @Temporal(TemporalType.DATE)
    // que signifie @Temporal
    // @Temporal est une annotation qui permet de définir le type de date et d'heure pour un champ de date.
    private Date dateDepense;

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
    @JoinColumn(name = "activite_id", nullable = false)
    private Activite activite;

    // Constructors, getters and setters

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }


    public Depense(String libelle, String justification, long montant, Date dateDepense, Activite activite) {
        this.libelle = libelle;
        this.justification = justification;
        this.montant = montant;
        this.dateDepense = dateDepense;
        this.activite = activite;
    }

   
}
