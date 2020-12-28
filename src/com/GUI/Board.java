package com.GUI;

import com.Logic.Board.LogicBoard;
import com.Logic.Player.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Board extends JPanel {
    private List<JButton> gridCells;
    private LogicBoard logicBoard;
    private Player p1, p2;
    private Header header;
    private boolean[] ended = {false, false};
    private final Window parent;

    ActionListener gameListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent action) {
            String cmd = action.getActionCommand();
            Play p = new Play(Integer.parseInt(cmd.substring(1)), Integer.parseInt(cmd.substring(0,1)), logicBoard.getTurn() == 1 ? p1 : p2);

            if(logicBoard.makePlay(p.x, p.y, p.player)) {
                ((JButton)action.getSource()).setText(p.player.getSym());
                ended = logicBoard.checkEnd();

                if(ended[0]) {
                   stopGame();
                } else {
                    if(ended[1]) stopGame();
                }

            } else {
                header.setContent("That position is taken. Try again!");
                new Thread(() -> {
                    TimeUnit t = TimeUnit.of(ChronoUnit.MILLIS);
                    try {
                        t.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    header.revert();
                }).start();
            }
        }
    };

    public Board(LogicBoard logicBoard, Window parent) {
        this.logicBoard = logicBoard;
        this.parent = parent;
        header = (Header) parent.getPanel(Window.WindowContentConstants.HEADER);
        p1 = parent.getPlayerOne();
        p2 = parent.getPlayerTwo();

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


        gridCells.forEach(cell -> {
            cell.addActionListener(gameListener);
            add(cell);
        });
        startGame();
    }

    private JButton makeButton(String cmd) {
        JButton button1 = new JButton("");
        button1.setActionCommand(cmd);
        button1.setBorder(BorderFactory.createBevelBorder(1));
        button1.setBackground(Color.WHITE);
        return button1;
    }

    public void startGame() {
        header.reset();
        logicBoard.restart();
        gridCells.forEach(cell -> {
            cell.setText(" ");
            cell.setEnabled(true);
        });
    }

    public void stopGame() {
        gridCells.forEach(cell -> {
            cell.setEnabled(false);
            Footer footer = (Footer) parent.getPanel(Window.WindowContentConstants.FOOTER);
            footer.setContentPane(parent.getPanel(Window.WindowContentConstants.RESTART));
        });
        if(ended[0] || ended[1]) roundOver();
    }

    private void roundOver() {
        if(ended[0]) {
            if(ended[1]) {
                header.setContent(parent.getPlayerOne().getName() + " has won!");
            } else {
                header.setContent(parent.getPlayerTwo().getName() + " Player two has won!");
            }
        } else {
            header.setContent("The game has ended as a TIE!");
        }

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
