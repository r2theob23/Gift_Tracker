package com.robertsmith.gifttracker;

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

    // Provide a suitable constructor (depends on the kind of dataset)
    public PeopleAdapter(ArrayList<Person> people)
    {
        this.people = people;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PeopleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
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

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return people.size();
    }
}
