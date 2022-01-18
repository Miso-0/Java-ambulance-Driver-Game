package csc2a.dp.VD;

import csc2a.models.Ambulance;
import csc2a.models.Hospital;
import csc2a.models.Patient;
import csc2a.models.Walls;

public interface GameVisitor {
    /**
     * spawn methods
     */
    public  void Spawn(Ambulance ambulance);
    public  void Spawn(Hospital hospital);
    public  void Spawn(Walls walls);
    public  void Spawn(Patient patient);
}
