package com.GUI;

import com.Logic.Board.LogicBoard;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private static Header header;
    private static  Footer footer;
    private static PlayerPanel panel1, panel2;
    private static Board board;
    private static JPanel restartPanel = new JPanel() {
        {
            JButton reset = new JButton("Restart");
            reset.addActionListener(action -> {
                board.startGame();
                footer.reset();
                header.reset();
            });
            reset.setAlignmentX(getWidth()/2);
            reset.setAlignmentY(getHeight()/2);
            add(reset);
        }
    };

    static {

        header = new Header();
        footer = new Footer();
        panel1 = new PlayerPanel("Player One");
        panel2 = new PlayerPanel("Player Two");
    }


    public Window(LogicBoard logicBoard) {
        getInsets().set(10, 10, 10, 10);
        setSize(500,500);

        board = new Board(logicBoard, this);



        add(header, BorderLayout.NORTH);
        add(board, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
        add(panel1, BorderLayout.WEST);
        add(panel2, BorderLayout.EAST);

        //pack();
        setVisible(true);
    }

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
