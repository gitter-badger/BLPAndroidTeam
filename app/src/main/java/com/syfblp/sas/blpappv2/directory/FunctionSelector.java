package com.syfblp.sas.blpappv2.directory;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.syfblp.sas.blpappv2.R;


/**
 * Created by 212464350 on 11/4/2015.
 */
public class FunctionSelector implements AdapterView.OnItemSelectedListener {


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        TabsFragment.names.clear();

        ListView listView=(ListView) parent.getRootView().findViewById(R.id.listView);
        String test =parent.getItemAtPosition(position).toString();

        for(int i=0; i < TabsFragment.people.size();i++) {
            Person person = TabsFragment.people.get(i);
            if (test.equals(person.getFunction())) {
                String lvnames= person.getFirstName()+ " "+person.getLastName()+"- "+person.getLocation();
                TabsFragment.names.add(lvnames);

            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(parent.getContext(), android.R.layout.simple_list_item_1, TabsFragment.names);

        listView.setAdapter(adapter);
    }


    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}