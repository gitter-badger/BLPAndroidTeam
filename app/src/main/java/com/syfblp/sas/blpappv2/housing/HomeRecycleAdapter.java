package com.syfblp.sas.blpappv2.housing;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.syfblp.sas.blpappv2.announcements.Announcement;
import com.syfblp.sas.blpappv2.R;

import java.util.ArrayList;


public class HomeRecycleAdapter extends RecyclerView.Adapter<HomeRecycleAdapter.ViewHolder> {
    private ArrayList<Announcement> mDataset;
    private Activity activity;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public Button btnURL;

        public ViewHolder(View v) {
            super(v);
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            btnURL = (Button) v.findViewById(R.id.btnURL);
        }
    }

    public void add(int position, Announcement item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(Announcement item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public HomeRecycleAdapter(Activity activity, ArrayList<Announcement> myDataset) {
        mDataset = myDataset;
        this.activity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Announcement name = mDataset.get(position);
        final String link = name.getNewsurl();


        if (link != null && !link.isEmpty()) {
            holder.btnURL.setVisibility(View.VISIBLE);
            holder.btnURL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // On click
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                    activity.startActivity(browserIntent);

                }
            });


            }
        else {
                holder.btnURL.setVisibility(View.GONE);
            }


        holder.txtHeader.setText(name.getDescription());
        holder.txtFooter.setText(name.getTime());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}