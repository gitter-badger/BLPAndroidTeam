package com.syfblp.sas.blpappv2.events;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.syfblp.sas.blpappv2.R;
import com.syfblp.sas.blpappv2.ServiceHandler;

import java.util.ArrayList;


public class EventFragment extends Fragment {

    private RecyclerView erecyclerView;
    private EventAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Event> events = new ArrayList<>() ;
    private static String url = "https://uat.onlinecreditcenter6.com/cs/groups/cmswebsite/documents/websiteasset/calendar_android.json";


    public static EventFragment newInstance(){
        EventFragment fragment= new EventFragment();
        return fragment;
    }


    public View onCreateView(LayoutInflater inflater,ViewGroup containter,Bundle savedInstanceState){
        View rootview= inflater.inflate(R.layout.event_fragment,containter,false);
        erecyclerView = (RecyclerView) rootview.findViewById(R.id.event_recycler_view);
        mLayoutManager=new LinearLayoutManager(getActivity().getApplicationContext());
        erecyclerView.setLayoutManager(mLayoutManager);
        new JSONParse().execute();

        return rootview;
    }

    private class JSONParse extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            final String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
            Log.d("Response: ", "> " + jsonStr);

            final Events events = new Gson().fromJson(jsonStr, Events.class);

            if(events == null || events.getEvent().isEmpty()) {
                Log.e("Err", "No events found!");
                return null;
            }

            EventFragment.this.events = events.getEvent();

            mAdapter = new EventAdapter(EventFragment.this.events, getActivity());

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            mAdapter = new EventAdapter(events,  getActivity());

            erecyclerView.setAdapter(mAdapter);
        }
    }
}
