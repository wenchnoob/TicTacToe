package com.Logic.Player;

public class Player {

    private int ID;
    String name;
    String sym;

    public Player(String name, int id) {
        this.name = name;
        ID = id;
        if(id == 1) sym = "X";
        else sym = "O";
    }

    public Player(int id) {
        switch (id) {
            case 1 ->  {
                this.name = "Player One";
                ID = 1;
                sym = "X";
            }
            case 2 -> {
                this.name = "Player Two";
                ID = 2;
                sym = "O";
            }
        }
    }

    public String getName() {
        return name;
    }
    public int getID() { return ID; }
    public String getSym() { return sym; }
}
