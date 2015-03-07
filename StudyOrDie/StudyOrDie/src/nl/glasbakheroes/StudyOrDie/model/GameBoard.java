package nl.glasbakheroes.StudyOrDie.model;

import java.util.Observable;

import nl.glasbakheroes.StudyOrDie.Objects.*;
import nl.glasbakheroes.StudyOrDie.custom.Avatar;
import nl.glasbakheroes.StudyOrDie.custom.LevelLoader;
import nl.glasbakheroes.StudyOrDie.game.CombatActivity;
import nl.glasbakheroes.StudyOrDie.game.CoreActivity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * The game board, which is a rectangular array of GameObject.
 * 
 * You should subclass this for your own game.
 * There you will (among other things) implement
 *    public void onEmptyTileClicked(int x, int y);
 * which will be called when the user clicked on a tile which had no game object
 * on it.
 * 
 * @author Paul de Groot
 * @Co-Author EnJee
 */
public abstract class GameBoard extends Observable {
	private static final String TAG = "Playground";
	private CoreActivity activity;

	/** The game this game board is a part of. */
	private Game game;
	
	/** The game objects on the board. */
	private GameObject[][] gameBoard;
	private Avatar avatar;

	/**
	 * Create a new game board.
	 * 
	 * @param width   The width of the board, in tiles.
	 * @param height  The height of the board, in tiles.
	 */
	public GameBoard(int width, int height) {
		this.gameBoard = new GameObject[width][height];
	}

	/**
	 * Returns the number of tiles in the X-direction of the board.
	 */
	public int getWidth() {
		return gameBoard.length;
	}

	/**
	 * Returns the number of tiles in the Y-direction of the board.
	 */
	public int getHeight() {
		return gameBoard[0].length;
	}

	/**
	 * Add a game object to the board.
	 * 
	 * @param obj  The object to place on the board.
	 * @param x    The X-coordinate to place the object at.
	 * @param y    The Y-coordinate to place the object at.
	 *
	 * @throws IllegalArgumentException if (x,y) is not empty
	 */
	public void addGameObject(GameObject obj, int x, int y) {
		if (obj instanceof Avatar) {
			avatar = (Avatar) obj;
		}
		if( gameBoard[x][y] != null ) {
			throw new IllegalArgumentException("Destination already contains an object");
		}
		
		gameBoard[x][y] = obj;
		obj.setPosition(x,  y);
	}

	/**
	 * Move an object on the board.
	 * 
	 * @param obj   The object to move.
	 * @param newX  The new X-coordinate of the object.
	 * @param newY  The new Y-coordinate of the object.
	 *
	 * @throws IllegalArgumentException if (newX,newY) is not empty
	 */
	public void moveObject(GameObject obj, int newX, int newY) {
		int oldX = obj.getPositionX();
		int oldY = obj.getPositionY();

		gameBoard[oldX][oldY] = null;
		
		if( gameBoard[newX][newY] != null ) {
			throw new IllegalArgumentException("Destination already contains an object");
		}
		
		gameBoard[newX][newY] = obj;
		obj.setPosition(newX, newY);
	}

	/**
	 * Retrieves the object at the location (x, y) on the board.
	 * 
	 * @param x  The X-coordinate of the tile
	 * @param y  The Y-coordinate of the tile
	 * @return   The GameObject at (x, y) or null if there was none.
	 */
	public GameObject getObject(int x, int y) {
		return gameBoard[x][y];
	}

	/**
	 * Call this to notify the game board view that it should redraw.
	 * You should call this any time you are done changing things on the board
	 * and want to make your changes visible.
	 */
	public void updateView() {
		Log.d(TAG, "Updating game view");

		setChanged();
		notifyObservers();
	}

	/**
	 * Remove an object from the board.
	 * 
	 * @param object  The object to remove from the board.
	 */
	public void removeObject(GameObject object) {
		int x = object.getPositionX();
		int y = object.getPositionY();
		gameBoard[x][y] = null;
	}
	
	/**
	 * Remove all objects from the game board.
	 */
	public void removeAllObjects() {
		for( int x = 0; x < getWidth(); x++ ) {
			for( int y = 0; y < getHeight(); y++ ) {
				gameBoard[x][y] = null;
			}
		}
	}

	/**
	 * Get a reference to the game class. You can use this to access the game
	 * state.
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * Called if the user clicked on a tile, and no object was present on that tile.
	 * 
	 * @param x  The x-coordinate of the clicked tile
	 * @param y  The y-coordinate of the clicked tile
	 */
	public abstract void onEmptyTileClicked(int x, int y);

	/**
	 * Retrieves the background image that must be used for a specific tile.
	 * Return null to use the image set using setEmptyTile().
	 * 
	 * @param x  The x-coordinate of the tile
	 * @param y  The x-coordinate of the tile
	 * @return   A image identifier to draw on this tile, or null to use the empty tile.
	 */
	public String getBackgroundImg(int x, int y) {
		return null;
	}
	
	/** Used by Game. */
	void setGame(Game game) {
		this.game = game;
	}
	
	/**
	 * Create a horizontal wall in the gameboard.
	 * @param x1	The x position in the grid to start the wall.
	 * @param x2	The x position in the grid to end the wall.
	 * @param y		The y position in the grid to place the wall.
	 */
	public void createWallHorizontal(int x1, int x2, int y) {
		int max = 0;
		int min = 0;
		if (x1 > x2) {
			max = x1;
			min = x2;
		} else {
			max = x2;
			min = x1;
		}
		for (; min <= max ; min++) {
			addGameObject(new Wall("Horizontal"), min, y);
		}
	}
	
