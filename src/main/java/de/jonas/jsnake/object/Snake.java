package de.jonas.jsnake.object;

import de.jonas.JSnake;
import de.jonas.jsnake.constant.SnakeFieldDirection;
import de.jonas.jsnake.constant.SnakeFieldState;
import de.jonas.jsnake.gui.WindowGui;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.LinkedList;

import static de.jonas.jsnake.object.gui.Draw.GAME_RECT_SIZE;

/**
 * Die Schlange besteht aus einem Kopf, der von dem Nutzer gesteuert werden kann und einer (fast) unbegrenzten Anzahl
 * (sofern sie die Größe des Spielfeldes nicht überschreiten) an Körperteilen, die sie hinter sich herzieht bzw. die in
 * genau derselben Linie, wie der Kopf hinterher-gezogen werden. Der Weg der einzelnen Körperteile kann nicht vom Nutzer
 * gesteuert werden, sondern wird automatisch festgelegt.
 */
@NotNull
public final class Snake {

    //<editor-fold desc="LOCAL FIELDS">
    /** Eine in Reihenfolge sortierte Liste, mit allen Positionen (Nummern der Felder) der Körperteile. */
    @Getter
    @NotNull
    private final LinkedList<Integer> bodyPositions = new LinkedList<>();
    /** Die derzeitige Position (Nummer des Feldes) des Kopfes dieser Schlange. */
    @Getter
    @Range(from = 0, to = Integer.MAX_VALUE)
    private int headPosition;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz bzw. erzeugt eine neue {@link Snake Schlange}. Die Schlange
     * besteht aus einem Kopf, der von dem Nutzer gesteuert werden kann und einer (fast) unbegrenzten Anzahl (sofern sie
     * die Größe des Spielfeldes nicht überschreiten) an Körperteilen, die sie hinter sich herzieht bzw. die in genau
     * derselben Linie, wie der Kopf hinterher-gezogen werden. Der Weg der einzelnen Körperteile kann nicht vom Nutzer
     * gesteuert werden, sondern wird automatisch festgelegt. Mit dem erzeugen dieser Instanz wird die Position (Nummer
     * des Feldes) des Schlangen-Kopf in die Mitte des Spielfeldes gesetzt.
     */
    public Snake() {
        this.headPosition = GAME_RECT_SIZE * GAME_RECT_SIZE / 2;
    }
    //</editor-fold>


    /**
     * Filtert aus der Liste aller {@link SnakeField Felder} das {@link SnakeField} des Kopfes der Schlange heraus.
     *
     * @return Das {@link SnakeField} des Kopfes der Schlange.
     */
    @Nullable
    public SnakeField getHeadField() {
        return JSnake.SNAKE_FIELDS.get(this.headPosition);
    }

    /**
     * Fügt dem bisherigen Körper ein weiteres Körperteil hinzu, oder wenn noch kein Körper vorhanden ist, wird ein
     * Körper erzeugt.
     */
    public void addBody() {
        // add first body
        if (this.bodyPositions.size() == 0) {
            final SnakeField head = getHeadField();
            assert head != null;
            this.bodyPositions.addLast(head.getNumber() + head.getState().getDirection().getOpposite().getAddition());
            return;
        }

        // add body to other bodies
        final SnakeField before = JSnake.SNAKE_FIELDS.get(this.bodyPositions.getLast());

        this.bodyPositions.addLast(before.getNumber() + before.getState().getDirection().getOpposite().getAddition());
    }

    /**
     * Lässt sich die Schlange, mit allen Körperteilen (zufalls welche Vorhanden sind), bewegen. Zudem wird bei der
     * Bewegung überprüft, ob die Schlange das Spielfeld verlässt, oder ob sich die Schlange selbst berührt. Wenn dies
     * der Fall ist, wird der Zustand, ob das Spiel läuft auf {@code false} gesetzt und das {@link WindowGui} geöffnet,
     * welches dann das 'Verloren!' anzeigt.
     */
    public void move() {
        // get head field
        final SnakeField head = getHeadField();

        // dont start until user pressed a key
        assert head != null;
        if (head.getState().getDirection() == SnakeFieldDirection.NONE) {
            getHeadField().setState(SnakeFieldState.HEAD);
            return;
        }

        final int addition = head.getState().getDirection().getAddition();

        // move head-field in current direction
        this.headPosition += addition;

        // check if head-field is null
        if (getHeadField() == null) {
            this.headPosition -= addition;
            JSnake.setGameRunning(false);
            JSnake.getWindowGui().show();
            return;
        }

        // check if snake bites herself
        if (selfCollide()) {
            JSnake.setGameRunning(false);
            JSnake.getWindowGui().show();
        }

        // check if snake eats an apple
        if (getHeadField().getState() == SnakeFieldState.APPLE) {
            addBody();
        }

        // check if snake has a body
        if (bodyPositions.size() == 0) {
            // dont move body
            head.setState(SnakeFieldState.NONE);
        } else {
            // move body
            head.setState(SnakeFieldState.SNAKE);

            JSnake.SNAKE_FIELDS.get(this.bodyPositions.getLast()).setState(SnakeFieldState.NONE);

            this.bodyPositions.removeLast();
            this.bodyPositions.addFirst(head.getNumber());
        }

        // move head
        getHeadField().setState(SnakeFieldState.HEAD);

        // check if snake leaves game-area
        if (leavesArea()) {
            JSnake.setGameRunning(false);
            JSnake.getWindowGui().show();
        }
    }

    /**
     * Prüft, ob die Schlange in irgendeiner Richtung das Spielfeld verlässt bzw verlassen wird.
     *
     * @return Wenn die Schlange das Spielfeld verlässt bzw. verlassen wird, {@code true}, ansonsten {@code false}.
     */
    private boolean leavesArea() {
        // check if snake leaves area
        if (getHeadField() == null) {
            return true;
        }

        switch (getHeadField().getState().getDirection()) {
            case LEFT:
                return getHeadField().getNumber() % 15 == 14;

            case RIGHT:
                return getHeadField().getNumber() % 15 == 0;

            default:
                return false;
        }
    }

    /**
     * Prüft, ob sich die Schlange in irgendeiner Art und Weise selbst berührt.
     *
     * @return Wenn sich die Schlange selbst berührt, {@code true}, ansonsten {@code false}.
     */
    private boolean selfCollide() {
        // check if snake bites herself
        final SnakeField head = getHeadField();

        assert head != null;
        if (head.getState() == SnakeFieldState.APPLE) {
            return false;
        }

        return getHeadField().getState() != SnakeFieldState.NONE;
    }
}
