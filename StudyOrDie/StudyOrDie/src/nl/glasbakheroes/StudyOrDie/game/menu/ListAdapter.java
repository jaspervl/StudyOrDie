package nl.glasbakheroes.StudyOrDie.game.menu;

import java.util.ArrayList;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.custom.Item;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
/**
 * 
 * @author Jasper
 * Custom adapter which will be used by the listview
 * Currently only used to display items -- not generic (yet)
 *
 */
public class ListAdapter extends ArrayAdapter<Item> {
	private ViewHolder holder;
	
	
	static class ViewHolder {
		TextView name;
	}
	
	public ListAdapter(Context context, int resource,ArrayList<Item> item) {
		super(context, resource,item);
	}
	
	@Override
	public View getView(int pos, View convertView, ViewGroup parent){
		Log.w("ListAdapter", "getView called");
		Item item = super.getItem(pos);
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
		
		
		if(item.isConsumable())
		{
			/* Green for consumables - Just testing */
			convertView.setBackgroundColor(Color.GREEN);
		}
		else
		{
			/* Blue for equipment items */
			convertView.setBackgroundColor(Color.BLUE);
		}
		holder.name.setText(String.format(item.getName()));
		return convertView;
	}
}
