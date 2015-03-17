package nl.glasbakheroes.StudyOrDie.activities;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Activity where the player can pick a avatar and set his/her name
 * @author enjee
 */
public class PickAvatarActivity extends Activity {
	
	public static final int RESULT_TO_STARTACTIVITY = StartActivity.REQUEST_AVATAR_SELECTION;
	private ImageView imgAvatar1, imgAvatar2, imgAvatar3, imgAvatar4;
	private EditText inputChar;
	Handler handler = new Handler();
	private StudyOrDieModel model;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/* Remove title bar */
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    /* Remove notification bar */
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_pick_avatar);
		
		model = ((StudyOrDieApplication)getApplication()).getModel();
		
		imgAvatar1 = (ImageView)findViewById(R.id.imgAvatar1);
		imgAvatar2 = (ImageView)findViewById(R.id.imgAvatar2);
		imgAvatar3 = (ImageView)findViewById(R.id.imgAvatar3);
		imgAvatar4 = (ImageView)findViewById(R.id.imgAvatar4);
		inputChar = (EditText)findViewById(R.id.etFillName);
		
		ButtonListener listener = new ButtonListener();
		imgAvatar1.setOnClickListener(listener); 
		imgAvatar2.setOnClickListener(listener);
		imgAvatar3.setOnClickListener(listener);
		imgAvatar4.setOnClickListener(listener);
	}
	
	private class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent resultIntent = new Intent(PickAvatarActivity.this, CoreActivity.class);
			model.getAvatar().setName(inputChar.getText() + "");
			Log.w("FIRST STAGE", "Name should be: " + inputChar.getText());
//			model.getAvatar().setAvatarImage();  <--- Implement this
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
