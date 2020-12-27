package com.GUI;

import javax.swing.*;
import java.awt.*;

public class Footer extends JPanel {
    private final DefaultPane defaultPane;
    private JPanel contentPane;

    public Footer() {
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

    private class DefaultPane extends JPanel {
        public DefaultPane() {
            add(new JLabel("Footer"));
        }
    }

    private class StarterPane extends JPanel {
        private String p1Name;
        private String p2Name;

        private JTextField p1Input = new JTextField();
        private JTextField p2Input = new JTextField();

        private JButton proceed = new JButton("Proceed");

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
                p1Name = p1Input.getText();
                p2Name = p2Input.getText();
                Footer.this.reset();
            });

            add(new JPanel() {
                {
                    setLayout(new FlowLayout());
                    add(proceed);
                }
            });
        }

        public String getP1Name() {
            return p1Name;
        }

        public String getP2Name() {
            return p2Name;
        }
    }
}
