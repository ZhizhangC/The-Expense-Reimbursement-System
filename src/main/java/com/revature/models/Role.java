package com.revature.models;

import javax.persistence.*;

@Entity
@Table(name = "roles")

public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role_title", unique = true, nullable = false)
    private String title;

    public Role() {
    }

    public Role(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id=" + id +
                ", status_title='" + title + '\'' +
                '}';
    }

}
