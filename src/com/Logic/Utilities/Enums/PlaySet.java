package com.Logic.Utilities.Enums;

public enum PlaySet {
    X('X'), O('O'), BLANK(' ');

    private char ID;

    PlaySet(char ID) {
        this.ID = ID;
    }

    public char getID() { return ID;}
}
