package csc2a.models;

import csc2a.dp.VD.GameVisitable;
import csc2a.dp.VD.GameVisitor;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Patient extends  GameAssets implements GameVisitable {
    private String name;
    private int Age;

    public Patient(double x, double y, double width, double height, Color color) {
        super(x, y, width, height, color);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    @Override
    public void accept(GameVisitor gameVisitor) {

    }
}
