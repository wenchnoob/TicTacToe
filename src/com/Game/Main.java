package com.Game;

import com.Utilities.Annotations.Author;
import com.Utilities.Annotations.Published;
import com.Utilities.Annotations.Title;

@Title
@Author
@Published("11/27/2020")
public class Main {

    public static void main(String[] args) {
        Thread t = new Thread(new Game());
        t.start();
    }
}
