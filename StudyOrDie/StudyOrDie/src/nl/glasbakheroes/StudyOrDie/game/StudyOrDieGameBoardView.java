package nl.glasbakheroes.StudyOrDie.game;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import nl.glasbakheroes.StudyOrDie.view.GameBoardView;

public class StudyOrDieGameBoardView extends GameBoardView{

	public StudyOrDieGameBoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public StudyOrDieGameBoardView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	private void init() {
		setBackgroundColor(Color.GREEN);
	}

}
