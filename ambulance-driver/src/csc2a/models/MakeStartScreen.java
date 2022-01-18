package csc2a.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MakeStartScreen extends  GameAssets{

    public MakeStartScreen(double x, double y, double width, double height, Color color) {
        super(x, y, width, height, color);
    }

    public void renderStart(GraphicsContext graphicsContext){
        graphicsContext.save();
        graphicsContext.setFill(this.getColor());
        graphicsContext.fillRect(this.getX(),this.getY(), this.getWidth(), this.getHeight());
        graphicsContext.save();

        graphicsContext.setStroke(Color.WHITE);
        graphicsContext.setFont(Font.font("Verdana",30));
        graphicsContext.strokeText("Select a Level in Options\n"+"And start saving Lives",150,73);

        graphicsContext.setStroke(Color.WHITE);
        graphicsContext.setFont(Font.font("Verdana",12));
        graphicsContext.strokeText("Level 1 \n"+"Level 2\n"+"Level 3\n"+"! ! Hint do not drive\n" +
                "close to the walls ",150,160);

    }
}
