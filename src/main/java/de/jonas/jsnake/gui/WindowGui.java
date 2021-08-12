package de.jonas.jsnake.gui;

import de.jonas.jsnake.object.gui.DrawWindow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import javax.swing.JWindow;

import java.awt.Color;

/**
 * Das {@link WindowGui} ist ein durchsichtiges Fenster, in dem mit roter Schrift "Verloren!" geschrieben steht (Dieser
 * Schriftzug wird mithilfe des {@link DrawWindow} gezeichnet). Dieses Fenster wird über dem {@link Gui Haupt-Fenster}
 * des Spiels eingeblendet, sobald der Nutzer verloren hat.
 */
@NotNull
public final class WindowGui {

    //<editor-fold desc="CONSTANTS">
    /** Die Breite dieses Fensters. */
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int WIDTH = 155;
    /** Die Höhe dieses Fensters. */
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int HEIGHT = 100;
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Das Fenster, welches durchsichtig gemacht wird und auf dem der Schriftzug gezeichnet wird. */
    @NotNull
    private final JWindow window;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz des {@link WindowGui}. Das {@link WindowGui} ist ein
     * durchsichtiges Fenster, in dem mit roter Schrift "Verloren!" geschrieben steht (Dieser Schriftzug wird mithilfe
     * des {@link DrawWindow} gezeichnet). Dieses Fenster wird über dem {@link Gui Haupt-Fenster} des Spiels
     * eingeblendet, sobald der Nutzer verloren hat.
     */
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
    //</editor-fold>


    /**
     * Macht das Fenster mit allen Komponenten sichtbar.
     */
    public void show() {
        this.window.setVisible(true);
    }

    /**
     * Macht das Fenster mit allen Komponenten unsichtbar.
     */
    public void close() {
        this.window.setVisible(false);
    }

}
