package nl.glasbakheroes.StudyOrDie.game;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.view.GameBoardView;
import nl.glasbakheroes.StudyOrDie.view.SpriteCache;
import nl.glasbakheroes.StudyOrDie.Objects.Boss;
import nl.glasbakheroes.StudyOrDie.Objects.Door;
import nl.glasbakheroes.StudyOrDie.Objects.Elevator;
import nl.glasbakheroes.StudyOrDie.Objects.Key;
import nl.glasbakheroes.StudyOrDie.Objects.Wall;
import nl.glasbakheroes.StudyOrDie.custom.Avatar;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

/**
 * A View on the SoD game board.
 * @author enjee 
 *
 */
public class StudyOrDieGameBoardView extends GameBoardView {
	private static final String TAG = "GameView";
	public StudyOrDieGameBoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (!isInEditMode()) {
			initGameView();
		}
	}

	/**
	 * Constructors
	 */
	public StudyOrDieGameBoardView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		if (!isInEditMode()) {
			initGameView();
		}
	}

	private void initGameView() {
		
		Log.d(TAG, "Loading all images");

		SpriteCache spriteCache = SpriteCache.getInstance(); 
		spriteCache.setContext(this.getContext());

		// Load the 'empty' cell bitmap and tell the tile view that this is the
				// image to use for cells without GameObject
				spriteCache.loadTile("empty", R.drawable.floortile_turqoise);
				setEmptyTile("empty");
				
				spriteCache.loadTile(Avatar.AVATAR_FRONT, R.drawable.char_player_front);
				spriteCache.loadTile(Avatar.AVATAR_BACK, R.drawable.char_player_back);
				spriteCache.loadTile(Avatar.AVATAR_LEFT, R.drawable.char_player_left);
				spriteCache.loadTile(Avatar.AVATAR_RIGHT, R.drawable.char_player_right);
				
				spriteCache.loadTile(Wall.WALL_HORIZONTAL_IMAGE, R.drawable.wall_horizontal);
				spriteCache.loadTile(Wall.WALL_VERTICAL_IMAGE, R.drawable.wall_vertical);
				spriteCache.loadTile(Wall.WALL_NORTH_WEST, R.drawable.wall_corner_northwest);
				spriteCache.loadTile(Wall.WALL_SOUTH_WEST, R.drawable.wall_corner_southwest);
				spriteCache.loadTile(Wall.WALL_SOUTH_EAST, R.drawable.wall_corner_southeast);
				spriteCache.loadTile(Wall.WALL_NORTH_EAST, R.drawable.wall_corner_northeast);
				
				spriteCache.loadTile(Boss.BOSS_IMAGE, R.drawable.wombat);
				spriteCache.loadTile(Door.DOOR_CLOSED_IMAGE, R.drawable.door_orange_south);
				spriteCache.loadTile(Elevator.ELEVATOR_IMAGE, R.drawable.door_elevator_locked);
				spriteCache.loadTile(Key.KEY_IMAGE, R.drawable.prop_keycard_blue);


	}
}
