package nl.glasbakheroes.StudyOrDie.game;

import nl.glasbakheroes.StudyOrDie.Objects.Wall;
import nl.glasbakheroes.StudyOrDie.custom.Avatar;
import nl.glasbakheroes.StudyOrDie.model.Game;
import nl.glasbakheroes.StudyOrDie.model.GameBoard;

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
		/* Create a wall */
		createWall(0,10,2,2, board);
		createWall(0,24,9,9, board);
		createWall(10,10,0,3, board);
		createWall(14,14,0,2, board);
		createWall(14,24,2,2, board);
		board.updateView();	
	}
	
	/**
	 * Creates a wall that is always or straight or perfectly angled, never inbetween.
	 * if the difference between the x's and the y's is the same it will be angled.
	 */
	private void createWall(int x1, int x2, int y1, int y2, GameBoard board) {
		int smallestX = 0;
		int smallestY = 0;
		if (x1 < x2) { smallestX = x1; } else { smallestX = x2; }
		if (y1 < y2) { smallestY = y1; } else { smallestY = y2; }
		
		int differenceX = Math.abs(x1 - x2);
		int differenceY = Math.abs(y1 - y2);
		int biggestDifference = 0;
		if (differenceX > differenceY) 	{ biggestDifference = differenceX; } 
		else 							{ biggestDifference = differenceY; }
		
		for (int i = 0 ; i < biggestDifference ; i++) {
			board.addGameObject(new Wall(), 
					Math.round(smallestX + (i * (differenceX / biggestDifference))), 
					Math.round(smallestY + (i * (differenceY / biggestDifference))));
		}
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
