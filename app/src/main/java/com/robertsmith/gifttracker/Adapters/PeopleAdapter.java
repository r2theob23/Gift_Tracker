package com.robertsmith.gifttracker.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.robertsmith.gifttracker.Activities.MainActivity;
import com.robertsmith.gifttracker.Activities.ViewPerson;
import com.robertsmith.gifttracker.Data_Sources.Person;
import com.robertsmith.gifttracker.R;

import java.util.ArrayList;

//Robert Smith
//Gift Tracker
//12/2014

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder>
{
    private static ArrayList<Person> people;
    private static Activity mActivity;
//***************************************************************************************************
    public static class ViewHolder extends RecyclerView.ViewHolder //implements View.OnClickListener
    {
        public View mView;

        public ViewHolder(View v)
        {
            super(v);
            mView = v;
        }

    }
//***************************************************************************************************
    public PeopleAdapter(ArrayList<Person> people, Activity activity)
    {
        //people == DS
        this.people = people;
        //mActivity == Main Activity
        this.mActivity = activity;
    }
//***************************************************************************************************
    @Override
    public PeopleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
//***************************************************************************************************
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        //Assign vars to elements
        TextView name = (TextView) holder.mView.findViewById(R.id.name);
        TextView budget = (TextView) holder.mView.findViewById(R.id.budget);
        ImageView pic = (ImageView) holder.mView.findViewById(R.id.giftPic);


        name.setText(people.get(position).getName());
        budget.setText(people.get(position).getBudget());
        pic.setImageURI(Uri.parse(people.get(position).getPic()));
        holder.mView.setOnClickListener(
        new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mActivity == main activity
                Intent intent = new Intent(mActivity, ViewPerson.class);

                Person p = people.get(position);
                Log.e("ITEM CLICKED", p.getName() + "");

                //pass entire object
                intent.putExtra("PERSON",  p);
                ((MainActivity) mActivity).startActivityForResult(intent, 10);
            }
        });
    }
//***************************************************************************************************
    @Override
    public int getItemCount()
    {
        if(people == null)
        {
            return 0;
        }
        else
        {
            return people.size();
        }
    }

    public static ArrayList<Person> getPeople() {
        return people;
    }
}
