package de.jonas.jsnake.object.gui;

import org.jetbrains.annotations.NotNull;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

@NotNull
public final class DrawWindow extends JLabel {

    @NotNull
    private static final Font WINDOW_FONT = new Font("Arial", Font.BOLD, 35);
    @NotNull
    private static final String WINDOW_TEXT = "Verloren!";


    @Override
    protected void paintComponent(@NotNull final Graphics g) {
        super.paintComponent(g);

        final Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setFont(WINDOW_FONT);
        g.setColor(Color.RED);

        final int textWidth = g.getFontMetrics().stringWidth(WINDOW_TEXT);

        g.drawString(
            WINDOW_TEXT,
            this.getWidth() / 2 - textWidth / 2,
            this.getHeight() / 2 - WINDOW_FONT.getSize() / 2
        );
    }
}
