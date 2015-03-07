package nl.glasbakheroes.StudyOrDie.custom;

import nl.glasbakheroes.StudyOrDie.Objects.Boss;
import nl.glasbakheroes.StudyOrDie.Objects.Door;
import nl.glasbakheroes.StudyOrDie.model.GameBoard;

public class LevelLoader {

	private GameBoard board;
	private Avatar avatar;

	public LevelLoader(GameBoard board, Avatar avatar) {
		this.board = board;
		this.avatar = avatar;
	}

	public void loadLevel(String level) {
		switch (level) {
		case "1.1":
			
			/* Sets the starting position of the avatar. */
			board.addGameObject(avatar, board.getWidth() / 2, board.getHeight() / 2);
			/* Create a wall */
			board.createWallHorizontal(0, 4, 10);
			board.createWallHorizontal(6, 10, 10);
			board.addGameObject(new Door(true), 5, 10);
			board.addGameObject(new Door(false), 20, 6);
			board.createWallVertical(7, 11, 20);
			board.createWallVertical(0, 10, 11);
			board.createWallHorizontal(14, 23, 5);
			board.createWallVertical(4, 0, 14);
			board.updateView();
			/* Create a boss */
			board.addGameObject(new Boss(), 0, 11);
			
			break;
		case "1.2": 
			board.removeAllObjects();
			board.moveObject(avatar, board.getWidth() / 2, board.getHeight() - 1);
				
			board.createWallVertical(0, 11, 10);
			board.createWallVertical(0, 11, 14);
			
			board.updateView();
			break;
		case "1.3":
			
			
			break;
		default:
			break;
		}
	}
}
