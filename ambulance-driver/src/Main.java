/**
 * @authur M Menze 220034828
 * @version Practical X 2020
 */

import csc2a.ui.GamePane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {


    @Override
    public void start(Stage stage) {
        ArrayList<String> Inputs = new ArrayList<>();
        GamePane gamePane = new GamePane();
        gamePane.setInputs(Inputs);

        Scene GamePlay = new Scene(gamePane);


        GamePlay.setOnKeyPressed(e->{
            String Key = e.getCode().toString();
            if(!Inputs.contains(Key))
                Inputs.add(Key);
        });
        GamePlay.setOnKeyReleased(e->{
            String key = e.getCode().toString();
            if(Inputs.contains(key))
                Inputs.remove(key);
        });

            stage.setScene(GamePlay);
            stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
