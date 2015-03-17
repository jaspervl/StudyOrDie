package nl.glasbakheroes.StudyOrDie.view;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.activities.CombatActivity;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * A UI component which contains 6 attack buttons which represent 
 * 		different attacks the player can make.
 * @author enjee
 */
public class BattleAttackOptionsView extends LinearLayout {
	
	private Button btnAttack1, btnAttack2, btnAttack3, btnAttack4;
	private StudyOrDieModel model;

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
	private void init() {
		if (!isInEditMode()) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			inflater.inflate(R.layout.battle_attack_options_view, this);
		} else {
			setBackgroundColor(Color.BLUE);
		}
		
		model = ((StudyOrDieApplication) getContext().getApplicationContext()).getModel();
		
		btnAttack1 = (Button) findViewById(R.id.btnAttack1);
		btnAttack2 = (Button) findViewById(R.id.btnAttack2);
		btnAttack3 = (Button) findViewById(R.id.btnAttack3);
		btnAttack4 = (Button) findViewById(R.id.btnAttack4); 
		
		/* Define the attack buttons */
		btnAttack1.setText("Throw exception\nCost: -5E, -5M\n15 Damage");
		btnAttack2.setText("Hard question\nCost: -10E, +5M\n10 Damage");
		btnAttack3.setText("Apple talk\nCost: -3E, -5M\n12 Damage");
		btnAttack4.setText("Skip class\nCost: +5E, -10M\n10 Damage");
		
		ButtonListener listener = new ButtonListener();
		btnAttack1.setOnClickListener(listener);
		btnAttack2.setOnClickListener(listener); 
		btnAttack3.setOnClickListener(listener);
		btnAttack4.setOnClickListener(listener);
	}
	
	private class ButtonListener implements OnClickListener {

		String attackName = "";
		int damage = 0;
		int energyModifier = 0;
		int motivationModifier = 0;
		CombatActivity activity = (CombatActivity) getContext();
		
		@Override
		public void onClick(View v) {
			if (v == btnAttack1) {
				attackName = "Throw exception";
				damage = 15;
				energyModifier = -5;
				motivationModifier = -5;
			} else if (v == btnAttack2) {
				attackName = "Hard question";
				damage = 10;
				energyModifier = -10;
				motivationModifier = +5;
			} else if (v == btnAttack3) {
				attackName = "Apple talk";
				damage = 12;
				energyModifier = -3;
				motivationModifier = -5;
			} else if (v == btnAttack4) {
				attackName = "Skip class";
				damage = 10;
				energyModifier = +5;
				motivationModifier = -10;
			}
			
			model.getAvatar().setCurrentEnergy(model.getAvatar().getCurrentEnergy() + energyModifier);
			model.getAvatar().setCurrentMotivation(model.getAvatar().getCurrentMotivation() + motivationModifier);
			activity.performAttack(damage);
			Toast.makeText(activity, "Attack casted: " + attackName, Toast.LENGTH_SHORT).show();
		}
	}

	public void disableAll() {
		btnAttack1.setOnClickListener(null);
		btnAttack2.setOnClickListener(null);
		btnAttack3.setOnClickListener(null);
		btnAttack4.setOnClickListener(null);
	}

}
