package nl.glasbakheroes.StudyOrDie.game;

import nl.glasbakheroes.StudyOrDie.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * The start screen activity, gives back a result to the core activity.
 * @author enjee
 */
public class StartActivity extends Activity {

	private Button btnNewGame;
	private Button btnLoad;
	private EditText etAvatarName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		btnNewGame = (Button) findViewById(R.id.btnNewGame);
		btnLoad = (Button) findViewById(R.id.btnLoad); 
		etAvatarName = (EditText) findViewById(R.id.etAvatarName);
		
		ButtonListener listener = new ButtonListener();
		btnNewGame.setOnClickListener(listener);
		btnLoad.setOnClickListener(listener);
	}
	
	private class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			String action = "";
			String avatarName = "Avatar";
			if ((etAvatarName.getText() + "").length() >= 2) {
				avatarName = etAvatarName.getText() + "";
			}
			
			if (v == btnNewGame) {
				action = "new";
			} else if (v == btnLoad) {
				action = "load";
			}
			Intent resultIntent = new Intent();
			resultIntent.putExtra("action", action);
			resultIntent.putExtra("avatarName", avatarName);
			setResult(1337, resultIntent);
			finish();
		}
		
	}
}
