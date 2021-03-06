package nl.glasbakheroes.StudyOrDie.game;

import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
import android.app.Application;

/**
 * Application class which holds the one single copy of the StudyOrDieModel.
 * @author Niels Jan
 */
public class StudyOrDieApplication extends Application {
	
	private StudyOrDieModel model;
	
	public StudyOrDieApplication() {
		this.model = new StudyOrDieModel();
	}
	/**
	 * Returns the current global instance of model
	 */
	public StudyOrDieModel getModel() {
		return model;
	}
	/**
	 * makes a new instance of model
	 */
	public void regenModel() {
		this.model = new StudyOrDieModel();
	}
	
}
