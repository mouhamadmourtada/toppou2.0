package dgi.dic2.a4l0u_c0d3.toppou20.dto;

import dgi.dic2.a4l0u_c0d3.toppou20.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetChefProjetResponse {
    List<User> chefProjets;
}
