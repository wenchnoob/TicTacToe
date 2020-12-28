package com.GUI;

import com.Logic.Board.LogicBoard;
import com.Logic.Player.Player;
import com.Logic.Utilities.Exceptions.NoSuchPlayerException;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private static Player p1;
    private static Player p2;

    private static Header header;
    private static Footer footer;
    private static PlayerPanel panel1, panel2;
    private static Board board;

    private static final JPanel restartPanel = new JPanel() {
        {
            JButton reset = new JButton("Restart");
            reset.addActionListener(action -> {
                board.startGame();
                footer.reset();
                header.reset();
            });
            reset.setAlignmentX(getWidth()/2.0f);
            reset.setAlignmentY(getHeight()/2.0f);
            add(reset);
        }
    };

    public Window(LogicBoard logicBoard) {
        // Unless an attempt is made to get a player other than 1 or 2, no error should be thrown.
        try {
            p1 = logicBoard.getPlayer(1);
            p2 = logicBoard.getPlayer(2);
        } catch (NoSuchPlayerException e) { }

        header = new Header(this);
        footer = new Footer(this);
        panel1 = new PlayerPanel(p1.getID(), this);
        panel2 = new PlayerPanel(p2.getID(), this);

        getInsets().set(100, 10, 10, 10);
        setSize(500,500);



        board = new Board(logicBoard, this);

        header.setPreferredSize(new Dimension(300, 50));
        //footer.setPreferredSize(new Dimension(300, 50));

        panel1.setPreferredSize(new Dimension(100, 300));
        panel2.setPreferredSize(new Dimension(100, 300));

        add(header, BorderLayout.NORTH);
        add(board, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
        add(panel1, BorderLayout.WEST);
        add(panel2, BorderLayout.EAST);

        pack();
        setVisible(true);
    }

    public void updatePanels() {
        panel1.updateAll();
        panel2.updateAll();
    }

    public Player getPlayerOne() { return p1; }
    public Player getPlayerTwo() { return p2; }

    public JPanel getPanel(int which) {
        return switch(which) {
            case WindowContentConstants.RESTART -> restartPanel;
            case WindowContentConstants.BOARD -> board;
            case WindowContentConstants.FOOTER -> footer;
            case WindowContentConstants.HEADER -> header;
            case WindowContentConstants.PANEL1 -> panel1;
            case WindowContentConstants.PANEL2 -> panel2;
            default -> null;
        };
    }

    static class WindowContentConstants {
        public static final int HEADER = 1, FOOTER = 2, PANEL1 = 3, PANEL2 = 4, BOARD = 5, RESTART = 6;
    }

}
