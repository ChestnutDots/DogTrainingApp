package com.myApps.DogTrainingApp.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @OneToMany(mappedBy="user",
                fetch=FetchType.LAZY,
                cascade={CascadeType.REFRESH, CascadeType.DETACH,
                CascadeType.MERGE, CascadeType.PERSIST})
    private List<Dog> dogs;

    public User(){

    }

    public User(String username, String password){
        this.username=username;
        this.password=password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Helper method for the bi-directional relationship between user and dogs
     */
    public void add(Dog tempDog){
        if(dogs==null){
            dogs=new ArrayList();
        }

        dogs.add(tempDog);

        tempDog.setUser(this);
    }
}
