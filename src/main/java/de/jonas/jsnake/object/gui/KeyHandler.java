package de.jonas.jsnake.object.gui;

import de.jonas.JSnake;
import de.jonas.jsnake.constant.SnakeFieldDirection;
import de.jonas.jsnake.gui.Gui;
import de.jonas.jsnake.object.SnakeField;
import org.jetbrains.annotations.NotNull;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * <p>Mithilfe des {@link KeyHandler} wird jede Aktion, welche auf der Tastatur ausgeführt wird, während das {@link Gui
 * Fenster} fokussiert wird, abgepasst und verarbeitet. Es werden alle nötigen Informationen beim Triggern dieses Events
 * weitergegeben, sodass beispielsweise überprüft werden kann, um welche Taste es sich handelt.</p>
 *
 * <p>Dieser Listener wird ausschließlich genutzt, um zu überprüfen, wann der Nutzer die Pfeil-Tasten drückt, damit
 * die Richtung der Schlange bestimmt werden kann. Zudem wird jede Aktion der Pfeiltasten ausgeführt, sobald diese
 * gedrückt wurden. NICHT nachdem sie wieder losgelassen wurden.</p>
 */
@NotNull
public final class KeyHandler implements KeyListener {

    //<editor-fold desc="implementation">
    @Override
    public void keyTyped(@NotNull final KeyEvent e) {
    }

    @Override
    public void keyPressed(@NotNull final KeyEvent e) {
        final SnakeField head = JSnake.getSnake().getHeadField();

        assert head != null;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (head.getState().getDirection() == SnakeFieldDirection.DOWN
                    && JSnake.getSnake().getBodyPositions().size() != 0
                ) {
                    return;
                }

                head.getState().setDirection(SnakeFieldDirection.UP);
                break;

            case KeyEvent.VK_DOWN:
                if (head.getState().getDirection() == SnakeFieldDirection.UP
                    && JSnake.getSnake().getBodyPositions().size() != 0
                ) {
                    return;
                }

                head.getState().setDirection(SnakeFieldDirection.DOWN);
                break;

            case KeyEvent.VK_LEFT:
                if (head.getState().getDirection() == SnakeFieldDirection.RIGHT
                    && JSnake.getSnake().getBodyPositions().size() != 0
                ) {
                    return;
                }

                head.getState().setDirection(SnakeFieldDirection.LEFT);
                break;

            case KeyEvent.VK_RIGHT:
                if (head.getState().getDirection() == SnakeFieldDirection.LEFT
                    && JSnake.getSnake().getBodyPositions().size() != 0
                ) {
                    return;
                }

                head.getState().setDirection(SnakeFieldDirection.RIGHT);
                break;

            default:
                break;
        }
    }

    @Override
    public void keyReleased(@NotNull final KeyEvent e) {

    }
    //</editor-fold>
}
