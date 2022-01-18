/**
 * @authur M Menze 220034828
 * @version Practical X 2020
 */
package csc2a.ui;

import csc2a.Files.BinaryFile;
import csc2a.Files.TextualFile;
import csc2a.dp.ABF.AbstractGameFactory;
import csc2a.dp.ABF.LevelGameFactory;
import csc2a.dp.VD.ConcreteGameVisitor;
import csc2a.models.*;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class  handles all the game functions
 * it render all the levels according to the player's request
 */
public class GamePane extends StackPane {
    private GraphicsContext graphicsContext;
    private Canvas canvas;
    private double x =490;
    private double y=554;
    private double width,height;
    private ArrayList<String> Inputs;
    private ArrayList<Patient> patientArrayList;
    private ArrayList<Patient> patientArrayListInWorld;
    private double Speed;
    private  boolean Up,Left,Down,Right;
    private ConcreteGameVisitor concreteGameVisitor;
    private int hasPatient ;
    private int hasLost;
    private AbstractGameFactory factory =null;
    private  GameLevels levelOne =null;
    private  GameLevels levelTwo =null;
    private  GameLevels levelThree =null;
    private String choice;
    public boolean LevelOpened;
    private MenuItem OpenLevel;
    private int numPatientsSaved;
    private  MakeStartScreen makeStartScreen;



