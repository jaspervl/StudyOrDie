package nl.glasbakheroes.StudyOrDie.game;

import nl.glasbakheroes.StudyOrDie.R;
import android.app.Activity;
import android.os.Bundle;

public class CombatActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_combat);
	}
	
	@Override
	public void onBackPressed() {
		/* Don't do anything, there is no escape from the fight! */
	}
}
