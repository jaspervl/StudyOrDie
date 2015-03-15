package nl.glasbakheroes.StudyOrDie.game.menu;

import nl.glasbakheroes.StudyOrDie.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

/**
 * ListView in which you can select what sub-menu to open
 * @author Jasper
 */
public class ListFragment extends Fragment {
	ListView selection;
	ListAdapter adapter;
	int pos;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_list, container, false);
		selection = (ListView)v.findViewById(R.id.listFragment);
		adapter = new ListAdapter(getActivity(), android.R.layout.simple_list_item_1);
		selection.setAdapter(adapter);
		
		selection.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				pos = position;
		
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}});
		
		return v;
	}
	
	public int getPosition(){
		return pos;
	}

}
