package com.Logic.Game;

import com.Logic.Board.LogicBoard;
import com.Logic.Player.Player;

import java.util.Scanner;

public class Game implements Runnable{

    private boolean isOver = false;
    private LogicBoard logicBoard;
    private Player p1;
    private Player p2;
    private int turn = 0;
    private int winner = 0;

    public Game(LogicBoard logicBoard) {
        this.logicBoard = logicBoard;
        p1 = new Player(1);
        p2 = new Player(2);
    }

    @Override
    public void run() {
        while (!isOver) {
            gameLoop();
        }

        System.out.println(logicBoard);

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

    private void gameLoop() {
        switch (turn%2) {
            case(0):
                getPlay(p1);
                break;
            case(1):
                getPlay(p2);
        }

        boolean[] gameState = logicBoard.checkEnd();
        if(gameState[0]) {
            isOver = true;
            winner = gameState[1] ? 1 : 2;
        } else {
            if(gameState[1]) isOver = true;
        }
    }

    private void getPlay(Player p) {
        var read = new Scanner(System.in);

        int x, y;

        boolean played;
        System.out.println(logicBoard.toString());
        do {
            System.out.print("\n" + p.getName() + " please input your desired row position {1, 3}: ");
            y = read.nextInt();

            System.out.print("\n" +p.getName() + " please input your desired column position {1, 3}: ");
            x = read.nextInt();

            played = logicBoard.makePlay(x-1, y-1, logicBoard.getTurn() == 1 ? p1 : p2);

        } while(!played);

        System.out.println("\n" +p.getName() + " played at position (" + x + ", " + y + ")");
        turn++;
    }

}
