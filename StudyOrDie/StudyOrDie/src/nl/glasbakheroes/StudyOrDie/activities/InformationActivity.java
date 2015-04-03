package nl.glasbakheroes.StudyOrDie.activities;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Activity is called when the avatar has 0 health or (0 energy AND motivation)
 * 
 * @author EnJee
 */
public class InformationActivity extends Activity {

	private Button btnRestart;
	private TextView tvScore;
	private String info;
	private TextView tvScoreLabel;
	private TextView tvFail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/* Remove title bar */
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    /* Remove notification bar */
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_information);	
		
		/* Link the interface buttons */
		btnRestart = (Button) findViewById(R.id.btnRestart);
		tvScore = (TextView) findViewById(R.id.tvScoreValue);
		tvScoreLabel = (TextView) findViewById(R.id.tvScoreLabel);
		tvFail = (TextView) findViewById(R.id.tvGameOver);

		StudyOrDieModel model = ((StudyOrDieApplication) getApplication()).getModel();
		
		/* Get intent information */
		info = getIntent().getStringExtra("info");
		
		if (info.equals("Fail")) {

			/* Re-arrange the interface */
			btnRestart.setText("Spawn at start");
			tvScore.setTextSize(25);
			tvScoreLabel.setVisibility(View.VISIBLE);
			tvFail.setVisibility(View.VISIBLE);
			
			/* Calculate score from steps and time */
			int stepScore = (int) ((1000.0 / model.getSteps()) * Math.pow(model.getNumberOpenedLevels(), 3));
			int timeScore = (int) ((1000.0 / model.getTime()) * Math.pow(model.getNumberOpenedLevels(), 3));
			/* Raise the score by the adequate value */
			model.raiseScore(stepScore);
			model.raiseScore(timeScore);
		
			tvScore.setText(model.getScore() + "");
			
		} else if (info.equals("Story")) {
			
			/* Re-arrange the interface */
			btnRestart.setText("Skip");
			tvScore.setTextSize(14);
			tvScoreLabel.setVisibility(View.GONE);
			tvFail.setVisibility(View.GONE);
			
			switch (model.getLevel() / 10) {
				case 0: tvScore.setText(
					"Goedemorgen " +  model.getAvatar().getName() + ", je bent wakker geworden na een hoorcollege Creatief Probleem Oplossen." +
					"Iedereen is al weg, en je hebt de helft van de uitleg gemist doordat je in slaap bent gevallen.\n" +
					"Omdat je graag het tentamen wil halen, ga je op zoek naar Frank om meer uitleg te vragen.\n" +
					"Het probleem is alleen dat er conciërges van het Saxion bezig zijn met het herinrichten van het gebouw," +
					" daarbij kunnen ze geen studenten gebruiken. Ze proberen je dus constant weg te jagen. Versla de conciërges en vind Frank!\n" +
					"Gelukkig is de studiefinanciering vandaag weer binnen gekomen, en hebben ze een nieuwe automaat neergezet bij de infobalie. " +
					"Hierin zitten voorwerpen die je kunnen helpen om wakker en gemotiveerd te blijven.\n" +
					"Helaas zit de deur hiervoor op slot, zoek dus eerst de sleutel voor de deur. Veel succes!"); break;
					
				case 1:  tvScore.setText(
					"Het volgende vak wat op de planning staat is Mobile Computing. Door je zoektocht naar Frank ben je te laat voor de les, " +
					"en de deur zit dicht. Door Tristans deurbeleid mag je er niet zomaar meer in.\n" +
					"Nu moet je dus een manier zoeken om er toch in te komen, en daarna sta je tegenover hem! " +
					"We weten nog niet echt wat er gebeurt als je uiteindelijk tegenover Tristan staat, " +
					"hij heeft er waarschijnlijk weinig zin in om je er nog aan mee te laten doen.\n" +
					"Ondertussen zijn ook hier de conciërges bezig met hun werk, zorg dat je ze niet in de weg loopt! Veel succes!"); break;
					
				case 2: 
					tvScore.setText(""); break;
					
				case 3: 
					tvScore.setText(""); break;
					
				case 4: 
					tvScore.setText(""); break;
			
			} 
		
		}
		
		
		btnRestart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (info.equals("Fail")) {
					StudyOrDieModel model = ((StudyOrDieApplication) getApplication()).getModel();
					model.spawnAfterFail();
					finish();
				} else if (info.equals("Story")) {
					finish();
				}
			}
		});
	}

	/** Call this method when the back button is clicked */
	@Override
	public void onBackPressed() {
		/* Don't do anything, there is no escape from the fight! */
	}

}
