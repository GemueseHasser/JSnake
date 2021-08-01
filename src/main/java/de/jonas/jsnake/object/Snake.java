package de.jonas.jsnake.object;

import de.jonas.JSnake;
import de.jonas.jsnake.constant.SnakeFieldDirection;
import de.jonas.jsnake.constant.SnakeFieldState;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

@NotNull
public final class Snake {

    private static final int GAME_RECT_SIZE = 15;


    @Getter
    private int headPosition;
    @Getter
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
            this.bodyPositions.addLast(head.getNumber() + head.getState().getDirection().getOpposite().getAddition());
            return;
        }

        // add body to other bodies
        final SnakeField before = JSnake.SNAKE_FIELDS.get(this.bodyPositions.getLast());

        this.bodyPositions.addLast(before.getNumber() + before.getState().getDirection().getOpposite().getAddition());
    }

    public void move() {
        // get head field
        final SnakeField head = getHeadField();

        // dont start until user pressed a key
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

    private boolean selfCollide() {
        // check if snake bites herself
        if (getHeadField().getState() == SnakeFieldState.APPLE) {
            return false;
        }

        return getHeadField().getState() != SnakeFieldState.NONE;
    }
}
