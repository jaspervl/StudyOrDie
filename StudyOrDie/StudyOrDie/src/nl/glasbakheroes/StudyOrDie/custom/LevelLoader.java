package nl.glasbakheroes.StudyOrDie.custom;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.media.ExifInterface;
import android.util.Log;
import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.Objects.Door;
import nl.glasbakheroes.StudyOrDie.Objects.Elevator;
import nl.glasbakheroes.StudyOrDie.Objects.ItemWrap;
import nl.glasbakheroes.StudyOrDie.Objects.Prop;
import nl.glasbakheroes.StudyOrDie.Objects.Key;
import nl.glasbakheroes.StudyOrDie.Objects.VendingMachine;
import nl.glasbakheroes.StudyOrDie.activities.InformationActivity;
import nl.glasbakheroes.StudyOrDie.activities.StartActivity;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieGameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
import nl.glasbakheroes.StudyOrDie.view.SpriteCache;

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
	}

	public void createBosses() {
		model.addBoss("Frank", 100, 1);
		model.addBoss("Tristan", 110, 2);
		model.addBoss("Ruud", 120, 3);
		model.addBoss("Evert", 130, 4);
		model.addBoss("Syntaxis", 150, 5);
		model.addBoss("Jan", 200, 5);
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
		if (!model.getStoryLineShowed()[model.getLevel() / 10] && (model.getLevel() / 10) != 0) {
			Intent storyIntent = new Intent(model.getActivity() ,InformationActivity.class);
			storyIntent.putExtra("info", "Story");
			model.getActivity().startActivity(storyIntent);
			model.setStoryLineShowed(model.getLevel() / 10);
		}
		
		switch(model.getLevel() / 10) {
		case 0 : 	model.getActivity().getGameBoardView().setEmptyTile("empty_0"); break;
		case 1 : 	model.getActivity().getGameBoardView().setEmptyTile("empty_1"); break;
		case 2 : 	model.getActivity().getGameBoardView().setEmptyTile("empty_2"); break;
		case 3 : 	model.getActivity().getGameBoardView().setEmptyTile("empty_3"); break;
		case 4 : 	model.getActivity().getGameBoardView().setEmptyTile("empty_4"); break;
		}
		
		switch (model.getLevel()) {
		/** case 1 is where ground floor starts */ 
		case GROUND_LEVEL_1:
			if (spawnArea.equals("newGame")) { 
				board.addGameObject(avatar, 20, 5);
				Intent storyIntent = new Intent(model.getActivity() ,InformationActivity.class);
				storyIntent.putExtra("info", "Story");
				model.getActivity().startActivity(storyIntent);
				model.setStoryLineShowed(model.getLevel() / 10);
			} else if (spawnArea.equals("fail")) {
				board.addGameObject(avatar, 20, 5);
			}
			
		
			/* Create all default objects */
			board.addGameObject(new Door(), 8, MAX_BOARD_HEIGHT);
			board.addGameObject(new Door(), 9, MAX_BOARD_HEIGHT);
			createRoom(0, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT, null, 0, 0);
			fillCollegeRoom();
			
			break;

		/** case 2 is part 2 of the ground floor */
		case GROUND_LEVEL_2:
		
			placeLootableItem(12, 6, model.getLootableItem("Excaliniet"));
			board.addGameObject(new Prop("Plant1"), 7, 10);
			board.addGameObject(new Prop("Plant1"), 14, 10);

			
			board.createWallCorners(0, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT);
			board.addGameObject(new Door(), 8, 0);
			board.addGameObject(new Door(), 9, 0);
			board.createWallHorizontal(0, MAX_BOARD_WIDTH, 0);
			board.createWallVertical(0, MAX_BOARD_HEIGHT, 0);
			board.createWallVertical(0, MAX_BOARD_HEIGHT, MAX_BOARD_WIDTH);
			board.createWallHorizontal(0, 7, MAX_BOARD_HEIGHT);
			board.createWallHorizontal(14, MAX_BOARD_WIDTH, MAX_BOARD_HEIGHT);
			createBenches(2);
			createBenches(MAX_BOARD_WIDTH-2);
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
			createBenches(8);
			board.addGameObject(new Prop("Plant2"), 8, 6);
			board.addGameObject(new Prop("Plant2"), 22, 6);

			break;
			
		case GROUND_LEVEL_4:

			if (model.getBoss("Frank").getAlive()) {
				board.addGameObject(model.getBoss("Frank"), MAX_BOARD_WIDTH-1, 5);
			}
			board.createWallHorizontal(6, 20, MAX_BOARD_HEIGHT);
			createRoom(0,7,0,MAX_BOARD_HEIGHT, new Door(),7,MAX_BOARD_HEIGHT / 2);
			createRoom(14,MAX_BOARD_WIDTH,0,MAX_BOARD_HEIGHT, new Door(1), 14, MAX_BOARD_HEIGHT / 2);
			createRightClassRoom();
			placeKey(1, 5, 1);
			board.addGameObject(new Prop("Plant1"), 8, 1);
			board.addGameObject(new Prop("Plant1"), 13, 1);

			break;
		/** Case 11 is where the second floor starts */
		case FIRST_FLOOR_1:
			
			board.createWallVertical(1, MAX_BOARD_HEIGHT, 0);
			board.createWallVertical(1, MAX_BOARD_HEIGHT, MAX_BOARD_WIDTH);
			board.createWallCorners(0, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT);
			board.createWallHorizontal(0, MAX_BOARD_WIDTH, 0);
			board.createWallVertical(0, 3, 7);
			board.createWallVertical(MAX_BOARD_HEIGHT - 2, MAX_BOARD_HEIGHT, 7);
			createCantineTables(1, 1, 1, MAX_BOARD_HEIGHT -1);
			createCantineTables(6, 6, 1, 5);
			createCantineTables(6, 6, 9, 11);
			board.addGameObject(new Prop("CantineBankBottom"), 6, 8);
			board.addGameObject(new Prop("CantineBankTop"), 1, 10);
			board.addGameObject(new Prop("CantineBankTop"), 6, 10);
			board.addGameObject(new Prop("CantineBankTop"), 6, 4);
			board.addGameObject(new Prop("CantineBank"), 1, 2);
			board.addGameObject(new Prop("CantineBank"), 1, 4);
			board.addGameObject(new Prop("CantineBank"), 1, 6);
			board.addGameObject(new Prop("CantineBank"), 1, 8);
			board.addGameObject(new Prop("CantineBank"), 6, 2);


			
			placeKey(3, 4, 2);
			break;
		case FIRST_FLOOR_2:
			board.createWallVertical(0, MAX_BOARD_HEIGHT, 0);
			board.createWallVertical(0, MAX_BOARD_HEIGHT, MAX_BOARD_WIDTH);
			board.createWallVertical(0, 3, 7);
			board.createWallVertical(MAX_BOARD_HEIGHT - 3, MAX_BOARD_HEIGHT, 7);
			createRoom(13, MAX_BOARD_WIDTH, 8, 10, new Door(2), 22, 10);
			board.addGameObject(new Door(), 13, MAX_BOARD_HEIGHT);
			createCantineTables(1, 1, 1, MAX_BOARD_HEIGHT -1);
			createCantineTables(6, 6, 1, 5);
			createCantineTables(6, 6, 9, 11);
			board.addGameObject(new Prop("CantineBankBottom"), 6, 8);
			board.addGameObject(new Prop("CantineBankTop"), 1, 10);
			board.addGameObject(new Prop("CantineBankTop"), 6, 10);
			board.addGameObject(new Prop("CantineBankTop"), 6, 4);
			board.addGameObject(new Prop("CantineBank"), 1, 2);
			board.addGameObject(new Prop("CantineBank"), 1, 4);
			board.addGameObject(new Prop("CantineBank"), 1, 6);
			board.addGameObject(new Prop("CantineBank"), 1, 8);
			board.addGameObject(new Prop("CantineBank"), 6, 2);
			placeKey(14, 9, 3);
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
			createCantineTables(1, 1, 1, MAX_BOARD_HEIGHT -1);
			createCantineTables(6, 6, 1, 5);
			createCantineTables(6, 6, 9, 11);
			
			board.addGameObject(new Prop("CantineBankBottom"), 6, 8);
			board.addGameObject(new Prop("CantineBankTop"), 1, 10);
			board.addGameObject(new Prop("CantineBankTop"), 6, 10);
			board.addGameObject(new Prop("CantineBankTop"), 6, 4);
			board.addGameObject(new Prop("CantineBank"), 1, 2);
			board.addGameObject(new Prop("CantineBank"), 1, 4);
			board.addGameObject(new Prop("CantineBank"), 1, 6);
			board.addGameObject(new Prop("CantineBank"), 1, 8);
			board.addGameObject(new Prop("CantineBank"), 6, 2);

			for (int i = 0 ; i < 5 ; i++) {
				board.addGameObject(new Prop("Furnace"), 16 + i, 3);
			}
			break;
			
		case FIRST_FLOOR_4:
			
			if (model.getBoss("Tristan").getAlive()) {
				board.addGameObject(model.getBoss("Tristan"), MAX_BOARD_WIDTH-1, MAX_BOARD_HEIGHT-1);
			}
			board.addGameObject(new Door(), 13, 1);
			board.createWallVertical(0, MAX_BOARD_HEIGHT - 1, 0);
			board.createWallVertical(0, MAX_BOARD_HEIGHT - 1, MAX_BOARD_WIDTH);
			board.createWallCorners(0, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT);
			board.createWallHorizontal(0, MAX_BOARD_WIDTH, MAX_BOARD_HEIGHT);
			board.createWallVertical(0, 3, 7);
			board.createWallVertical(MAX_BOARD_HEIGHT - 2, MAX_BOARD_HEIGHT, 7);
			board.addGameObject(new Door(3), 8, MAX_BOARD_HEIGHT - 3);
			board.createWallHorizontal(9, MAX_BOARD_WIDTH, MAX_BOARD_HEIGHT - 3);
			board.createWallVertical(0, 2, 13);
			board.createWallCorners(13, MAX_BOARD_WIDTH, 0, 3);
			board.createWallCorners(7, MAX_BOARD_WIDTH, MAX_BOARD_HEIGHT - 3, MAX_BOARD_HEIGHT);
			board.createWallHorizontal(13, MAX_BOARD_WIDTH, 3);
			createCantineTables(1, 1, 1, MAX_BOARD_HEIGHT -1);
			createCantineTables(6, 6, 1, 5);
			createCantineTables(6, 6, 9, 11);
			board.addGameObject(new Prop("CantineBankBottom"), 6, 8);
			board.addGameObject(new Prop("CantineBankTop"), 1, 10);
			board.addGameObject(new Prop("CantineBankTop"), 6, 10);
			board.addGameObject(new Prop("CantineBankTop"), 6, 4);
			board.addGameObject(new Prop("CantineBank"), 1, 2);
			board.addGameObject(new Prop("CantineBank"), 1, 4);
			board.addGameObject(new Prop("CantineBank"), 1, 6);
			board.addGameObject(new Prop("CantineBank"), 1, 8);
			board.addGameObject(new Prop("CantineBank"), 6, 2);
			break;

		case SECOND_FLOOR_1:
			
			if (model.getBoss("Ruud").getAlive()) {
				board.addGameObject(model.getBoss("Ruud"), MAX_BOARD_WIDTH-3, 1);
			}
			createBasicLevel(14, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT,
					new Door( 5), 14, MAX_BOARD_HEIGHT / 2);
			board.createWallHorizontal(10, 14, 0);
			
			createRightClassRoom();
			createStudyRoom1();

			break;
		case SECOND_FLOOR_2:
			createBasicLevel(14, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT,
					new Door(), 14, MAX_BOARD_HEIGHT / 2);
			placeKey(20, 4, 4);
			createRightClassRoom();
			createStudyRoom2();

			break;
		case SECOND_FLOOR_3:
			if (spawnArea.equals("Elevator")) {
				board.addGameObject(avatar, 9, 2);
			}
			board.addGameObject(new Elevator(), 8, 1);
			board.addGameObject(new Elevator(), 9, 1);
			createBasicLevel(14, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT,
					new Door(), 14, MAX_BOARD_HEIGHT / 2);
			createRightClassRoom();
			createStudyRoom3();

			break;
		case SECOND_FLOOR_4:
			createBasicLevel(14, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT,
					new Door( 4), 14, MAX_BOARD_HEIGHT / 2);
			board.createWallHorizontal(10, 14, MAX_BOARD_HEIGHT);
			createRightClassRoom();
			createStudyRoom1();
			placeKey(22, 10, 5);
			break;

		case THIRD_FLOOR_1:
			createBasicLevel(14, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT,
					new Door( 3), 14, MAX_BOARD_HEIGHT / 2);
			board.createWallHorizontal(0, MAX_BOARD_WIDTH, 0);
			createRightClassRoom();
			createStudyRoom2();
			placeKey(5, 10, 6);
			break;

		case THIRD_FLOOR_2:
			createBasicLevel(14, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT,
					new Door( 3), 14, MAX_BOARD_HEIGHT / 2);

			createStudyRoom3();
			createRightClassRoom();
			break;

		case THIRD_FLOOR_3:
			if (spawnArea.equals("Elevator")) {
				board.addGameObject(avatar, 9, 2);
			}
			board.addGameObject(new Elevator(), 8, 1);
			board.addGameObject(new Elevator(), 9, 1);
			createBasicLevel(14, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT,
					new Door(), 14, MAX_BOARD_HEIGHT / 2);

			createRightClassRoom();
			createStudyRoom1();
			break;

		case THIRD_FLOOR_4:
			if (model.getBoss("Evert").getAlive()) {
				board.addGameObject(model.getBoss("Evert"), MAX_BOARD_WIDTH-3, 1);
			}
			
			createBasicLevel(14, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT,
					new Door( 6), 14, MAX_BOARD_HEIGHT / 2);
			board.createWallHorizontal(10, 14, MAX_BOARD_HEIGHT);
			createRightClassRoom();
			createStudyRoom2();
			break;

		case FOURTH_FLOOR_1:
			if (model.getBoss("Jan").getAlive()) {
				board.addGameObject(model.getBoss("Jan"), 20, 10);
			}
			board.createWallHorizontal(1, MAX_BOARD_WIDTH - 1, 0);
			createRoom(0,7,0,MAX_BOARD_HEIGHT, new Door(),7,MAX_BOARD_HEIGHT / 2);
			createRoom(14,MAX_BOARD_WIDTH,0,MAX_BOARD_HEIGHT, new Door( 8), 14, MAX_BOARD_HEIGHT / 2);
			createItRoom(2,1);
			createItRoom(16,1);
			createItRoom(20,1);
			board.addGameObject(new Prop("Plant2"), 1, 1);
			board.addGameObject(new Prop("Plant2"), 1, 5);
			break;

		case FOURTH_FLOOR_2:
			board.createWallHorizontal(14, MAX_BOARD_WIDTH - 1, 0);
			board.createWallVertical(1 , MAX_BOARD_HEIGHT, MAX_BOARD_WIDTH); 
			board.createWallCorners(0, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT);
			board.createWallVertical(3, MAX_BOARD_HEIGHT, 11);
			createRoom(0,6,0,MAX_BOARD_HEIGHT, new Door(),6,MAX_BOARD_HEIGHT / 2);
			board.addGameObject(new Prop("Plant2"),11,2);
			createStudyAreaRow3(12,3);
			createStudyAreaRow2(12,6);
			createStudyAreaRow1(12,9);
			createItRoom(2,1);
			board.addGameObject(new Prop("Plant1"), 1, 1);
			board.addGameObject(new Prop("Plant1"), 1, 5);
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
			createRoom(16,MAX_BOARD_WIDTH,6,MAX_BOARD_HEIGHT, new Door(7),18,6);
			placeKey(5, 10, 7);
			placeKey(22, 7, 8);
			board.createWallVertical(7, 8, 21);
			createStudyAreaRow1(12,0);
			createItRoom(2,1);
			board.addGameObject(new Prop("Plant2"), 1, 1);
			board.addGameObject(new Prop("Plant2"), 1, 5);
			
			
			
			
			
			break;

		case FOURTH_FLOOR_4:
			
			
			if (spawnArea.equals("Elevator")) {
				board.addGameObject(avatar, 9, 2);
			}
			
			board.addGameObject(new Elevator(), 8, 1);
			board.addGameObject(new Elevator(), 9, 1);
			board.createWallHorizontal(10, 15, MAX_BOARD_HEIGHT);
			createBasicLevel(14, MAX_BOARD_WIDTH, 0, MAX_BOARD_HEIGHT,
					new Door(3), 14, MAX_BOARD_HEIGHT / 2);
			createRightClassRoom();
			createStudyRoom3();
			break;
		default:
			break;
		}

		board.updateView();
	}
	
	private void createBenches(int x) {
		for (int i = 1 ; i <= MAX_BOARD_HEIGHT -1 ; i++) {
			if (i%3 != 0) {
				board.addGameObject(new Prop("Bench"), x, i);
			}
		}
	}

	private void createRightClassRoom() {
		board.addGameObject(new Prop("TeacherDesk"), 20, 2);
		board.addGameObject(new Prop("TeacherDesk"), 21, 2);
		board.addGameObject(new Prop("ClassChair"), 21, 1);
		
		board.addGameObject(new Prop("ClassTable"), 16, 2);
		board.addGameObject(new Prop("ImacTable"), 16, 3);
		board.addGameObject(new Prop("GreyTableLaptopBack"), 17, 2);
		board.addGameObject(new Prop("ImacTable"), 17, 3);
		board.addGameObject(new Prop("ClassChair"), 16, 1);
		board.addGameObject(new Prop("ClassChair"), 17, 1);
		board.addGameObject(new Prop("ClassChairBack"), 16, 4);
		board.addGameObject(new Prop("ClassChairBack"), 17, 4);
		
		board.addGameObject(new Prop("GreyTableBookPencil"), 16, 7);
		board.addGameObject(new Prop("ImacTable"), 16, 8);
		board.addGameObject(new Prop("ClassTable"), 17, 7);
		board.addGameObject(new Prop("ImacTable"), 17, 8);
		board.addGameObject(new Prop("ClassChair"), 16, 6);
		board.addGameObject(new Prop("ClassChair"), 17, 6);
		board.addGameObject(new Prop("ClassChairBack"), 16, 9); 
		board.addGameObject(new Prop("ClassChairBack"), 17, 9);
		
		board.addGameObject(new Prop("ClassTable"), 20, 7);
		board.addGameObject(new Prop("GreyTableLaptopFront"), 20, 8);
		board.addGameObject(new Prop("GreyTableBookPencil"), 21, 7);
		board.addGameObject(new Prop("ImacTable"), 21, 8);
		board.addGameObject(new Prop("ClassChair"), 20, 6);
		board.addGameObject(new Prop("ClassChair"), 21, 6);
		board.addGameObject(new Prop("ClassChairBack"), 20, 9);
		board.addGameObject(new Prop("ClassChairBack"), 21, 9);
	}

	private void fillCollegeRoom() {
		board.addGameObject(new Prop("ImacTable"), 3, 6); 
		board.addGameObject(new Prop("Speach"), 2, 4);
		for (int i = 5 ; i < MAX_BOARD_WIDTH -1 ; i += 2) {
			for (int j = 1 ; j < MAX_BOARD_HEIGHT -1 ; j +=2) {
				board.addGameObject(new Prop("CollegeSeat"), i, j);
			}
		}
	}
	private void createStudyRoom1() {
		board.addGameObject(new Prop("ClassChair"), 2, 1);
		board.addGameObject(new Prop("ClassChair"), 3, 1);
		board.addGameObject(new Prop("ClassChair"), 2, 8);
		board.addGameObject(new Prop("ClassChair"), 3, 8);
		board.addGameObject(new Prop("ClassChairBack"), 2, 3);
		board.addGameObject(new Prop("ClassChairBack"), 3, 3);
		board.addGameObject(new Prop("ClassChairBack"), 2, 10);		
		board.addGameObject(new Prop("ClassChairBack"), 3, 10);
		board.addGameObject(new Prop("BrownTableLeft"), 2, 2);
		board.addGameObject(new Prop("BrownTableRightBookpencil"), 3, 2);
		board.addGameObject(new Prop("BrownTableLeftLaptop"), 2, 9);
		board.addGameObject(new Prop("BrownTableRight"), 3, 9);		
}
	private void createStudyRoom2() {
		board.addGameObject(new Prop("ClassChair"), 2, 1);
		board.addGameObject(new Prop("ClassChair"), 3, 1);
		board.addGameObject(new Prop("ClassChair"), 2, 8);
		board.addGameObject(new Prop("ClassChair"), 3, 8);
		board.addGameObject(new Prop("ClassChairBack"), 2, 3);
		board.addGameObject(new Prop("ClassChairBack"), 3, 3);
		board.addGameObject(new Prop("ClassChairBack"), 2, 10);		
		board.addGameObject(new Prop("ClassChairBack"), 3, 10);
		board.addGameObject(new Prop("BrownTableLeftLaptop"), 2, 2);
		board.addGameObject(new Prop("BrownTableRightBookpencil"), 3, 2);
		board.addGameObject(new Prop("BrownTableLeft"), 2, 9);
		board.addGameObject(new Prop("BrownTableRightLaptopback"), 3, 9);		
}
	private void createStudyRoom3() {
		board.addGameObject(new Prop("ClassChair"), 2, 1);
		board.addGameObject(new Prop("ClassChair"), 3, 1);
		board.addGameObject(new Prop("ClassChair"), 2, 8);
		board.addGameObject(new Prop("ClassChair"), 3, 8);
		board.addGameObject(new Prop("ClassChairBack"), 2, 3);
		board.addGameObject(new Prop("ClassChairBack"), 3, 3);
		board.addGameObject(new Prop("ClassChairBack"), 2, 10);		
		board.addGameObject(new Prop("ClassChairBack"), 3, 10);
		board.addGameObject(new Prop("BrownTableLeftLaptop"), 2, 2);
		board.addGameObject(new Prop("BrownTableRight"), 3, 2);
		board.addGameObject(new Prop("BrownTableLeftLaptop"), 2, 9);
		board.addGameObject(new Prop("BrownTableRightBookpencil"), 3, 9);		
}
	private void createStudyAreaRow1(int sX, int sY) {
		int sY1 = sY + 1;
		int sY2 = sY + 2;
		board.addGameObject(new Prop("ClassChair"), sX, sY);
		board.addGameObject(new Prop("ClassChair"), sX + 1, sY);
		board.addGameObject(new Prop("ClassChair"), sX + 5, sY);
		board.addGameObject(new Prop("ClassChair"), sX + 6, sY);
		board.addGameObject(new Prop("ClassChair"), sX + 9, sY);
		board.addGameObject(new Prop("ClassChair"), sX + 10, sY);
		board.addGameObject(new Prop("ClassChairBack"), sX, sY2);
		board.addGameObject(new Prop("ClassChairBack"), sX + 1, sY2);
		board.addGameObject(new Prop("ClassChairBack"), sX + 5, sY2);
		board.addGameObject(new Prop("ClassChairBack"), sX + 6, sY2);
		board.addGameObject(new Prop("ClassChairBack"), sX + 9, sY2);
		board.addGameObject(new Prop("ClassChairBack"), sX + 10, sY2);
		board.addGameObject(new Prop("BrownTableLeftLaptop"), sX, sY1);
		board.addGameObject(new Prop("BrownTableRight"), sX + 1, sY1);
		board.addGameObject(new Prop("BrownTableLeft"), sX + 5, sY1);
		board.addGameObject(new Prop("BrownTableRightBookpencil"), sX + 6, sY1);
		board.addGameObject(new Prop("BrownTableLeft"), sX + 9, sY1);
		board.addGameObject(new Prop("BrownTableRightLaptopback"), sX + 10, sY1);
	}
	private void createStudyAreaRow2(int sX, int sY) {
		int sY1 = sY + 1;
		int sY2 = sY + 2;
		board.addGameObject(new Prop("ClassChair"), sX, sY);
		board.addGameObject(new Prop("ClassChair"), sX + 1, sY);
		board.addGameObject(new Prop("ClassChair"), sX + 5, sY);
		board.addGameObject(new Prop("ClassChair"), sX + 6, sY);
		board.addGameObject(new Prop("ClassChair"), sX + 9, sY);
		board.addGameObject(new Prop("ClassChair"), sX + 10, sY);
		board.addGameObject(new Prop("ClassChairBack"), sX, sY2);
		board.addGameObject(new Prop("ClassChairBack"), sX + 1, sY2);
		board.addGameObject(new Prop("ClassChairBack"), sX + 5, sY2);
		board.addGameObject(new Prop("ClassChairBack"), sX + 6, sY2);
		board.addGameObject(new Prop("ClassChairBack"), sX + 9, sY2);
		board.addGameObject(new Prop("ClassChairBack"), sX + 10, sY2);
		board.addGameObject(new Prop("BrownTableLeft"), sX, sY1);
		board.addGameObject(new Prop("BrownTableRightBookpencil"), sX + 1, sY1);
		board.addGameObject(new Prop("BrownTableLeftLaptop"), sX + 5, sY1);
		board.addGameObject(new Prop("BrownTableRightBookpencil"), sX + 6, sY1);
		board.addGameObject(new Prop("BrownTableLeft"), sX + 9, sY1);
		board.addGameObject(new Prop("BrownTableRight"), sX + 10, sY1);
	}
	private void createStudyAreaRow3(int sX, int sY) {
		int sY1 = sY + 1;
		int sY2 = sY + 2;
		board.addGameObject(new Prop("ClassChair"), sX, sY);
		board.addGameObject(new Prop("ClassChair"), sX + 1, sY);
		board.addGameObject(new Prop("ClassChair"), sX + 5, sY);
		board.addGameObject(new Prop("ClassChair"), sX + 6, sY);
		board.addGameObject(new Prop("ClassChair"), sX + 9, sY);
		board.addGameObject(new Prop("ClassChair"), sX + 10, sY);
		board.addGameObject(new Prop("ClassChairBack"), sX, sY2);
		board.addGameObject(new Prop("ClassChairBack"), sX + 1, sY2);
		board.addGameObject(new Prop("ClassChairBack"), sX + 5, sY2);
		board.addGameObject(new Prop("ClassChairBack"), sX + 6, sY2);
		board.addGameObject(new Prop("ClassChairBack"), sX + 9, sY2);
		board.addGameObject(new Prop("ClassChairBack"), sX + 10, sY2);
		board.addGameObject(new Prop("BrownTableLeft"), sX, sY1);
		board.addGameObject(new Prop("BrownTableRightLaptopback"), sX + 1, sY1);
		board.addGameObject(new Prop("BrownTableLeft"), sX + 5, sY1);
		board.addGameObject(new Prop("BrownTableRight"), sX + 6, sY1);
		board.addGameObject(new Prop("BrownTableLeftLaptop"), sX + 9, sY1);
		board.addGameObject(new Prop("BrownTableRightLaptopback"), sX + 10, sY1);
	}
	

	private void createItRoom(int sX, int sY){
		board.addGameObject(new Prop("ImacTable"), sX, sY);
		board.addGameObject(new Prop("ImacTable"), sX, sY + 3);
		board.addGameObject(new Prop("ImacTable"), sX, sY + 6);
		board.addGameObject(new Prop("ImacTable"), sX + 2, sY);
		board.addGameObject(new Prop("ImacTable"), sX + 2, sY + 3);
		board.addGameObject(new Prop("ImacTable"), sX + 2, sY + 6);
		board.addGameObject(new Prop("ClassChairBack"), sX, sY + 1);
		board.addGameObject(new Prop("ClassChairBack"), sX, sY + 4);
		board.addGameObject(new Prop("ClassChairBack"), sX, sY + 7);
		board.addGameObject(new Prop("ClassChairBack"), sX + 2, sY + 1);
		board.addGameObject(new Prop("ClassChairBack"), sX + 2, sY + 4);
		board.addGameObject(new Prop("ClassChairBack"), sX + 2, sY + 7);
	}

	private void createCantineTables(int x1, int x2, int y1, int y2) {
		if (x1-x2 == 0) {
			for (int i = 0; i < (y2 - y1) ; i += 2) {
				board.addGameObject(new Prop("TableCantine"), x1, y1 + i);
			}
		} else if (y1-y2 == 0) {
			for (int i = 0; i < (x2 - x1) ; i ++) {
				board.addGameObject(new Prop("TableCantine"), x1 + i, y1);
			}
		}
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
	
	public void placeLootableItem(int x, int y, Item item) {
		if (item != null) {
			if (!item.isLooted()) {
				board.addGameObject(new ItemWrap(item), x, y);
			}
		}
	}
}
