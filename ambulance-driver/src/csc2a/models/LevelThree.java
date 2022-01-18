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
 * render level Three to the canvas and its sub components
 * namely :- the walls,
 * the game background,
 * the Patients on during gameplay
 *
 */
public class LevelThree extends  GameLevels{


    private ArrayList<Walls>  walls;

    public LevelThree(){
        this.walls = new ArrayList<>();
        this.addPatients();
        this.setExperience(1000);
    }

    public  void addPatients(){
        this.getPatientArrayList().add(new Patient(171,116,30,30,Color.BLACK));
        this.getPatientArrayList().add(new Patient(568,126,20,20,Color.YELLOWGREEN));
        this.getPatientArrayList().add(new Patient(327,197,20,20,Color.GREEN));

    }


    /**
     *  renders level Three to the canvas and its sub components
     * @param graphicsContext
     */
    public  void renderLevel(GraphicsContext graphicsContext){
        ConcreteGameVisitor concreteGameVisitor = new ConcreteGameVisitor(graphicsContext);
        graphicsContext.save();
        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillRect(0,0,700,600);
        graphicsContext.restore();


        Walls wall1 = new Walls(118,70,500 ,10, Color.BLACK);
        Walls wall2 = new Walls(614,70,10 ,450, Color.BLACK);
        Walls wall3 = new Walls(70,517,554,10, Color.BLACK);//
        Walls wall4 = new Walls(118,70,10 ,90, Color.BLACK);
        Walls wall5 = new Walls(118,158,250 ,10, Color.BLACK);
        Walls wall6 = new Walls(368,158,10 ,280, Color.BLACK);
        Walls wall7 = new Walls(272,158,10 ,200, Color.BLACK);
        Walls wall8 = new Walls(0,356,283 ,10, Color.BLACK);
        Walls wall9 = new Walls(70,435,470 ,10, Color.BLACK);
        Walls wall10 = new Walls(0,235,190 ,10, Color.BLACK);
        Walls wall11 = new Walls(189,235,10 ,60, Color.BLACK);
        Walls wall12 = new Walls(477,79,10 ,60, Color.BLACK);
        Walls wall13 = new Walls(477,199,10 ,50, Color.BLACK);
        Walls wall14 = new Walls(477,248,148 ,10, Color.BLACK);
        Walls wall15 = new Walls(372,327,60 ,10, Color.BLACK);
        Walls wall16 = new Walls(490,327,50 ,10, Color.BLACK);
        Walls wall17 = new Walls(539,327,10 ,119, Color.BLACK);


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
        this.getWalls().add(wall14);
        this.getWalls().add(wall15);
        this.getWalls().add(wall16);
        this.getWalls().add(wall17);
        super.setWalls(walls);


        hospital = new Hospital(0,246,70,70,Color.WHITE);
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
