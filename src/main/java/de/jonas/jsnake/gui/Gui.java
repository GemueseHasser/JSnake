package de.jonas.jsnake.gui;

import de.jonas.JSnake;
import de.jonas.jsnake.object.gui.Draw;
import de.jonas.jsnake.object.gui.KeyHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Das Haupt-Fenster der Anwendung, auf dem das gesamte Spiel, die nötigen Informationen zu dem Spiel und das
 * Interaktions-Menü, bestehend aus beispielsweise dem Button zum Neustarten des Spiels, angezeigt werden. Der
 * Hintergrund des Fensters, das Spielfeld und die Informationen zu dem Spiel werden mithilfe des {@link Draw} auf das
 * Fenster gezeichnet.
 */
@NotNull
public final class Gui implements ActionListener {

    //<editor-fold desc="CONSTANTS">
    /** Der Titel des Fensters. */
    @NotNull
    private static final String TITLE = "Snake - by Jonas";

    /** Die Breite des Fensters. */
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int WIDTH = 750;
    /** Die Höhe des Fensters. */
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int HEIGHT = 500;

    /** Der Text, welcher auf dem Neustart-Button angezeigt wird. */
    @NotNull
    private static final String RESTART_BUTTON_TEXT = "Spiel Neustarten";
    /** Die X-Koordinate des Neustart-Buttons. */
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int RESTART_BUTTON_X = 510;
    /** Die Y-Koordinate des Neustart-Buttons. */
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int RESTART_BUTTON_Y = 215;
    /** Die Breite des Neustart-Buttons. */
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int RESTART_BUTTON_WIDTH = 150;
    /** Die Höhe des Neustart-Buttons. */
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int RESTART_BUTTON_HEIGHT = 40;
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Das Fenster, welches beim Instanziieren erstellt wird und auf welches das {@link Gui} aufbaut. */
    @NotNull
    private final JFrame frame;
    /** Der Button im Interaktions-Menü, welcher genutzt wird, um das Spiel neu zu starten. */
    @NotNull
    private final JButton restart;
    /** Die Klasse, welche genutzt wird, um auf das Fenster zu zeichnen. */
    @NotNull
    private Draw draw;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue Instanz des {@link Gui}. Das {@link Gui} ist das Haupt-Fenster der Anwendung, auf dem das
     * gesamte Spiel, die nötigen Informationen zu dem Spiel und das Interaktions-Menü, bestehend aus beispielsweise dem
     * Button zum Neustarten des Spiels, angezeigt werden. Der Hintergrund des Fensters, das Spielfeld und die
     * Informationen zu dem Spiel werden mithilfe des {@link Draw} auf das Fenster gezeichnet.
     */
    public Gui() {
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
    //</editor-fold>


    /**
     * Macht das Fenster mit allen Komponenten sichtbar.
     */
    public void open() {
        this.frame.setVisible(true);
    }


    /**
     * Lädt das Fenster neu (Initialisiert das {@link Draw} neu).
     */
    public void reload() {
        this.draw = new Draw();
        this.draw.setBounds(0, 0, WIDTH, HEIGHT);
        this.draw.setVisible(true);
    }

    //<editor-fold desc="implementation">
    @Override
    public void actionPerformed(@NotNull final ActionEvent e) {
        if (e.getSource().equals(this.restart)) {
            JSnake.restart();
        }
    }
    //</editor-fold>
}
