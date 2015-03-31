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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Character menu fragment, displays the avatar details
 * @author Jasper
 */
public class CharacterFragment extends Fragment implements Observer{
	private ImageView avatarDisplay;
	private TextView avatarName;
	private TextView avatarHP;
	private Avatar selectedAvatar;
	private StudyOrDieModel model;
	private Button btnMute;
	private AudioManager audioManager;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_character, container , false);
		
		StudyOrDieApplication app = ( StudyOrDieApplication ) getActivity().getApplication();
		model = app.getModel();
		model.addObserver(this);
		avatarDisplay = (ImageView) v.findViewById(R.id.characterpicture);
		avatarName = (TextView)v.findViewById(R.id.charactername);
		avatarHP = (TextView)v.findViewById(R.id.characterhp);
		
		/* Mute button settings and listener below */
		btnMute = (Button) v.findViewById(R.id.btnMute);
		audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
		if (audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) == 0) {
			btnMute.setText("Unmute sound");
		} else {
			btnMute.setText("Mute sound");
		}
		
		btnMute.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
				if (currentVolume > 0) {
					int soundVolume = 0;
					audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, soundVolume, 0);
					btnMute.setText("Unmute sound");
				} else {
					int soundVolume = 100;
					audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, soundVolume, 0);
					btnMute.setText("Mute sound");
				}
			}
		});
		/* End of mute button section */
		
		setter();		
		return v;
	}
	
	private void setter(){
		selectedAvatar = model.getAvatar();
		avatarName.setText(selectedAvatar.getName());
		avatarHP.setText(	"HP : " + selectedAvatar.getCurrentHP() + "/"+ selectedAvatar.getMaxHP()
				 			+ "\nEnergy : " + model.getAvatar().getCurrentEnergy() + "/" + model.getAvatar().getMaxEnergy()
				 			+ "\nMotivation : " + model.getAvatar().getCurrentMotivation() + "/" + model.getAvatar().getMaxMotivation()
				 			+ "\nCoins: " + model.getBalance());

		
	}

	@Override
	public void update(Observable observable, Object data) {
		setter();
		
	}}
