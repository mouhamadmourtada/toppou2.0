package dgi.dic2.a4l0u_c0d3.toppou20.dto;

import jakarta.validation.constraints.NotEmpty;

public class PasswordResetRequest {
    private String password;

    // Getter et Setter
    @NotEmpty(message =  "il faut renseigner le mot de passe")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

