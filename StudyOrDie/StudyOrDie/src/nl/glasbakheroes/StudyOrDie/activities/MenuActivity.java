package nl.glasbakheroes.StudyOrDie.activities;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.game.menu.CharacterFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * The menu screen activity
 * @author Jasper
 */
public class MenuActivity extends FragmentActivity {
	Button temp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* Remove title bar */
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    /* Remove notification bar */
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_menu);

		FragmentManager manager = getSupportFragmentManager();

		CharacterFragment menu = new CharacterFragment(); 

		manager.beginTransaction().add(R.id.fragmentContainer, menu).commit();
		temp = (Button)findViewById(R.id.btnItems);
		temp.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent inventoryShow = new Intent(MenuActivity.this,ItemActivity.class);
				startActivity(inventoryShow);
				
			}});

	}

}
