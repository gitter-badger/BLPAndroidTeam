package com.syfblp.sas.blpappv2.housing;

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

import com.google.gson.Gson;
import com.syfblp.sas.blpappv2.R;
import com.syfblp.sas.blpappv2.ServiceHandler;

import java.util.ArrayList;

/**
 * Created by 212464350 on 11/24/2015.
 */
public class HousingFragment extends Fragment {
    ArrayList<Housing_> input = new ArrayList<>();
    ArrayList<String> alllocation = new ArrayList<>();

    ListView listView;
    ArrayAdapter<String> adapter;

    private static String url = "https://uat.onlinecreditcenter6.com/cs/groups/cmswebsite/documents/websiteasset/housing_android.json" ;
    public static HousingFragment newInstance() {
        HousingFragment fragment = new HousingFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup containter, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_housing, containter, false);
        new JSONParse().execute();

        adapter = new ArrayAdapter<>(rootview.getContext(), android.R.layout.simple_list_item_1, alllocation);
        listView = (ListView) rootview.findViewById(R.id.listView);

        android.widget.AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent openHousing = new Intent(parent.getContext(), HousingActivity.class);

                String clickedOnCity = alllocation.get(position);
                openHousing.putExtra("snails", clickedOnCity);
               openHousing.putExtra("json",input);


                startActivity(openHousing);


            }
        };

        listView.setOnItemClickListener(mMessageClickedHandler);


        return rootview;
    }

    private class JSONParse extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            final String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
            final Housing housing = new Gson().fromJson(jsonStr, Housing.class);

            Log.d("Response: ", "> " + jsonStr);

            if(housing == null || housing.getHousing().isEmpty()) {
                Log.e("Err", "No events found!");
                return null;
            }

            input = housing.getHousing();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            int i;
            for (i = 0; i < input.size(); i++) {
                Housing_ housing = input.get(i);
                String lvnames = housing.getSyfLocation();
                if (checkUnique(lvnames,alllocation)) {
                    alllocation.add(lvnames);
                }
            }

            listView.setAdapter(adapter);
        }

    }

    private boolean checkUnique(String lvnames, ArrayList<String> location) {
       boolean unique=true;
            for (int i=0; 0<location.indexOf(lvnames);i++) {
                if (location.get(i).equals(lvnames)) {
                    unique = false;
                    break;
                }
            }

            for (int y=location.indexOf(lvnames)+1; y<location.size();y++){
                if (location.get(y).equals(lvnames)){
                    unique=false;
                    break;
                }
            }


        return unique;
    }
}

