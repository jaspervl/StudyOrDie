package nl.glasbakheroes.StudyOrDie.view;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.activities.CombatActivity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * A UI component which contains all the menu options accessible from within a battle.
 * @author enjee
 */
public class BattleMenuOptionsView extends LinearLayout {
	private Button btnItems, btnCharacter, btnForfeit;

	public BattleMenuOptionsView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}
	public BattleMenuOptionsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	public BattleMenuOptionsView(Context context) {
		super(context);
		init();
	}
	private void init() {
		if (!isInEditMode()) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			inflater.inflate(R.layout.battle_menu_options_view, this);
		} else {
			setBackgroundColor(Color.GREEN);
		}
		btnItems = (Button) findViewById(R.id.btnBattleItems);
		btnCharacter = (Button) findViewById(R.id.btnBattleCharacter);
		btnForfeit = (Button) findViewById(R.id.btnForfeit);
		
		ButtonListener listener = new ButtonListener();
		btnForfeit.setOnClickListener(listener);
	}
	
	private class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			if (v == btnForfeit) {
				CombatActivity activity = (CombatActivity) getContext();
				activity.killAvatar();
			} else if (v == btnItems) {
				
				// intent to open menu with intventory HERE!
			
			}
		}
		
	}
	
}
