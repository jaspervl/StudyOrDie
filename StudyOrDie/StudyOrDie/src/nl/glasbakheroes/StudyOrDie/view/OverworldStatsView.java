package nl.glasbakheroes.StudyOrDie.view;

import java.util.Observable;
import java.util.Observer;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.activities.CoreActivity;
import nl.glasbakheroes.StudyOrDie.custom.Avatar;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class OverworldStatsView extends LinearLayout implements Observer {
	
	private ProgressBar barHP, barEnergy, barStat;
	private Avatar avatar;
	private CoreActivity activity;
	private TextView tvOverworldvatarName, tvHP, tvEnergy, tvMotivation;
	private ImageView ivAvatarImage;

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
			tvHP = (TextView) findViewById(R.id.tvOverWorldHP);
			tvEnergy = (TextView) findViewById(R.id.tvOverWorldEnergy);
			tvMotivation = (TextView) findViewById(R.id.tvOverWorldMotivation);
			ivAvatarImage = (ImageView) findViewById(R.id.ivOverWorldAvatar);
			
			setBackgroundColor(Color.BLACK);
			setAlpha(0.8F);
			updateData();
			tvOverworldvatarName = (TextView) findViewById(R.id.tvOverworldvatarName);
			StudyOrDieApplication app = (StudyOrDieApplication) activity.getApplication();
			tvOverworldvatarName.setText(app.getModel().getAvatar().getName());
		} else {
			setBackgroundColor(Color.CYAN);
		}		
	}
	
	public void setMinimize(boolean minimal) {
		if (minimal) {
			tvHP.setText("H");
			tvEnergy.setText("E");
			tvMotivation.setText("M");
			ivAvatarImage.setAlpha(0F);
			this.setLayoutParams(new RelativeLayout.LayoutParams(200,200));
		} else {
			tvHP.setText("Health");
			tvEnergy.setText("Energy");
			tvMotivation.setText("Motivation");
			ivAvatarImage.setAlpha(0.9F);
			this.setLayoutParams(new RelativeLayout.LayoutParams(600,200));
		}
	}
	@Override
	public void update(Observable observable, Object data) {
		updateData();
	}
	
	public void updateData() {
		barHP.setMax(avatar.getMaxHP());
		barHP.setProgress(avatar.getCurrentHP());
		barEnergy.setMax(avatar.getMaxEnergy());
		barEnergy.setProgress(avatar.getCurrentEnergy());
		barStat.setMax(avatar.getMaxMotivation());
		barStat.setProgress(avatar.getCurrentMotivation());
	}
	
	
}
