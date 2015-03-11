package nl.glasbakheroes.StudyOrDie.activities;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.game.menu.CharacterFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * The menu screen activity
 * @author Jasper
 */
public class MenuActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		FragmentManager manager = getSupportFragmentManager();

		CharacterFragment menu = new CharacterFragment(); 

		manager.beginTransaction().add(R.id.fragmentContainer, menu).commit();

	}

}
