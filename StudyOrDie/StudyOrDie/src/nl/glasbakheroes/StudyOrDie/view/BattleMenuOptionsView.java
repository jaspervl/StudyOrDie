package nl.glasbakheroes.StudyOrDie.view;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.activities.CombatActivity;
import nl.glasbakheroes.StudyOrDie.activities.ItemActivity;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * A UI component which contains all the menu options accessible from within a battle.
 * @author Niels Jan
 */
public class BattleMenuOptionsView extends LinearLayout {
	/** Instance variables */
	private Button btnItems, btnCharacter, btnForfeit;
	private ImageView ivCharacter;

	/** Constructors */
	public BattleMenuOptionsView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}
	public BattleMenuOptionsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	public BattleMenuOptionsView(Context context) {
		super(context);
		init();
	}
	
	/** Initialize method which will be called from every constructor */
	private void init() {
		if (!isInEditMode()) {
			/* Convert the views XML into java-code */
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			inflater.inflate(R.layout.battle_menu_options_view, this);
		} else {
			setBackgroundColor(Color.GREEN);
		}
		
		/* Link the interface components to this class instance */
		btnItems = (Button) findViewById(R.id.btnBattleItems);
		btnCharacter = (Button) findViewById(R.id.btnBattleCharacter);
		btnForfeit = (Button) findViewById(R.id.btnForfeit);
		ivCharacter = (ImageView) findViewById(R.id.imgBattleAvatar);
		
		StudyOrDieModel model = ((StudyOrDieApplication)getContext().getApplicationContext()).getModel();
		ivCharacter.setImageBitmap(SpriteCache.getInstance().get(model.getAvatar().getImageId()));
		
		/* Set a listener for the forfeit button */
		ButtonListener listener = new ButtonListener();
		btnItems.setOnClickListener(listener);
		btnForfeit.setOnClickListener(listener);
	}
	
	/* Listener which listens to the forfeit button only at the moment */
	private class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			CombatActivity activity = (CombatActivity) getContext();
			if (v == btnForfeit) {
				activity.killAvatar();
			} else if (v == btnItems) {
				Intent menuIntent = new Intent(activity, ItemActivity.class);
				menuIntent.putExtra("onlyConsumables", true);
				activity.startActivity(menuIntent);
				// intent to open menu with intventory HERE!
			}
		}
	}
}
