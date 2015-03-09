package nl.glasbakheroes.StudyOrDie.game;

import nl.glasbakheroes.StudyOrDie.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle; 
import android.view.View;
import android.widget.Button;

public class CoreActivity extends Activity {
	
	private StudyOrDieGameBoardView gameView;
	private StudyOrDieGame game;
	private Button upButton;
	private Button downButton;
	private Button leftButton; 
	private Button rightButton;
	private Button menuButton;
	 
	
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
		menuButton = (Button) findViewById(R.id.btnMenu);
		
		/* Create the game object */
		game = new StudyOrDieGame(this);
		
		/* Set listeners for direction-buttons */
		ClickListener listener = new ClickListener();
		upButton.setOnClickListener(listener);
		downButton.setOnClickListener(listener); 
		leftButton.setOnClickListener(listener); 
		rightButton.setOnClickListener(listener);
		menuButton.setOnClickListener(listener);
	}
		
	/**
	 * Listens for touches on direction-buttons and tells the game instance to move the avatar.
	 * @author enjee
	 */
	private class ClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			StudyOrDieGameBoard board = (StudyOrDieGameBoard) game.getGameBoard();
			if (v == upButton) { 
				board.moveAvatar("Up");
			} else if (v == downButton) {
				board.moveAvatar("Down");
			} else if (v == leftButton) {
				board.moveAvatar("Left");
			} else if (v == rightButton) {
				board.moveAvatar("Right");
			} else if (v == menuButton) {
				Intent menuIntent = new Intent(CoreActivity.this, MenuActivity.class);
				startActivity(menuIntent);
				
			}
		}
	}

	/**
	 * Game board view getter.
	 * @return
	 */
	public StudyOrDieGameBoardView getGameBoardView() {
		return gameView;
	}
	
	public StudyOrDieGame getGame() {
		return game;
	}

}
