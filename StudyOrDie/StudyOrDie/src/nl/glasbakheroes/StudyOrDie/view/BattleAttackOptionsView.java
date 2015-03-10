package nl.glasbakheroes.StudyOrDie.view;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.game.CombatActivity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * A UI component which contains 6 attack buttons which represent 
 * 		different attacks the player can make.
 * @author enjee
 */
public class BattleAttackOptionsView extends LinearLayout {
	
	private Button btnAttack1, btnAttack2, btnAttack3, btnAttack4, btnAttack5, btnAttack6;

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
		btnAttack1 = (Button) findViewById(R.id.btnAttack1);
		btnAttack2 = (Button) findViewById(R.id.btnAttack2);
		btnAttack3 = (Button) findViewById(R.id.btnAttack3);
		btnAttack4 = (Button) findViewById(R.id.btnAttack4);
		btnAttack5 = (Button) findViewById(R.id.btnAttack5);
		btnAttack6 = (Button) findViewById(R.id.btnAttack6); 
		
		ButtonListener listener = new ButtonListener();
		btnAttack1.setOnClickListener(listener);
		btnAttack2.setOnClickListener(listener);
		btnAttack3.setOnClickListener(listener);
		btnAttack4.setOnClickListener(listener);
		btnAttack5.setOnClickListener(listener);
		btnAttack6.setOnClickListener(listener);
	}
	
	private class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			CombatActivity activity = (CombatActivity) getContext();
			activity.killBoss();
		}
		
	}

}
