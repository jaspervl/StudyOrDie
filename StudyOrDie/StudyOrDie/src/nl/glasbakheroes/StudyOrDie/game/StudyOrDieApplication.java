package nl.glasbakheroes.StudyOrDie.game;

import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
import android.app.Application;

public class StudyOrDieApplication extends Application {
	
	private StudyOrDieModel model;
	
	public StudyOrDieApplication() {
		this.model = new StudyOrDieModel();
	}
	
	public StudyOrDieModel getModel() {
		return model;
	}
}
