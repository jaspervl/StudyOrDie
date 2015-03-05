package nl.glasbakheroes.StudyOrDie.game;

<<<<<<< HEAD
import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.view.GameBoardView;
import nl.glasbakheroes.StudyOrDie.view.SpriteCache;
import nl.glasbakheroes.StudyOrDie.custom.Character;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;


public class StudyOrDieGameBoardView extends GameBoardView {
	private static final String TAG = "GameView";
	public StudyOrDieGameBoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initGameView();
	}

	private void initGameView() {
		Log.d(TAG, "Loading all images");

		SpriteCache spriteCache = SpriteCache.getInstance();
		spriteCache.setContext(this.getContext());

		// Load the 'empty' cell bitmap and tell the tile view that this is the
		// image to use for cells without GameObject
		spriteCache.loadTile("empty", R.drawable.cell);
		setEmptyTile("empty");
		
		spriteCache.loadTile(Character.IMAGE, R.drawable.rock);
=======
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import nl.glasbakheroes.StudyOrDie.view.GameBoardView;

public class StudyOrDieGameBoardView extends GameBoardView{

	public StudyOrDieGameBoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public StudyOrDieGameBoardView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	private void init() {
		setBackgroundColor(Color.GREEN);
>>>>>>> origin/master
	}

}
