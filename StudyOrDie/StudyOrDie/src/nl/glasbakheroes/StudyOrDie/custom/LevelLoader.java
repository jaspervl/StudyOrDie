package nl.glasbakheroes.StudyOrDie.custom;

import android.util.Log;
import nl.glasbakheroes.StudyOrDie.Objects.Door;
import nl.glasbakheroes.StudyOrDie.Objects.Elevator;
import nl.glasbakheroes.StudyOrDie.Objects.Prop;
import nl.glasbakheroes.StudyOrDie.Objects.Key;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieGameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;

/**
 * Level loader, class where all levels are saved at. Will be called by the
 * GameBoard and certain activities.
 *
 * @Author Niels Jan
 */
public class LevelLoader {

	/* Level constants */
	public static final int GROUND_LEVEL_1 = 1;
	public static final int GROUND_LEVEL_2 = 2;
	public static final int GROUND_LEVEL_3 = 3;
	public static final int GROUND_LEVEL_4 = 4;

	public static final int FIRST_FLOOR_1 = 11;
	public static final int FIRST_FLOOR_2 = 12;
	public static final int FIRST_FLOOR_3 = 13;
	public static final int FIRST_FLOOR_4 = 14;

	public static final int SECOND_FLOOR_1 = 21;
	public static final int SECOND_FLOOR_2 = 22;
	public static final int SECOND_FLOOR_3 = 23;
	public static final int SECOND_FLOOR_4 = 24;

	public static final int THIRD_FLOOR_1 = 31;
	public static final int THIRD_FLOOR_2 = 32;
	public static final int THIRD_FLOOR_3 = 33;
	public static final int THIRD_FLOOR_4 = 34;

	public static final int FOURTH_FLOOR_1 = 41;
	public static final int FOURTH_FLOOR_2 = 42;
	public static final int FOURTH_FLOOR_3 = 43;
	public static final int FOURTH_FLOOR_4 = 44;

	/* Gameboard measurement constants */
	private static final int MAX_BOARD_WIDTH = StudyOrDieGameBoard.GAMEBOARD_WIDTH -1;
	private static final int MAX_BOARD_HEIGHT = StudyOrDieGameBoard.GAMEBOARD_HEIGHT -1;

	/* Imported variables */
	private StudyOrDieModel model;
	private StudyOrDieGameBoard board;
	private Avatar avatar;

	/** Constructor */
	public LevelLoader(GameBoard board, Avatar avatar) {
		this.board = (StudyOrDieGameBoard) board;
		this.avatar = avatar;
		model = ((StudyOrDieApplication) this.board.getActivity()
				.getApplication()).getModel();
		createBosses();
	}

	private void createBosses() {
		model.addBoss("Ruud", 100, 1);
		model.addBoss("Frank", 120, 2);
		model.addBoss("Tristan", 130, 3);
		model.addBoss("Evert", 140, 4);
		model.addBoss("Syntaxis", 150, 5);
		model.addBoss("Jan", 200, 6);
	}
	private void createBasicLevel(){
		board.createWallCorners(0, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT);
		board.createWallHorizontal(1, 10, 0);
		board.createWallHorizontal(1, 10, MAX_BOARD_HEIGHT);
		board.createWallVertical(1, MAX_BOARD_HEIGHT - 1, 0);
		board.createWallVertical(1, 2, 6);
		board.createWallVertical(9, MAX_BOARD_HEIGHT - 1, 6);
	}
	private void createBasicLevel(int x1, int x2, int y1, int y2, Door door,
			int x, int y) {
		createBasicLevel();
		createRoom(x1, x2, y1, y2, door, x, y);
	}
	private void createSquare(int x1,int x2,int y1,int y2)
	{
		board.createWallCorners(x1, x2, y1, y2);
		board.createWallVertical(y1, y2, x1);
		board.createWallVertical(y1, y2, x2);
		board.createWallHorizontal(x1, x2, y1);
		board.createWallHorizontal(x1, x2, y2);
	}

