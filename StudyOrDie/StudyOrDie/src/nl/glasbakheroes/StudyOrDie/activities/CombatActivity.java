package nl.glasbakheroes.StudyOrDie.activities;



import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.Objects.Boss;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
import nl.glasbakheroes.StudyOrDie.view.BattleAttackOptionsView;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Surface;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Activity where the avatar battles a boss
 * @author enjee
 */
public class CombatActivity extends Activity {
	private ImageView bossImage;
	private Boss boss;
	private Handler handler = new Handler();
	private StudyOrDieModel model;
	private TextView tvBossHP;
	private int bossMaxHP;
	private BattleAttackOptionsView attackOptions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/* Remove title bar */
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    /* Remove notification bar */
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_combat);
		
		model = ((StudyOrDieApplication) getApplication()).getModel();
		Bundle extras = getIntent().getExtras();
		String bossImageId = extras.getString("BossImageId");
		String bossName = extras.getString("bossName"); 
		
		tvBossHP = (TextView) findViewById(R.id.tvBossHp);
		attackOptions = (BattleAttackOptionsView) findViewById(R.id.battleAttackOptionsView1);
		
		Log.w("Combat", bossName);
		boss = model.getBoss(bossName);
		bossMaxHP = boss.getHP();
		tvBossHP.setText("HP: " + boss.getHP() + "/" + boss.getHP());
		
	}
	
	/** Call this method when the back button is clicked */
	@Override
	public void onBackPressed() { 
		/* Don't do anything, there is no escape from the fight! */
	}
	
	/** Kill the boss and return this result to the coreActivity */
	public void killBoss() {
		boss.killBoss(); 
		model.getLoader().loadLevel("Boss");
		delayedFinish();	
	}
	 
	/**
	 * Kill the avatar by leaving the boss alive and passing that result to the core activity.
	 * Public because Views want to use this. (e.g. forfeit button in battle mode)
	 */
	public void killAvatar() {
		model.getLoader().setLevel(model.getLoader().getLevel() - 2);
		model.getLoader().loadLevel("Bottom");
		delayedFinish();
	}
	
	/** Finish the activity after a second to make sure the active events get time to finish (Toast) */
	private void delayedFinish() {
		handler.postDelayed(new Runnable() {
			public void run() {
				finish();
			}
		}, 1000);
	}
	
	/**
	 * Perform a attack against the saved boss
	 */
	public void performAttack(int damage) {
		
		boss.setHP(boss.getHP() - damage);
		if (boss.getHP() <= 0) {
			attackOptions.disableAll();
			boss.setHP(0);
			killBoss();
		}
		tvBossHP.setText("HP: " + boss.getHP() + "/" + bossMaxHP);
		
	}
}
