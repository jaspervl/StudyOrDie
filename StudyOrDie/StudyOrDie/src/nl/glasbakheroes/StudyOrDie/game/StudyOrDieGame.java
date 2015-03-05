package nl.glasbakheroes.StudyOrDie.game;

import nl.glasbakheroes.StudyOrDie.model.Game;
import nl.glasbakheroes.StudyOrDie.model.GameBoard;

public class StudyOrDieGame extends Game {

	private CoreActivity activity;
	
	public StudyOrDieGame(CoreActivity activity) {
		super(new StudyOrDieGameBoard(0, 0));
		
	}

	
	
}