	private void createRoom(int x1, int x2, int y1, int y2, Door door, int x,
			int y) {
		if(door !=null){
		board.addGameObject(door, x, y);
		}
		board.createWallCorners(x1, x2, y1, y2);
		board.createWallHorizontal(x1, x2, y1);
		board.createWallHorizontal(x1, x2, y2);
		board.createWallVertical(y1, y2, x1);
		board.createWallVertical(y1, y2, x2);
	}
	/**
	 * Load a new level with the correct items on the correct tile.
	 * 
	 * @param spawnArea
	 *            The area where the Avatar has to spawn (came from): * Top:
	 *            Avatar came from a level from the top. * Bottom: Avatar came
	 *            from a level from the bottom. * savedLocation:The previous
	 *            avatar location. * Elevator: Avatar came from the elevator. *
	 *            Key: Avatar picked up a key.
	 */
	public void loadLevel(String spawnArea) {

		Log.w("Loadlevel called", "from: " + spawnArea + ", at level: " + model.getLevel());
		board.removeAllObjects();

		if (spawnArea.equals("savedLocation")) {
		board.addGameObject(avatar, model.getSavedLocation()[0], model.getSavedLocation()[1]);
		Log.w("Saved location:", model.getSavedLocation()[0] + "   " + model.getSavedLocation()[1]);
		} else if (spawnArea.equals("newLocation")) {
			if (avatar.getPositionY() == 0) {
				board.addGameObject(avatar, avatar.getPositionX(), 11);
			} else {
				board.addGameObject(avatar, avatar.getPositionX(), 0);
			}
		}
		
		switch (model.getLevel()) {
		/** case 1 is where ground floor starts */
		case GROUND_LEVEL_1:
		
			/* Create all default objects */
			board.addGameObject(new Door(), 8, MAX_BOARD_HEIGHT);
			board.addGameObject(new Door(), 9, MAX_BOARD_HEIGHT);
			createRoom(0, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT, null, 0, 0);
			
			break;

		/** case 2 is part 2 of the ground floor */
		case GROUND_LEVEL_2:
			placeKey(21, 6, 1);
			
			placeKey(10, 1, 1);
			placeKey(10, 2, 2);
			placeKey(10, 3, 3);
			placeKey(10, 4, 4);
			placeKey(10, 5, 5);
			placeKey(10, 6, 6);
			placeKey(10, 7, 7);
			placeKey(10, 8, 8);
			placeKey(10, 9, 9);
			board.createWallCorners(0, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT);
			board.addGameObject(new Door(), 8, 0);
			board.addGameObject(new Door(), 9, 0);
			board.createWallHorizontal(0, MAX_BOARD_WIDTH, 0);
			board.createWallVertical(0, MAX_BOARD_HEIGHT, 0);
			board.createWallVertical(0, MAX_BOARD_HEIGHT, MAX_BOARD_WIDTH);
			board.createWallHorizontal(0, 7, MAX_BOARD_HEIGHT);
			board.createWallHorizontal(14, MAX_BOARD_WIDTH, MAX_BOARD_HEIGHT);
			break;

		/** case 3 is part 3 of the ground floor */
		case GROUND_LEVEL_3:
			
			if (spawnArea.equals("Elevator")) {
				board.addGameObject(avatar, MAX_BOARD_WIDTH-2, 2);
			}
			
			/* Create all default objects */
			board.addGameObject(new Elevator(), MAX_BOARD_WIDTH-1, 1);
			board.addGameObject(new Elevator(), MAX_BOARD_WIDTH-2, 1);
			board.createWallHorizontal(MAX_BOARD_WIDTH-2, MAX_BOARD_WIDTH-9, 1);
			board.createWallHorizontal(MAX_BOARD_WIDTH-2, MAX_BOARD_WIDTH-9, MAX_BOARD_HEIGHT);
			board.createWallCorners(0, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT);
			createSquare(0,7,0,MAX_BOARD_HEIGHT);
			board.createWallHorizontal(14, MAX_BOARD_WIDTH, 0);
			board.createWallHorizontal(16, MAX_BOARD_WIDTH, MAX_BOARD_HEIGHT);
			board.createWallVertical(0, MAX_BOARD_HEIGHT, MAX_BOARD_WIDTH);
			// Add stuff on the ground
			
			
			break;
			
		case GROUND_LEVEL_4:

			if (model.getBoss("Ruud").getAlive()) {
				board.addGameObject(model.getBoss("Ruud"), MAX_BOARD_WIDTH-1, 5);
			}
			board.createWallHorizontal(6, 20, MAX_BOARD_HEIGHT);
			createRoom(0,7,0,MAX_BOARD_HEIGHT, new Door(),7,MAX_BOARD_HEIGHT / 2);
			createRoom(14,MAX_BOARD_WIDTH,0,MAX_BOARD_HEIGHT, new Door(true,1), 14, MAX_BOARD_HEIGHT / 2);
			break;
		/** Case 11 is where the second floor starts */
		case FIRST_FLOOR_1:
			board.createWallVertical(1, MAX_BOARD_HEIGHT, 0);
			board.createWallVertical(1, MAX_BOARD_HEIGHT, MAX_BOARD_WIDTH);
			board.createWallCorners(0, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT);
			board.createWallHorizontal(0, MAX_BOARD_WIDTH, 0);
			board.createWallVertical(0, 3, 7);
			board.createWallVertical(MAX_BOARD_HEIGHT - 2, MAX_BOARD_HEIGHT, 7);
			break;
		case FIRST_FLOOR_2:
			board.createWallVertical(0, MAX_BOARD_HEIGHT, 0);
			board.createWallVertical(0, MAX_BOARD_HEIGHT, MAX_BOARD_WIDTH);
			board.createWallVertical(0, 3, 7);
			board.createWallVertical(MAX_BOARD_HEIGHT - 3, MAX_BOARD_HEIGHT, 7);
			createRoom(13, MAX_BOARD_WIDTH, 8, 10, new Door(true,1), 22, 10);
			board.addGameObject(new Door(), 13, MAX_BOARD_HEIGHT);
			break;
		case FIRST_FLOOR_3:
			/* Create conditional objects */
			/* Create all default objects */
			if (spawnArea.equals("Elevator")) {
				board.addGameObject(avatar, 7, 4);
			}
			board.addGameObject(new Elevator(), 7, 3);
			board.createWallVertical(0, MAX_BOARD_HEIGHT, 0);
			board.createWallVertical(0, MAX_BOARD_HEIGHT, MAX_BOARD_WIDTH);
			board.createWallVertical(0, 3, 7);
			board.createWallVertical(MAX_BOARD_HEIGHT - 3, MAX_BOARD_HEIGHT, 7);
			board.createWallVertical(0, MAX_BOARD_HEIGHT, 13);
			break;
			
		case FIRST_FLOOR_4:
			
			if (model.getBoss("Frank").getAlive()) {
				board.addGameObject(model.getBoss("Frank"), MAX_BOARD_WIDTH-1, MAX_BOARD_HEIGHT-1);
			}
			board.addGameObject(new Door(), 13, 1);
			board.createWallVertical(0, MAX_BOARD_HEIGHT - 1, 0);
			board.createWallVertical(0, MAX_BOARD_HEIGHT - 1, MAX_BOARD_WIDTH);
			board.createWallCorners(0, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT);
			board.createWallHorizontal(0, MAX_BOARD_WIDTH, MAX_BOARD_HEIGHT);
			board.createWallVertical(0, 3, 7);
			board.createWallVertical(MAX_BOARD_HEIGHT - 2, MAX_BOARD_HEIGHT, 7);
			board.addGameObject(new Door(true,2), 8, MAX_BOARD_HEIGHT - 3);
			board.createWallHorizontal(9, MAX_BOARD_WIDTH, MAX_BOARD_HEIGHT - 3);
			board.createWallVertical(0, 2, 13);
			board.createWallCorners(13, MAX_BOARD_WIDTH, 0, 3);
			board.createWallCorners(7, MAX_BOARD_WIDTH, MAX_BOARD_HEIGHT - 3, MAX_BOARD_HEIGHT);
			board.createWallHorizontal(13, MAX_BOARD_WIDTH, 3);
			break;

		case SECOND_FLOOR_1:
			
			if (model.getBoss("Tristan").getAlive()) {
				board.addGameObject(model.getBoss("Tristan"), MAX_BOARD_WIDTH-1, 1);
			}
			createBasicLevel(14, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT,
					new Door(), 14, MAX_BOARD_HEIGHT / 2);
			board.createWallHorizontal(10, 14, 0);
			placeKey(19, 6, 2);

			break;
		case SECOND_FLOOR_2:
			createBasicLevel(14, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT,
					new Door(true, 1), 14, MAX_BOARD_HEIGHT / 2);
			placeKey(20, 4, 4);

			break;
		case SECOND_FLOOR_3:
			if (spawnArea.equals("Elevator")) {
				board.addGameObject(avatar, 9, 2);
			}
			board.addGameObject(new Elevator(), 8, 1);
			board.addGameObject(new Elevator(), 9, 1);
			createBasicLevel(14, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT,
					new Door(true, 2), 14, MAX_BOARD_HEIGHT / 2);
			placeKey(17, 8, 5);

			break;
		case SECOND_FLOOR_4:
			createBasicLevel(14, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT,
					new Door(true, 3), 14, MAX_BOARD_HEIGHT / 2);
			board.createWallHorizontal(10, 14, MAX_BOARD_HEIGHT);
			placeKey(19, 6, 6);
			break;

		case THIRD_FLOOR_1:
			createBasicLevel(14, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT,
					new Door(true, 3), 14, MAX_BOARD_HEIGHT / 2);
			placeKey(10, 14, 7);
			break;

		case THIRD_FLOOR_2:
			createBasicLevel(14, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT,
					new Door(true, 3), 14, MAX_BOARD_HEIGHT / 2);
	
			break;

		case THIRD_FLOOR_3:
			if (spawnArea.equals("Elevator")) {
				board.addGameObject(avatar, 9, 2);
			}
			board.addGameObject(new Elevator(), 8, 1);
			board.addGameObject(new Elevator(), 9, 1);
			createBasicLevel(14, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT,
					new Door(true, 3), 14, MAX_BOARD_HEIGHT / 2);

			break;

		case THIRD_FLOOR_4:
			if (model.getBoss("Evert").getAlive()) {
				board.addGameObject(model.getBoss("Evert"), MAX_BOARD_WIDTH-1, MAX_BOARD_HEIGHT-1);
			}
			
			createBasicLevel(14, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT,
					new Door(true, 3), 14, MAX_BOARD_HEIGHT / 2);
			board.createWallHorizontal(10, 14, MAX_BOARD_HEIGHT);
			break;

		case FOURTH_FLOOR_1:
			if (model.getBoss("Jan").getAlive()) {
				board.addGameObject(model.getBoss("Jan"), MAX_BOARD_WIDTH-1, 5);
			}
			board.createWallHorizontal(1, MAX_BOARD_WIDTH - 1, 0);
			createRoom(0,7,0,MAX_BOARD_HEIGHT, new Door(),7,MAX_BOARD_HEIGHT / 2);
			createRoom(14,MAX_BOARD_WIDTH,0,MAX_BOARD_HEIGHT, new Door(true,1), 14, MAX_BOARD_HEIGHT / 2);
			break;

		case FOURTH_FLOOR_2:
			board.createWallHorizontal(11, MAX_BOARD_WIDTH - 1, 0);
			board.createWallVertical(1 , MAX_BOARD_HEIGHT, MAX_BOARD_WIDTH); 
			board.createWallCorners(0, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT);
			board.createWallVertical(3, MAX_BOARD_HEIGHT, 11);
			createRoom(0,6,0,MAX_BOARD_HEIGHT, new Door(),6,MAX_BOARD_HEIGHT / 2);
			placeKey(20, 7, 8);
			
			break;

		case FOURTH_FLOOR_3:
			if (model.getBoss("Syntaxis").getAlive()) {
				board.addGameObject(model.getBoss("Syntaxis"), MAX_BOARD_WIDTH-1, MAX_BOARD_HEIGHT-3);
			}
			board.createWallHorizontal(1, 10, MAX_BOARD_HEIGHT);
			board.createWallHorizontal(15, MAX_BOARD_WIDTH - 1, MAX_BOARD_HEIGHT);
			board.createWallVertical(0 , MAX_BOARD_HEIGHT - 3, 11);
			board.createWallVertical(0, MAX_BOARD_HEIGHT - 1, MAX_BOARD_WIDTH);
			board.createWallCorners(0, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT);
			createRoom(0,6,0,MAX_BOARD_HEIGHT, new Door(),6,MAX_BOARD_HEIGHT / 2);
			createRoom(16,MAX_BOARD_WIDTH,6,MAX_BOARD_HEIGHT, new Door(),18,6);
			break;

		case FOURTH_FLOOR_4:
			
			
			if (spawnArea.equals("Elevator")) {
				board.addGameObject(avatar, 9, 2);
			}
			
			board.addGameObject(new Elevator(), 8, 1);
			board.addGameObject(new Elevator(), 9, 1);
			board.createWallHorizontal(10, 15, MAX_BOARD_HEIGHT);
			createBasicLevel(14, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT,
					new Door(true, 3), 14, MAX_BOARD_HEIGHT / 2);
			placeKey(20,6,3);
			break;
		default:
			break;
		}

		board.updateView();
	}
	
	/**
	 * Place a key on the gameboard if the avatar didnt loot it yet.
	 * @param x	 The x position to place the key
	 * @param y	The y position to place the key
	 * @param keyType	The integer type of the key
	 */
	public void placeKey(int x, int y, int keyType) {
		if (!avatar.hasKey(keyType)) {
			board.addGameObject(new Key(keyType), x, y);
		}
	}
}
