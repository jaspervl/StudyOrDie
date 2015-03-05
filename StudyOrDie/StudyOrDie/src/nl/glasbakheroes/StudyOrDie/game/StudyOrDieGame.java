package nl.glasbakheroes.StudyOrDie.game;

import nl.glasbakheroes.StudyOrDie.model.Game;
import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.view.GameBoardView;

public class StudyOrDieGame extends Game {

	private CoreActivity activity;
	
	public StudyOrDieGame(CoreActivity activity) {
		super(new StudyOrDieGameBoard());
		
		this.activity = activity; 
		
		newGame();
		
		StudyOrDieGameBoardView gameView = activity.getGameBoardView();
		GameBoard gameBoard = getGameBoard();
		gameView.setGameBoard(gameBoard);
		
		gameView.setFixedGridSize(gameBoard.getWidth(), gameBoard.getHeight());
		
	}

	public void newGame() {
		
		// Hier worden eventueel stats en eigenschappen naar 0 gezet
		
		GameBoard board = getGameBoard();
		
		
	}

	
	
}