	/**
	 * Create a vertical wall in the gameboard.
	 * @param y1	The y position in the grid to start the wall.
	 * @param y2	The y position in the grid to end the wall.
	 * @param x		The x position in the grid to place the wall.
	 */
	public void createWallVertical(int y1, int y2, int x) {
		int max = 0;
		int min = 0;
		if (y1 > y2) {
			max = y1;
			min = y2;
		} else {
			max = y2;
			min = y1;
		}
		for (; min <= max ; min++) {
			addGameObject(new Wall("Vertical"), x, min);
		}
	}
	
	/**
	 * Method to move the avatar.
	 * 
	 * @param direction The direction the avatar wants to move. [Up / Down / Left / Right]
	 */
	public void moveAvatar(String direction) {
		/* Gets the gameboard */
		/* Checks weather the avatar is too close to boundaries and other objects */
		if (checkBoundaries(direction)) {
			/* Moves the avatar in a certain direction */
			switch (direction) {
			case "Up":
				moveObject(avatar, avatar.getPositionX(),
						avatar.getPositionY() - 1);
				break;
			case "Down": 
				moveObject(avatar, avatar.getPositionX(),
						avatar.getPositionY() + 1);
				break;
			case "Left":
				moveObject(avatar, avatar.getPositionX() - 1,
						avatar.getPositionY());
				break;
			case "Right":
				moveObject(avatar, avatar.getPositionX() + 1,
						avatar.getPositionY());
				break;
			default:
				break;
			}
			updateView();
		}

	}
	
	/**
	 * Helper method, check weather the desired movement is possible and what objects are present.
	 * @param direction	The direction the avatar wants to move.
	 * @return			Returns true for valid movement, false for invalid movement.
	 */
	private boolean checkBoundaries(String direction) {
		int avatarNewX = 0;
		int avatarNewY = 0;
		switch (direction) {
		case "Up":
			avatarNewX = avatar.getPositionX();
			avatarNewY = avatar.getPositionY() -1;
			if (avatarNewY >= 0 ) {
				return inspectObject(avatarNewX, avatarNewY, direction);
			} else {
				/* Edge of the screen, get new screen items */
				LevelLoader levelLoader = activity.getGame().getLevelLoader();
				levelLoader.setLevel(levelLoader.getLevel() + 1);
				levelLoader.loadLevel();
				return false;
			}
		case "Down":
			avatarNewX = avatar.getPositionX();
			avatarNewY = avatar.getPositionY() +1;
			if (avatarNewY < getHeight() ) {
				return inspectObject(avatarNewX, avatarNewY, direction);
			} else {
				/* Edge of the screen, get new screen items */
				LevelLoader levelLoader = activity.getGame().getLevelLoader();
				levelLoader.setLevel(levelLoader.getLevel() - 1);
				levelLoader.loadLevel();
				return false;
			}
		case "Left":
			avatarNewX = avatar.getPositionX() -1;
			avatarNewY = avatar.getPositionY();
			if (avatarNewX >= 0 ) {
				return inspectObject(avatarNewX, avatarNewY, direction);
			} else {
				return false;
			}			
		case "Right":
			avatarNewX = avatar.getPositionX() +1;
			avatarNewY = avatar.getPositionY();
			if (avatarNewX < getWidth() ) {
				return inspectObject(avatarNewX, avatarNewY, direction);
			} else {
				return false;
			}
		default:
			return false;
		}
	}

	/**
	 * Checks what object is present at the location the avatar wants to move.
	 * @param avatarNewX	Desired x of the avatar
	 * @param avatarNewY	Desired y of the avatar
	 * @return				True for movement, false for no movement.
	 */
	private boolean inspectObject(int avatarNewX, int avatarNewY, String direction) {
		if (getObject(avatarNewX, avatarNewY) == null) {
			/* No object present, avatar can move. */
			return true;
		} else if (getObject(avatarNewX, avatarNewY) instanceof Boss) {
			/* Boss present, avatar will enter a fight and won't move. */
			Log.w("GameBoard.inspectObject", "ENTERING A FIGHT!");
			Intent combatIntent = new Intent(activity, CombatActivity.class);
			activity.getGame().getLevelLoader().killBoss(activity.getGame().getLevelLoader().getLevel());
			activity.startActivity(combatIntent);
			return false;
		} else if (getObject(avatarNewX, avatarNewY) instanceof Door) {
			/* Door present */
			if (((Door) getObject(avatarNewX, avatarNewY)).isLocked()) {
				Toast.makeText(activity, "Door is locked", Toast.LENGTH_SHORT).show();
			} else {
				removeObject(getObject(avatarNewX, avatarNewY));
				updateView();
			}
			return false;
		} else if (getObject(avatarNewX, avatarNewY) instanceof Elevator) {
			/* Elevator is present, go to the next/last major level */
			LevelLoader levelLoader = activity.getGame().getLevelLoader();
			if (levelLoader.getLevel() % 10 == 3) {
				levelLoader.setLevel(levelLoader.getLevel() + 8);
			} else {
				levelLoader.setLevel(levelLoader.getLevel() - 8);
			}
			levelLoader.loadLevel();
			return false;
		} else {
			/* Not a tile present to move on, avatar won't move. */
			return false;
		}
	}

	/** Save the core activity so the gameboard can start other activities. */
	public void setCoreActivity(CoreActivity activity) {
		this.activity = activity;
	}

}
