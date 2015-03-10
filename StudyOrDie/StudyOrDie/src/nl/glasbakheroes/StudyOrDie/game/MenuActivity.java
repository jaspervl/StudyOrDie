package nl.glasbakheroes.StudyOrDie.game;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.game.menu.CharacterMenu;
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

		CharacterMenu menu = new CharacterMenu(); 

		manager.beginTransaction().add(R.id.fragmentContainer, menu).commit();

	}

}
