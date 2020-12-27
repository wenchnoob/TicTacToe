package com;

import com.GUI.Window;
import com.Logic.Board.LogicBoard;
import com.Logic.Game.Game;
import com.Logic.Utilities.Annotations.Author;
import com.Logic.Utilities.Annotations.Published;
import com.Logic.Utilities.Annotations.Title;

@Title
@Author
@Published("11/27/2020")
public class Main {

    public static void main(String[] args) {
        LogicBoard logicBoard = new LogicBoard();

        //Game game = new Game(logicBoard);
        //Thread t = new Thread(game);
        //t.start();

        Window win = new Window(logicBoard);
    }
}
