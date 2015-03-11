package nl.glasbakheroes.StudyOrDie.activities;

import nl.glasbakheroes.StudyOrDie.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class PickAvatarActivity extends Activity {
	
	private static final int RESULT_CODE = 1000;
	private ImageView imgAvatar1, imgAvatar2, imgAvatar3, imgAvatar4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pick_avatar);
		
		imgAvatar1 = (ImageView)findViewById(R.id.imgAvatar1);
		imgAvatar2 = (ImageView)findViewById(R.id.imgAvatar2);
		imgAvatar3 = (ImageView)findViewById(R.id.imgAvatar3);
		imgAvatar4 = (ImageView)findViewById(R.id.imgAvatar4);
		
		ButtonListener listener = new ButtonListener();
		imgAvatar1.setOnClickListener(listener);
		imgAvatar2.setOnClickListener(listener);
		imgAvatar3.setOnClickListener(listener);
		imgAvatar4.setOnClickListener(listener);
	}
	
	/** Returns some default stuff [DUMMY] */
	private class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent resultIntent = new Intent();
			resultIntent.putExtra("action", "new");
			resultIntent.putExtra("avatarName", "JasperVL");
			resultIntent.putExtra("avatarPicure", "default_avatar_picture");
			setResult(RESULT_CODE, resultIntent);
			finish();
		}
		
	}
	
	
	/**
	 * ENTER IMPLEMENTATION TO RETURN THE CHOSEN NAME AND PICURE, DUMMY ACTIVITY FOR NOW.
	 */
	
}
