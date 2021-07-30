package de.jonas.jsnake.object;

import de.jonas.JSnake;
import de.jonas.jsnake.constant.SnakeFieldState;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

@NotNull
public final class Snake {

    private static final int GAME_RECT_SIZE = 15;


    @Getter
    private int headPosition;
    private final LinkedList<Integer> bodyPositions = new LinkedList<>();


    public Snake() {
        this.headPosition = GAME_RECT_SIZE * GAME_RECT_SIZE / 2;
    }

    public SnakeField getHeadField() {
        return JSnake.SNAKE_FIELDS.get(this.headPosition);
    }

    public void addBody() {
        // add first body
        if (this.bodyPositions.size() == 0) {
            final SnakeField head = getHeadField();
            this.bodyPositions.addLast(head.getNumber() + head.getState().getDirection().getOpposide().getAddition());
            return;
        }

        // add body to other bodies
        final SnakeField before = JSnake.SNAKE_FIELDS.get(this.bodyPositions.getLast());

        this.bodyPositions.addLast(before.getNumber() + before.getState().getDirection().getOpposide().getAddition());
    }

    public void move() {
        // get head field
        final SnakeField head = getHeadField();

        // dont start until user pressed a key
        if (head.getState().getDirection() == SnakeFieldState.SnakeFieldDirection.NONE) {
            getHeadField().setState(SnakeFieldState.HEAD);
            return;
        }

        // move head-field in current direction
        this.headPosition += head.getState().getDirection().getAddition();

        // check if snake leaves the game-area or bites herself
        if (leavesArea() || selfCollide()) {
            System.exit(0);
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
    }

    private boolean leavesArea() {
        // check if snake leaves area
        return false;
    }

    private boolean selfCollide() {
        return getHeadField().getState() != SnakeFieldState.NONE;
    }
}
