package nl.glasbakheroes.StudyOrDie.game;

import nl.glasbakheroes.StudyOrDie.custom.Avatar;
import nl.glasbakheroes.StudyOrDie.custom.LevelLoader;
import nl.glasbakheroes.StudyOrDie.model.Game;

/**
 * The actual game.
 * @author enjee & Jasper
 */
public class StudyOrDieGame extends Game {
	/* Tag used for log messages. */
	public static final String TAG = "StudyOrDieGame";

	/* Instance of the CoreActivity and LevelCache. */
	private CoreActivity activity;
	private LevelLoader levelLoader;
	
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
		StudyOrDieGameBoard gameBoard = (StudyOrDieGameBoard) getGameBoard();
		gameView.setGameBoard(gameBoard);

		/* Set size of the view to that of one game board. */
		gameView.setFixedGridSize(gameBoard.getWidth(), gameBoard.getHeight());
	}

	/**
	 * Starts a new game.
	 */
	public void StartNewGame() {

		/* Gets the saved board and removes all objects. */
		StudyOrDieGameBoard board = (StudyOrDieGameBoard) getGameBoard();
		board.setCoreActivity(activity);
		board.removeAllObjects();
		StudyOrDieApplication app = (StudyOrDieApplication) activity.getApplication();
		levelLoader = new LevelLoader(board, app.getModel().getAvatar());
		levelLoader.loadLevel("Bottom");
	}
	
	
	public LevelLoader getLevelLoader() {
		return levelLoader;
	}

}
