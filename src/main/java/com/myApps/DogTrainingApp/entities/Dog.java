package com.myApps.DogTrainingApp.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="dogs")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="age")
    private int age;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy="dog",
                fetch=FetchType.LAZY,
                cascade={CascadeType.REFRESH, CascadeType.DETACH,
                CascadeType.MERGE, CascadeType.PERSIST})
    private List<TrainingSession> trainingSessionList;

    public Dog(){

    }

    public Dog(String name, User owner, int age){
        this.name=name;
        this.user =owner;
        this.age=age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<TrainingSession> getTrainingSessionList() {
        return trainingSessionList;
    }

    public void setTrainingSessionList(List<TrainingSession> trainingSessionList) {
        this.trainingSessionList = trainingSessionList;
    }

    /**
     *  Helper method for adding training session:
     */
    public void add(TrainingSession session){
        if(trainingSessionList==null){
            trainingSessionList= new ArrayList();
        }
        trainingSessionList.add(session);
    }
}
