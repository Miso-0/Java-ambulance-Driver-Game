package csc2a.dp.VD;

import csc2a.dp.VD.GameVisitor;

public interface GameVisitable {
    public void accept(GameVisitor gameVisitor);
}
