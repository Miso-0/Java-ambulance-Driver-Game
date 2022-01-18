package csc2a.dp.ABF;

import csc2a.models.GameLevels;
import csc2a.models.LevelOne;
import csc2a.models.LevelThree;
import csc2a.models.LevelTwo;

public class LevelGameFactory implements AbstractGameFactory {
    @Override
    public GameLevels MakeLevelOne() {
        return new LevelOne();
    }

    @Override
    public GameLevels MakeLevelTwo() {
        return new LevelTwo();
    }

    @Override
    public GameLevels MakeLevelThree() {
        return new LevelThree();
    }
}
