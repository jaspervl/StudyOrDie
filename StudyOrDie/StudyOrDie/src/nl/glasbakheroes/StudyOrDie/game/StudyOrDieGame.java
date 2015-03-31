package nl.glasbakheroes.StudyOrDie.game;

import android.util.Log;
import nl.glasbakheroes.StudyOrDie.activities.CoreActivity;
import nl.glasbakheroes.StudyOrDie.custom.LevelLoader;
import nl.glasbakheroes.StudyOrDie.model.Game;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;

/**
 * The actual game.
 * @author Niels Jan & Jasper
 */
public class StudyOrDieGame extends Game {
	/* Tag used for log messages. */
	public static final String TAG = "StudyOrDieGame";

	/* Instance of the CoreActivity and LevelCache. */
	private CoreActivity activity;
	private LevelLoader levelLoader;
	private StudyOrDieModel model;
	
	/**
	 * Constructor
	 */
	public StudyOrDieGame(CoreActivity activity) {
		/* Create new board and pass it to the parent class (which saves it). */
		super(new StudyOrDieGameBoard());
		model = ((StudyOrDieApplication) activity.getApplication()).getModel();

		this.activity = activity;

		updateGame(); 
 
		/* Tell the game which board to use. */
		StudyOrDieGameBoardView gameView = activity.getGameBoardView();
		StudyOrDieGameBoard gameBoard = (StudyOrDieGameBoard) getGameBoard();
		gameView.setGameBoard(gameBoard);
		model.setBoard(gameBoard);

		/* Set size of the view to that of one game board. */
		gameView.setFixedGridSize(gameBoard.getWidth(), gameBoard.getHeight());
	}

	/**
	 * Starts a new game or updates the game when the CoreActivity was destroyed.
	 */
	public void updateGame() {
		
		/* Gets the saved board and removes all objects. */
		StudyOrDieGameBoard board = (StudyOrDieGameBoard) getGameBoard();
		board.setCoreActivity(activity); 
		board.removeAllObjects();
		levelLoader = new LevelLoader(board, model.getAvatar());
		model.setLoader(levelLoader);
		/* Called when the game already has been initialized */
		if (model.isGameInitialized()) {
			Log.w("StudyOrDie Game", "Game is being reloaded, CoreActivity had been destroyed for freeing memory");
			model.loadGame();
		/* If the game is new, start a new game */
		} else {
			Log.w("StudyOrDie Game", "New game is being started");
			model.gameHasBeenInitialized();
			levelLoader.loadLevel("savedLocation");
		}
	}
	
	/** Gives the model */
	public StudyOrDieModel getModel() {
		return model;
	}

}
