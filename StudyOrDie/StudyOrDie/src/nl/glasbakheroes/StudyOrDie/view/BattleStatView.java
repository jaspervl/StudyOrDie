package nl.glasbakheroes.StudyOrDie.view;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.custom.Avatar;
import nl.glasbakheroes.StudyOrDie.game.CombatActivity;
import nl.glasbakheroes.StudyOrDie.game.CoreActivity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

/**
 * Battle stats UI component which visualize the Avatars status within a battle.
 * @author enjee
 *
 */
public class BattleStatView extends LinearLayout {

	private ProgressBar barHP, barEnergy, barStat;
	private Avatar avatar;

	public BattleStatView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}
	public BattleStatView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	public BattleStatView(Context context) {
		super(context);
		init();
	}
	private void init() {
		if (!isInEditMode()) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			inflater.inflate(R.layout.battle_stats_view, this);
		} else {
			setBackgroundColor(Color.CYAN);
		}

		barHP = (ProgressBar) findViewById(R.id.barHP);
		barEnergy = (ProgressBar) findViewById(R.id.barEnergy);
		barStat = (ProgressBar) findViewById(R.id.barStat);
		
		
//		CANT CAST BattleActivity TO CoreActivity...
//		Hoe de avatar hier krijgen?!?!
//		avatar = ((CoreActivity) getContext()).getGame().getLevelLoader().getAvatar();
//		
//		barHP.setMax(avatar.getMaxHP());
//		barHP.setProgress(avatar.getCurrentHP());
		
	}
	
}
