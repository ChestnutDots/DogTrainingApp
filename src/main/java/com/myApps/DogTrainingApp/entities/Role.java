package com.myApps.DogTrainingApp.entities;

import jakarta.persistence.*;

@Entity
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="role")
    private String role;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Role(){

    }

    public Role(String role, User theUser){
        this.role=role;
        this.user=theUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
