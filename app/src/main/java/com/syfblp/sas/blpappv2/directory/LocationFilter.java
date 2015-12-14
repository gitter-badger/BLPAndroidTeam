package com.syfblp.sas.blpappv2.directory;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.syfblp.sas.blpappv2.R;

import java.util.ArrayList;


/**
 * Created by 212464350 on 12/9/2015.
 */
public class LocationFilter extends Fragment {

    private Spinner spinner1;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter1;
    ArrayList<String> func= TabsFragment.location;
    ArrayList<String> peop= TabsFragment.names;

    public static LocationFilter newInstance() {
        LocationFilter fragment = new LocationFilter();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview= inflater.inflate(R.layout.location_layout, container, false);

        spinner1 = (Spinner) rootview.findViewById(R.id.spinner2);
        adapter = new ArrayAdapter<>(rootview.getContext(), android.R.layout.simple_list_item_1, func);
        adapter1 = new ArrayAdapter<>(rootview.getContext(), android.R.layout.simple_list_item_1, peop);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setOnItemSelectedListener(new LocationSelector());
        spinner1.setAdapter(adapter);

        ListView listView = (ListView) rootview.findViewById(R.id.listView2);
        AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent = new Intent(parent.getContext(), Profile.class);
                Person person = TabsFragment.changeList.get(position);
                intent.putExtra("snails", person);
                intent.putExtra("json", TabsFragment.people);
                startActivity(intent);
            }
        };
        adapter1.notifyDataSetChanged();
        listView.setOnItemClickListener(mMessageClickedHandler);
        listView.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();

        return rootview;
    }
}
