package com.GUI;

import javax.swing.*;

public class PlayerPanel extends JPanel {
    private final JLabel label;

    public PlayerPanel(String name) {
        label = new JLabel(name);
        add(label);
    }

    public JLabel getLabel() {
        return label;
    }
}
