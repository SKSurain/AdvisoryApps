package com.example.surai.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.surai.myapplication.model.Listing;

import java.util.ArrayList;

public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.ViewHolder> {
    private ArrayList<Listing> listing;

    public ListingAdapter(ArrayList<Listing> listing) {
        this.listing = listing;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listing_item, parent, false);
        return new ViewHolder(v);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Listing list = listing.get(position);
        holder.listName.setText(list.getList_name());
        holder.distance.setText("" + list.getDistance());
    }

    @Override
    public int getItemCount() {
        if (listing != null) {
            return listing.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView listName;
        public final TextView distance;
        public final View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            listName = view.findViewById(R.id.txtListName);
            distance = view.findViewById(R.id.txtDistance);
        }
    }
}
