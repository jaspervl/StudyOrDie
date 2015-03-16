package nl.glasbakheroes.StudyOrDie.game.menu;

import java.util.Observable;
import java.util.Observer;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.custom.Avatar;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Character menu fragment, displays the avatar details
 * @author Jasper
 */
public class CharacterFragment extends Fragment implements Observer{
	ImageView avatarDisplay;
	TextView avatarName;
	TextView avatarHP;
	Avatar selectedAvatar;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_character, container , false);
		
		StudyOrDieApplication app = ( StudyOrDieApplication ) getActivity().getApplication();
		app.getModel().addObserver(this);
		avatarDisplay = (ImageView) v.findViewById(R.id.characterpicture);
		avatarName = (TextView)v.findViewById(R.id.charactername);
		avatarHP = (TextView)v.findViewById(R.id.characterhp);
		setter();		
		return v;
	}
	
	private void setter(){
		selectedAvatar = StudyOrDieModel.getAvatar();
		avatarHP.setText("HP : " + selectedAvatar.getMaxHP() + "/" + selectedAvatar.getCurrentHP());
		avatarName.setText(selectedAvatar.getName());
		
		
		
	}

	@Override
	public void update(Observable observable, Object data) {
		setter();
		
	}}
