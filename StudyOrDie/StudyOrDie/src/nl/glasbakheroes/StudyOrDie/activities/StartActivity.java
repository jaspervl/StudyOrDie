package nl.glasbakheroes.StudyOrDie.activities;

import nl.glasbakheroes.StudyOrDie.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * The start screen activity, gives back a result to the core activity.
 * @author enjee
 */
public class StartActivity extends Activity {

	public static final int RESULT_TO_CORE_ACTIVITY = CoreActivity.REQUEST_START_CODE;
	public static final int REQUEST_AVATAR_SELECTION = 3;
	
	private Button btnNewGame;
	private Button btnLoad;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		btnNewGame = (Button) findViewById(R.id.btnNewGame);
		btnLoad = (Button) findViewById(R.id.btnLoad); 
		
		ButtonListener listener = new ButtonListener();
		btnNewGame.setOnClickListener(listener);
		btnLoad.setOnClickListener(listener);
	}
	
	/** Listener for the two buttons */
	private class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {	
			if (v == btnNewGame) {
				/* Start a new activity for a result, the 'pick avatar' activity */
				Intent pickAvatarIntent = new Intent(StartActivity.this, PickAvatarActivity.class);
				startActivityForResult(pickAvatarIntent, REQUEST_AVATAR_SELECTION);
			} else if (v == btnLoad) {
				/* Finish this intent, tell the CoreActivity you want to load a game */
				Intent resultIntent = new Intent();
				resultIntent.putExtra("action", "load");
				setResult(RESULT_TO_CORE_ACTIVITY, resultIntent);
				finish();
			}
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_AVATAR_SELECTION) { 
			/* Pass the given information from the avatar selection activity to the core activity */
			Intent resultIntent = new Intent();
			Bundle extras = new Bundle();
			extras.putString("action", "new");
			extras.putString("avatarName", data.getExtras().getString("avatarName"));
			extras.putString("avatarPicure", data.getExtras().getString("avatarPicure"));
			resultIntent.putExtras(extras);
			setResult(RESULT_TO_CORE_ACTIVITY, resultIntent);
			finish();
		}
	}
	
	
	public void onBackPressed() {
		/* When the back key is pressed pass 'abort' to the core activity */
		Intent resultIntent = new Intent();
		Bundle extras = new Bundle();
		extras.putString("action", "abort");
		extras.putString("avatarName", "default_avatar_name");
		resultIntent.putExtras(extras);
		setResult(RESULT_TO_CORE_ACTIVITY, resultIntent);
		finish();
	}
	
	
}
