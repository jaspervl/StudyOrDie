package nl.glasbakheroes.StudyOrDie.game;

import nl.glasbakheroes.StudyOrDie.R;
import android.app.Activity;
import android.os.Bundle;

public class CoreActivity extends Activity {
	
	private StudyOrDieGameBoardView gameView;
	private StudyOrDieGame game;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		gameView = (StudyOrDieGameBoardView) findViewById(R.id.studyOrDieGameBoardView1);
		game = new StudyOrDieGame(this);
	}

	public StudyOrDieGameBoardView getGameBoardView() {
		return gameView;
	}

}
