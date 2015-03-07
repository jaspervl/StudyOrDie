package nl.glasbakheroes.StudyOrDie.custom;

import nl.glasbakheroes.StudyOrDie.Objects.Boss;
import nl.glasbakheroes.StudyOrDie.Objects.Door;
import nl.glasbakheroes.StudyOrDie.Objects.Elevator;
import nl.glasbakheroes.StudyOrDie.Objects.Key;
import nl.glasbakheroes.StudyOrDie.model.GameBoard;

	/**
	* 	Level loader, class where all levels are saved at.
	*	Will be called by the GameBoard and certain activities.
	*
	*	- Alive bosses: 	Each 'floor' or major level has a position in this array.
	*	- Keys:				Same principal as with the bosses.
	*	- Door locked:		Again for each major level a boolean.
	*	- Current level:	The level currently to displayed.
	*						* 1,2 and 3 are major level 1.
	*						* 11, 12 and 13 are major level 2. Etc.
	*	@Author EnJee	
	*/
public class LevelLoader {
	
	private GameBoard board;
	private Avatar avatar;
	private int currentLevel;
	private boolean[] aliveBosses = { true, true };
	private boolean[] keys = {true, true};
	private boolean[] doorLocked = {true, true};

	public LevelLoader(GameBoard board, Avatar avatar) {
		this.board = board;
		this.avatar = avatar;
		this.currentLevel = 1;
	}

	public void setLevel(int level) {
		currentLevel = level;
	}

	/**
	 * Load a new level with the correct items on the correct tile.
	 * @param spawnArea		The area where the Avatar has to spawn (came from).
	 */
	public void loadLevel(String spawnArea) {
		
		board.removeAllObjects();
		
		switch (currentLevel) {
			/** case 1 is where ground floor starts */
		case 1:	
			
			/* Create conditional objects */
			if (spawnArea.equals("Top")) {
				board.addGameObject(avatar, board.getWidth() / 2, 1);
			} else if (spawnArea.equals("Bottom")){
				board.addGameObject(avatar, 20, 8);
			}

			/* Create all default objects */
			board.createWallHorizontal(0, 23, 11);
			board.createWallHorizontal(0, 11, 0);
			board.createWallHorizontal(13, 23, 0);
			board.createWallVertical(1, 10, 0);
			board.createWallVertical(1, 10, 23);
			board.createWallVertical(1, 5, 14);
			board.createWallVertical(7, 10, 14);
			board.addGameObject(new Door(false), 12, 0);
			board.addGameObject(new Door(false), 14, 6);
			break;
			
			/** case 2 is part 2 of the ground floor */
		case 2: 
			
			/* Create conditional objects */
			if (spawnArea.equals("Top")) {
				board.moveObject(avatar, board.getWidth() / 2, 0);
			} else if (spawnArea.equals("Bottom")) {
				board.moveObject(avatar, board.getWidth() / 2, board.getHeight() - 1);
			} else if (spawnArea.equals("Key")) {
				board.moveObject(avatar, 19, 9);
			}
			if (keys[0] == true) {
			board.addGameObject(new Key(), 19, 9);
			}
			
			/* Create all default objects */
			board.createWallHorizontal(0, 10, 11);
			board.createWallHorizontal(14, 23, 11);
			board.createWallHorizontal(0, 10, 0);
			board.createWallHorizontal(14, 23, 0);
			board.createWallVertical(1, 5, 10);
			board.createWallVertical(10, 7, 10);
			board.createWallVertical(1, 5, 14);
			board.createWallVertical(1, 10, 0);
			board.createWallVertical(10, 7, 14);
			board.addGameObject(new Door(false), 14, 6);
			board.addGameObject(new Door(false), 10, 6);
			break;
			
			/** case 3 is part 3 of the ground floor */
		case 3:

			/* Create conditional objects */
			if (aliveBosses[0] == true) {
				board.addGameObject(new Boss(), 2, 1);
			}
			if (doorLocked[0] == true) { 
				board.addGameObject(new Door(true), 10, 6);
			} else {
				board.addGameObject(new Door(false), 10, 6);
			}
			if (spawnArea.equals("Elevator")) {
				board.moveObject(avatar, 2, 1);
			} else if (spawnArea.equals("Bottom")) {
				board.moveObject(avatar, board.getWidth() / 2, board.getHeight() - 1);
			} else if (spawnArea.equals("Boss")) {
				board.addGameObject(avatar, 2, 1);
			}

			/* Create all default objects */
			board.createWallHorizontal(0, 10, 11);
			board.createWallHorizontal(14, 23, 11);
			board.createWallHorizontal(0, 23, 0);
			board.createWallVertical(1, 5, 10);
			board.createWallVertical(10, 7, 10);
			board.createWallVertical(1, 5, 14);
			board.createWallVertical(1, 10, 0);
			board.createWallVertical(10, 7, 14);
			board.createWallHorizontal(1, 2, 2);
			board.addGameObject(new Door(false), 14, 6);
			board.addGameObject(new Elevator(), 1, 1);
			break;
			
			/** Case 11 is where the second floor starts */
		case 11:

			/* Create conditional objects */
			if (spawnArea.equals("Elevator")) {
				board.moveObject(avatar, 2, 2);
			} else if (spawnArea.equals("Top")) {
				// Fill when expanding levels
			}
			
			/* Create all default objects */
			board.createWallHorizontal(0, 23, 0);
			board.createWallHorizontal(0, 23, 11);
			board.createWallVertical(1, 10, 0);
			board.createWallVertical(1, 10, 23);
			board.addGameObject(new Elevator(), 1,1);
			break;
			
			/** case 12 is part 2 of the second floor */
		case 12:
			
			
			break; 
			/** case 13 is part 3 of the second floor */
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
	
	public void killBoss(int subLevel) {
		switch (subLevel) {
		case 3 	: aliveBosses[0] = false; break;
		case 13	: aliveBosses[1] = false; break;
		default : break;
		}
		loadLevel("Boss");
	}
	
	public void takeKey(int subLevel) {
		switch (subLevel) {
		case 2 	: keys[0] = false; break;
		case 12 : keys[1] = false; break;
		default	: break;
		}
		loadLevel("Key");
	}
	
	public void unlockDoor(int subLevel) {
		switch (subLevel) {
		case 3 	: doorLocked[0] = false; break;
		case 13 : doorLocked[1] = false; break;
		default	: break;
		}
	}
}
