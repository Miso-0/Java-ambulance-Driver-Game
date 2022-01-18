package csc2a.dp.ABF;

import csc2a.models.GameLevels;

public interface AbstractGameFactory {
    public abstract GameLevels MakeLevelOne();
    public abstract  GameLevels MakeLevelTwo();
    public abstract  GameLevels MakeLevelThree();
}
