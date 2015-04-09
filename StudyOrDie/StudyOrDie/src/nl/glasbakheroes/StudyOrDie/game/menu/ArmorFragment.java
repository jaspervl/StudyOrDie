package nl.glasbakheroes.StudyOrDie.game.menu;

import java.util.Observable;
import java.util.Observer;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.custom.Avatar;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
import android.app.Fragment;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
		
		StudyOrDieApplication app = ( StudyOrDieApplication ) getActivity().getApplication();
		model = app.getModel();
		model.addObserver(this);
		head = (TextView)v.findViewById(R.id.mHeadDescription);
		body = (TextView)v.findViewById(R.id.mBodyDescription);
		hand = (TextView)v.findViewById(R.id.mHandDescription);
		legs = (TextView)v.findViewById(R.id.mLegsDescription);
		feet = (TextView)v.findViewById(R.id.mFeetDescription);
		setter();		
		return v;
	}
	
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

	@Override
	public void update(Observable observable, Object data) {
		setter();
		
	}}
