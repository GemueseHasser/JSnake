package de.jonas.jsnake.gui;

import de.jonas.JSnake;
import de.jonas.jsnake.object.gui.Draw;
import de.jonas.jsnake.object.gui.KeyHandler;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class GUI implements ActionListener {

    private static final String TITLE = "Snake - by Jonas";

    private static final int WIDTH = 750;
    private static final int HEIGHT = 500;

    private static final String RESTART_BUTTON_TEXT = "Spiel Neustarten";
    private static final int RESTART_BUTTON_X = 510;
    private static final int RESTART_BUTTON_Y = 215;
    private static final int RESTART_BUTTON_WIDTH = 150;
    private static final int RESTART_BUTTON_HEIGHT = 40;


    private final JFrame frame;

    private Draw draw;

    private final JButton restart;

    public GUI() {
        this.frame = new JFrame(TITLE);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setBounds(0, 0, WIDTH, HEIGHT);
        this.frame.setLocationRelativeTo(null);
        this.frame.setLayout(null);
        this.frame.setResizable(false);
        this.frame.addKeyListener(new KeyHandler());

        this.draw = new Draw();
        this.draw.setBounds(0, 0, WIDTH, HEIGHT);
        this.draw.setVisible(true);

        this.restart = new JButton(RESTART_BUTTON_TEXT);
        this.restart.setBounds(RESTART_BUTTON_X, RESTART_BUTTON_Y, RESTART_BUTTON_WIDTH, RESTART_BUTTON_HEIGHT);
        this.restart.setOpaque(true);
        this.restart.setBackground(Color.DARK_GRAY);
        this.restart.setForeground(Color.WHITE);
        this.restart.setFocusable(false);
        this.restart.addActionListener(this);

        this.frame.add(this.restart);
        this.frame.add(draw);
    }


    public void open() {
        this.frame.setVisible(true);
    }

    public void reload() {
        this.draw = new Draw();
        this.draw.setBounds(0, 0, WIDTH, HEIGHT);
        this.draw.setVisible(true);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource().equals(this.restart)) {
            JSnake.restart();
        }
    }
}
