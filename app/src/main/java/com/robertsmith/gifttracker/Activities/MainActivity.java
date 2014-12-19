package com.robertsmith.gifttracker.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.robertsmith.gifttracker.Adapters.PeopleAdapter;
import com.robertsmith.gifttracker.Data_Sources.Gift;
import com.robertsmith.gifttracker.Data_Sources.Person;
import com.robertsmith.gifttracker.R;

import java.security.PublicKey;
import java.util.ArrayList;

//Robert Smith
//Gift Tracker
//12/2014

public class MainActivity extends Activity {

    private RecyclerView mRecyclerView;
    public static PeopleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Person> people =  new ArrayList<Person>();
    public TextView totalBudget;
    public TextView totalSpent;
    public static Context context;
    public int bud = 0;

    public int ADD_PERSON_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalBudget = (TextView) findViewById(R.id.budgetLabel);
        totalSpent = (TextView) findViewById(R.id.spentLabel);

        totalBudget.setText(Integer.toString(bud));

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

//        for(int i = 0; i<1; i++)
//        {
//            people.add(new Person("Jessica","$1100",R.drawable.ic_launcher));
//        }

        mAdapter = new PeopleAdapter(people, this);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_new)
        {
            Intent intent = new Intent(this, AddPersonActivity.class);
            startActivityForResult(intent, ADD_PERSON_REQUEST);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_PERSON_REQUEST)
        {
            if(data != null)
            {
                String name = data.getStringExtra("NAME");
                String budget = data.getStringExtra("BUDGET");

                int newBudget = Integer.parseInt(budget);

                bud = newBudget + bud;
                totalBudget.setText(Integer.toString(bud));


                String pic = data.getStringExtra("URI");
                Uri picData = Uri.parse(pic);

                if(people != null)
                {
                    people.add(new Person(name, budget, pic));
                    mAdapter.notifyDataSetChanged();
                }
                else
                {
                    people = new ArrayList<Person>();
                    people.add(new Person(name, budget, pic));
                    mAdapter.notifyDataSetChanged();
                }
            }
        }

        Log.e("IN Activity RESULT", "Returned to home screen!");
        if(resultCode == 10)
        {
            if(data != null)
            {
                String name = data.getStringExtra("NAME");
                Person p = (Person) data.getSerializableExtra("PERSON");
                ArrayList<Gift> gifts = p.getGifts();
                Log.e("IN BACK RESULT", "received data! "+name);

                for(Person person: ((PeopleAdapter) mAdapter).getPeople())
                {
                    if(person.getName().equals(name))
                    {
                        person.setGifts(gifts);
                    }
                }
                mAdapter.notifyDataSetChanged();

            }
        }

    }
}
