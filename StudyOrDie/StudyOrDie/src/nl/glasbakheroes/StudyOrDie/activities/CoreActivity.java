package nl.glasbakheroes.StudyOrDie.activities;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieGame;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieGameBoard;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieGameBoardView;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
import nl.glasbakheroes.StudyOrDie.view.OverworldStatsView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Main activity in the game, represents the overworld
 * 
 * @author enjee & Jasper
 */
public class CoreActivity extends Activity {

	public static final int REQUEST_START_CODE = 2;
	public static final int REQUEST_COMBAT_CODE = StudyOrDieGameBoard.REQUEST_COMBAT_CODE;
	private static boolean startMenuShown = false;
	
	private StudyOrDieGameBoardView gameView;
	private StudyOrDieGame game;
	private Button upButton;
	private Button downButton; 
	private Button leftButton;
	private Button rightButton;
	private Button menuButton;
	private ImageView btnFoldUnfold;
	private Handler handler;
	protected String moveDirection = "";
	private StudyOrDieModel model;
	private OverworldStatsView statView;
	private boolean folding = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/* Remove title bar */
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    /* Remove notification bar */
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.main);
		
		/** Call start screen once for the lifetime of the application */
		if (!startMenuShown) {
			Intent startScreenIntent = new Intent(CoreActivity.this, StartActivity.class);
			startActivityForResult(startScreenIntent, REQUEST_START_CODE);
			startMenuShown = true;
		} 
		
		handler = new Handler();
		model = ((StudyOrDieApplication) getApplication()).getModel();

		/* Find interface elements */
		gameView = (StudyOrDieGameBoardView) findViewById(R.id.studyOrDieGameBoardView1);
		upButton = (Button) findViewById(R.id.btnUp);
		downButton = (Button) findViewById(R.id.btnDown);
		leftButton = (Button) findViewById(R.id.btnLeft);
		rightButton = (Button) findViewById(R.id.btnRight);
		menuButton = (Button) findViewById(R.id.btnMenu);
		btnFoldUnfold = (ImageView) findViewById(R.id.ivFoldUnfold);
		statView = (OverworldStatsView) findViewById(R.id.overWorldStatView);
		
		/* Set transparant to certain interface elements */
		statView.setAlpha(0.5F);
		btnFoldUnfold.setAlpha(0.5F);

		/* Create the game object */
		game = new StudyOrDieGame(this);

		/* Set listeners for direction-buttons */
		TouchListener listener = new TouchListener();
		upButton.setOnTouchListener(listener);
		downButton.setOnTouchListener(listener);
		leftButton.setOnTouchListener(listener);
		rightButton.setOnTouchListener(listener);
		menuButton.setOnTouchListener(listener); 
		btnFoldUnfold.setOnTouchListener(listener);
		
	}
	
	
	/** Making a new runnable action which can be repeatedly played on 1 thread */
	Runnable movement = new Runnable() {
		@Override
		public void run() {
			StudyOrDieGameBoard board = (StudyOrDieGameBoard) game.getGameBoard();
			board.moveAvatar(moveDirection);
			handler.postDelayed(movement, 200);
		}
	};

	void startMovingLoop() {
		movement.run();
	}

	void stopMovingLoop() {
		handler.removeCallbacks(movement);
	}

	/** Listener for the buttons in the overworld */
	private class TouchListener implements OnTouchListener {

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
				} else if (v == btnFoldUnfold) {
					if (folding) {
						btnFoldUnfold.setImageResource(R.drawable.unfold_arrow);
						statView.setMinimize(folding);
						folding = false;
					} else {
						btnFoldUnfold.setImageResource(R.drawable.fold_arrow);
						statView.setMinimize(folding);
						folding = true;
					}
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

	/** Result from another activity received */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		/** Result from combat screen occurred */
		if (requestCode == REQUEST_COMBAT_CODE) { 
			boolean bossDead = data.getBooleanExtra("bossDead", false);
			String bossName = data.getStringExtra("bossName");
			/* If the boss is dead according to the returned data, kill him */
			if (bossDead) {
				Toast.makeText(this, bossName + " has been killed!", Toast.LENGTH_SHORT).show();
			} else {
				model.getLoader().setLevel(model.getLoader().getLevel() - 2);
				model.getLoader().loadLevel("Bottom");
			} 
			
		/** Result from start screen occurred */
		} else if (requestCode == REQUEST_START_CODE) { 
			/* Save the received data into strings */ 
			String action = data.getStringExtra("action"); 
			String avatarName = data.getStringExtra("avatarName");
			String avatarPicture = data.getStringExtra("avatarPicure");
			
			model.getAvatar().setName(avatarName);
			
			/* Call methods corresponding with the data */
			if (action.equals("new")) {
				startNewGame(avatarName, avatarPicture);
			} else if (action.equals("load")) {
				loadGame(avatarName); 
			} else if (action.equals("abort")) {
				startNewGame(avatarName, "default_picure_name");
			}
		}
	}

	/** At the moment these methods add nothing to the game, 
	 * once we start allocating data to the model we can use these methods. */
	private void startNewGame(String avatarName, String avatarPicure) {
		// Set up the model for a new game.	
		Log.w("CoreActivity", "New game was chosen by the player with avatar name: " + avatarName);
	}
	
	private void loadGame(String avatarName) {
		// Start load screen from here.
		Log.w("CoreActivity", "Load game was chosen by the player with avatar name: " + avatarName);
	}
	
}
