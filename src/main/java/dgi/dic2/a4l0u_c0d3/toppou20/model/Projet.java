package dgi.dic2.a4l0u_c0d3.toppou20.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "projets")
@SQLDelete(sql = "UPDATE projets SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "objectif", columnDefinition = "text")
    private String objectif;

    @Column(name = "coutPlanifies")
    private String coutPlanifies;

    @Column(name = "avancement")
    private int avancement;

    @Column(name = "depenseActuel")
    private long depenseActuel;

    @Column(name = "dateDebutPrevue")
    private Date dateDebutPrevue;

    @Column(name = "dateFinPrevue")
    private Date dateFinPrevue;

    @Column(name = "dateDebutReel")
    private Date dateDebutReel;

    @Column(name = "dateFinReel")
    private Date dateFinReel;

    @Column(name = "status")
    private String status;

    @Column(name = "budgetAlloue")
    private float budgetAlloue;

    @Column(name = "budgetRestant")
    private float budgetRestant;

    @ManyToMany
    @JoinTable(
            name = "projet_acteurs",
            joinColumns = @JoinColumn(name = "projet_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> acteurs = new HashSet<>();

    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Financement> financements = new HashSet<>();



    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

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
    @JoinColumn(name = "bailleur_id", referencedColumnName = "id")
    private User bailleur;

    @ManyToOne
    @JoinColumn(name = "chef_projet_id", referencedColumnName = "id")
    private User chefProjet;
}
