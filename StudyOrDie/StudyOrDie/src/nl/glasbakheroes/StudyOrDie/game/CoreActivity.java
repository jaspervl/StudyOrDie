package nl.glasbakheroes.StudyOrDie.game;

import nl.glasbakheroes.StudyOrDie.R;
import android.app.Activity;
import android.os.Bundle; 
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class CoreActivity extends Activity {
	
	private StudyOrDieGameBoardView gameView;
	private StudyOrDieGame game;
	private Button upButton;
	private Button downButton;
	private Button leftButton; 
	private Button rightButton;
	 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		/* Load main xml */
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		/* Find interface elements */
		gameView = (StudyOrDieGameBoardView) findViewById(R.id.studyOrDieGameBoardView1);
		upButton = (Button) findViewById(R.id.btnUp);
		downButton = (Button) findViewById(R.id.btnDown);
		leftButton = (Button) findViewById(R.id.btnLeft);
		rightButton = (Button) findViewById(R.id.btnRight);
		
		/* Create the game object */
		game = new StudyOrDieGame(this);
		
		/* Set listeners for direction-buttons */
		MoveTouchListener listener = new MoveTouchListener();
		upButton.setOnTouchListener(listener);
		downButton.setOnTouchListener(listener); 
		leftButton.setOnTouchListener(listener); 
		rightButton.setOnTouchListener(listener);
	}
		
	/**
	 * Listens for touches on direction-buttons and tells the game instance to move the avatar.
	 * @author enjee
	 */
	private class MoveTouchListener implements View.OnTouchListener {

		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (v == upButton) {
				game.moveAvatar("Up");
			} else if (v == downButton) {
				game.moveAvatar("Down");
			} else if (v == leftButton) {
				game.moveAvatar("Left");
			} else if (v == rightButton) {
				game.moveAvatar("Right");
			}
			return false; 
		}
	}

	/**
	 * Game board view getter.
	 * @return
	 */
	public StudyOrDieGameBoardView getGameBoardView() {
		return gameView;
	}

}
