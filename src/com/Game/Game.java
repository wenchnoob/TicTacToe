package com.Game;

import com.Board.Board;
import com.Player.Player;
import com.Player.PlayerOne;
import com.Player.PlayerTwo;

import java.io.*;
import java.util.Scanner;

public class Game implements Runnable{

    private boolean isOver = false;
    private Board board;
    private PlayerOne p1;
    private PlayerTwo p2;
    private int turn = 0;
    private int winner = 0;

    public Game() {
        board = new Board();
        p1 = new PlayerOne(board);
        p2 = new PlayerTwo(board);
    }

    @Override
    public void run() {
        while (!isOver) {
            try {
                gameLoop();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(board.toString());

        switch(winner) {
            case 0:
                System.out.println("The game is a tie!");
                break;
            case 1:
                System.out.println("Player one has won!!!! :) ");
                break;
            case 2:
                System.out.println("Player two has won!!!! :) ");
        }
    }

    private void gameLoop() throws IOException {
        switch (turn%2) {
            case(0):
                getPlay(p1);
                break;
            case(1):
                getPlay(p2);
        }
        checkEnd(board.state());
    }

    private void getPlay(Player p) throws IOException {
        var read = new Scanner(System.in);

        int x, y;

        boolean played;
        System.out.println(board.toString());
        do {
            System.out.print("\n" + p.getName() + " please input your desired row position {1, 3}: ");
            y = read.nextInt();

            System.out.print("\n" +p.getName() + " please input your desired column position {1, 3}: ");
            x = read.nextInt();

            if (p instanceof PlayerOne) {
                played = ((PlayerOne)p).play(x-1, y-1);
            } else {
                played = ((PlayerTwo)p).play(x-1, y-1);
            }

        } while(!played);

        System.out.println("\n" +p.getName() + " played at position (" + x + ", " + y + ")");
        turn++;
    }

    private void checkEnd(int[][] gameState) {
        if (checkRow(gameState, 0)) return;
        if (checkRow(gameState, 1)) return;
        if (checkRow(gameState, 2)) return;

        if (checkCol(gameState, 0)) return;
        if (checkCol(gameState, 1)) return;
        if (checkCol(gameState, 2)) return;

        checkDiag(gameState);
    }

    private boolean checkRow(int[][] gameState, int i) {
        if (gameState[i][0] == 88 && gameState[i][1] == 88 && gameState[i][2] == 88) {
            winner = 1;
            isOver = true;
            return true;
        } else if (gameState[i][0] == 79 && gameState[i][1] == 79 && gameState[i][2] == 79) {
            winner = 2;
            isOver = true;
            return true;
        }
        return false;
    }

    private boolean checkCol(int[][] gameState, int i) {
        if (gameState[0][i] == 88 && gameState[1][i] == 88 && gameState[2][i] == 88) {
            winner = 1;
            isOver = true;
            return true;
        } else if (gameState[0][i] == 79 && gameState[1][i] == 79 && gameState[2][i] == 79) {
            winner = 2;
            isOver = true;
            return true;
        }
        return false;
    }

    private boolean checkDiag(int[][] gameState) {
        if (gameState[0][0] == 88 && gameState[1][1] == 88 && gameState[2][2] == 88 ||
                gameState[0][2] == 88 && gameState[1][1] == 88 && gameState[2][0] == 88) {
            winner = 1;
            isOver = true;
            return true;
        } else if (gameState[0][0] == 79 && gameState[1][1] == 79 && gameState[2][2] == 79 ||
                gameState[0][2] == 79 && gameState[1][1] == 79 && gameState[2][0] == 79) {
            winner = 2;
            isOver = true;
            return true;
        }
        return  false;
    }

}
