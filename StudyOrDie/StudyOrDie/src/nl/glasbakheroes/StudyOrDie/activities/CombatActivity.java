package nl.glasbakheroes.StudyOrDie.activities;



import java.util.Observable;
import java.util.Observer;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.Objects.Boss;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
import nl.glasbakheroes.StudyOrDie.view.BattleAttackOptionsView;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity where the avatar battles a boss
 * @author Niels Jan
 */
public class CombatActivity extends Activity implements Observer {
	
	/** Instance variables */
	private ImageView bossImage;
	private Boss boss;
	private Handler handler = new Handler();
	private StudyOrDieModel model;
	private TextView tvBossHP, tvBossName;
	private int bossMaxHP;
	private BattleAttackOptionsView attackOptions;
	private ProgressBar barBossHp;

	/** Will be called when the activity is created [also after it finished / got destroyed] */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/* Remove title bar */
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    /* Remove notification bar */
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_combat);
		
		model = ((StudyOrDieApplication) getApplication()).getModel();
		model.addObserver(this);
		
		Bundle extras = getIntent().getExtras();
		String bossImageId = extras.getString("BossImageId");
		String bossName = extras.getString("bossName"); 
		
		tvBossHP = (TextView) findViewById(R.id.tvBossHp);
		tvBossName = (TextView) findViewById(R.id.tvCombatBossName);
		barBossHp = (ProgressBar) findViewById(R.id.barBossHp);
		attackOptions = (BattleAttackOptionsView) findViewById(R.id.battleAttackOptionsView1);
		Log.w("Combat", bossName);
		boss = model.getBoss(bossName);
		bossMaxHP = boss.getHP();
		tvBossHP.setText("HP: " + boss.getHP() + "/" + boss.getHP());
		barBossHp.setMax(boss.getHP());
		barBossHp.setProgress(boss.getHP());
		tvBossName.setText(bossName);
		
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
		Toast.makeText(getApplicationContext(), boss.getName() + " has been defeated!", Toast.LENGTH_SHORT).show();
		delayedFinish();	
	}
	 
	/**
	 * Kill the avatar by leaving the boss alive and passing that result to the core activity.
	 * Public because Views want to use this. (e.g. forfeit button in battle mode)
	 */
	public void killAvatar() {
		model.getLoader().setLevel(model.getLoader().getLevel() - 2);
		model.getLoader().loadLevel("Bottom");
		Toast.makeText(getApplicationContext(), "You have been defeated!", Toast.LENGTH_SHORT).show();
		delayedFinish();
	}
	
	/** Finish the activity after two seconds to make sure the active events get time to finish (Toast) */
	private void delayedFinish() {
		handler.postDelayed(new Runnable() {
			public void run() {
				finish();
			}
		}, 2000);
	}
	
	/**
	 * Perform a attack against the saved boss
	 */
	public void performAttack(int damage) {
		attackOptions.disableAllButtons();		
		boss.setHP(boss.getHP() - damage);
		if (boss.getHP() <= 0) {
			attackOptions.disableAllButtons();
			boss.setHP(0);
			killBoss();
		}
		if (boss.getHP() > 0 && model.getAvatar().getCurrentHP() > 0) {
			bossAttack();
		}
		
	}
	
	private void bossAttack() {
		handler.postDelayed(new Runnable() {
			public void run() {
				String attackName = "Homework";
				int damage = 7;
				Toast.makeText(getApplicationContext(), boss.getName() + " attacks with " + attackName + " and does " + damage + " damage!", Toast.LENGTH_SHORT).show();
				model.getAvatar().setCurrentHP(model.getAvatar().getCurrentHP() - damage);
				handler.postDelayed(new Runnable() {
					public void run() {
						if (model.getAvatar().getCurrentHP() <= 0) {
							killAvatar();
						} else {
							attackOptions.enableAllButtons();
						}
					}
				}, 3000);
			}
		}, 3000);
	}

	@Override
	public void update(Observable observable, Object data) {
		tvBossHP.setText("HP: " + boss.getHP() + "/" + bossMaxHP);
		barBossHp.setProgress(boss.getHP());
	}
}
