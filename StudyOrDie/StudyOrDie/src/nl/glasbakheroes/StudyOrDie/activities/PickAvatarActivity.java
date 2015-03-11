package nl.glasbakheroes.StudyOrDie.activities;

import nl.glasbakheroes.StudyOrDie.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * Activity where the player can pick a avatar and set his/her name
 * @author enjee
 */
public class PickAvatarActivity extends Activity {
	
	public static final int RESULT_TO_STARTACTIVITY = StartActivity.REQUEST_AVATAR_SELECTION;
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
			Intent resultIntent = new Intent(PickAvatarActivity.this, CoreActivity.class);
			resultIntent.putExtra("action", "new");
			resultIntent.putExtra("avatarName", "JasperVL");
			resultIntent.putExtra("avatarPicure", "default_avatar_picture");
			setResult(RESULT_TO_STARTACTIVITY, resultIntent);
			finish();
		}
	}
	
	/**
	 * ENTER IMPLEMENTATION TO RETURN THE CHOSEN NAME AND PICURE, DUMMY ACTIVITY FOR NOW.
	 */
	
}
