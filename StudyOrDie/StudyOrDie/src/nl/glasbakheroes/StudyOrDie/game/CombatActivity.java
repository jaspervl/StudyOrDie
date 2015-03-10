package nl.glasbakheroes.StudyOrDie.game;

import nl.glasbakheroes.StudyOrDie.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Activity where the avatar battles a boss
 * @author enjee
 */
public class CombatActivity extends Activity {
	private Button btnBeatMe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_combat);
		
		btnBeatMe = (Button) findViewById(R.id.btnBeatMe);
		btnBeatMe.setOnClickListener(new ButtonListener());
	}
	
	/** Listener for buttons in the UI */
	private class ButtonListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			if (v == btnBeatMe) {
				killBoss();
			} 
		}	
	}
	
	/** Call this method when the back button is clicked */
	@Override
	public void onBackPressed() {
		/* Don't do anything, there is no escape from the fight! */
	}
	
	/** Kill the boss and return this result to the coreActivity */
	public void killBoss() {
		Intent resultIntent = new Intent();
		resultIntent.putExtra("bossDead", true);
		setResult(1, resultIntent);
		finish();
	}
	
	/**
	 * Kill the avatar by leaving the boss alive and passing that result to the core activity.
	 * Public because Views want to use this. (e.g. forfeit button in battle mode)
	 */
	public void killAvatar() {
		Intent resultIntent = new Intent();
		resultIntent.putExtra("BossDead", false);
		setResult(1, resultIntent);
		finish();
	}
}
