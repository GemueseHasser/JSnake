package de.jonas.jsnake.object;

import de.jonas.jsnake.constant.SnakeFieldState;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

/**
 * Ein {@link SnakeField} ist ein Feld, welches einen {@link SnakeFieldState} und eine Nummer beinhaltet. Das Feld kann
 * entweder leer sein, es kann sich der Apfel in ihm befinden, es kann sich ein Körperteil der Schlange darin befinden,
 * oder es kann sich der Kopf der Schlange darin befinden. Und das macht jeweils der {@link SnakeFieldState} des Feldes
 * aus. Der {@link SnakeFieldState} entscheidet ebenfalls die Richtung die das Feld haben soll und wenn sich die
 * Schlange in dem Feld befindet (mit dem Kopf oder einem Körperteil) wird dieser/dieses bei der nächsten Bewegung in
 * jene Richtung verschoben, welche durch den {@link SnakeFieldState} vordefiniert ist. Die Nummer ist die Nummer, um
 * das Feld identifizieren zu können. Die Felder sind von oben links in der Ecke (bei 0 angefangen) bis unten rechts in
 * der Ecke des Spielfeldes durch-nummeriert, sodass man sehr einfach an jedes Feld kommt.
 */
@NotNull
public final class SnakeField {

    //<editor-fold desc="LOCAL FIELDS">
    /** Der {@link SnakeFieldState Status} des jeweiligen Feldes. */
    @Getter
    @Setter
    @NotNull
    private SnakeFieldState state;
    /** Die Nummer des jeweiligen Feldes, also an welcher Stelle auf dem Spielfeld sich das Feld befindet. */
    @Getter
    @Range(from = 0, to = Integer.MAX_VALUE)
    private final int number;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz bzw. erstellt ein neues {@link SnakeField}, mithilfe einer
     * Nummer. Diese Nummer kann nicht mehr geändert werden, sondern nur noch abgerufen werden. Der {@link
     * SnakeFieldState Status} des Feldes ist variabel und kann jederzeit geändert werden.
     *
     * @param number Die Nummer des jeweiligen Feldes, also an welcher Stelle auf dem Spielfeld sich das Feld befindet.
     */
    public SnakeField(@Range(from = 0, to = Integer.MAX_VALUE) final int number) {
        this.state = SnakeFieldState.NONE;
        this.number = number;
    }
    //</editor-fold>
}
