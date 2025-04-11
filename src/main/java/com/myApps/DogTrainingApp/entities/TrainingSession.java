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

    @Column(name="progress")
    private Map<String, int[]> progress;

    public TrainingSession(){

    }

    public TrainingSession(LocalDate date){
        this.date=date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, int[]> getProgress() {
        return progress;
    }

    public void setProgress(Map<String, int[]> progress) {
        this.progress = progress;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
