package com.Utilities.Enums;

public enum PlaySet {
    PlayerOne('X'), PlayerTwo('O'), Erase(' ');

    private char ID;

    PlaySet(char ID) {
        this.ID = ID;
    }

    public char getID() { return ID;}
}
