package com.Logic.Game;

import com.GUI.Window;
import com.Logic.Board.LogicBoard;
import com.Logic.Utilities.Annotations.Author;
import com.Logic.Utilities.Annotations.Published;
import com.Logic.Utilities.Annotations.Title;

@Title
@Author
@Published("11/27/2020")
public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        Thread t = new Thread(game);
        t.start();

        Window win = new Window(new LogicBoard());
    }
}
