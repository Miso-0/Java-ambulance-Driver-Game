package csc2a.models;

import csc2a.dp.VD.GameVisitable;
import csc2a.dp.VD.GameVisitor;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Inherits GameAssets ,handles the spawning of the ambulance
 */
public class Ambulance extends  GameAssets implements GameVisitable {

    public Ambulance(double x, double y, double width, double height, Color color) {
        super(x, y, width, height, color);
    }

    /**
     *
     * @param dx
     * @param dy
     */
    public void Move(double dx,double dy){
        double x = super.getX();
        double y = super.getY();
        x+=dx;
        y+=dy;
        super.setXY(x,y);
    }

    /**
     *
     * @param wall
     * @return
     */
    public  boolean touches(Walls wall){
        boolean touch;
        if(this.getX()+this.getWidth() <wall.getX()||
                wall.getX() + wall.getWidth() < this.getWidth()||
                this.getY() + this.getHeight() < wall.getY()||
                wall.getY()+wall.getHeight() < this.getY()){
            touch = false;
        }else {
            touch = true;
        }
        return touch;
    }

    /**
     *
     * @param screenWidth
     * @param screenHeight
     * @return
     */
    public  boolean IsOutOfWorld(double screenWidth,double screenHeight){
        boolean OutOfWord = false;
        if(this.getX() + this.getWidth()/2 <0 )
            OutOfWord=true;
        if(this.getX() > screenWidth)
            OutOfWord =true;
        if(this.getY() + this.getHeight()/2 <0)
            OutOfWord=true;
        if(this.getY()  > screenHeight)
            OutOfWord =true;
        return  OutOfWord;
    }

    /**
     *
     * @param gameVisitor
     */
    @Override
    public void accept(GameVisitor gameVisitor) {
        gameVisitor.Spawn(this);
    }
}
