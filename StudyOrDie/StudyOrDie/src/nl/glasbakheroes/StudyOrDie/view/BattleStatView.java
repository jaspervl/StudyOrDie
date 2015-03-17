package nl.glasbakheroes.StudyOrDie.view;

import java.util.Observable;
import java.util.Observer;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.activities.CombatActivity;
import nl.glasbakheroes.StudyOrDie.custom.Avatar;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
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
public class BattleStatView extends LinearLayout implements Observer { 

	private ProgressBar barHP, barEnergy, barStat;
	private Avatar avatar;
	private CombatActivity activity;

	public BattleStatView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		activity = (CombatActivity) context;
		init();
	}
	public BattleStatView(Context context, AttributeSet attrs) {
		super(context, attrs);
		activity = (CombatActivity) context;
		init();
	}
	public BattleStatView(Context context) {
		super(context);
		activity = (CombatActivity) context;
		init();
	}
	private void init() {
		if (!isInEditMode()) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			inflater.inflate(R.layout.battle_stats_view, this);
		} else {
			setBackgroundColor(Color.CYAN);
		}
		
		StudyOrDieModel model = ((StudyOrDieApplication) getContext().getApplicationContext()).getModel();
		model.addObserver(this);
		
		avatar = ((StudyOrDieApplication) activity.getApplication()).getModel().getAvatar();
		barHP = (ProgressBar) findViewById(R.id.barHP);
		barEnergy = (ProgressBar) findViewById(R.id.barEnergy);
		barStat = (ProgressBar) findViewById(R.id.barStat);
		
		updateData();
		
	}
	@Override
	public void update(Observable observable, Object data) {
		updateData();
	}
	
	private void updateData() {
		barHP.setMax(avatar.getMaxHP());
		barHP.setProgress(avatar.getCurrentHP());
		barEnergy.setMax(avatar.getMaxEnergy());
		barEnergy.setProgress(avatar.getCurrentEnergy());
		barStat.setMax(avatar.getMaxMotivation());
		barStat.setProgress(avatar.getCurrentMotivation());
	}
	
}
