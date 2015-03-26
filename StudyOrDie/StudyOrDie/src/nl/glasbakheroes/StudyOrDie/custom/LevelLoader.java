package nl.glasbakheroes.StudyOrDie.custom;

import nl.glasbakheroes.StudyOrDie.Objects.Door;
import nl.glasbakheroes.StudyOrDie.Objects.Elevator;
import nl.glasbakheroes.StudyOrDie.Objects.Prop;
import nl.glasbakheroes.StudyOrDie.Objects.Key;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieGameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;

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
	
	/* Imported variables */
	private StudyOrDieModel model;
	private StudyOrDieGameBoard board;
	private Avatar avatar;
	
	/** Constructor */
	public LevelLoader(GameBoard board, Avatar avatar) {
		this.board = (StudyOrDieGameBoard) board;
		this.avatar = avatar;
		model = ((StudyOrDieApplication)this.board.getActivity().getApplication()).getModel();
		createBosses();
	}

	private void createBosses() {
		model.addBoss("Wombat", 100);
		model.addBoss("Ruud", 120);
	}

	/**
	 * Load a new level with the correct items on the correct tile.
	 * 
	 * @param spawnArea		The area where the Avatar has to spawn (came from):
	 * 						* Top:		Avatar came from a level from the top.
	 * 						* Bottom:	Avatar came from a level from the bottom.
	 * 						* Boss:		Level regenerated because the boss was fought.
	 * 						* Elevator:	Avatar came from the elevator.
	 * 						* Key:		Avatar picked up a key.
	 */
	public void loadLevel(String spawnArea) {
		
		board.removeAllObjects();
		
		switch (model.getLevel()) {
			/** case 1 is where ground floor starts */
		case 1:	
		
			/* Create conditional objects */
			if (spawnArea.equals("Top")) {
				board.addGameObject(avatar, board.getWidth() / 2, 1);
			} else if (spawnArea.equals("Bottom")){
				board.addGameObject(avatar, 20, 8);
			} else if (spawnArea.equals("Fight")) {
				board.addGameObject(avatar, model.getBeforeFightLocation()[0], model.getBeforeFightLocation()[1]);
			}

			/* Create all default objects */
			board.createWallHorizontal(1, 22, 11);
			board.createWallHorizontal(1, 11, 0);
			board.createWallHorizontal(13, 22, 0);
			board.createWallVertical(1, 10, 0);
			board.createWallVertical(1, 10, 23);
			board.createWallVertical(1, 5, 14);
			board.createWallVertical(7, 10, 14);
			board.createWallCorners(0, 23, 0, 11);
			board.addGameObject(new Door(), 12, 0);
			board.addGameObject(new Door(), 14, 6);
			break;
			
			/** case 2 is part 2 of the ground floor */
		case 2: 
			
			/* Create conditional objects */
			if (spawnArea.equals("Top")) {
				board.addGameObject(avatar, 12, 0);
			} else if (spawnArea.equals("Bottom")) {
				board.addGameObject(avatar,12, 11);
			} else if (spawnArea.equals("Key")) {
				board.addGameObject(avatar, 18, 9);
			} else if (spawnArea.equals("Fight")) {
				board.addGameObject(avatar,  model.getBeforeFightLocation()[0],  model.getBeforeFightLocation()[1]);
			}
			if (model.getKeys()[0] == true) {
			board.addGameObject(new Key(2), 19, 9);
			}
			board.addGameObject(new Key(1), 19, 8);
			
			/* Create all default objects */
			board.createWallHorizontal(1, 9, 11);
			board.createWallHorizontal(15, 22, 11);
			board.createWallHorizontal(1, 9, 0);
			board.createWallHorizontal(15, 22, 0);
			board.createWallVertical(1, 5, 10);
			board.createWallVertical(1, 10, 23);
			board.createWallVertical(10, 7, 10);
			board.createWallVertical(1, 5, 14);
			board.createWallVertical(1, 10, 0);
			board.createWallVertical(10, 7, 14);
			board.createWallCorners(0, 10, 0, 11);
			board.createWallCorners(14, 23, 0, 11);
			board.addGameObject(new Door(), 14, 6);
			board.addGameObject(new Door(), 10, 6);
			board.addGameObject(new Prop("TableGreyImac"), 6, 6);
			break;
			
			/** case 3 is part 3 of the ground floor */
		case 3:
			
			/* Create conditional objects */
			if (model.getBoss("Wombat").getAlive()) {
				board.addGameObject(model.getBoss("Wombat"), 1, 2);
				board.addGameObject(new Elevator(true), 1, 1);
			} else {
				board.addGameObject(new Elevator(false), 1, 1);
			}
			if (model.getDoors()[0] == true) { 
				board.addGameObject(new Door(true,2), 10, 6);
			} else {
				board.addGameObject(new Door(), 10, 6);
			}
			if (spawnArea.equals("Elevator")) {
				board.addGameObject(avatar, 1, 2);
			} else if (spawnArea.equals("Bottom")) {
				board.addGameObject(avatar, 12, 11);
			} else if (spawnArea.equals("Fight")) {
				board.addGameObject(avatar,  model.getBeforeFightLocation()[0],  model.getBeforeFightLocation()[1]);
			}

			/* Create all default objects */
			board.createWallHorizontal(1, 9, 11);
			board.createWallHorizontal(15, 22, 11);
			board.createWallHorizontal(1, 9, 0);
			board.createWallHorizontal(15, 22, 0);
			board.createWallHorizontal(11, 13, 0);
			board.createWallVertical(1, 5, 10);
			board.createWallVertical(1, 10, 23);
			board.createWallVertical(10, 7, 10);
			board.createWallVertical(1, 5, 14);
			board.createWallVertical(1, 10, 0);
			board.createWallVertical(10, 7, 14);
			board.createWallCorners(0, 10, 0, 11);
			board.createWallCorners(14, 23, 0, 11);
			board.addGameObject(new Door(true,1), 14, 6);
			break;
			
			/** Case 11 is where the second floor starts */
		case 11:
			
			/* Create conditional objects */
			if (spawnArea.equals("Elevator")) {
				board.addGameObject(avatar, 1, 2);
			} else if (spawnArea.equals("Fight")) {
				board.addGameObject(avatar,  model.getBeforeFightLocation()[0],  model.getBeforeFightLocation()[1]);
			}
			
			/* Create all default objects */
			board.createWallHorizontal(1, 22, 0);
			board.createWallHorizontal(1, 22, 11);
			board.createWallVertical(1, 10, 0);
			board.createWallVertical(1, 10, 23);
			board.createWallCorners(0, 23, 0, 11);
			board.addGameObject(new Elevator(false), 1,1);
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
}
