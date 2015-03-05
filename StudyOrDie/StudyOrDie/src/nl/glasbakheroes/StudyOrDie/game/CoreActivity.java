package nl.glasbakheroes.StudyOrDie.game;

import nl.glasbakheroes.StudyOrDie.R;
import android.app.Activity;
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
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		gameView = (StudyOrDieGameBoardView) findViewById(R.id.studyOrDieGameBoardView1);
		game = new StudyOrDieGame(this);
		
		
		upButton = (Button) findViewById(R.id.btnUp);
		downButton = (Button) findViewById(R.id.btnDown);
		leftButton = (Button) findViewById(R.id.btnLeft);
		rightButton = (Button) findViewById(R.id.btnRight);
		
		MoveClickListener listener = new MoveClickListener();
		upButton.setOnClickListener(listener);
		downButton.setOnClickListener(listener);
		leftButton.setOnClickListener(listener);
		rightButton.setOnClickListener(listener);
	}
	
	private class MoveClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			if (v == upButton) {
				game.moveAvatar("Up");
			} else if (v == downButton) {
				game.moveAvatar("Down");
			} else if (v == leftButton) {
				game.moveAvatar("Left");
			} else if (v == rightButton) {
				game.moveAvatar("Right");
			}
			
			
		}
	}

	public StudyOrDieGameBoardView getGameBoardView() {
		return gameView;
	}

}
