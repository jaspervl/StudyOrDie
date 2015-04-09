package nl.glasbakheroes.StudyOrDie.view;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.Objects.Boss;
import nl.glasbakheroes.StudyOrDie.activities.CombatActivity;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * A UI component which contains 6 attack buttons which represent 
 * 		different attacks the player can make.
 * @author Niels Jan
 */
public class BattleAttackOptionsView extends LinearLayout {
	
	/** Instance variables */
	private Button btnAttack1, btnAttack2, btnAttack3, btnAttack4;
	private StudyOrDieModel model;
	private String addToMessage = "";

	/** Constructors */
	public BattleAttackOptionsView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}
	public BattleAttackOptionsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	public BattleAttackOptionsView(Context context) {
		super(context);
		init();
	}
	
	/** Initialize method which will be called from every constructor */
	private void init() {
		if (!isInEditMode()) {
			/* Convert the XML view into java-code */
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			inflater.inflate(R.layout.battle_attack_options_view, this);
		} else {
			setBackgroundColor(Color.BLUE);
		}
		
		/* Retrieve the model from the application */
		model = ((StudyOrDieApplication) getContext().getApplicationContext()).getModel();
		
		/* Link all buttons to the interface */
		btnAttack1 = (Button) findViewById(R.id.btnAttack1);
		btnAttack2 = (Button) findViewById(R.id.btnAttack2);
		btnAttack3 = (Button) findViewById(R.id.btnAttack3);
		btnAttack4 = (Button) findViewById(R.id.btnAttack4); 
		disableAllButtons();
		
		/* Enable all buttons 2 seconds after creating this view */
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				enableAllButtons();
			}
		}, 2000);
		
	}

	
	/** Listens for button presses */
	private class ButtonListener implements OnClickListener {

		/* Initialize the local variables */
		String attackName = "";
		int damage = 0;
		int energyModifier = 0;
		int motivationModifier = 0;
		
		/* Get the activity where this view is called from */
		CombatActivity activity = (CombatActivity) getContext();
		
		/* Set the local variables according to the value of the button that is clicked */
		@Override
		public void onClick(View v) {
			int plusE = 0;
			int plusM = 0;
			Boss boss = activity.getBoss();
			addToMessage = "";
			if (v == btnAttack1) {
				attackName = "Throw exception";
				damage = 20; 	// 100 for testing 
				energyModifier = -5;
				motivationModifier = -5;
				if (boss.getWeakness().equals("Throw exception")) {
					damage = (int) (damage * 1.2);
					addToMessage = ", it is SUPER effective!";
				}
			} else if (v == btnAttack2) {
				attackName = "Hard question";
				damage = 10;
				energyModifier = -10;
				motivationModifier = +5;
				plusM = 5;
				if (boss.getWeakness().equals("Hard question")) {
					damage = (int) (damage * 1.2);
					addToMessage = ", it is SUPER effective!";
				}
			} else if (v == btnAttack3) {
				attackName = "Apple talk";
				damage = 12;
				energyModifier = -3;
				motivationModifier = -5;
				if (boss.getWeakness().equals("Apple talk")) {
					damage = (int) (damage * 1.2);
					addToMessage = ", it is SUPER effective!";
				}
			} else if (v == btnAttack4) {
				attackName = "Skip class";
				damage = 10;
				energyModifier = +5;
				plusE = 5;
				motivationModifier = -10;
				if (boss.getWeakness().equals("Skip class")) {
					damage = (int) (damage * 2);
					addToMessage = ", it is SUPER effective!";
				}
			}
			
			/* Modify the avatar and the boss (through a activity method) according to the button that is clicked */
			model.getAvatar().setCurrentEnergy(model.getAvatar().getCurrentEnergy() + energyModifier);
			model.getAvatar().setCurrentMotivation(model.getAvatar().getCurrentMotivation() + motivationModifier);
			if (model.getAvatar().getCurrentEnergy() <= 1 || model.getAvatar().getCurrentMotivation() <= 1) {
				model.getAvatar().setCurrentMotivation(model.getAvatar().getCurrentMotivation() - plusM);
				model.getAvatar().setCurrentEnergy(model.getAvatar().getCurrentEnergy() - plusE);
				Toast.makeText(activity, "Attack failed! not enough energy or motivation", Toast.LENGTH_SHORT).show();
				return;
			} else {
				Toast.makeText(activity, "Attack casted: " + attackName + addToMessage, Toast.LENGTH_SHORT).show();
				
				
				activity.performAttack(damage);
			}
		}
	}

	/** Disable all buttons while the conditions require them to be disabled */
	public void disableAllButtons() {
		btnAttack1.setOnClickListener(null);
		btnAttack2.setOnClickListener(null);
		btnAttack3.setOnClickListener(null);
		btnAttack4.setOnClickListener(null);
	}
	
	/** Enable all buttons again, will be called if it's the players turn and neither the boss or the player lost all their HP */
	public void enableAllButtons() {
		ButtonListener listener = new ButtonListener();
		btnAttack1.setOnClickListener(listener);
		btnAttack2.setOnClickListener(listener); 
		btnAttack3.setOnClickListener(listener);
		btnAttack4.setOnClickListener(listener);
	}

}
