package de.thbingen.UserService.dto;

public class LogoutDto {

    private String email;

    public LogoutDto(String email) {
        this.email = email;
    }
    public LogoutDto() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "LogoutDto{" +
                "email='" + email + '\'' +
                '}';
    }
}
