package com.GUI;

import com.Logic.Board.LogicBoard;
import com.Logic.Player.Player;

import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Board extends JPanel {
    List<JButton> gridCells;
    LogicBoard logicBoard;
    Player p1, p2;
    Header header = Window.getHead();

    public Board(LogicBoard logicBoard) {
        this.logicBoard = logicBoard;
        p1 = new Player(1);
        p2 = new Player(2);

        setLayout(new GridLayout(3, 3));

        gridCells = new LinkedList<>();

        gridCells.add(makeButton("00"));
        gridCells.add(makeButton("01"));
        gridCells.add(makeButton("02"));

        gridCells.add(makeButton("10"));
        gridCells.add(makeButton("11"));
        gridCells.add(makeButton("12"));

        gridCells.add(makeButton("20"));
        gridCells.add(makeButton("21"));
        gridCells.add(makeButton("22"));

        gridCells.forEach(elem -> {
            elem.addActionListener(action -> {
                String cmd = action.getActionCommand();
                Play p = new Play(Integer.parseInt(cmd.substring(0,1)), Integer.parseInt(cmd.substring(1)), logicBoard.getTurn() == 1 ? p1 : p2);

                if(logicBoard.makePlay(p.x, p.y, p.player)) {
                    ((JButton)action.getSource()).setText(p.player.getSym());
                } else {
                    String cont = header.getLabel().getText();
                    header.getLabel().setText("Illegal move. That position is taken. Try again!");
                    new Thread(() -> {
                        TimeUnit t = TimeUnit.of(ChronoUnit.SECONDS);
                        try {
                            t.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        header.getLabel().setText(cont);
                    }).start();
                }
            });

            add(elem);
        });

    }

    private JButton makeButton(String cmd) {
        JButton button1 = new JButton("");
        button1.setActionCommand(cmd);
        return button1;
    }

    private static class Play {

        public int x, y;
        public Player player;

        public Play(int x, int y, Player player) {
            this.x = x;
            this.y = y;
            this.player = player;
        }

    }
}
