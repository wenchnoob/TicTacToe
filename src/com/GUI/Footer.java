package com.GUI;

import javax.swing.*;

public class Footer extends JPanel {
    private final JLabel label;

    public Footer() {
        label = new JLabel("Label");
        add(label);
    }

    public JLabel getLabel() {
        return label;
    }
}
