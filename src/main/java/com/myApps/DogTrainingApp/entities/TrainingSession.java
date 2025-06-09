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

    @Column(name="nr_trials")
    private int nr_trials;

    @Column(name="nr_successful")
    private int nr_successful;

    @Column(name="progress")
    private int progress;

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

    public int getNr_trials() {
        return nr_trials;
    }

    public void setNr_trials(int trials) {
        this.nr_trials = trials;
    }

    public int getNr_successful() {
        return nr_successful;
    }

    public void setNr_successful(int nr_successful) {
        this.nr_successful = nr_successful;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
