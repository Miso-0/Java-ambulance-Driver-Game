/**
 * @authur M Menze 220034828
 * @version Practical X 2020
 */
package csc2a.ui;

import csc2a.Files.TextualFile;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;




public class OptionLayout {
    private TextArea textArea;
    private Button close = new Button("Close window");
    private Button about = new Button("About");
    private Button Controls = new Button("Controls");
    private Button MoreAboutDeveloper = new Button("Developer");
    private Button Quit = new Button("Quit");
    private Button PlayerStats = new Button("Your Statistics");

    public  void Display(String whatToShow){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Game Options");
        window.setMinWidth(600);
        window.setMinHeight(420);

        textArea = new TextArea();
        textArea.setPrefSize(420,400);
        textArea.setEditable(false);

        close.setOnAction(e->{
            window.close();
        });

        BorderPane root = new BorderPane();

        switch (whatToShow){
            case "Controls":
                root.setCenter(ShowControls());
                break;
            case "Stats":
                root.setCenter(ShowStatistics());
                break;
            case "About":
                root.setCenter(ShowAbout());
                break;
            case "Dev":
                root.setCenter(ShowDev());
                break;
        }

        Controls.setOnAction(e->{
            textArea.clear();
            root.setCenter(ShowControls());
        });

        about.setOnAction(e->{
            textArea.clear();
            root.setCenter(ShowAbout());
        });


        PlayerStats.setOnAction(e->{
            textArea.clear();
            root.setCenter(ShowStatistics());
        });

        MoreAboutDeveloper.setOnAction(e->{
            textArea.clear();
            root.setCenter(ShowDev());
        });
        Quit.setOnAction(e->{
            window.close();
            Platform.exit();
        });

        root.setLeft(MakeSideButtons());

        Scene scene = new Scene(root);
        window.setScene(scene);
        window.showAndWait();
    }

    public TextArea ShowControls(){
        for(String line : TextualFile.readerControls("data/controls.txt"))
        textArea.appendText(line+"\n");
        return textArea;
    }

    public TextArea ShowStatistics(){
        for(String line : TextualFile.readerControls("data/game_save.txt"))
            textArea.appendText(line+"\n");
        return textArea;
    }
    public TextArea ShowAbout(){
        for(String line : TextualFile.readerControls("data/about.txt"))
            textArea.appendText(line+"\n");
        return textArea;
    }
    public TextArea ShowDev(){
            textArea.appendText("220034828\n"+"Menze ,M");
        return textArea;
    }
    public VBox MakeSideButtons(){
        VBox root = new VBox();
        root.setPrefSize(200,400);
        Controls.setPrefSize(200,70);
        PlayerStats.setPrefSize(200,70);
        about.setPrefSize(200,70);
        MoreAboutDeveloper.setPrefSize(200,70);
        Quit.setPrefSize(200,70);
        close.setPrefSize(200,70);


        root.getChildren().addAll(Controls,about,PlayerStats,MoreAboutDeveloper,close,Quit);

        return root;
    }
}
