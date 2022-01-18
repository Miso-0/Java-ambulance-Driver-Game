/**
 * @authur M Menze 220034828
 * @version Practical X 2020
 */
package csc2a.models;

import csc2a.Files.TextualFile;
import csc2a.dp.VD.ConcreteGameVisitor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

/**
 * Inherits from GameLevels super class
 * render level Two to the canvas and its sub components
 * namely :- the walls,
 * the game background,
 * the Patients on during gameplay
 *
 */
public class LevelTwo extends  GameLevels{

    private ArrayList<Walls>  walls;

    public LevelTwo(){
        this.walls = new ArrayList<>();
        this.addPatients();
        this.setExperience(2500);
    }

    public  void addPatients(){
        this.getPatientArrayList().add(new Patient(660,42,30,30,Color.NAVY));
        this.getPatientArrayList().add(new Patient(24,456,20,20,Color.CORAL));
        this.getPatientArrayList().add(new Patient(548,301,20,20,Color.ALICEBLUE));
    }


    /**
     *  renders level Two to the canvas and its sub components
     * @param graphicsContext
     */
    public  void renderLevel(GraphicsContext graphicsContext){
        /*
           29/05/2021
           https://all-free-download.com/free-photos/
           LevelTwo.jpg
         */
        ConcreteGameVisitor concreteGameVisitor = new ConcreteGameVisitor(graphicsContext);
        graphicsContext.save();
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillRect(0,0,700,600);
        graphicsContext.restore();


        Walls wall1 = new Walls(118,90,600 ,10, Color.BLUE);
        Walls wall2 = new Walls(118,100,10 ,400, Color.BLUE);
        Walls wall3 = new Walls(227,185,10 ,600, Color.BLUE);
        Walls wall5 = new Walls(306,185,10 ,81, Color.BLUE);
        Walls wall6 = new Walls(306,265,250 ,10, Color.BLUE);
        Walls wall7 = new Walls(550,187,10 ,86, Color.BLUE);
        Walls wall8 = new Walls(550,187,50 ,10, Color.BLUE);
        Walls wall9 = new Walls(600,187,10 ,150, Color.BLUE);
        Walls wall10 = new Walls(236,330,371 ,10, Color.BLUE);
        Walls wall11 = new Walls(400,96,10 ,100, Color.BLUE);
        Walls wall12 = new Walls(450,96,10 ,100, Color.BLUE);
        Walls wall13 = new Walls(400,194,61 ,10, Color.BLUE);
        Walls wall14 = new Walls(315,421,500 ,10, Color.BLUE);
        Walls wall15 = new Walls(315,421,10 ,40, Color.BLUE);
        Walls wall16 = new Walls(232,528,370 ,10, Color.BLUE);
        Walls wall17 = new Walls(320,456,390 ,10, Color.BLUE);


        this.getWalls().add(wall1);
        this.getWalls().add(wall2);
        this.getWalls().add(wall3);
        this.getWalls().add(wall5);
        this.getWalls().add(wall6);
        this.getWalls().add(wall7);
        this.getWalls().add(wall8);
        this.getWalls().add(wall9);
        this.getWalls().add(wall10);
        this.getWalls().add(wall11);
        this.getWalls().add(wall12);
        this.getWalls().add(wall13);
        this.getWalls().add(wall14);
        this.getWalls().add(wall15);
        this.getWalls().add(wall16);
        this.getWalls().add(wall17);

        super.setWalls(walls);


        hospital = new Hospital(239,539,70,70,Color.WHITE);
        super.setHospital(hospital);
        concreteGameVisitor.Spawn(super.getHospital());

        graphicsContext.setStroke(Color.YELLOW);
        graphicsContext.setFont(Font.font("Verdana",12));
        graphicsContext.strokeText("Experience : "+ this.getExperience(),0,11);

        for(int i =0;i<this.getWalls().size();i++){
            concreteGameVisitor.Spawn(super.getWalls().get(i));
            if(super.getAmbulance().OverlapsImage(super.getWalls().get(i))){
                TextualFile.saveStats("Collided with wall "+i);
                this.SubExp();
            }
        }

    }
}
