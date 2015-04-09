package nl.glasbakheroes.StudyOrDie.game.menu;

import java.util.Observable;
import java.util.Observer;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.custom.Avatar;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Displays your current equiped items and their stats
 * @author Jasper
 *
 */
public class ArmorFragment extends Fragment implements Observer{
	private StudyOrDieModel model;
	private Avatar selectedAvatar;
	private TextView head;
	private TextView body;
	private TextView hand;
	private TextView legs;
	private TextView feet;
	

	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_armor, container , false);
		// Inflating views, retrieving model and adding this instance as observer to the model
		StudyOrDieApplication app = ( StudyOrDieApplication ) getActivity().getApplication();
		model = app.getModel();
		model.addObserver(this);
		head = (TextView)v.findViewById(R.id.mHeadDescription);
		body = (TextView)v.findViewById(R.id.mBodyDescription);
		hand = (TextView)v.findViewById(R.id.mHandDescription);
		legs = (TextView)v.findViewById(R.id.mLegsDescription);
		feet = (TextView)v.findViewById(R.id.mFeetDescription);
		head.setTextColor(Color.BLACK);
		body.setTextColor(Color.BLACK);
		hand.setTextColor(Color.BLACK);
		legs.setTextColor(Color.BLACK);
		feet.setTextColor(Color.BLACK);
		setter();		
		return v;
	}
	/**
	 * Inputs the current equipment details into the views
	 */
	private void setter(){
		selectedAvatar = model.getAvatar();
		if(selectedAvatar == null){
			return;
		}
		head.setText(String.valueOf(selectedAvatar.getHead().getInfo()));
		body.setText(String.valueOf(selectedAvatar.getBody().getInfo()));
		hand.setText(String.valueOf(selectedAvatar.getHand().getInfo()));
		legs.setText(String.valueOf(selectedAvatar.getLegs().getInfo()));
		feet.setText(String.valueOf(selectedAvatar.getFeet().getInfo()));

		
	}
/**
 * Making sure to update whenever the model changes
 */
	@Override
	public void update(Observable observable, Object data) {
		setter();
		
	}}
