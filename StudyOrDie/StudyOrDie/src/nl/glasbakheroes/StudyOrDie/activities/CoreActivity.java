package nl.glasbakheroes.StudyOrDie.activities;

import javax.crypto.spec.IvParameterSpec;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieGame;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieGameBoard;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieGameBoardView;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
import nl.glasbakheroes.StudyOrDie.view.OverworldStatsView;
import android.app.Activity;
import android.app.usage.UsageEvents.Event;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Main activity in the game, represents the overworld
 * 
 * @author Niels Jan & Jasper
 */
public class CoreActivity extends Activity {
	/** Constants */
	public static final int REQUEST_START_CODE = 2;
	private static boolean startMenuShown = false;
	private MotionEvent touchEvent;
	
	/* Major class instances */
	private StudyOrDieGameBoardView gameView;
	private StudyOrDieGame game;
	private StudyOrDieModel model;
	
	/* Interface components */
	private Button joystickButton;
	private Button menuButton;
	private ImageView btnFoldUnfold;
	private OverworldStatsView statView;
	private MediaPlayer player;
	int length;

	/* Helper variables */
	private Handler handler;
	protected int moveDirection = 0;
	private boolean folding = true;
	private boolean disableMovement = false;
	private boolean allowFinish = true;

	/* Will be called when the activity is created [Also after it got destroyed/finished] */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		player = MediaPlayer.create(getApplicationContext(), R.raw.pokemon_mix);
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
		length = 0;
		allowFinish = false;
		
		/* Retrieve the model from the application and create a handler for delayed actions */
		handler = new Handler();
		model = ((StudyOrDieApplication) getApplication()).getModel();
		model.setActivity(this);

		/* Find interface elements */
		gameView = (StudyOrDieGameBoardView) findViewById(R.id.studyOrDieGameBoardView1);
		
		joystickButton = (Button) findViewById(R.id.btnJoystickStick);
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
		joystickButton.setOnTouchListener(listener);
		menuButton.setOnTouchListener(listener); 
		btnFoldUnfold.setOnTouchListener(listener);
		
	}

	/** Game board view getter. */
	public StudyOrDieGameBoardView getGameBoardView() {
		return gameView;
	}

	/** Game getter. */
	public StudyOrDieGame getGame() {
		return game;
	}

	/** Result from another activity received */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		/** Result from start screen occurred */
		statView.setDisplayName(model.getAvatar().getName());
		 if (requestCode == REQUEST_START_CODE) { 
			/* Save the received data into strings */ 
			String action = data.getStringExtra("action"); 
			
			/* Call methods corresponding with the data */
			if (action.equals("new")) {
				startNewGame();
			} else if (action.equals("load")) {
				loadGame();  
			} else if (action.equals("abort")) {
				// Do something when player backs out of start menu.
				startNewGame();
			}
		} else if (requestCode == StudyOrDieGameBoard.REQUEST_COMBAT_INTENT) {
			// do nothing
		}
	}

	/** When activity is resumed */
	@Override
	protected void onResume() {
		/* Enable all movement in this activity */
		disableMovement = false;
		player.seekTo(length);
		player.start();
		super.onResume();
	}
	
	/** When activity is paused */
	@Override
	protected void onPause() {
		/* Disable all movement in this activity */
		disableMovement = true;
		if(player.isPlaying()){
		player.pause();
		length= player.getCurrentPosition();
		}
		super.onPause();
	}
	
	/**
	 * Dummy methods at the moment!
	 */
	private void startNewGame() {
		// Start a fresh game.
	}
	private void loadGame() {
		// Start load screen from here.
	}
	/**
	 * End of dummy methods
	 */	
	
	/** Listener for the buttons in the overworld */
	private class TouchListener implements OnTouchListener {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {

			/* A button is pressed down */
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				touchEvent = event;
				calculateJoystickPosition();
				
				if (v == joystickButton) {
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
					clearJoystick();
					stopMovingLoop();
				}
			} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
				touchEvent = event;
				calculateJoystickPosition();
			}
			return false;
		}

	}

	/** Calculate the movement direction for the joystick touch event */
	private void calculateJoystickPosition() {
		float scaledDensity = getApplicationContext().getResources().getDisplayMetrics().scaledDensity;
		final float JOYSTICK_RADIUS = 60 * scaledDensity;
		Float xTouch = touchEvent.getX() - JOYSTICK_RADIUS;
		Float yTouch = touchEvent.getY() - JOYSTICK_RADIUS;
		if (xTouch > -JOYSTICK_RADIUS - 50 && xTouch < JOYSTICK_RADIUS + 50 && yTouch > -JOYSTICK_RADIUS - 50 && yTouch < JOYSTICK_RADIUS + 50) {
			if (Math.abs(xTouch) > Math.abs(yTouch)) {
				if (xTouch > 0) {
					moveDirection = StudyOrDieGameBoard.RIGHT;
					joystickButton.setBackgroundResource(R.drawable.joystick_stick_right);
				} else {
					moveDirection = StudyOrDieGameBoard.LEFT;
					joystickButton.setBackgroundResource(R.drawable.joystick_stick_left);
				}
			} else {
				if (yTouch < 0) {
					moveDirection = StudyOrDieGameBoard.UP;
					joystickButton.setBackgroundResource(R.drawable.joystick_stick_up);
				} else {
					moveDirection = StudyOrDieGameBoard.DOWN; 
					joystickButton.setBackgroundResource(R.drawable.joystick_stick_down);
				}
			}
		} else {
			stopMovingLoop();
		}
	}
	
	
	/** Making a new runnable action which can be repeatedly played on 1 thread */
	Runnable movement = new Runnable() {
		@Override
		public void run() {
			if (!disableMovement) {
				StudyOrDieGameBoard board = (StudyOrDieGameBoard) game.getGameBoard();
				calculateJoystickPosition();
				board.moveAvatar(moveDirection);
				handler.postDelayed(movement, 300);
			}
		}
	};
	
	/** Enable and disable movement */
	public void disableMovement() {
		disableMovement = true;
	}
	public void enableMovement() {
		disableMovement = false;
	}

	/** Starts the movement method for infinite time, until stop moving is called. */
	void startMovingLoop() {
		movement.run();
	}

	/** Stop the infinite movement loop */
	void stopMovingLoop() {
		handler.removeCallbacks(movement);
	}

	public void clearJoystick() {
		joystickButton.setBackgroundResource(R.drawable.joystick_stick);
	}
}
