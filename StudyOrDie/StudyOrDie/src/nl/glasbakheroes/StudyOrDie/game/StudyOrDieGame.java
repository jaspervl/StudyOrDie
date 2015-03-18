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

		StartNewGame();

		/* Tell the game which board to use. */
		StudyOrDieGameBoardView gameView = activity.getGameBoardView();
		StudyOrDieGameBoard gameBoard = (StudyOrDieGameBoard) getGameBoard();
		gameView.setGameBoard(gameBoard);
		model.setBoard(gameBoard);

		/* Set size of the view to that of one game board. */
		gameView.setFixedGridSize(gameBoard.getWidth(), gameBoard.getHeight());
	}

	/**
	 * Starts a new game.
	 */
	public void StartNewGame() {

		Log.w("StudyOrDie Game", "New game is being started");
		
		/* Gets the saved board and removes all objects. */
		StudyOrDieGameBoard board = (StudyOrDieGameBoard) getGameBoard();
		board.setCoreActivity(activity);
		board.removeAllObjects();
		levelLoader = new LevelLoader(board, model.getAvatar());
		model.setLoader(levelLoader);
		
		/* If this method is called from a final level (always ends with a 3) then the CoreActivity onCreate is called after a bossfight */
		if ((model.getLevel() - 3) % 10 == 0) {
			levelLoader.loadLevel("Boss");
		/* If not a boss-level, start from the bottom */
		} else {
			levelLoader.loadLevel("Bottom");
		}
	}
	
	/** Gives the model */
	public StudyOrDieModel getModel() {
		return model;
	}

}
