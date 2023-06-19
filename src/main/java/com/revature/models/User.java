package com.revature.models;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "role_id_fk")
    private Role role_id_fk;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public User() {
    }

    public User(int id, String firstName, String lastName, Role role_id_fk, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role_id_fk = role_id_fk;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole_id_fk() {
        return role_id_fk;
    }

    public void setRole_id_fk(Role role_id_fk) {
        this.role_id_fk = role_id_fk;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role_id_fk=" + role_id_fk +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
