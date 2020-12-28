package com.GUI;

import javax.swing.*;

public class Header extends JPanel {

    private final JLabel contentLabel;
    private String prevContent;
    private final Window parent;

    public Header(Window parent) {
        this.parent = parent;
        contentLabel = new JLabel("Click a gridsquare to start the game!!");
        add(contentLabel);
    }

    public void reset() {
        setContent("Click a gridsquare to start the game!!");
    }

    public void revert() {
        setContent(prevContent);
    }

    public void setContent(String content) {
        prevContent = contentLabel.getText();
        contentLabel.setText(content);
    }

}
