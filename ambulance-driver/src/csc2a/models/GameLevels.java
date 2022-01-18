package csc2a.models;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

/**
 * Abstract classes to render levels
 * during game ply
 *
 */

public  class GameLevels {
    private ArrayList<Walls> walls;
    public Hospital hospital;
    private Ambulance ambulance;
    private ArrayList<Patient> patientArrayList;
    private int Experience;

    public GameLevels(){
        this.walls = new ArrayList<>();
        this.patientArrayList = new ArrayList<>();
    }

    public int getExperience() {
        return Experience;
    }

    public void setExperience(int experience) {
        Experience = experience;
    }
    public void addExp(){
      setExperience(this.Experience+20000);
    }
    public void SubExp(){
        setExperience((int) (this.Experience-0.000000005));
    }
    public boolean isFired(){
        boolean isFired;
        if(this.Experience<=0){
            isFired =true;
        }else{
            isFired = false;
        }
        return  isFired;
    }
    public boolean hasWon(){
        boolean hasWon ;
        if(this.Experience >= 60000){
            hasWon = true;
        }else {
            hasWon =false;
        }
        return hasWon;
    }

    public ArrayList<Walls> getWalls() {
        return walls;
    }

    public void setWalls(ArrayList<Walls> walls) {
        this.walls = walls;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Ambulance getAmbulance() {
        return ambulance;
    }
    public ArrayList<Patient> getPatientArrayList() {
        return patientArrayList;
    }

    public void setPatientArrayList(ArrayList<Patient> patientArrayList) {
        this.patientArrayList = patientArrayList;
    }

    public  void addPatients(){}
    public  void setAmbulance(Ambulance ambulance){
        this.ambulance = ambulance;
    }
    public void renderLevel(GraphicsContext graphicsContext){}
}
