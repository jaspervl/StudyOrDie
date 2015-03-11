package nl.glasbakheroes.StudyOrDie.activities;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.Objects.Boss;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieGameBoard;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Activity where the avatar battles a boss
 * @author enjee
 */
public class CombatActivity extends Activity {
	private ImageView bossImage;
	public static final int RESULT_COREACTIVITY_CODE = StudyOrDieGameBoard.REQUEST_COMBAT_CODE;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_combat);
		
		Bundle extras = getIntent().getExtras();
		String bossImageId = extras.getString("BossImageId");
		
		// Get bosses from model later on when model has a boss array.
		
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
		setResult(RESULT_COREACTIVITY_CODE, resultIntent);
		finish();
	}
	
	/**
	 * Kill the avatar by leaving the boss alive and passing that result to the core activity.
	 * Public because Views want to use this. (e.g. forfeit button in battle mode)
	 */
	public void killAvatar() {
		Intent resultIntent = new Intent();
		resultIntent.putExtra("BossDead", false);
		setResult(RESULT_COREACTIVITY_CODE, resultIntent);
		finish();
	}
}
