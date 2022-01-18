package csc2a.models;

import csc2a.dp.VD.GameVisitable;
import csc2a.dp.VD.GameVisitor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Hospital extends GameAssets implements GameVisitable {

    public Hospital(double x, double y, double width, double height, Color color) {
        super(x, y, width, height, color);
    }
    @Override
    public void accept(GameVisitor gameVisitor) {
        gameVisitor.Spawn(this);
    }
}
