package nl.glasbakheroes.StudyOrDie.game.menu;

import java.util.Observable;
import java.util.Observer;

import nl.glasbakheroes.StudyOrDie.R;
import nl.glasbakheroes.StudyOrDie.custom.Item;
import nl.glasbakheroes.StudyOrDie.custom.TransmitInfo;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieApplication;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle; 
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * ListView that tells which item has been clicked. Information is handled through the activity
 * @author Jasper
 */
public class ListFragment extends Fragment implements Observer {
	ListView selection;
	ListAdapter adapter;
	TransmitInfo send;
	private StudyOrDieModel model;
	
	/*
	 * Basic inflation of the view that needs to be returned to the activity.
	 * This fragment showcases the items in a suitable list
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_list, container, false);
				
		model = ((StudyOrDieApplication) v.getContext().getApplicationContext()).getModel();
		model.addObserver(this);
		
		selection = (ListView)v.findViewById(R.id.itemList);
		adapter = new ListAdapter(getActivity(), android.R.layout.simple_list_item_1, model.getItemList());
		selection.setAdapter(adapter);
		selection.setOnItemClickListener(new OnItemClickListener(){
			
		

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				send.getInfo(position);				
			}});
		
		return v;
	}
	/*
	 * 
	 * Checks whether the calling activity has the interface Transmit info  implemented
	 */
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            send = (TransmitInfo) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " Transmit info interface not implemented");
        }

	}
	/**
	 * 	Removes an item directly from the adapters arrayList
	 */
	public void remove(Item item)
	{
		adapter.remove(item);
	}
	/**
	 * Update method when changes occur in the model
	 */
	@Override
	public void update(Observable observable, Object data) {
		adapter.notifyDataSetChanged();
	}
}
