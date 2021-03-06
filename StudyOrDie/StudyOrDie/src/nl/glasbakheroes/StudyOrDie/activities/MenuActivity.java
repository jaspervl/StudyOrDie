package nl.glasbakheroes.StudyOrDie.activities;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.game.menu.CharacterFragment;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * The menu screen activity. Holds the characterfragment which displays information regarding the avatar.
 * Also contains a button to navigate to the item menu
 * @author Jasper
 */
public class MenuActivity extends Activity {
	private Button temp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* Remove title bar */
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    /* Remove notification bar */
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_menu);

		/*
		 * Making the transaction
		 */
		FragmentManager manager = getFragmentManager();
 
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
