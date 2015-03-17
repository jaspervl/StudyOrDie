package nl.glasbakheroes.StudyOrDie.activities;



import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Surface;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Activity where the avatar battles a boss
 * @author enjee
 */
public class CombatActivity extends Activity {
	private ImageView bossImage;
	private String bossName;
	private Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/* Remove title bar */
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    /* Remove notification bar */
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_combat);
		
		Bundle extras = getIntent().getExtras();
		String bossImageId = extras.getString("BossImageId");
		bossName = extras.getString("bossName"); 
		
		Log.w("Combat", bossName);
		
	}
	
	/** Call this method when the back button is clicked */
	@Override
	public void onBackPressed() {
		/* Don't do anything, there is no escape from the fight! */
	}
	
	/** Kill the boss and return this result to the coreActivity */
	public void killBoss() {
		StudyOrDieApplication app = (StudyOrDieApplication) getApplication();
		app.getModel().getBoss(bossName).killBoss();
		app.getModel().getLoader().loadLevel("Boss");
		delayedFinish();	
	}
	 
	/**
	 * Kill the avatar by leaving the boss alive and passing that result to the core activity.
	 * Public because Views want to use this. (e.g. forfeit button in battle mode)
	 */
	public void killAvatar() {
		StudyOrDieApplication app = (StudyOrDieApplication) getApplication();
		app.getModel().getLoader().setLevel(app.getModel().getLoader().getLevel() - 2);
		app.getModel().getLoader().loadLevel("Bottom");
		delayedFinish();
	}
	
	/** Finish the activity after a second to make sure the active events get time to finish (Toast) */
	private void delayedFinish() {
		handler.postDelayed(new Runnable() {
			public void run() {
				finish();
			}
		}, 2000);
	}
}
