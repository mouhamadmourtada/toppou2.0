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
import org.springframework.data.rest.core.annotation.RestResource;

@Data
// @Data c'est pour quoi ?
// @Data est un projet Lombok qui génère automatiquement les méthodes equals, hashCode, toString, les getters et les setters pour tous les champs de la classe.
@AllArgsConstructor
@NoArgsConstructor
@Builder
// @Builder c'est pour quoi ?
// @Builder est un projet Lombok qui génère automatiquement un constructeur qui initialise toutes les propriétés requises et un constructeur qui initialise toutes les propriétés.
@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "date_naissance")
    private Date dateNaissance;

    @Column(name = "lieu_naissance")
    private String lieuNaissance;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "titre")
    private String titre;

    @Column(name = "status")
    private String status;

    @Column(name = "grade")
    private String grade;

    @Column(name = "is_confirmed", nullable = false, columnDefinition = "boolean default false")
    private boolean isConfirmed = false;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date createdAt;

    @Column(name = "updated_at",
        nullable = true
    )
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date updatedAt;

    @Column(name = "deleted", nullable = false)
    @JsonIgnore
    private boolean deleted = false;

    @Column(name = "actif", nullable = false)
    private boolean actif = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

//    @ManyToMany(mappedBy = "users")
    @ManyToMany(mappedBy = "acteurs")
    private Set<Projet> projets = new HashSet<>();
//    private Set<Projet> projets = new HashSet<>();

    @OneToMany(mappedBy = "bailleur")
    private Set<Projet> projetsBailleurs = new HashSet<>();
//    les projets dont il est le bailleur

    @OneToMany(mappedBy = "chefProjet")
    private Set<Projet> projetsChefs = new HashSet<>();
//    les projets dont il est le chef de projet

    @ManyToMany(mappedBy = "bailleurs")
    private Set<Financement> financements = new HashSet<>();
//    les financement dont il a participé

    // Constructors, getters and setters

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }



    public boolean getActif () {
        return this.actif;
    }

    public void setIsConfirmed(boolean isConfirmed){
        this.isConfirmed = isConfirmed;

    }

    // public User() {
    // }

    // public User(String email, String username, String password) {
    //     this.email = email;
    //     this.username = username;
    //     this.password = password;
    // }

    // Getters and setters
    // ...
}
