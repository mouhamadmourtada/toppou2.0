package dgi.dic2.a4l0u_c0d3.toppou20.model.projection;


import dgi.dic2.a4l0u_c0d3.toppou20.model.Projet;
import dgi.dic2.a4l0u_c0d3.toppou20.model.User;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "projetWithChefProjet", types = { Projet.class })
public interface ProjetWithChefProjet {
    Long getId();
    String getNom();
    User getChefProjet();
    Long getCoutPlanifies();
    int getAvancement();
    Long getDepenseActuel();
    Date getDateDebutPrevue();
    Date getDateFinPrevue();
    String getStatus();
    Float getBudgetAlloue();
    Float getBudgetRestant();
    String getObjectif();
    Date getDateDebutReel();
    Date getDateFinReel();

}

