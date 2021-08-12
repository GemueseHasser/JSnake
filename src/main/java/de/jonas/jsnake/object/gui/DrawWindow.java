package de.jonas.jsnake.object.gui;

import de.jonas.jsnake.gui.WindowGui;
import org.jetbrains.annotations.NotNull;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Diese Klasse wird genutzt, um auf das {@link WindowGui} die entsprechende Schrift zu zeichnen. In diesem Fall wird
 * das {@link WindowGui} nur genutzt, um "Verloren!" anzuzeigen und somit zeichnet diese Klasse auch nur jenen
 * Schriftzug auf das {@link WindowGui}.
 */
@NotNull
public final class DrawWindow extends JLabel {

    //<editor-fold desc="CONSTANTS">
    /** Die Schriftart, mit der der Schriftzug auf das Fenster gezeichnet werden soll. */
    @NotNull
    private static final Font WINDOW_FONT = new Font("Arial", Font.BOLD, 35);
    /** Der Schriftzug, der auf das Fenster gezeichnet werden soll. */
    @NotNull
    private static final String WINDOW_TEXT = "Verloren!";
    //</editor-fold>


    //<editor-fold desc="implementation">
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
    //</editor-fold>
}
