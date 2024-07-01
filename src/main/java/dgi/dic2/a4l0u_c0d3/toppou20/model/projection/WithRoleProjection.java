package dgi.dic2.a4l0u_c0d3.toppou20.model.projection;

import dgi.dic2.a4l0u_c0d3.toppou20.model.User;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.Set;
import dgi.dic2.a4l0u_c0d3.toppou20.model.Role;

@Projection(name = "withRolesProjection", types = { User.class })
public interface WithRoleProjection {
    Long getId();
    String getAdresse();
    String getEmail();
    String getGrade();
    String getNom();
//    String getPassword();
    String getPrenom();
    String getStatus();
    String getTelephone();
    String getTitre();
    String getUsername();
    boolean isActif();
    Date getDateNaissance();
    Set<Role> getRoles();
}
