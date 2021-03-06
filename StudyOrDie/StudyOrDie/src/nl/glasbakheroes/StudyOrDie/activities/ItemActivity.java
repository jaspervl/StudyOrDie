package nl.glasbakheroes.StudyOrDie.activities;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.custom.Item;
import nl.glasbakheroes.StudyOrDie.custom.TransmitInfo;
import nl.glasbakheroes.StudyOrDie.game.menu.ItemFragment;
import nl.glasbakheroes.StudyOrDie.game.menu.ListFragment;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
/**
 * 
 * @author Jasper
 *	Activity that handles the inventory screen. A player can use / equip items
 */
public class ItemActivity extends Activity implements TransmitInfo {
	private ListFragment item;
	private ItemFragment detail;
	private int position;
	
	/**
	 * Initialize variables  & binds fragments to the containers.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* Remove title bar */
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    /* Remove notification bar */
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_item);

		/* Check if this activity started with extras */
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			/* If the onlyConsumables true boolean was given, don't show equippable items */
			boolean onlyConsumables = extras.getBoolean("onlyConsumables");
			if (onlyConsumables) {
				// implementation to only show consumables here
			}
		}
		
		
		FragmentManager manager = getFragmentManager();

		item = new ListFragment(); 
		detail = new ItemFragment();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.lvSelectItem, item);
		transaction.add(R.id.detailContainer, detail).commit();

	}
	/*
	 * Interface methods for fragment communication
	 * @see nl.glasbakheroes.StudyOrDie.custom.TransmitInfo#getInfo(int)
	 */
	@Override
	public void getInfo(int key) {
		detail.setVariables(key);;
	}

	/**
	 * Removes an item
	 */
	@Override
	public void actionUpdate(Item currentItem) {
		item.remove(currentItem);
		
	}
	
}
