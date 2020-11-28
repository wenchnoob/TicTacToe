package com.Board;

import com.Utilities.Exceptions.PositionTakenException;
import java.util.Arrays;

public class Board {

    private char[][] board = {
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '}
    };


    public int[][] state() {
        return new int[][] {{board[0][0], board[0][2], board[0][4]}, {board[2][0], board[2][2], board[2][4]}, {board[4][0], board[4][2], board[4][4]}};
    }

    public boolean place(int x, int y, char c, boolean isErasing) throws PositionTakenException {
        switch(x) {
            case 0, 1, 2:
                switch (y) {
                    case 0, 1, 2 -> {
                        if (board[y*2][x*2] != ' ' && !isErasing) throw new PositionTakenException();
                        board[y*2][x*2] = c;
                        return true;
                    }
                }
        }
        return false;
    }

    public boolean erase(int x, int y, char id) throws PositionTakenException { return place(x, y, ' ', true);}

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (char[] charArr: board) {
            for (char c: charArr) {
                sb.append(c);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
