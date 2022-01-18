/**
 * @authur M Menze 220034828
 * @version Practical X 2020
 */
package csc2a.models;

import csc2a.Files.TextualFile;
import csc2a.dp.VD.ConcreteGameVisitor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


import java.util.ArrayList;

/**
 * Inherits from GameLevels super class
 * render level One to the canvas and its sub components
 * namely :- the walls,
 * the game background,
 * the Patients on during gameplay
 *
 */
public class LevelOne extends GameLevels {

    private ArrayList<Walls>  walls;


    public LevelOne(){
        this.walls = new ArrayList<>();
        this.addPatients();
        this.setExperience(5000);
    }
    public  void addPatients(){
        this.getPatientArrayList().add(new Patient(58,40,30,30,Color.GREEN));
        this.getPatientArrayList().add(new Patient(140,306,30,30,Color.YELLOWGREEN));
        this.getPatientArrayList().add(new Patient(185,55,30,30,Color.RED));
    }

    /**
     *  renders level One to the canvas and its sub components
     * @param graphicsContext
     */
    public  void renderLevel(GraphicsContext graphicsContext){
        ConcreteGameVisitor concreteGameVisitor = new ConcreteGameVisitor(graphicsContext);
        graphicsContext.save();
        graphicsContext.setFill(Color.NAVY);
        graphicsContext.fillRect(0,0,700,600);
        graphicsContext.restore();





        Walls wall1 = new Walls(0,88,600 ,10,Color.RED);
        Walls wall2 = new Walls(101,176,600,10,Color.RED);
        Walls wall3 = new Walls(101,176,10,90,Color.RED);
        Walls wall4 = new Walls(101,265,60,10,Color.RED);
        Walls wall5 = new Walls(0,351,250,10,Color.RED);
        Walls wall6 = new Walls(160,184,10,90,Color.RED);
        Walls wall7 = new Walls(250,271,10,90,Color.RED);
        Walls wall8 = new Walls(260,271,340,10,Color.RED);
        Walls wall9 = new Walls(90,436,610,10,Color.RED);//
        Walls wall10 = new Walls(347,358,500,10,Color.RED);
        Walls wall11 = new Walls(347,364,10,80,Color.RED);
        Walls wall12 = new Walls(90,441,10,60,Color.RED);
        Walls wall13 = new Walls(90,501,610,10,Color.RED);


        this.getWalls().add(wall1);
        this.getWalls().add(wall2);
        this.getWalls().add(wall3);
        this.getWalls().add(wall4);
        this.getWalls().add(wall5);
        this.getWalls().add(wall6);
        this.getWalls().add(wall7);
        this.getWalls().add(wall8);
        this.getWalls().add(wall9);
        this.getWalls().add(wall10);
        this.getWalls().add(wall11);
        this.getWalls().add(wall12);
        this.getWalls().add(wall13);


        hospital = new Hospital(610,510,90,90,Color.WHITE);
        super.setHospital(hospital);
        concreteGameVisitor.Spawn(super.getHospital());

        graphicsContext.setStroke(Color.RED);
        graphicsContext.setFont(Font.font("Verdana",12));
        graphicsContext.strokeText("Experience : "+ this.getExperience(),0,11);


        super.setWalls(walls);

        for(int i =0;i<this.getWalls().size();i++){
            concreteGameVisitor.Spawn(super.getWalls().get(i));
            if(super.getAmbulance().OverlapsImage(super.getWalls().get(i))){
                TextualFile.saveStats("Collided with wall "+i);
                this.SubExp();
            }
        }

    }

}
