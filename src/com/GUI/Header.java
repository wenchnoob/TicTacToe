package com.GUI;

import javax.swing.*;

public class Header extends JPanel {

    private final JLabel label;

    public Header() {
        label = new JLabel("Label");
        add(label);
    }

    public JLabel getLabel() {
        return label;
    }
}
