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

/**
 * Activity is called when the avatar has 0 health or (0 energy AND motivation)
 * 
 * @author EnJee
 */
public class GameOverActivity extends Activity {

	private Button btnRestart;
	
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
		btnRestart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/* Get the model and reset the timer */
				StudyOrDieModel model = ((StudyOrDieApplication) getApplication()).getModel();
				model.resetGame();
				
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
