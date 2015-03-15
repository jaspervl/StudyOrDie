package nl.glasbakheroes.StudyOrDie.activities;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.custom.TransmitInfo;
import nl.glasbakheroes.StudyOrDie.game.menu.ItemFragment;
import nl.glasbakheroes.StudyOrDie.game.menu.ListFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;

public class ItemActivity extends FragmentActivity implements TransmitInfo {
	ListFragment item;
	ItemFragment detail;
	int position;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* Remove title bar */
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    /* Remove notification bar */
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_item);

		FragmentManager manager = getSupportFragmentManager();

		item = new ListFragment(); 
		detail = new ItemFragment();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.lvSelectItem, item);
		transaction.add(R.id.detailContainer, detail).commit();

	}

	@Override
	public void getInfo() {
		item.getPosition();
	}

	@Override
	public void actionUpdate(int key) {
		// TODO Auto-generated method stub
		
	}
	
}
