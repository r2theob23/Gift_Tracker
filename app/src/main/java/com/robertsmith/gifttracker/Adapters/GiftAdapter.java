package com.robertsmith.gifttracker.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.robertsmith.gifttracker.Activities.AddGift;
import com.robertsmith.gifttracker.Activities.MainActivity;
import com.robertsmith.gifttracker.Activities.ViewGift;
import com.robertsmith.gifttracker.Activities.ViewPerson;
import com.robertsmith.gifttracker.Data_Sources.Gift;
import com.robertsmith.gifttracker.Data_Sources.Person;
import com.robertsmith.gifttracker.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//Robert Smith
//Gift Tracker
//12/2014

public class GiftAdapter extends RecyclerView.Adapter<GiftAdapter.ViewHolder>

{
    private static ArrayList<Gift> gifts;
    private static Activity mActivity;

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

    //Activity is ViewPerson Activity
    public GiftAdapter(ArrayList<Gift> myDataset,  Activity activity)
    {
        //<Gift> DS
        this.gifts = myDataset;
        mActivity = activity;
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
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        //Assign vars to ui elements in cells
        TextView name = (TextView) holder.mView.findViewById(R.id.productName);
        TextView budget = (TextView) holder.mView.findViewById(R.id.productPrice);
        ImageView pic = (ImageView) holder.mView.findViewById(R.id.giftPicImage);
        CheckBox box = (CheckBox) holder.mView.findViewById(R.id.checkBox);

        //set vars from ds
        name.setText(gifts.get(position).getGiftName());
        budget.setText(gifts.get(position).getGiftPrice());
        Picasso.with(ViewPerson.mContext).load(Uri.parse(gifts.get(position).getGiftPic())).into(pic);
        Boolean purchased = gifts.get(position).getPurchased();
        if (purchased == false)
        {
            box.setChecked(false);
        }
        else
        {
            box.setChecked(true);
        }

        holder.mView.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        //mActivity == View Person Activity
                        Intent intent = new Intent(mActivity, ViewGift.class);

                        Gift g = gifts.get(position);
                        Log.e("ITEM CLICKED", g.getGiftName() + "");

                        intent.putExtra("GIFT",  g);
                        (mActivity).startActivityForResult(intent, 10);
                    }
                });

    }

    @Override
    public int getItemCount()
    {
        return gifts.size();
    }
}
