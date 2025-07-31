package Model;

import java.time.LocalDateTime;

public class UserModel {
    private String id;
    private String username;
    private String password;
    private Role roles;
    private LocalDateTime createdAt;

    public enum Role {
        PENYEWA,
        USERS
    }

    public UserModel() {}

    public UserModel(String id, String username, String password, Role role) {
        this.setId(id);
        this.setUsername(username);
        this.setPassword(password);
        this.setRoles(role);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role role) {
        this.roles = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
