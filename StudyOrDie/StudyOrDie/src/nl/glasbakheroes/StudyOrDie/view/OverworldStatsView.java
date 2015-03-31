package nl.glasbakheroes.StudyOrDie.view;

import java.util.Observable;
import java.util.Observer;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.activities.CoreActivity;
import nl.glasbakheroes.StudyOrDie.custom.Avatar;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * A view that contains the stats of the Avatar.
 * Shown in the overworld.
 * 
 * @author Niels Jan
 */
public class OverworldStatsView extends LinearLayout implements Observer {
	
	/** Instance variables */
	private ProgressBar barHP, barEnergy, barStat;
	private Avatar avatar;
	private CoreActivity activity;
	private TextView tvOverworldAvatarName, tvHP, tvEnergy, tvMotivation, tvTime, tvSteps;
	private ImageView ivAvatarImage;
	private StudyOrDieModel model;

	/** Constructors */
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
	
	/** Init method which will be called from all of the constructors */
	private void init() {
		if (!isInEditMode()) {
			/* Convert the views XML into java-code */
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			inflater.inflate(R.layout.overworld_stats_view, this);
			
			/* Retrieve the application and get the model from it */
			StudyOrDieApplication app = (StudyOrDieApplication) activity.getApplication();
			model = app.getModel();
			model.addObserver(this);
			
			/* Set / link the instance variables */
			avatar = ((StudyOrDieApplication) activity.getApplication()).getModel().getAvatar();
			barHP = (ProgressBar) findViewById(R.id.barOverWorldHP);
			barEnergy = (ProgressBar) findViewById(R.id.barOverWorldEnergy);
			barStat = (ProgressBar) findViewById(R.id.barOverWorldMotivation);
			tvHP = (TextView) findViewById(R.id.tvOverWorldHP);
			tvEnergy = (TextView) findViewById(R.id.tvOverWorldEnergy);
			tvMotivation = (TextView) findViewById(R.id.tvOverWorldMotivation);
			tvSteps = (TextView) findViewById(R.id.tvAmountOfSteps);
			tvTime = (TextView) findViewById(R.id.tvTimePassed);
			ivAvatarImage = (ImageView) findViewById(R.id.ivOverWorldAvatar);
			tvOverworldAvatarName = (TextView) findViewById(R.id.tvOverworldvatarName);
			
			tvOverworldAvatarName.setText(model.getAvatar().getName()); 
			StudyOrDieModel model = ((StudyOrDieApplication)getContext().getApplicationContext()).getModel();
			ivAvatarImage.setImageBitmap(SpriteCache.getInstance().get(model.getAvatar().getImageId()));

			/* Put the relevant data inside the interface components */
			updateData();
		} else {
			setBackgroundColor(Color.CYAN);
		}		
	}
	
	/** Make the overworld view small or full-size */
	public void setMinimize(boolean minimal) {
		if (minimal) {
			this.setLayoutParams(new RelativeLayout.LayoutParams(0,200));
		} else {
			/* Set the view to its full size */
			tvHP.setText("Health");
			tvEnergy.setText("Energy");
			tvMotivation.setText("Motivation");
			this.setLayoutParams(new RelativeLayout.LayoutParams(600,200));
		}
	}
	
	/** Observer method, call the helper method if data should be updated */
	@Override
	public void update(Observable observable, Object data) {
		updateData();
	}
	
	/** Put the current data into the interface components */
	public void updateData() {
		tvOverworldAvatarName.setText(model.getAvatar().getName());
		barHP.setMax(avatar.getMaxHP());
		barHP.setProgress(avatar.getCurrentHP());
		barEnergy.setMax(avatar.getMaxEnergy());
		barEnergy.setProgress(avatar.getCurrentEnergy());
		barStat.setMax(avatar.getMaxMotivation());
		barStat.setProgress(avatar.getCurrentMotivation());
		tvSteps.setText("Steps taken: " + model.getSteps());
		tvTime.setText("Time passed: " + model.getTime());
		this.setAlpha(0.9F);
	}
	
	/** Method which will be called from the CoreActivity, sets the Avatar name that will be displayed. */
	public void setDisplayName(String name) {
		this.tvOverworldAvatarName.setText(name);
	}
}
