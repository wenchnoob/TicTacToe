package com.Player;

import com.Board.Board;
import com.Utilities.Enums.PlaySet;
import com.Utilities.Interfaces.Plays;
import com.Utilities.Exceptions.PositionTakenException;

public abstract class Player implements Plays {

    Board board;
    String Name;

    Player(Board board) {
        this.board = board;
    }

    @Override
    public boolean play(int x, int y, char c) {
        try {
            return board.place(x, y, c, false);
        } catch(PositionTakenException e) {
            return false;
        }
    }

    @Override
    public boolean erase(int x, int y) {
        try {
            return board.erase(x, y, PlaySet.Erase.getID());
        } catch(PositionTakenException e) {
            return false;
        }
    }

    public String getName() {
        return Name;
    }
}
