package nl.glasbakheroes.StudyOrDie.game;

import nl.glasbakheroes.StudyOrDie.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CombatActivity extends Activity {
	private Button btnBeatMe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_combat);
		
		btnBeatMe = (Button) findViewById(R.id.btnBeatMe);
		btnBeatMe.setOnClickListener(new ButtonListener());
	}
	
	private class ButtonListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			if (v == btnBeatMe) {
				killBoss();
			} 
		}
		
	}
	
	@Override
	public void onBackPressed() {
		/* Don't do anything, there is no escape from the fight! */
	}
	
	private void killBoss() {
		Intent resultIntent = new Intent();
		resultIntent.putExtra("bossDead", true);
		setResult(1, resultIntent);
		finish();
	}
	
	private void killAvatar() {
		Intent resultIntent = new Intent();
		resultIntent.putExtra("BossDead", false);
		setResult(1, resultIntent);
		finish();
	}
}
