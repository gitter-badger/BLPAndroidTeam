package com.syfblp.sas.blpappv2.directory;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.syfblp.sas.blpappv2.R;
import com.syfblp.sas.blpappv2.ServiceHandler;
import com.syfblp.sas.blpappv2.housing.HousingFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by 212464350 on 11/24/2015.
 */
public class DirectoryFragment extends Fragment {

    ArrayList<Person> input = new ArrayList<>();
    ArrayList<String> peopleArray = new ArrayList<>();
    ArrayAdapter<String> adapter;


    public static Fragment newInstance() {
        DirectoryFragment fragment = new DirectoryFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup containter, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.content_blpdirectory, containter, false);

        adapter = new ArrayAdapter<>(rootview.getContext(), android.R.layout.simple_list_item_1, TabsFragment.nameBackup);
        ListView listView = (ListView) rootview.findViewById(R.id.BLPDirectorylistView);

        android.widget.AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                Intent intent = new Intent(parent.getContext(), Profile.class);
                String clickedOnCity = TabsFragment.nameBackup.get(position);
                intent.putExtra("snails", clickedOnCity);
                intent.putExtra("json", TabsFragment.peopleBackup);
                startActivity(intent);
            }


        };
        listView.setOnItemClickListener(mMessageClickedHandler);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return rootview;
    }
}