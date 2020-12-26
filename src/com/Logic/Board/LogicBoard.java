package com.Logic.Board;

import com.Logic.Player.Player;
import com.Logic.Utilities.Exceptions.PositionTakenException;

public class LogicBoard {

    private int turn = 1;

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

    public boolean makePlay(int x, int y, Player p) {
        int id = p.getID();
        try {
            if(id == 1) {
                place(x, y, 'X', false);
            } else {
                place(x, y, 'O', false);
            }

            turn = id == 1 ? 2 : 1;

            return true;
        } catch (PositionTakenException ex) {
            return false;
        }
    }

    public int getTurn() {
        return turn;
    }

    // return [t,t] if player one wins, return [t,f] if player two wins, return [f,f] if no one has won, and return [f, t] if the game is a tie.
    public boolean[] checkEnd() {
        return checkEnd(state());
    }

    private boolean[] checkEnd(int[][] gameState) {
        boolean[] result = checkRow(gameState, 0);
        if (result[0]) return result;

        result = checkRow(gameState, 1);
        if (result[0]) return result;

        result = checkRow(gameState, 2);
        if (result[0]) return result;

        result = checkCol(gameState, 0);
        if (result[0]) return result;

        result = checkCol(gameState, 1);
        if (result[0]) return result;

        result = checkCol(gameState, 2);
        if (result[0]) return result;

        result = checkDiag(gameState);
        if (result[0]) return result;

        return new boolean[]{false, isFilled(gameState)};
    }

    private boolean[] checkRow(int[][] gameState, int i) {
        if (gameState[i][0] == 88 && gameState[i][1] == 88 && gameState[i][2] == 88) {
            return new boolean[] {true, true};
        } else if (gameState[i][0] == 79 && gameState[i][1] == 79 && gameState[i][2] == 79) {
            return new boolean[] {true, false};
        }
        return new boolean[] {false, false};
    }

    private boolean[] checkCol(int[][] gameState, int i) {
        if (gameState[0][i] == 88 && gameState[1][i] == 88 && gameState[2][i] == 88) {
            return new boolean[] {true, true};
        } else if (gameState[0][i] == 79 && gameState[1][i] == 79 && gameState[2][i] == 79) {
            return new boolean[] {true, false};
        }
        return new boolean[] {false, false};
    }

    private boolean[] checkDiag(int[][] gameState) {
        if (gameState[0][0] == 88 && gameState[1][1] == 88 && gameState[2][2] == 88 ||
                gameState[0][2] == 88 && gameState[1][1] == 88 && gameState[2][0] == 88) {
            return new boolean[] {true, true};
        } else if (gameState[0][0] == 79 && gameState[1][1] == 79 && gameState[2][2] == 79 ||
                gameState[0][2] == 79 && gameState[1][1] == 79 && gameState[2][0] == 79) {
            return new boolean[] {true, false};
        }
        return new boolean[] {false, false};
    }

    private boolean isFilled(int[][] gameState){
        for(int i = 0; i < gameState.length; i++) {
            for (int j = 0; j < gameState[i].length; j++) if (gameState[i][j] != 88 && gameState[i][j] != 79) return false;
        }
        return true;
    }

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