    public GamePane(){
        this.height =600;
        this.width=700;
        this.Speed =10;
        this.choice ="Level 1";
        LevelOpened = false;
        makeStartScreen = new MakeStartScreen(0,0,700,600,Color.GREEN);

        BorderPane root = new BorderPane();

        canvas = new Canvas(width,height);
        graphicsContext = canvas.getGraphicsContext2D();
        concreteGameVisitor = new ConcreteGameVisitor(this.graphicsContext);
        patientArrayListInWorld = new ArrayList<>();

        Ambulance ambulance= new Ambulance(this.x,this.y,25,25,Color.YELLOW);



        //Making the factory
         factory = new LevelGameFactory();
         levelOne = factory.MakeLevelOne();
         levelTwo =factory.MakeLevelTwo();
         levelThree = factory.MakeLevelThree();

        levelOne.setAmbulance(ambulance);
        levelTwo.setAmbulance(ambulance);
        levelThree.setAmbulance(ambulance);




        /**
        this is the initialization of the choice which is used to select the level the user would
        like to play,now it is initialized to level1
         */
        choice ="start";
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                switch (choice){
                    case "start":
                        makeStartScreen.renderStart(graphicsContext);
                        break;
                    case "Level 1":
                        Speed =8;
                        //making use of the render function to  render the gameplay level onto the canvas
                        levelOne.renderLevel(graphicsContext);
                        setPatientArrayList(levelOne.getPatientArrayList());

                        //Spawning the ambulance onto the canvas
                       concreteGameVisitor.Spawn(ambulance);
                       //Detecting if the world has any patient spawned
                       //if there is no patient in the game it will be spawned in a random place and wait for the ambulance
                        if(patientArrayListInWorld.isEmpty()&& hasPatient==0&&hasLost==0){
                            patientArrayListInWorld.add(patientArrayList.get(GenRand()));
                        }
                        //The detection here is for checking if there is collision between the ambulance and the
                        //Patient if the is collision the ambulance picks up the patient and transport to the hospital
                        for(int i=0;i<patientArrayListInWorld.size();i++){
                            concreteGameVisitor.Spawn(patientArrayListInWorld.get(i));
                            if(ambulance.OverlapsImage(patientArrayListInWorld.get(i))) {
                                patientArrayListInWorld.remove(i);
                                hasPatient=1;
                            }
                        }

                        //Key Pressed handling according to the key pressed the ambulance moves to a certain direction
                        if(Inputs.contains("D"))
                            ambulance.Move(Speed,0);
                        if(Inputs.contains("A"))
                            ambulance.Move(-Speed,0);
                        if(Inputs.contains("W"))
                            ambulance.Move(0,-Speed);
                        if(Inputs.contains("S"))
                            ambulance.Move(0,Speed);
                        //Detecting collision between the ambulance and the hospital and if the ambulance has a patient
                        //if the ambulance has a patient ,the patient is the transported successfully to the hospital
                        // and the player gains experience
                        if(ambulance.OverlapsImage(levelOne.getHospital())&&hasPatient==1){
                            levelOne.addExp();
                            numPatientsSaved+=1;
                            TextualFile.saveStats("Number of Patients Saved: "+numPatientsSaved);
                            TextualFile.saveStats("Experience: "+String.format("%d",levelOne.getExperience()));
                            hasPatient=0;
                        }
                        //if the experience of the player reaches zero then, the player is fired(Loses the game)
                        if(levelOne.isFired()){
                            TextualFile.saveStats("Level 1");
                            TextualFile.saveStats("You Lost!!");
                            TextualFile.saveStats("Your Experience Reached Zero and You got fired");
                            renderLoseText();
                            patientArrayListInWorld.clear();
                            hasLost=1;
//                            Platform.exit();
                        }
                        if(levelOne.hasWon()){
                            TextualFile.saveStats("Level 1");
                            TextualFile.saveStats("You Won!!");
                            TextualFile.saveStats("Your Experience Reached 60000");
                            renderWonText();
                            patientArrayListInWorld.clear();
                            hasLost=1;
                        }
                        //Detecting if the player(ambulance is out of the world
                        //if the player if out of the world ,the experience decrease
                        //the player must find a way back to the game world before the experience gets to Zero
                        if(ambulance.IsOutOfWorld(width,height)){
                            levelOne.SubExp();
                        }
                        break;
                    case "Level 2":
                        Speed =12;
                        //making use of the render function to  render the gameplay level onto the canvas
                        levelTwo.renderLevel(graphicsContext);
                        setPatientArrayList(levelTwo.getPatientArrayList());

                        //Spawning the ambulance onto the canvas
                        concreteGameVisitor.Spawn(ambulance);
                        //Detecting if the world has any patient spawned
                        //if there is no patient in the game it will be spawned in a random place and wait for the ambulance
                        if(patientArrayListInWorld.isEmpty()&& hasPatient==0&&hasLost==0){
                            patientArrayListInWorld.add(patientArrayList.get(GenRand()));
                        }
                        //The detection here is for checking if there is collision between the ambulance and the
                        //Patient if the is collision the ambulance picks up the patient and transport to the hospital
                        for(int i=0;i<patientArrayListInWorld.size();i++){
                            concreteGameVisitor.Spawn(patientArrayListInWorld.get(i));
                            if(ambulance.OverlapsImage(patientArrayListInWorld.get(i))) {
                                patientArrayListInWorld.remove(i);
                                hasPatient=1;
                            }
                        }

                        //Key Pressed handling according to the key pressed the ambulance moves to a certain direction
                        if(Inputs.contains("D"))
                            ambulance.Move(Speed,0);
                        if(Inputs.contains("A"))
                            ambulance.Move(-Speed,0);
                        if(Inputs.contains("W"))
                            ambulance.Move(0,-Speed);
                        if(Inputs.contains("S"))
                            ambulance.Move(0,Speed);
                        //Detecting collision between the ambulance and the hospital and if the ambulance has a patient
                        //if the ambulance has a patient ,the patient is the transported successfully to the hospital
                        // and the player gains experience
                        if(ambulance.OverlapsImage(levelTwo.getHospital())&&hasPatient==1){
                            levelTwo.addExp();
                            numPatientsSaved+=1;
                            TextualFile.saveStats("Number of Patients Saved: "+numPatientsSaved);
                            TextualFile.saveStats("Experience: "+String.format("%d",levelTwo.getExperience()));
                            hasPatient=0;
                        }
                        //if the experience of the player reaches zero then, the player is fired(Loses the game)
                        if(levelTwo.isFired()){
                            TextualFile.saveStats("Level 3");
                            TextualFile.saveStats("You Lost!!");
                            TextualFile.saveStats("Your Experience Reached Zero and You got fired");
                            renderLoseText();
                            patientArrayListInWorld.clear();
                            hasLost=1;
//                            Platform.exit();
                        }
                        if(levelTwo.hasWon()){
                            TextualFile.saveStats("Level 2");
                            TextualFile.saveStats("You Won!!");
                            TextualFile.saveStats("Your Experience Reached 60000");
                            renderWonText();
                            patientArrayListInWorld.clear();
                            hasLost=1;
                        }
                        //Detecting if the player(ambulance is out of the world
                        //if the player if out of the world ,the experience decrease
                        //the player must find a way back to the game world before the experience gets to Zero
                        if(ambulance.IsOutOfWorld(width,height)){
                            levelTwo.SubExp();
                        }
                        break;
                    case "Level 3":
                        Speed =15;
                        //making use of the render function to  render the gameplay level onto the canvas
                        levelThree.renderLevel(graphicsContext);
                        setPatientArrayList(levelThree.getPatientArrayList());

                        //Spawning the ambulance onto the canvas
                        concreteGameVisitor.Spawn(ambulance);
                        //Detecting if the world has any patient spawned
                        //if there is no patient in the game it will be spawned in a random place and wait for the ambulance
                        if(patientArrayListInWorld.isEmpty()&& hasPatient==0 && hasLost==0){
                            patientArrayListInWorld.add(patientArrayList.get(GenRand()));
                        }
                        //The detection here is for checking if there is collision between the ambulance and the
                        //Patient if the is collision the ambulance picks up the patient and transport to the hospital
                        for(int i=0;i<patientArrayListInWorld.size();i++){
                            concreteGameVisitor.Spawn(patientArrayListInWorld.get(i));
                            if(ambulance.OverlapsImage(patientArrayListInWorld.get(i))) {
                                patientArrayListInWorld.remove(i);
                                hasPatient=1;
                            }
                        }

                        //Key Pressed handling according to the key pressed the ambulance moves to a certain direction
                        if(Inputs.contains("D"))
                            ambulance.Move(Speed,0);
                        if(Inputs.contains("A"))
                            ambulance.Move(-Speed,0);
                        if(Inputs.contains("W"))
                            ambulance.Move(0,-Speed);
                        if(Inputs.contains("S"))
                            ambulance.Move(0,Speed);
                        //Detecting collision between the ambulance and the hospital and if the ambulance has a patient
                        //if the ambulance has a patient ,the patient is the transported successfully to the hospital
                        // and the player gains experience
                        if(ambulance.OverlapsImage(levelThree.getHospital())&&hasPatient==1){
                            levelThree.addExp();
                            numPatientsSaved+=1;
                            TextualFile.saveStats("Number of Patients Saved: "+numPatientsSaved);
                            TextualFile.saveStats("Experience: "+String.format("%d",levelThree.getExperience()));
                            hasPatient=0;
                        }
                        //if the experience of the player reaches zero then, the player is fired(Loses the game)
                        if(levelThree.isFired()){
                            TextualFile.saveStats("Level 3");
                            TextualFile.saveStats("You Lost!!");
                            TextualFile.saveStats("Your Experience Reached Zero and You got fired");
                            renderLoseText();
                            patientArrayListInWorld.clear();
                            hasLost=1;
//                            Platform.exit();
                        }
                        if(levelThree.hasWon()){
                            TextualFile.saveStats("Level 3");
                            TextualFile.saveStats("You Won!!");
                            TextualFile.saveStats("Your Experience Reached 60000");
                            renderWonText();
                            patientArrayListInWorld.clear();
                            hasLost=1;
                        }
                        //Detecting if the player(ambulance is out of the world
                        //if the player if out of the world ,the experience decrease
                        //the player must find a way back to the game world before the experience gets to Zero
                        if(ambulance.IsOutOfWorld(width,height)){
                            levelThree.SubExp();
                        }
                        break;
                }

            }
        };

        root.setTop(MakeMenu());
        root.setCenter(canvas);
        this.getChildren().add(root);
        animationTimer.start();
    }
    public  void setPatientArrayList(ArrayList<Patient> patientArrayList){
        this.patientArrayList=patientArrayList;
    }

    public void setInputs(ArrayList<String> inputs) {
        this.Inputs = inputs;
    }
    public  int GenRand(){
        Random random = new Random();
        return random.nextInt(3);
    }



    public MenuBar MakeMenu(){
         MenuBar menuBar = new MenuBar();
         Menu Options = new Menu("Options");
         OpenLevel = new MenuItem("Choose Level");
         MenuItem about = new MenuItem("About");
         MenuItem Controls = new MenuItem("Controls");
         MenuItem MoreAboutDeveloper = new MenuItem("Developer");
         MenuItem Quit = new MenuItem("Quit");
         MenuItem PlayerStats = new MenuItem("Your Statistics");

        Options.getItems().addAll(OpenLevel,Controls,PlayerStats,about ,MoreAboutDeveloper,Quit);
        menuBar.getMenus().add(Options);

        OpenLevel.setOnAction(e->{
            final FileChooser fileChooser= new FileChooser();
            fileChooser.setInitialDirectory(new File("data/Levels"));
            File OpenFile = fileChooser.showOpenDialog(null);
            if(OpenFile!=null){
                this.choice = BinaryFile.readerBin(OpenFile);
                LevelOpened = true;
            }
        });

        Controls.setOnAction(e->{
           optionLayout.Display("Controls");
        });

        about.setOnAction(e->{
            optionLayout.Display("About");
        });
        PlayerStats.setOnAction(e->{
            optionLayout.Display("Stats");
        });
        MoreAboutDeveloper.setOnAction(e->{
            optionLayout.Display("Dev");
        });
        Quit.setOnAction(e->{
            Platform.exit();
        });
        return menuBar;
    }

    public void  renderLoseText(){
        graphicsContext.setStroke(Color.WHITE);
        graphicsContext.setFont(Font.font("Verdana",20));
        graphicsContext.strokeText("You Lost!!\n"+"The ambulance \n"+"Collided with the wall",305,273);
    }
    public void  renderWonText(){
        graphicsContext.setStroke(Color.WHITE);
        graphicsContext.setFont(Font.font("Verdana",30));
        graphicsContext.strokeText("You Win,Your Experience\n" +
                "has reached 60000 exp",305,273);
    }

    private OptionLayout optionLayout= new OptionLayout();
}
