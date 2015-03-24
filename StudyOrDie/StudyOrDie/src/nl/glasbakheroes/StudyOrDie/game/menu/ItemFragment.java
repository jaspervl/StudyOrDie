package nl.glasbakheroes.StudyOrDie.game.menu;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.custom.Item;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 			
 * @author Jasper
 * Displays an item in the detail fragment
 */
public class ItemFragment extends Fragment {
	ImageView picture;
	TextView name;
	TextView description;
	TextView stats;
	Button equipButton;
	Item currentItem;
	private StudyOrDieModel model;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_item, container, false);
		
		model = ((StudyOrDieApplication) v.getContext().getApplicationContext()).getModel();
		
		name = (TextView) v.findViewById(R.id.tvItemName);
		description = (TextView) v.findViewById(R.id.tvDescription);
		equipButton = (Button) v.findViewById(R.id.btnEquipButton);
		stats = (TextView) v.findViewById(R.id.tvAddStat);
		
		if (model.getItemList().get(0) == null) {
			return v;
		}
		setVariables(0);
		equipButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				StudyOrDieModel model = ((StudyOrDieApplication)getActivity().getApplication()).getModel();
				if(currentItem.isConsumable())
				{
					model.getAvatar().setCurrent(currentItem.getHpModifier(), currentItem.getEnergyModifier(), currentItem.getMotivationModifier());
					model.removeItem(currentItem);
					return;
				} 
				currentItem.equip();
				if (currentItem.getEquipped()) {
					model.addItemToAvatar(currentItem); 
					equipButton.setText(String.format("Unequip"));
				} else {
					model.unEquipAvatarItem(currentItem); 
					equipButton.setText(String.format("Equip"));
				}

			}

		});

		return v;
	}

	public void setVariables(int pos) {
		currentItem = model.getItemList().get(pos);
		name.setText(String.format(currentItem.getName()));
		description.setText(String.format(currentItem.getDescription()));
		stats.setText(String.format("HP " + currentItem.getHpModifier()) + String.format("\nEnergy " + currentItem.getEnergyModifier()) + String.format("\nMotivation " + currentItem.getMotivationModifier()));
		// Make 3 textviews for this 'thing' above.
		if (currentItem.getEquipped()) {
			equipButton.setText(String.format("Unequip"));
		} else {
			equipButton.setText(String.format("Equip"));
		}
		
		if(currentItem.isConsumable())
		{
			equipButton.setText("Use");
		}

	}
}
