package com.robertsmith.gifttracker;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by robertsmith on 12/4/14.
 */
public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder>
{
    private ArrayList<Person> people;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public View mView;

        public ViewHolder(View v)
        {
            super(v);
            mView = v;
        }
    }

    public PeopleAdapter(ArrayList<Person> people)
    {
        this.people = people;
    }

    @Override
    public PeopleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        TextView name = (TextView) holder.mView.findViewById(R.id.name);
        TextView budget = (TextView) holder.mView.findViewById(R.id.budget);
        ImageView pic = (ImageView) holder.mView.findViewById(R.id.imageView);

        name.setText(people.get(position).getName());
        budget.setText(people.get(position).getBudget());
        pic.setImageResource(people.get(position).getPic());
    }

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
}
