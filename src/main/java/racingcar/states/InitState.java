package racingcar.states;

import racingcar.Context;
import racingcar.GameBoard;
import racingcar.GameController;
import racingcar.State;

public class InitState implements State {
    private GameBoard gameBoard;
    private GameController gameController;

    public InitState(GameBoard board, GameController controller) {
        gameBoard = board;
        gameController = controller;
    }

    @Override
    public void viewUpdate() {

    }

    @Override
    public Context readInput() {
        return null;
    }

    @Override
    public void evaluateData(Context context) {

    }

    @Override
    public void nextState() {

    }
}
