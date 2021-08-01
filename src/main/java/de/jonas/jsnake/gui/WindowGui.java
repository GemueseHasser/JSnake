package de.jonas.jsnake.gui;

import de.jonas.jsnake.object.gui.DrawWindow;

import javax.swing.JWindow;

import java.awt.Color;

public final class WindowGui {

    private static final int WIDTH = 155;
    private static final int HEIGHT = 100;

    private final JWindow window;


    public WindowGui() {
        this.window = new JWindow();
        this.window.pack();
        this.window.setBounds(0, 0, WIDTH, HEIGHT);
        this.window.setBackground(new Color(0, 0, 0, 0));
        this.window.setLocationRelativeTo(null);
        this.window.toFront();
        this.window.setAlwaysOnTop(true);

        final DrawWindow draw = new DrawWindow();
        draw.setVisible(true);

        this.window.add(draw);
    }


    public void show() {
        this.window.setVisible(true);
    }

    public void close() {
        this.window.setVisible(false);
    }

}
