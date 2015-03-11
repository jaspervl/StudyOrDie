package nl.glasbakheroes.StudyOrDie.game;

import nl.glasbakheroes.StudyOrDie.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Main activity in the game, represents the overworld
 * 
 * @author enjee & Jasper
 */
public class CoreActivity extends Activity {

	private StudyOrDieGameBoardView gameView;
	private StudyOrDieGame game;
	private Button upButton;
	private Button downButton;
	private Button leftButton;
	private Button rightButton;
	private Button menuButton;
	private Handler handler;
	protected String moveDirection = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		/* Load main xml */
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		handler = new Handler();

		/* Find interface elements */
		gameView = (StudyOrDieGameBoardView) findViewById(R.id.studyOrDieGameBoardView1);
		upButton = (Button) findViewById(R.id.btnUp);
		downButton = (Button) findViewById(R.id.btnDown);
		leftButton = (Button) findViewById(R.id.btnLeft);
		rightButton = (Button) findViewById(R.id.btnRight);
		menuButton = (Button) findViewById(R.id.btnMenu);

		/* Create the game object */
		game = new StudyOrDieGame(this);

		/* Set listeners for direction-buttons */
		TouchListener listener = new TouchListener();
		upButton.setOnTouchListener(listener);
		downButton.setOnTouchListener(listener);
		leftButton.setOnTouchListener(listener);
		rightButton.setOnTouchListener(listener);
		menuButton.setOnTouchListener(listener);
	}

	/** Making a new runnable action which can be repeatedly played on 1 thread */
	Runnable movement = new Runnable() {
		@Override
		public void run() {
			StudyOrDieGameBoard board = (StudyOrDieGameBoard) game.getGameBoard();
			board.moveAvatar(moveDirection);
			handler.postDelayed(movement, 250);
		}
	};

	void startMovingLoop() {
		movement.run();
	}

	void stopMovingLoop() {
		handler.removeCallbacks(movement);
	}

	/** Listener for the buttons in the overworld */
	private class TouchListener implements View.OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {

			/* A button is pressed down */
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				
				/* Start the moving loops till button is released */
				if (v == upButton) {
					moveDirection = "Up";
					startMovingLoop();	
				} else if (v == downButton) {
					moveDirection = "Down";
					startMovingLoop();	
				} else if (v == leftButton) {
					moveDirection = "Left";
					startMovingLoop();	
				} else if (v == rightButton) {
					moveDirection = "Right";
					startMovingLoop();
					
					/* Menu button pressed */
				} else if (v == menuButton) {
					Intent menuIntent = new Intent(CoreActivity.this, MenuActivity.class);
					startActivity(menuIntent);
				}
				
				/* A button is released */
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				if (v != menuButton) {
					stopMovingLoop();
				}
			}
			return false;
		}
	}

	/**
	 * Game board view getter.
	 */
	public StudyOrDieGameBoardView getGameBoardView() {
		return gameView;
	}

	public StudyOrDieGame getGame() {
		return game;
	}

	/**
	 * If a result is returned from combatActivity, the boss in the current
	 * level will be killed.
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
			boolean bossDead = data.getBooleanExtra("bossDead", false);
			if (bossDead) {
				game.getLevelLoader()
						.killBoss(game.getLevelLoader().getLevel());
				game.getLevelLoader().loadLevel("Boss");
			} else {
				game.getLevelLoader().setLevel(
						game.getLevelLoader().getLevel() - 2);
				game.getLevelLoader().loadLevel("Bottom");
			}
		}
	}

}
