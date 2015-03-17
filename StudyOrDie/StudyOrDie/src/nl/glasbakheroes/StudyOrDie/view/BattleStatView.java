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
 * @author Niels Jan
 *
 */
public class BattleStatView extends LinearLayout implements Observer { 

	/** Instance variables */
	private ProgressBar barHP, barEnergy, barStat;
	private Avatar avatar;
	private CombatActivity activity;

	/** Constructors */
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
	
	/** Initialize method which will be called from every constructor */
	private void init() {
		if (!isInEditMode()) {
			/* Convert the views XML file into java-code */
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			inflater.inflate(R.layout.battle_stats_view, this);
		} else {
			setBackgroundColor(Color.CYAN);
		}
		
		/* Get the model from the application */
		StudyOrDieModel model = ((StudyOrDieApplication) getContext().getApplicationContext()).getModel();
		/* Add the view as a observer to the model */
		model.addObserver(this);
		
		/* Link the interface components to this class instance and retrieve the avatar from the model */
		avatar = ((StudyOrDieApplication) activity.getApplication()).getModel().getAvatar();
		barHP = (ProgressBar) findViewById(R.id.barHP);
		barEnergy = (ProgressBar) findViewById(R.id.barEnergy);
		barStat = (ProgressBar) findViewById(R.id.barStat);
		
		/* Fill the progressbars with relevant data */
		updateData();
	}
	
	/** Update will be called from the Observable class StudyOrDieModel */
	@Override
	public void update(Observable observable, Object data) {
		updateData();
	}
	
	/** Update all data that is dynamic (the progress bars) */
	private void updateData() {
		barHP.setMax(avatar.getMaxHP());
		barHP.setProgress(avatar.getCurrentHP());
		barEnergy.setMax(avatar.getMaxEnergy());
		barEnergy.setProgress(avatar.getCurrentEnergy());
		barStat.setMax(avatar.getMaxMotivation());
		barStat.setProgress(avatar.getCurrentMotivation());
	}
	
}
