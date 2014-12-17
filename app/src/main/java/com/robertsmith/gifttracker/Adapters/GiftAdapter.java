package com.robertsmith.gifttracker.Adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.robertsmith.gifttracker.Activities.ViewPerson;
import com.robertsmith.gifttracker.Data_Sources.Gift;
import com.robertsmith.gifttracker.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by robertsmith on 12/16/14.
 */

public class GiftAdapter extends RecyclerView.Adapter<GiftAdapter.ViewHolder>

{
    private ArrayList<Gift> gifts;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        // each data item is just a string in this case
        public View mView;
        public ViewHolder(View v)
        {
            super(v);
            mView = v;
        }
    }

    public GiftAdapter(ArrayList<Gift> myDataset)
    {
        this.gifts = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GiftAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.giftrow, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        TextView name = (TextView) holder.mView.findViewById(R.id.productName);
        TextView budget = (TextView) holder.mView.findViewById(R.id.productPrice);
        ImageView pic = (ImageView) holder.mView.findViewById(R.id.giftPicImage);

        name.setText(gifts.get(position).getGiftName());
        budget.setText(gifts.get(position).getGiftPrice());
        Picasso.with(ViewPerson.mContext).load(Uri.parse(gifts.get(position).getGiftPic())).into(pic);

    }

    @Override
    public int getItemCount()
    {
        return gifts.size();
    }
}
