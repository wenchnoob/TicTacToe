package com.GUI;

import javax.swing.*;
import java.awt.*;

public class Footer extends JPanel {
    private final DefaultPane defaultPane;
    private JPanel contentPane;
    private final Window parent;

    public Footer(Window parent) {
        this.parent = parent;
        defaultPane = new DefaultPane();
        setContentPane(new StarterPane());
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public void reset() {
        setContentPane(defaultPane);
    }

    public void setContentPane(JPanel pane) {
        contentPane = pane;
        removeAll();
        add(contentPane);
        updateUI();
    }

    private static class DefaultPane extends JPanel {
        public DefaultPane() {
            add(new JLabel("Footer"));
        }
    }

    private class StarterPane extends JPanel {
        private final JTextField p1Input = new JTextField();
        private final JTextField p2Input = new JTextField();

        private final JButton proceed = new JButton("Proceed");

        public StarterPane() {
            setLayout(new GridLayout(3, 1));
            p1Input.setPreferredSize(new Dimension(150, 15));
            p2Input.setPreferredSize(new Dimension(150, 15));

            add(new JPanel() {
                {
                    setLayout(new FlowLayout());
                    add(new JLabel("Enter the name of player one or proceed with the default: "));
                    add(p1Input);
                }
            });

            add(new JPanel() {
                {
                    setLayout(new FlowLayout());
                    add(new JLabel("Enter the name of player two or proceed with the default: "));
                    add(p2Input);
                }
            });

            proceed.addActionListener(action -> {
                String p1Name = p1Input.getText();
                String p2Name = p2Input.getText();

                parent.getPlayerOne().setName(p1Name.equals("") ? "Player One": p1Name);
                parent.getPlayerTwo().setName(p2Name.equals("") ? "Player Two": p2Name);
                parent.updatePanels();
                Footer.this.reset();
            });

            add(new JPanel() {
                {
                    setLayout(new FlowLayout());
                    add(proceed);
                }
            });
        }
    }
}
