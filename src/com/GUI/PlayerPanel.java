package com.GUI;

import com.Logic.Player.Player;

import javax.swing.*;
import java.util.function.Function;

public class PlayerPanel extends JPanel {

    private Function<String, String> toUpper = str -> {
        char first = str.charAt(0);
        if(Character.isUpperCase(first) && Character.isAlphabetic(first)) return str;
        return str.replaceFirst(String.valueOf(first), String.valueOf((char)(first - 32)));
    };

    private final JLabel label;
    private final Window parent;

    private int id;

    public PlayerPanel(int id, Window parent) {
        this.id = id;
        this.parent = parent;
        label = new JLabel("");
        add(label);

        updateName(id);
    }

    public void updateAll() {
        updateName(id);
        //updateUI();
    }

    public void updateName(int id) {
        label.setText(id == 1 ? parent.getPlayerOne().getName().transform(toUpper): parent.getPlayerTwo().getName().transform(toUpper));
    }

    public void updateScore() {

    }

    public JLabel getLabel() {
        return label;
    }
}
