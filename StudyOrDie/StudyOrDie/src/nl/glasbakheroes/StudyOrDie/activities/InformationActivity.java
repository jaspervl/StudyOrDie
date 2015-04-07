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
	private TextView tvTitle;

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
		tvTitle = (TextView) findViewById(R.id.tvGameOver);

		final StudyOrDieModel model = ((StudyOrDieApplication) getApplication()).getModel();
		
		/* Get intent information */
		info = getIntent().getStringExtra("info");
		
		if (info.equals("Fail")) {

			/* Re-arrange the interface */
			btnRestart.setText("Spawn at start");
			tvScore.setTextSize(25);
			tvScoreLabel.setVisibility(View.VISIBLE);
			tvScoreLabel.setText("Score: ");
			tvTitle.setVisibility(View.VISIBLE);
			tvTitle.setText("You failed!");
			
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
			tvTitle.setVisibility(View.GONE);
			
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
					
				case 2: tvScore.setText(
					"Je hebt een tussenuur en je komt er met de opdracht van Introductie Programmeren nog niet helemaal uit, " +
					"je hebt hulp nodig.\n Hiervoor ga je Ruud opzoeken. Je hebt het net aan Tristan gevraagd, en hij zei dat Ruud " +
					"zich ergens op de 2e verdieping bevindt.\n Het blijkt nog een aardige zoektocht te worden," +
					" succes en pas wederom op voor de conciërges!"); break;
					
				case 3:	tvScore.setText(
					"Ruud had blijkbaar geen tijd om je te helpen, maar hij vertelde je dat Evert een verdieping hoger zit " +
					"en dat hij waarschijnlijk wel tijd heeft.\n Hij wist alleen niet precies waar. \n" +
					"Omdat je er zelf echt niet uit komt en je toch graag de opdrachten af wil maken ga je maar weer op zoek, " +
					"in de hoop dat Evert je wel kan helpen.\n Ondertussen ben je er eigenlijk ook wel een beetje klaar mee, " +
					"zodra je dit hebt gedaan wordt het tijd voor koffie!"); break;
					
				case 4:	tvScore.setText(
					"Gelukkig, Evert heeft je geholpen en je kan weer verder met je programmeerwerk. \n" +
					"Maar eerst is het tijd om even pauze te houden. Hier op de 4e verdieping zit studievereniging Syntaxis " +
					"en omdat je lid bent mag je hier gratis koffie halen.\n Alleen wat blijkt nou, de voorzitter van Syntaxis " + 
					"heeft zichzelf opgesloten in het hok, waar ook de koffieautomaat staat. \n" +
					"In de wandelgangen gaat het gerucht dat er ergens een reservesleutel verborgen is voor dit soort gevallen. \n" +
					"Succes met zoeken. Misschien kan je dan de voorzitter ook nog overtuigen dat hij zich niet weer moet opsluiten. \n" +
					"Ondertussen heb je ook te horen gekregen dat Jan je zoekt om nog een en ander over het project te bespreken. \n" +
					"Na de koffie ga je naar hem op zoek, waarschijnlijk zit hij in de docentenkamer. Schijnbaar heeft hij nog een leuk idee voor jullie project..."); break;
			
			} 
		
		} else if (info.equals("win")) {
			/* Re-arrange the interface */
			btnRestart.setText("Exit");
			tvScore.setTextSize(25);
			tvScoreLabel.setVisibility(View.VISIBLE);
			tvScoreLabel.setText("Score: ");
			tvTitle.setVisibility(View.VISIBLE);
			tvTitle.setText("You won! Game ends here");
			
			/* Calculate score from steps and time */
			int stepScore = (int) ((1000.0 / model.getSteps()) * Math.pow(model.getNumberOpenedLevels(), 3));
			int timeScore = (int) ((1000.0 / model.getTime()) * Math.pow(model.getNumberOpenedLevels(), 3));
			/* Raise the score by the adequate value */
			model.raiseScore(stepScore);
			model.raiseScore(timeScore);
		
			tvScore.setText(model.getScore() + "");
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
				} else if (info.equals("win")) {
					finish();
					model.update();
					// implement game end stuff here
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
