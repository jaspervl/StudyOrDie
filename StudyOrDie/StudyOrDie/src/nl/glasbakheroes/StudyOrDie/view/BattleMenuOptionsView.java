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

public class BattleMenuOptionsView extends LinearLayout {
	private Button btnItems, btnCharacter, btnMenu, btnForfeit;

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
		btnMenu = (Button) findViewById(R.id.btnBattleMenu);
		
		ButtonListener listener = new ButtonListener();
		btnForfeit.setOnClickListener(listener);
	}
	
	private class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			if (v == btnForfeit) {
				CombatActivity activity = (CombatActivity) getContext();
				activity.killAvatar();
			}
		}
		
	}
	
}
