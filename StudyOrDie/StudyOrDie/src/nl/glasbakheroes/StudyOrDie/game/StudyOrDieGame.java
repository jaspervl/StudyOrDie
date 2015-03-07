package nl.glasbakheroes.StudyOrDie.game;

import android.util.Log;
import nl.glasbakheroes.StudyOrDie.Objects.Boss;
import nl.glasbakheroes.StudyOrDie.Objects.Wall;
import nl.glasbakheroes.StudyOrDie.custom.Avatar;
import nl.glasbakheroes.StudyOrDie.model.Game;
import nl.glasbakheroes.StudyOrDie.model.GameBoard;

/**
 * The actual game.
 * 
 * @author enjee
 * 
 */
public class StudyOrDieGame extends Game {
	/* Tag used for log messages. */
	public static final String TAG = "StudyOrDieGame";

	/* Instance of the CoreActivity and game avatar. */
	private CoreActivity activity;

	/**
	 * Constructor
	 */
	public StudyOrDieGame(CoreActivity activity) {
		/* Create new board and pass it to the parent class (which saves it). */
		super(new StudyOrDieGameBoard());

		this.activity = activity;

		/* Start a new game. */
		StartNewGame();

		/* Tell the game which board to use. */
		StudyOrDieGameBoardView gameView = activity.getGameBoardView();
		GameBoard gameBoard = getGameBoard();
		gameView.setGameBoard(gameBoard);

		/* Set size of the view to that of one game board. */
		gameView.setFixedGridSize(gameBoard.getWidth(), gameBoard.getHeight());
	}

	/**
	 * Starts a new game.
	 */
	public void StartNewGame() {

		/* Gets the saved board and removes all objects. */
		GameBoard board = getGameBoard();
		board.setCoreActivity(activity);
		board.removeAllObjects();

		/* Sets the starting position of the avatar. */
		board.addGameObject(new Avatar(), board.getWidth() / 2, board.getHeight() / 2);
		/* Create a wall */
		board.createWallHorizontal(0, 10, 10);
		board.createWallVertical(0, 10, 11);
		board.createWallHorizontal(14, 23, 5);
		board.createWallVertical(4, 0, 14);
		board.updateView();
		/* Create a boss */
		board.addGameObject(new Boss(), 0, 11);
	}

}
