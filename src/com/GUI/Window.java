package com.GUI;

import com.Logic.Board.LogicBoard;

import javax.swing.JFrame;
import java.awt.*;

public class Window extends JFrame {

    private static Header header = new Header();
    private static  Footer foot = new Footer();
    private static Board board;


    public Window(LogicBoard logicBoard) {
        setSize(500,500);

        board = new Board(logicBoard);

        add(header, BorderLayout.NORTH);
        add(board, BorderLayout.CENTER);

        //pack();
        setVisible(true);
    }

    public static Header getHead() {
        return header;
    }


}
