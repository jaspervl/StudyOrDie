package nl.glasbakheroes.StudyOrDie.game;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.view.GameBoardView;
import nl.glasbakheroes.StudyOrDie.view.SpriteCache;
import nl.glasbakheroes.StudyOrDie.custom.Avatar;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;


public class StudyOrDieGameBoardView extends GameBoardView {
	private static final String TAG = "GameView";
	public StudyOrDieGameBoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
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
		spriteCache.loadTile("empty", R.drawable.tile_floor_turqois);
		setEmptyTile("empty");
		
		spriteCache.loadTile(Avatar.ROCK_IMAGE, R.drawable.tile_avatar);

	}
}
