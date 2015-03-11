package nl.glasbakheroes.StudyOrDie.activities;

import nl.glasbakheroes.StudyOrDie.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

	private static final int RESULT_CODE = 1337;
	private static final int REQUEST_CODE = 1000;
	
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
	
	private class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {	
			if (v == btnNewGame) {
				Intent pickAvatarIntent = new Intent(StartActivity.this, PickAvatarActivity.class);
				startActivityForResult(pickAvatarIntent, REQUEST_CODE);
			} else if (v == btnLoad) {
				Intent resultIntent = new Intent();
				resultIntent.putExtra("action", "load");
				setResult(RESULT_CODE, resultIntent);
				finish();
			}
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE) {  
			Intent resultIntent = new Intent();
			resultIntent.putExtra("action", "new");
			resultIntent.putExtra("avatarName", data.getStringExtra("avatarName"));
			resultIntent.putExtra("avatarPicure", data.getStringExtra("avatarPicure"));
			setResult(RESULT_CODE, resultIntent);
			finish();
		}
	}
	
	
	public void onBackPressed() {
		Intent resultIntent = new Intent();
		resultIntent.putExtra("action", "abort");
		resultIntent.putExtra("avatarName", "Avatar");
		setResult(RESULT_CODE, resultIntent);
		finish();
	}
}
