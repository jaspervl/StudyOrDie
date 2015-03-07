package nl.glasbakheroes.StudyOrDie.game;

import nl.glasbakheroes.StudyOrDie.R;
import android.app.Activity;
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
			finish();
		}
		
	}
	
	@Override
	public void onBackPressed() {
		/* Don't do anything, there is no escape from the fight! */
	}
}
