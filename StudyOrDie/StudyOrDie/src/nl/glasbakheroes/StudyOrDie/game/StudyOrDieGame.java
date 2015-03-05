package nl.glasbakheroes.StudyOrDie.game;

import nl.glasbakheroes.StudyOrDie.custom.Avatar;
import nl.glasbakheroes.StudyOrDie.model.Game;
import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.view.GameBoardView;

public class StudyOrDieGame extends Game {

	private CoreActivity activity;
	private Avatar avatar;
	
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
		board.removeAllObjects();
		
		/* avatar wordt aangemaakt en opgeslagen in het spel zodat hij vanuit hier aangeroepen kan worden */
		avatar = new Avatar(); 
		
		/* Set begin positie van de avatar */
		board.addGameObject(avatar, board.getWidth() / 2, board.getHeight() / 2);
		board.updateView();
		
	}
	
	/**
	 * Methode om de avatar mee te bewegen. 
	 * @param direction 	De richting die de avatar uit moet gaan.
	 */
	public void moveAvatar(String direction) {
		
		GameBoard board = getGameBoard();
		
		/* Afhankelijk van de direction zal het board het opgeslagen avatar object verplaatsen */
		switch (direction) {
		case "Up" : board.moveObject(avatar, avatar.getPositionX(), avatar.getPositionY() -1); break;
		case "Down" : board.moveObject(avatar, avatar.getPositionX(), avatar.getPositionY() +1); break;
		case "Left" : board.moveObject(avatar, avatar.getPositionX() -1, avatar.getPositionY()); break;
		case "Right" : board.moveObject(avatar, avatar.getPositionX() +1, avatar.getPositionY()); break;
		default : break;
		}
		getGameBoard().updateView();
	}

	
	
}
