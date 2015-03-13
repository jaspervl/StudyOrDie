package nl.glasbakheroes.StudyOrDie.game.menu;

import java.util.ArrayList;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter<T> extends ArrayAdapter<T> {
	private ViewHolder holder;
	private StudyOrDieModel model;
	
	static class ViewHolder {
		ImageView image;
		TextView titel;
		TextView duration;
		TextView date;
	}
	
	public ListAdapter(Context context, int resource,ArrayList<T> objects) {
		super(context, resource, objects);
		StudyOrDieApplication app = (StudyOrDieApplication) getContext().getApplicationContext();
		model = app.getModel();
		
	}
	
	@Override
	public T getItem(int position)
	{
		return null;
		
	}
	
	@Override
	public View getView(int pos, View convertView, ViewGroup parent){
		if(convertView == null)
		{
			
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_list, parent, false);
			holder = new ViewHolder();
            convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}
	
		
		return convertView;
	}

}
