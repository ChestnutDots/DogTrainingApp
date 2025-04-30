package com.myApps.DogTrainingApp.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Map;

@Entity
@Table(name="TrainingSessions")
public class TrainingSession {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="dog_id")
    private Dog dog;

    @Column(name="command")
    private String command;

    @Column(name="progress")
    private double progress;

    public TrainingSession(){

    }

    public TrainingSession(LocalDate date, Dog dog, User user){
        this.date=date;
        this.dog=dog;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }


}
