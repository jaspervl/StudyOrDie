package nl.glasbakheroes.StudyOrDie.game;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.view.GameBoardView;
import nl.glasbakheroes.StudyOrDie.view.SpriteCache;
import nl.glasbakheroes.StudyOrDie.Objects.Boss;
import nl.glasbakheroes.StudyOrDie.Objects.Door;
import nl.glasbakheroes.StudyOrDie.Objects.Elevator;
import nl.glasbakheroes.StudyOrDie.Objects.Key;
import nl.glasbakheroes.StudyOrDie.Objects.Prop;
import nl.glasbakheroes.StudyOrDie.Objects.Wall;
import nl.glasbakheroes.StudyOrDie.custom.Avatar;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

/**
 * A View on the SoD game board.
 * @author Niels Jan & Jasper
 */
public class StudyOrDieGameBoardView extends GameBoardView {
	private static final String TAG = "GameView";
	public StudyOrDieGameBoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (!isInEditMode()) {
			initGameView();
		}
	}

	/** Constructor */
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
				
				spriteCache.loadTile(Wall.WALL_HORIZONTAL_IMAGE, R.drawable.wall_horizontal);
				spriteCache.loadTile(Wall.WALL_VERTICAL_IMAGE, R.drawable.wall_vertical);
				spriteCache.loadTile(Wall.WALL_NORTH_WEST, R.drawable.wall_corner_northwest);
				spriteCache.loadTile(Wall.WALL_SOUTH_WEST, R.drawable.wall_corner_southwest);
				spriteCache.loadTile(Wall.WALL_SOUTH_EAST, R.drawable.wall_corner_southeast);
				spriteCache.loadTile(Wall.WALL_NORTH_EAST, R.drawable.wall_corner_northeast);
				
				spriteCache.loadTile(Door.DOOR_CLOSED_IMAGE, R.drawable.door_orange_south);
				spriteCache.loadTile(Elevator.ELEVATOR_IMAGE, R.drawable.door_elevator_open);
				spriteCache.loadTile(Key.KEY_IMAGE, R.drawable.prop_keycard_blue);
				
				/* Props */
				spriteCache.loadTile(Prop.PROP_TABLE_GREY, R.drawable.prop_table_grey);
				spriteCache.loadTile(Prop.PROP_TABLE_GREY_IMAC, R.drawable.prop_table_grey_imac);
				spriteCache.loadTile(Prop.PROP_CHAIR_GREEN, R.drawable.prop_chair_green);
				spriteCache.loadTile(Prop.PROP_TABLE_CANTINE, R.drawable.prop_table_cantine);
				spriteCache.loadTile(Prop.PROP_COLLEGE_SEAT, R.drawable.prop_college_seat);
				spriteCache.loadTile(Prop.PROP_SPEACH, R.drawable.prop_speach);
				spriteCache.loadTile(Prop.PROP_TEACHER_DESK, R.drawable.prop_teacher_desk);
				spriteCache.loadTile(Prop.PROP_CLASS_CHAIR, R.drawable.prop_class_chair);
				spriteCache.loadTile(Prop.PROP_CLASS_TABLE, R.drawable.prop_class_table);
				
				
				
				/* Bosses */
				spriteCache.loadTile(Boss.BOSS_IMAGE_RUUD, R.drawable.ruud_greven);
				spriteCache.loadTile(Boss.BOSS_IMAGE_CONCIERGE, R.drawable.concierge);
				spriteCache.loadTile(Boss.BOSS_IMAGE_FRANK, R.drawable.frank_van_doorn);
				spriteCache.loadTile(Boss.BOSS_IMAGE_TRISTAN, R.drawable.tristan_pothoven);
				spriteCache.loadTile(Boss.BOSS_IMAGE_EVERT, R.drawable.evert_duipmans);
				spriteCache.loadTile(Boss.BOSS_IMAGE_SYNTAXIS, R.drawable.syntaxis_voorzitter);
				spriteCache.loadTile(Boss.BOSS_IMAGE_JAN, R.drawable.jan_stroet);
				
				/* Avatar */
				spriteCache.loadTile(Avatar.AVATAR_FRONT_BLOND, R.drawable.char_player_blond_front0);
				spriteCache.loadTile(Avatar.AVATAR_BACK_BLOND, R.drawable.char_player_blond_back0);
				spriteCache.loadTile(Avatar.AVATAR_LEFT_BLOND, R.drawable.char_player_blond_left0);
				spriteCache.loadTile(Avatar.AVATAR_RIGHT_BLOND, R.drawable.char_player_blond_right0);
				spriteCache.loadTile(Avatar.AVATAR_FRONT_BROWN, R.drawable.char_player_brown_front0);
				spriteCache.loadTile(Avatar.AVATAR_BACK_BROWN, R.drawable.char_player_brown_back0);
				spriteCache.loadTile(Avatar.AVATAR_LEFT_BROWN, R.drawable.char_player_brown_left0);
				spriteCache.loadTile(Avatar.AVATAR_RIGHT_BROWN, R.drawable.char_player_brown_right0);
				spriteCache.loadTile(Avatar.AVATAR_FRONT_ASTRO, R.drawable.char_player_astro_front0);
				spriteCache.loadTile(Avatar.AVATAR_BACK_ASTRO, R.drawable.char_player_astro_back0);
				spriteCache.loadTile(Avatar.AVATAR_LEFT_ASTRO, R.drawable.char_player_astro_left0);
				spriteCache.loadTile(Avatar.AVATAR_RIGHT_ASTRO, R.drawable.char_player_astro_right0);
				spriteCache.loadTile(Avatar.AVATAR_FRONT_SANTA, R.drawable.char_player_santa_front0);
				spriteCache.loadTile(Avatar.AVATAR_BACK_SANTA, R.drawable.char_player_santa_back0);
				spriteCache.loadTile(Avatar.AVATAR_LEFT_SANTA, R.drawable.char_player_santa_left0);
				spriteCache.loadTile(Avatar.AVATAR_RIGHT_SANTA, R.drawable.char_player_santa_right0);
				


	}
}
