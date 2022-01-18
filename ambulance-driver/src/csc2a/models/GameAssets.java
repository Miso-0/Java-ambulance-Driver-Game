package csc2a.models;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class GameAssets {
    private double x,y;
    private double width,height;
    private Color color;

    public GameAssets(double x, double y, double width, double height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    public  void setXY(double x,double y){
        this.x =x;
        this.y =y;
    }

    public Color getColor() {
        return color;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }


    public boolean OverlapsImage(GameAssets otherAsset){
        boolean touches ;
            if(this.x+this.width-5 <otherAsset.getX()||
                    otherAsset.getX() + otherAsset.width -5< this.x||
                    this.y + this.height-5< otherAsset.getY()||
                    otherAsset.getY()+otherAsset.getHeight() -5< this.y){
                touches = false;
            }else {
                touches = true;
            }
            return touches;
    }

}
