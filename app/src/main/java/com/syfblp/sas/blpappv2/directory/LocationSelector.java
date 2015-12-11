package com.syfblp.sas.blpappv2.directory;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.syfblp.sas.blpappv2.R;


/**
 * Created by 212464350 on 11/4/2015.
 */
public class LocationSelector implements AdapterView.OnItemSelectedListener {


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        TabsFragment.names.clear();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(parent.getRootView().getContext(), android.R.layout.simple_list_item_1, TabsFragment.names);

        ListView listView = (ListView) parent.getRootView().findViewById(R.id.listView2);
        String test = parent.getItemAtPosition(pos).toString();

        for (int i = 0; i < TabsFragment.people.size(); i++) {
            Person person = TabsFragment.people.get(i);
            if (test.equals(person.getLocation())) {
                String lvnames = person.getFirstName() + " " + person.getLastName();
                TabsFragment.names.add(lvnames);
                adapter.notifyDataSetChanged();
            }
        }
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}