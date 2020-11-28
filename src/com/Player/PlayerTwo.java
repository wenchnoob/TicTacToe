package com.Player;

import com.Board.Board;
import com.Utilities.Enums.PlaySet;

public class PlayerTwo extends Player {
    public PlayerTwo(Board board) {
        super(board);
        Name = "Player Two";
    }
    public boolean play(int x, int y) { return super.play(x, y, PlaySet.PlayerTwo.getID()); }
}
