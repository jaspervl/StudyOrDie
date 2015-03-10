package nl.glasbakheroes.StudyOrDie.view;

import nl.glasbakheroes.StudyOrDie.R;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class BattleStatView extends LinearLayout {

	private ProgressBar barHP, barEnergy, barStat;

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
		
	}
	
}
