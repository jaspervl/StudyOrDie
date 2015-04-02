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

	public static final int FIFTH_FLOOR_1 = 51;
	public static final int FIFTH_FLOOR_2 = 52;
	public static final int FIFTH_FLOOR_3 = 53;
	public static final int FIFTH_FLOOR_4 = 54;

	public static final int SIXTH_FLOOR_1 = 61;
	public static final int SIXTH_FLOOR_2 = 62;
	public static final int SIXTH_FLOOR_3 = 63;
	public static final int SIXTH_FLOOR_4 = 64;

	public static final int SEVENTH_FLOOR_1 = 71;
	public static final int SEVENTH_FLOOR_2 = 72;
	public static final int SEVENTH_FLOOR_3 = 73;
	public static final int SEVENTH_FLOOR_4 = 74;

	public static final int EIGHTH_FLOOR_1 = 81;
	public static final int EIGHTH_FLOOR_2 = 82;
	public static final int EIGHTH_FLOOR_3 = 83;
	public static final int EIGHTH_FLOOR_4 = 84;

	public static final int NINTH_FLOOR_1 = 91;
	public static final int NINTH_FLOOR_2 = 92;
	public static final int NINTH_FLOOR_3 = 93;
	public static final int NINTH_FLOOR_4 = 94;

	public static final int TENTH_FLOOR_1 = 101;
	public static final int TENTH_FLOOR_2 = 102;
	public static final int TENTH_FLOOR_3 = 103;
	public static final int TENTH_FLOOR_4 = 104;

	/* Gameboard measurement constants */
	private static final int MAX_BOARD_WIDHT = 23;
	private static final int MAX_BOARD_HEIGHT = 11;

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

	}
	private void createBasicLevel(){
		board.createWallCorners(0, MAX_BOARD_WIDHT, 0, MAX_BOARD_HEIGHT);
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

		Log.w("Loadlevel called", "from: " + spawnArea);
		board.removeAllObjects();

		switch (model.getLevel()) {
		/** case 1 is where ground floor starts */
		case GROUND_LEVEL_1:
			/* Create conditional objects */
			if (spawnArea.equals("Top")) {
				board.addGameObject(avatar, board.getWidth() / 2, 1);
			} else if (spawnArea.equals("Bottom")) {
				board.addGameObject(avatar, 20, 8);
			} else if (spawnArea.equals("savedLocation")) {
				board.addGameObject(avatar, model.getSavedLocation()[0],
						model.getSavedLocation()[1]);
				Log.w("Saved location:", model.getSavedLocation()[0] + "   "
						+ model.getSavedLocation()[1]);
			}
			board.createWallHorizontal(10, 15, MAX_BOARD_HEIGHT);
			createBasicLevel(14, MAX_BOARD_WIDHT, 0, MAX_BOARD_HEIGHT,
					new Door(true, 3), 14, MAX_BOARD_HEIGHT / 2);
			/* Create all default objects */
			//board.addGameObject(new Door(), 8, MAX_BOARD_HEIGHT);
			//board.addGameObject(new Door(), 9, MAX_BOARD_HEIGHT);
			//createRoom(0, MAX_BOARD_WIDHT, 0, MAX_BOARD_HEIGHT, null, 0, 0);
			break;

		/** case 2 is part 2 of the ground floor */
		case GROUND_LEVEL_2:
			/* Create conditional objects */
			if (spawnArea.equals("Top")) {
				board.addGameObject(avatar, 12, 0);
			} else if (spawnArea.equals("Bottom")) {
				board.addGameObject(avatar, 12, 11);
			} else if (spawnArea.equals("Key")) {
				board.addGameObject(avatar, 18, 9);
			} else if (spawnArea.equals("savedLocation")) {
				board.addGameObject(avatar, model.getSavedLocation()[0],
						model.getSavedLocation()[1]);
			}
			int keyType = 1;
			if (!avatar.hasKey(keyType)) {
				board.addGameObject(new Key(keyType), 19, 8);
			}
			board.createWallCorners(0, MAX_BOARD_WIDHT, 0, MAX_BOARD_HEIGHT);
			board.addGameObject(new Door(), 8, 0);
			board.addGameObject(new Door(), 9, 0);
			board.createWallHorizontal(0, MAX_BOARD_WIDHT, 0);
			board.createWallVertical(0, MAX_BOARD_HEIGHT, 0);
			board.createWallVertical(0, MAX_BOARD_HEIGHT, MAX_BOARD_WIDHT);
			board.createWallHorizontal(0, 7, MAX_BOARD_HEIGHT);
			board.createWallHorizontal(14, MAX_BOARD_WIDHT, MAX_BOARD_HEIGHT);
			break;

		/** case 3 is part 3 of the ground floor */
		case GROUND_LEVEL_3:
			/* Create conditional objects */
			if (spawnArea.equals("Elevator")) {
				board.addGameObject(avatar, 2, 1);
			} else if (spawnArea.equals("Bottom")) {
				board.addGameObject(avatar, 12, 11);
			} else if (spawnArea.equals("savedLocation")) {
				board.addGameObject(avatar, model.getSavedLocation()[0],
						model.getSavedLocation()[1]);
			}

			if (model.getBoss("Ruud").getAlive()) {
				board.addGameObject(model.getBoss("Ruud"), 1, 6);
			}
			
			/* Create all default objects */
			board.createWallCorners(0, MAX_BOARD_WIDHT, 0, MAX_BOARD_HEIGHT);
			createSquare(0,7,0,MAX_BOARD_HEIGHT);
			board.createWallHorizontal(14, MAX_BOARD_WIDHT, 0);
			board.createWallHorizontal(16, MAX_BOARD_WIDHT, MAX_BOARD_HEIGHT);
			board.createWallVertical(0, MAX_BOARD_HEIGHT, MAX_BOARD_WIDHT);
			board.addGameObject(new Elevator(), 1, 1);
			
			
			break;
			
		case GROUND_LEVEL_4:
			createRoom(0,7,0,MAX_BOARD_HEIGHT, new Door(),7,MAX_BOARD_HEIGHT / 2);
			createRoom(14,MAX_BOARD_WIDHT,0,MAX_BOARD_HEIGHT, new Door(true,1), 14, MAX_BOARD_HEIGHT / 2);
			
			break;
		/** Case 11 is where the second floor starts */
		case FIRST_FLOOR_1:
			/* Create conditional objects */
			if (spawnArea.equals("Elevator")) {
				board.addGameObject(avatar, 1, 2);
			} else if (spawnArea.equals("savedLocation")) {
				board.addGameObject(avatar, model.getSavedLocation()[0],
						model.getSavedLocation()[1]);
			}
			/* Create all default objects */
			board.createWallHorizontal(1, MAX_BOARD_WIDHT - 1, 0);
			board.createWallHorizontal(1, MAX_BOARD_WIDHT - 1, MAX_BOARD_HEIGHT);
			board.createWallVertical(1, MAX_BOARD_HEIGHT - 1, 0);
			board.createWallVertical(1, MAX_BOARD_HEIGHT - 1, MAX_BOARD_WIDHT);
			board.createWallCorners(0, MAX_BOARD_WIDHT, 0, MAX_BOARD_HEIGHT);
			board.addGameObject(new Elevator(), 1, 1);
			break;
		case FIRST_FLOOR_2:
			break;
		case FIRST_FLOOR_3:
			break;

		case SECOND_FLOOR_1:
			createBasicLevel(14, MAX_BOARD_WIDHT, 0, MAX_BOARD_HEIGHT,
					new Door(), 14, MAX_BOARD_HEIGHT / 2);
			board.createWallHorizontal(10, 14, 0);
			board.addGameObject(new Key(2), 19, 6);

			break;
		case SECOND_FLOOR_2:
			createBasicLevel(14, MAX_BOARD_WIDHT, 0, MAX_BOARD_HEIGHT,
					new Door(true, 1), 14, MAX_BOARD_HEIGHT / 2);

			break;
		case SECOND_FLOOR_3:
			createBasicLevel(14, MAX_BOARD_WIDHT, 0, MAX_BOARD_HEIGHT,
					new Door(true, 2), 14, MAX_BOARD_HEIGHT / 2);

			break;
		case SECOND_FLOOR_4:
			createBasicLevel(14, MAX_BOARD_WIDHT, 0, MAX_BOARD_HEIGHT,
					new Door(true, 3), 14, MAX_BOARD_HEIGHT / 2);
			board.createWallHorizontal(10, 14, MAX_BOARD_HEIGHT);
			break;

		case THIRD_FLOOR_1:
			createBasicLevel(14, MAX_BOARD_WIDHT, 0, MAX_BOARD_HEIGHT,
					new Door(true, 3), 14, MAX_BOARD_HEIGHT / 2);
			board.createWallHorizontal(10, 14, MAX_BOARD_HEIGHT);
			break;

		case THIRD_FLOOR_2:
			createBasicLevel(14, MAX_BOARD_WIDHT, 0, MAX_BOARD_HEIGHT,
					new Door(true, 3), 14, MAX_BOARD_HEIGHT / 2);
			board.createWallHorizontal(10, 14, MAX_BOARD_HEIGHT);
			break;

		case THIRD_FLOOR_3:
			createBasicLevel(14, MAX_BOARD_WIDHT, 0, MAX_BOARD_HEIGHT,
					new Door(true, 3), 14, MAX_BOARD_HEIGHT / 2);
			board.createWallHorizontal(10, 14, MAX_BOARD_HEIGHT);
			break;

		case THIRD_FLOOR_4:
			createBasicLevel(14, MAX_BOARD_WIDHT, 0, MAX_BOARD_HEIGHT,
					new Door(true, 3), 14, MAX_BOARD_HEIGHT / 2);
			board.createWallHorizontal(10, 14, MAX_BOARD_HEIGHT);
			break;

		case FOURTH_FLOOR_1:
			board.createWallHorizontal(1, MAX_BOARD_WIDHT - 1, 0);
			createRoom(0,7,0,MAX_BOARD_HEIGHT, new Door(),7,MAX_BOARD_HEIGHT / 2);
			createRoom(14,MAX_BOARD_WIDHT,0,MAX_BOARD_HEIGHT, new Door(true,1), 14, MAX_BOARD_HEIGHT / 2);
			break;

		case FOURTH_FLOOR_2:
			board.createWallHorizontal(11, MAX_BOARD_WIDHT - 1, 0);
			board.createWallVertical(1 , MAX_BOARD_HEIGHT, MAX_BOARD_WIDHT);
			board.createWallCorners(0, MAX_BOARD_WIDHT, 0, MAX_BOARD_HEIGHT);
			board.createWallVertical(3, MAX_BOARD_HEIGHT, 11);
			createRoom(0,6,0,MAX_BOARD_HEIGHT, new Door(),6,MAX_BOARD_HEIGHT / 2);
			
			break;

		case FOURTH_FLOOR_3:
			board.createWallHorizontal(1, 10, MAX_BOARD_HEIGHT);
			board.createWallHorizontal(15, MAX_BOARD_WIDHT - 1, MAX_BOARD_HEIGHT);
			board.createWallVertical(0 , MAX_BOARD_HEIGHT - 3, 11);
			board.createWallVertical(0, MAX_BOARD_HEIGHT - 1, MAX_BOARD_WIDHT);
			board.createWallCorners(0, MAX_BOARD_WIDHT, 0, MAX_BOARD_HEIGHT);
			createRoom(0,6,0,MAX_BOARD_HEIGHT, new Door(),6,MAX_BOARD_HEIGHT / 2);
			createRoom(16,MAX_BOARD_WIDHT,6,MAX_BOARD_HEIGHT, new Door(),18,6);
			break;

		case FOURTH_FLOOR_4:
			board.createWallHorizontal(10, 15, MAX_BOARD_HEIGHT);
			createBasicLevel(14, MAX_BOARD_WIDHT, 0, MAX_BOARD_HEIGHT,
					new Door(true, 3), 14, MAX_BOARD_HEIGHT / 2);
			break;

		default:
			break;
		}

		board.updateView();
	}
}
