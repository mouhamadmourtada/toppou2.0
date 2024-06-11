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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "financement_bailleurs")
@SQLDelete(sql = "UPDATE financement_bailleurs SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class FinancementBailleurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "financement_id", referencedColumnName = "id")
    private Financement financement;

    @ManyToOne
    @JoinColumn(name = "bailleur_id", referencedColumnName = "id")
    private User bailleur;

    @Column(name = "montant")
    private double montant;

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

    // Constructeurs, getters, setters, etc.

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
}
