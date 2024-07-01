package dgi.dic2.a4l0u_c0d3.toppou20.dto;


public class PasswordResetResponse {
    private String message;

    public PasswordResetResponse(String passwordResetSuccessful) {
        this.message = passwordResetSuccessful;
    }

    // Getter et Setter
    public String getMessage() {
        return message;
    }

    public void setPassword(String message) {
        this.message = message;
    }
}
