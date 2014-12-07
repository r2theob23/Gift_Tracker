package com.robertsmith.gifttracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Person> people =  new ArrayList<Person>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

//        for(int i = 0; i<1; i++)
//        {
//            people.add(new Person("Jessica","$1100",R.drawable.ic_launcher));
//        }

        mAdapter = new PeopleAdapter(people);
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
            startActivityForResult(intent, 0);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0)
        {
            String name = data.getStringExtra("NAME");
            String budget = data.getStringExtra("BUDGET");

            if(people != null)
            {
                people.add(new Person(name, budget, R.drawable.ic_launcher));
                mAdapter.notifyDataSetChanged();
            }
            else
            {
                people = new ArrayList<Person>();
                people.add(new Person(name, budget, R.drawable.ic_launcher));
                mAdapter.notifyDataSetChanged();
            }
        }

    }
    public void refresh()
    {
       mAdapter.notifyDataSetChanged();
    }
}
