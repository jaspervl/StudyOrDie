package nl.glasbakheroes.StudyOrDie.game.menu;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.custom.Item;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<Item> {
	private ViewHolder holder;
	
	
	static class ViewHolder {
		TextView name;
	}
	
	public ListAdapter(Context context, int resource) {
		super(context, resource);
		new TextView(context);
	}
	
	@Override
	public View getView(int pos, View convertView, ViewGroup parent){
		Item item = StudyOrDieModel.getItemList().get(pos);
		if(convertView == null){
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.simpleitem, parent, false);
			holder = new ViewHolder();
			holder.name = (TextView)convertView.findViewById(R.id.theitemname);
            convertView.setTag(holder);
		}
		else
		{
			convertView.getTag();
		}
		
		holder.name.setText(String.format(item.getName()));
		
		if(item.getAddHP() > 0)
		{
			convertView.setBackgroundColor(Color.GREEN);
		}
		else
		{
			convertView.setBackgroundColor(Color.RED);
		}
	
		
		return convertView;
	}

}
