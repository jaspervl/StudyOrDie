package nl.glasbakheroes.StudyOrDie.activities;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Activity is called when the avatar has 0 health or (0 energy AND motivation)
 * 
 * @author EnJee
 */
public class GameOverActivity extends Activity {

	private Button btnRestart;
	private TextView tvScore;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/* Remove title bar */
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    /* Remove notification bar */
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_game_over);	
		
		
		/* Link the interface button so it can be clicked
		 * When the button is clicked, finish the activity */
		btnRestart = (Button) findViewById(R.id.btnRestart);
		tvScore = (TextView) findViewById(R.id.tvScoreValue);
		 
		StudyOrDieModel model = ((StudyOrDieApplication) getApplication()).getModel();
		
		int stepScore = 0;
		int timeScore = 0;

		stepScore = (int) ((1000.0 / model.getSteps()) * Math.pow(model.getNumberOpenedLevels(), 3));
		timeScore = (int) ((1000.0 / model.getTime()) * Math.pow(model.getNumberOpenedLevels(), 3));
		
		model.raiseScore(stepScore);
		model.raiseScore(timeScore);
		
		tvScore.setText(model.getScore() + "");
		btnRestart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/* Get the model and reset the timer */
				StudyOrDieModel model = ((StudyOrDieApplication) getApplication()).getModel();
				model.spawnAfterFail();
				finish();
			}
		});
	}
	
	/** Call this method when the back button is clicked */
	@Override
	public void onBackPressed() { 
		/* Don't do anything, there is no escape from the fight! */
	}
	
}
