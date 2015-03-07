package nl.glasbakheroes.StudyOrDie.custom;

import nl.glasbakheroes.StudyOrDie.Objects.Boss;
import nl.glasbakheroes.StudyOrDie.Objects.Door;
import nl.glasbakheroes.StudyOrDie.Objects.Elevator;
import nl.glasbakheroes.StudyOrDie.model.GameBoard;

public class LevelLoader {

	private GameBoard board;
	private Avatar avatar;
	private int currentLevel;
	private boolean[] aliveBosses = { true, true };

	public LevelLoader(GameBoard board, Avatar avatar) {
		this.board = board;
		this.avatar = avatar;
		this.currentLevel = 1;
	}

	public void setLevel(int level) {
		currentLevel = level;
	}
	
	
	public void loadLevel() {
		
		board.removeAllObjects();
		
		switch (currentLevel) {
		/* case 1 is where ground floor starts */
		case 1:	
			board.addGameObject(avatar, board.getWidth() / 2, board.getHeight() / 2);
			board.createWallHorizontal(0, 23, 11);
			board.createWallHorizontal(0, 11, 0);
			board.createWallHorizontal(13, 23, 0);
			board.addGameObject(new Door(false), 12, 0);
			board.createWallVertical(1, 10, 0);
			board.createWallVertical(1, 10, 23);
			board.createWallVertical(1, 5, 14);
			board.createWallVertical(7, 10, 14);
			board.addGameObject(new Door(false), 14, 6);
			break;
			/* case 2 is part 2 of the ground floor */
		case 2: 
			board.moveObject(avatar, board.getWidth() / 2, board.getHeight() - 2);
			board.createWallVertical(1, 10, 0);
			board.createWallHorizontal(0, 10, 11);
			board.createWallHorizontal(14, 23, 11);
			board.createWallHorizontal(0, 10, 0);
			board.createWallHorizontal(14, 23, 0);
			board.createWallVertical(1, 5, 10);
			board.createWallVertical(10, 7, 10);
			board.createWallVertical(1, 10, 14);
			board.addGameObject(new Door(true), 10, 6);
			break;
			/* case 3 is part 3 of the ground floor */
		case 3:
			if (aliveBosses[0] == true) {
				board.addGameObject(new Boss(), 2, 1);
				board.moveObject(avatar, board.getWidth() / 2, board.getHeight() - 2);
			} else {

				board.moveObject(avatar, 2, 1);
			}
			board.createWallVertical(1, 10, 0);
			board.createWallHorizontal(0, 10, 11);
			board.createWallHorizontal(14, 23, 11);
			board.createWallHorizontal(0, 23, 0);
			board.createWallVertical(1, 5, 10);
			board.createWallVertical(10, 7, 10);
			board.createWallVertical(1, 10, 14);
			board.addGameObject(new Door(false), 10, 6);
			board.createWallHorizontal(1, 2, 2);
			board.addGameObject(new Elevator(), 1, 1);
			break;
			/* Case 11 is where the second floor starts */
		case 11:
			board.createWallHorizontal(0, 23, 0);
			board.createWallHorizontal(0, 23, 11);
			board.createWallVertical(1, 10, 0);
			board.createWallVertical(1, 10, 23);
			board.moveObject(avatar, board.getWidth() / 2, board.getHeight() - 2);
			board.addGameObject(new Elevator(), 1,1);
			break;
			/* case 12 is part 2 of the second floor */
		case 12:
			
			
			break; 
			/* case 13 is part 3 of the second floor */
		case 13:
	
	
	break;
			
		default:
			break;
		}
		
		board.updateView();
	}

	public int getLevel() {
		return currentLevel;
	}
	
	public void killBoss(int majorLevel) {
		switch (majorLevel) {
		case 3 	: aliveBosses[0] = false; break;
		case 13	: aliveBosses[1] = false; break;
		default : break;
		}
		loadLevel();
	}
}
