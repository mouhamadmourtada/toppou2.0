package dgi.dic2.a4l0u_c0d3.toppou20.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dgi.dic2.a4l0u_c0d3.toppou20.enumeration.EtatActiviteEnumeration;
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
@Table(name = "activites")
@SQLDelete(sql = "UPDATE activites SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Activite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "date_debut_activite")
    @Temporal(TemporalType.DATE)
    private Date dateDebutActivite;

    @Column(name = "date_fin_activite", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateFinActivite;

    @Column(name = "date_debut_effective")
    @Temporal(TemporalType.DATE)
    private Date dateDebutEffective;

    @Column(name = "date_fin_effective")
    @Temporal(TemporalType.DATE)
    private Date dateFinEffective;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat", nullable = false)
    private EtatActiviteEnumeration etat;

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

    @ManyToOne
    @JoinColumn(name = "activite_mere_id")
    private Activite activiteMere;

    @OneToMany(mappedBy = "activiteMere")
    private Set<Activite> sousActivites = new HashSet<>();

    @OneToMany(mappedBy = "activite")
    private Set<Depense> depenses = new HashSet<>();

    // Constructors, getters and setters

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    // constructeur avec tous les attributs sauf l'id
    // public Activite (String libelle, String description, Date dateDebutActivite, Date dateFinActivite, Date dateDebutEffective, Date dateFinEffective, EtatActiviteEnumeration etat, Projet projet, Activite activiteMere) {
    //     this.libelle = libelle;
    //     this.description = description;
    //     this.dateDebutActivite = dateDebutActivite;
    //     this.dateFinActivite = dateFinActivite;
    //     this.dateDebutEffective = dateDebutEffective;
    //     this.dateFinEffective = dateFinEffective;
    //     this.etat = etat;
    //     this.projet = projet;
    //     this.activiteMere = activiteMere;
    // }
    

    public Activite(String libelle, Date dateFinActivite, EtatActiviteEnumeration etat, Projet projet) {
        this.libelle = libelle;
        this.dateFinActivite = dateFinActivite;
        this.etat = etat;
        this.projet = projet;
    }

}
