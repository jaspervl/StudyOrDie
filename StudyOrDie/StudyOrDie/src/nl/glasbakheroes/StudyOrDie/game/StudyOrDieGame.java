package nl.glasbakheroes.StudyOrDie.game;

import nl.glasbakheroes.StudyOrDie.custom.Avatar;
import nl.glasbakheroes.StudyOrDie.model.Game;
import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.view.GameBoardView;

/**
 * The actual game.
 * @author enjee
 *
 */
public class StudyOrDieGame extends Game {
	/* Tag used for log messages. */
	public static final String TAG = "StudyOrDieGame";

	/* Instance of the CoreActivity and game avatar. */
	private CoreActivity activity;
	private Avatar avatar;
	
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
		board.removeAllObjects();
		
		/* Avatar is created and saved so it can be used from the SoD Game board. */
		avatar = new Avatar(); 
		
		/* Sets the starting position of the avatar. */
		board.addGameObject(avatar, board.getWidth() / 2, board.getHeight() / 2);
		board.updateView();
		
	}
	
	/**
	 * Method to move the avatar.
	 * @param direction 	The direction the avatar wants to move.
	 */
	public void moveAvatar(String direction) {
		/* Gets the gameboard */
		GameBoard board = getGameBoard();
		
		/* Checks wether the avatar is too close to boundies */
		if (checkBoundries(direction, board)) {
			/* Moves the avatar in a certain direction */
			switch (direction) {
			case "Up" : board.moveObject(avatar, avatar.getPositionX(), avatar.getPositionY() -1); break;
			case "Down" : board.moveObject(avatar, avatar.getPositionX(), avatar.getPositionY() +1); break;
			case "Left" : board.moveObject(avatar, avatar.getPositionX() -1, avatar.getPositionY()); break;
			case "Right" : board.moveObject(avatar, avatar.getPositionX() +1, avatar.getPositionY()); break;
			default : break;
			}
			/* Update the gameboard with the new position of all objects */
			getGameBoard().updateView(); 
		} 
		
	}

	private boolean checkBoundries(String direction, GameBoard board) {
		switch (direction) {
		case "Up" : if (avatar.getPositionY() > 0) { return true; } else { return false; }
		case "Down" : if (avatar.getPositionY() < board.getHeight() - 1){ return true; } else { return false; }
		case "Left" : if (avatar.getPositionX() > 0) { return true; } else { return false; }
		case "Right" : if (avatar.getPositionX() < board.getWidth() - 1) { return true; } else { return false; }
		default : return false;
		}
	}

	
	
}
