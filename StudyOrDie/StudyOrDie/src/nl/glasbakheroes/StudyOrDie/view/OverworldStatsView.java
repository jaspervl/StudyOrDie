package nl.glasbakheroes.StudyOrDie.view;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.activities.CombatActivity;
import nl.glasbakheroes.StudyOrDie.activities.CoreActivity;
import nl.glasbakheroes.StudyOrDie.custom.Avatar;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class OverworldStatsView extends LinearLayout {
	
	private ProgressBar barHP, barEnergy, barStat;
	private Avatar avatar;
	private CoreActivity activity;
	private TextView tvOverworldvatarName;

	public OverworldStatsView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		activity = (CoreActivity) context;
		init();
	}
	public OverworldStatsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		activity = (CoreActivity) context;
		init();
	}
	public OverworldStatsView(Context context) {
		super(context);
		activity = (CoreActivity) context;
		init();
	}
	private void init() {
		if (!isInEditMode()) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			inflater.inflate(R.layout.overworld_stats_view, this);
			avatar = ((StudyOrDieApplication) activity.getApplication()).getModel().getAvatar();
			barHP = (ProgressBar) findViewById(R.id.barOverWorldHP);
			barEnergy = (ProgressBar) findViewById(R.id.barOverWorldEnergy);
			barStat = (ProgressBar) findViewById(R.id.barOverWorldMotivation);
			setBackgroundColor(Color.BLACK);
			setAlpha(0.8F);
			barHP.setMax(avatar.getMaxHP()); 
			barHP.setProgress(avatar.getCurrentHP());
			tvOverworldvatarName = (TextView) findViewById(R.id.tvOverworldvatarName);
			StudyOrDieApplication app = (StudyOrDieApplication) activity.getApplication();
			tvOverworldvatarName.setText(app.getModel().getAvatar().getName());
		} else {
			setBackgroundColor(Color.CYAN);
		}		
	}
	
}
