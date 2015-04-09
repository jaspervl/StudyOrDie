package nl.glasbakheroes.StudyOrDie.activities;



import java.util.Observable;
import java.util.Observer;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.Objects.Boss;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieGameBoard;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
import nl.glasbakheroes.StudyOrDie.view.BattleAttackOptionsView;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.GridLayout.Spec;

/**
 * Activity where the avatar battles a boss or janitor
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
	private MediaPlayer player;

	/** Will be called when the activity is created [also after it finished / got destroyed] */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* Remove title bar */
		player = MediaPlayer.create(getApplicationContext(), R.raw.battle);
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    /* Remove notification bar */
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_combat);
		player = MediaPlayer.create(getApplicationContext(), R.raw.battle);
		player.start();
		model = ((StudyOrDieApplication) getApplication()).getModel();
		model.addObserver(this);
		
		Bundle extras = getIntent().getExtras();
		String bossName = extras.getString("bossName"); 
		boss = model.getBoss(bossName);
		
		bossImage = (ImageView) findViewById(R.id.imgBattleAvatar);
		setBossImage();
	
		tvBossHP = (TextView) findViewById(R.id.tvBossHp);
		tvBossName = (TextView) findViewById(R.id.tvCombatBossName);
		barBossHp = (ProgressBar) findViewById(R.id.barBossHp);
		attackOptions = (BattleAttackOptionsView) findViewById(R.id.battleAttackOptionsView1);
		bossMaxHP = boss.getHP();
		tvBossHP.setText("HP: " + boss.getHP() + "/" + boss.getHP());
		barBossHp.setMax(boss.getHP());
		barBossHp.setProgress(boss.getHP());
		tvBossName.setText(bossName);
	}
	
	/** Set full size image in boss image imageView */
	private void setBossImage() {
		Resources resources = getApplicationContext().getResources();
		if (boss.getName().equals("Ruud")) {
			bossImage.setImageDrawable(resources.getDrawable(R.drawable.ruud_greven));
		} else if (boss.getName().equals("Frank")) {
			bossImage.setImageDrawable(resources.getDrawable(R.drawable.frank_van_doorn));
		} else if (boss.getName().equals("Tristan")) {
			bossImage.setImageDrawable(resources.getDrawable(R.drawable.tristan_pothoven));
		} else if (boss.getName().equals("Evert")) {
			bossImage.setImageDrawable(resources.getDrawable(R.drawable.evert_duipmans));
		} else if (boss.getName().equals("Syntaxis")) {
			bossImage.setImageDrawable(resources.getDrawable(R.drawable.syntaxis_voorzitter));
		} else if (boss.getName().equals("Jan")) {
			bossImage.setImageDrawable(resources.getDrawable(R.drawable.jan_stroet));
		}	else {
			bossImage.setImageDrawable(resources.getDrawable(R.drawable.concierge));
		}
	}

	/** Call this method when the back button is clicked */
	@Override
	public void onBackPressed() { 
		/* Don't do anything, there is no escape from the fight! */
	}
	
	/** Kill the boss and return this result to the coreActivity */
	public void killBoss() {
		model.raiseScore(10);
		model.addRandomItem();
		model.addItemToList(boss.getSpecialItem());
		model.update();
		boss.killBoss(); 
		model.getLoader().loadLevel("savedLocation");
		Toast.makeText(getApplicationContext(), boss.getName() + " has been defeated!", Toast.LENGTH_SHORT).show();
		delayedFinish();	 
	}
	 
	/**
	 * Kill the avatar by leaving the boss alive and passing that result to the core activity.
	 * Public because Views want to use this. (e.g. forfeit button in battle mode)
	 */
	public void killAvatar() {
		model.lowerScore(20);
		Intent gameOverIntent = new Intent(this, InformationActivity.class);
		gameOverIntent.putExtra("info", "Fail");
		startActivity(gameOverIntent);
		delayedFinish();
	}
	
	/** Finish the activity after two seconds to make sure the active events get time to finish (Toast) */
	private void delayedFinish() {
		handler.postDelayed(new Runnable() {
			public void run() {
				Intent returnCoreIntent = new Intent(CombatActivity.this, CoreActivity.class);
				returnCoreIntent.putExtra("result", "Dummy");
				setResult(StudyOrDieGameBoard.REQUEST_COMBAT_INTENT, returnCoreIntent);
				if (boss.getName().equals("Jan") && !boss.getAlive()) {
					Intent gameFinishIntent = new Intent(CombatActivity.this, InformationActivity.class);
					gameFinishIntent.putExtra("info", "win");
					startActivity(gameFinishIntent);
					model.getLoader().loadLevel("savedLocation");
				}
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
	
	/** Boss will attack and cast a random attack, chosen from 4 presets */
	private void bossAttack() {
		handler.postDelayed(new Runnable() {
			public void run() {
				String attackName = "";
				int damage = 0;
				int factor = (model.getLevel() / 10) +1;
				Log.w("Factor", factor + "");
				
				switch ((int)(Math.random() * 4) + 1) {
				case 1 : attackName = "Homework"; damage = 5 * factor ; break;
				case 2 : attackName = "Door policy"; damage = 3 * factor; break;
				case 3 : attackName = boss.getSpecialAttackName(); damage = boss.getSpecialAttackDamage(); break;
				case 4 : attackName = "Knowledge overload"; damage = 7 * factor; break;
				default : attackName = "No attack" ; damage = 0; break; 	// something went wrong if this occurs
				}
				
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

	/**
	 * Displays the bosses HP
	 */
	@Override
	public void update(Observable observable, Object data) {
		tvBossHP.setText("HP: " + boss.getHP() + "/" + bossMaxHP);
		barBossHp.setProgress(boss.getHP());
	}
	
	@Override
	protected void onDestroy() {
		player.stop();
		super.onPause();
	}
	
	public Boss getBoss() {
		return boss;
	}
}
