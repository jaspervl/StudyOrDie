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
 * @author Jasper Displays an item in the detail fragment
 */
public class ItemFragment extends Fragment {
	/** Instance variables */
	private ImageView picture;
	private TextView name;
	private TextView description;
	private TextView stats;
	private Button equipButton;
	private Item currentItem;
	private StudyOrDieModel model;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_item, container, false);

		model = ((StudyOrDieApplication) v.getContext().getApplicationContext())
				.getModel();
		name = (TextView) v.findViewById(R.id.tvItemName);
		description = (TextView) v.findViewById(R.id.tvDescription);
		equipButton = (Button) v.findViewById(R.id.btnEquipButton);
		stats = (TextView) v.findViewById(R.id.tvAddStat);
		picture = (ImageView) v.findViewById(R.id.ivItemPicture);

		if (model.getItemList().isEmpty()) {
			return v;
		}
		setVariables(0);
		equipButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				StudyOrDieModel model = ((StudyOrDieApplication) getActivity()
						.getApplication()).getModel();
				if (currentItem.isConsumable()) {
					model.getAvatar().setCurrent(currentItem.getHpModifier(),
							currentItem.getEnergyModifier(),
							currentItem.getMotivationModifier());
					model.removeItem(currentItem);
					equipButton.setEnabled(false);
					return;
				}
			
				if (model.isEquipped(currentItem)) {
					model.unEquipAvatarItem(currentItem);
					equipButton.setText(String.format("Equip"));
				} else {
					model.addItemToAvatar(currentItem);
					equipButton.setText(String.format("Unequip"));
				}
				

			}

		});

		return v;
	}

	public void setVariables(int pos) {
		currentItem = model.getItemList().get(pos);
		equipButton.setEnabled(true);
		name.setText(String.format(currentItem.getName()));
		description.setText(String.format(currentItem.getDescription()));
		stats.setText(String.format("HP " + currentItem.getHpModifier())
				+ String.format("\nEnergy " + currentItem.getEnergyModifier())
				+ String.format("\nMotivation "
						+ currentItem.getMotivationModifier()));
		// Make 3 textviews for this 'thing' above.
		if(currentItem != null){
		if (model.isEquipped(currentItem)) {
			equipButton.setText(String.format("Unequip"));
		} else {
			equipButton.setText(String.format("Equip"));
		}
		}

		if (currentItem.isConsumable()) {
			equipButton.setText("Use");
		}
		
		if(currentItem != null){
		switch (currentItem.getType()) {
		case 0:
			picture.setBackgroundResource(R.drawable.bread);
			break;
		case 1:
			picture.setBackgroundResource(R.drawable.helm);
			break;
		case 2:
			picture.setBackgroundResource(R.drawable.armor);
			break;
		case 3:
			picture.setBackgroundResource(R.drawable.sword);
			break;
		case 4:
			picture.setBackgroundResource(R.drawable.legs);
			break;
		case 5:
			picture.setBackgroundResource(R.drawable.feet);
			break;
		}
		}

	}
}
