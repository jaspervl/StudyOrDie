package nl.glasbakheroes.StudyOrDie.activities;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Activity where the player can pick a avatar and set his/her name
 * @author Niels Jan
 */
public class PickAvatarActivity extends Activity {
	
	public static final int RESULT_TO_STARTACTIVITY = StartActivity.REQUEST_AVATAR_SELECTION;
	private ImageView imgAvatar1, imgAvatar2, imgAvatar3, imgAvatar4;
	private EditText etFillName;
	private Handler handler = new Handler();
	private StudyOrDieModel model;
	private Button btnEasy, btnMedium, btnHard;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/* Remove title bar */
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    /* Remove notification bar */
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_pick_avatar);
		
		model = ((StudyOrDieApplication)getApplication()).getModel();

		etFillName = (EditText)findViewById(R.id.etFillName);
		imgAvatar1 = (ImageView)findViewById(R.id.imgAvatar1);
		imgAvatar2 = (ImageView)findViewById(R.id.imgAvatar2);
		imgAvatar3 = (ImageView)findViewById(R.id.imgAvatar3);
		imgAvatar4 = (ImageView)findViewById(R.id.imgAvatar4);
		btnEasy = (Button) findViewById(R.id.btnEasy);
		btnMedium = (Button) findViewById(R.id.btnMedium);
		btnHard = (Button) findViewById(R.id.btnHard);
		
		btnMedium.setAlpha(0.5F);
		btnHard.setAlpha(0.5F);
		model.setDifficulty(1);
		
		ButtonListener listener1 = new ButtonListener(1);
		ButtonListener listener2 = new ButtonListener(2);
		ButtonListener listener3 = new ButtonListener(3);
		ButtonListener listener4 = new ButtonListener(4);

		imgAvatar1.setOnClickListener(listener1); 
		imgAvatar2.setOnClickListener(listener2);
		imgAvatar3.setOnClickListener(listener3);
		imgAvatar4.setOnClickListener(listener4);
		
		DifficultyListener diffListener= new DifficultyListener();
		btnEasy.setOnClickListener(diffListener);
		btnMedium.setOnClickListener(diffListener);
		btnHard.setOnClickListener(diffListener);
	}
	/**
	 * Listener to determine the difficulty
	 */
	private class DifficultyListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			btnEasy.setAlpha(0.5F);
			btnMedium.setAlpha(0.5F);
			btnHard.setAlpha(0.5F);
			
			if (v == btnEasy) {
				model.setDifficulty(1);
				btnEasy.setAlpha(1F);
			} else if (v == btnMedium) {
				model.setDifficulty(2);
				btnMedium.setAlpha(1F);
			} else if (v == btnHard){
				model.setDifficulty(3);
				btnHard.setAlpha(1F);
			}
 		}
		
	}
	
	/** Listens to button clicks, which avatar is clicked does not matter at this point in time. */
	private class ButtonListener implements OnClickListener {
		
		int id;
			
		public ButtonListener(int num){
			id = num;
		}

		@Override
		public void onClick(View v) {
			if ((etFillName.getText() + "").length() == 0) {
				Toast.makeText(getApplicationContext(), "Please enter a name before picking a avatar", Toast.LENGTH_SHORT).show();
			} else {
				model.setStoryLineShowed(0);
				model.getAvatar().setName(etFillName.getText() + "");
				model.getAvatar().setAvatarImages(this.id);
				model.createBosses();
				Intent resultIntent = new Intent(PickAvatarActivity.this, CoreActivity.class);
				resultIntent.putExtra("action", "new");
				setResult(RESULT_TO_STARTACTIVITY, resultIntent);
				
				handler.postDelayed(new Runnable() {
					public void run() {
						finish();
					}
				}, 1000);
			}	
		}
	}	
}
