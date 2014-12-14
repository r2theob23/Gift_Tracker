package com.robertsmith.gifttracker.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.robertsmith.gifttracker.Activities.MainActivity;
import com.robertsmith.gifttracker.Activities.ViewPerson;
import com.robertsmith.gifttracker.Data_Sources.Person;
import com.robertsmith.gifttracker.R;

import java.util.ArrayList;

/**
 * Created by robertsmith on 12/4/14.
 */
public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder>
{
    private static ArrayList<Person> people;
//***************************************************************************************************
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public View mView;

        public ViewHolder(View v)
        {
            super(v);
            v.setOnClickListener(this);
            mView = v;
        }


        @Override
        public void onClick(View view)
        {
            Log.e("ITEM CLICKED", people.get(getPosition()).getName() + "");
            Intent intent = new Intent(view.getContext(), ViewPerson.class);

            intent.putExtra("NAME", people.get(getPosition()).getName());
            intent.putExtra("BUDGET", people.get(getPosition()).getBudget());
            intent.putExtra("PIC", people.get(getPosition()).getPic().toString());

            Log.e("***********", people.get(getPosition()).getPic().toString() + "--> " + people.get(getPosition()).getPic().toString());


            view.getContext().startActivity(intent);

        }
    }
//***************************************************************************************************
    public PeopleAdapter(ArrayList<Person> people, Context context)
    {
        this.people = people;
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
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        TextView name = (TextView) holder.mView.findViewById(R.id.name);
        TextView budget = (TextView) holder.mView.findViewById(R.id.budget);
        ImageView pic = (ImageView) holder.mView.findViewById(R.id.imageView);

        name.setText(people.get(position).getName());
        budget.setText(people.get(position).getBudget());
        pic.setImageURI(people.get(position).getPic());
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
}
