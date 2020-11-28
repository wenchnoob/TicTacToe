package com.Player;

import com.Board.Board;
import com.Utilities.Enums.PlaySet;

public class PlayerOne extends Player {

    public PlayerOne(Board board) {
        super(board);
        Name = "Player One";
    }
    public boolean play(int x, int y) { return super.play(x, y, PlaySet.PlayerOne.getID()); }
}
