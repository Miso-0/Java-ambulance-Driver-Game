package csc2a.dp.VD;

import csc2a.dp.VD.GameVisitor;
import csc2a.models.Ambulance;
import csc2a.models.Hospital;
import csc2a.models.Patient;
import csc2a.models.Walls;
import javafx.scene.canvas.GraphicsContext;

public class ConcreteGameVisitor implements GameVisitor {

    private GraphicsContext graphicsContext;

    public ConcreteGameVisitor(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        this.setGraphicsContext(graphicsContext);
    }

    public void setGraphicsContext(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void Spawn(Ambulance ambulance) {
        this.graphicsContext.save();
        this.graphicsContext.setFill(ambulance.getColor());
       this.graphicsContext.fillRect(ambulance.getX(),ambulance.getY(), ambulance.getWidth(), ambulance.getHeight());
       this.graphicsContext.save();
    }

    @Override
    public void Spawn(Hospital hospital) {
        this.graphicsContext.save();
        this.graphicsContext.setFill(hospital.getColor());
        this.graphicsContext.fillRect(hospital.getX(),hospital.getY(), hospital.getWidth(), hospital.getHeight());
        this.graphicsContext.save();
    }

    @Override
    public void Spawn(Walls walls) {
        graphicsContext.setFill(walls.getColor());
        graphicsContext.fillRect(walls.getX(),walls.getY(),walls.getWidth(),walls.getHeight());
    }

    @Override
    public void Spawn(Patient patient) {
        this.graphicsContext.save();
        this.graphicsContext.setFill(patient.getColor());
        this.graphicsContext.fillRect(patient.getX(),patient.getY(), patient.getWidth(), patient.getHeight());
        this.graphicsContext.save();
    }
}
