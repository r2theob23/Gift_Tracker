package com.robertsmith.gifttracker.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.robertsmith.gifttracker.Adapters.GiftAdapter;
import com.robertsmith.gifttracker.Data_Sources.Gift;
import com.robertsmith.gifttracker.Data_Sources.Person;
import com.robertsmith.gifttracker.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ViewPerson extends Activity
{
    private ImageView imageView;
    private TextView nameTV;
    private TextView budgetTV;
    ArrayList<Gift> gifts =  new ArrayList<Gift>();
    Person mPerson;

    public static Context mContext;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public int ADD_GIFT_REQUEST = 0;
    public int ON_BACK_REQUEST = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_person);
        setTitle("View Person");
        setTitleColor(getResources().getColor(R.color.green));

        imageView = (ImageView) findViewById(R.id.personPic);
        nameTV = (TextView) findViewById(R.id.name);
        budgetTV = (TextView) findViewById(R.id.budget);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Intent i = getIntent();
        if (i != null)
        {
           mPerson = (Person)i.getSerializableExtra("PERSON");
//            String budget = extras.getString("BUDGET");
//            String pic = extras.getString("PIC");
//            Uri picData = Uri.parse(pic);

        }
        nameTV.setText(mPerson.getName());
        budgetTV.setText(mPerson.getBudget());
        Picasso.with(this).load(Uri.parse(mPerson.getPic())).into(imageView);

        if(mPerson.getGifts() == null)
        {
            mPerson.setGifts(gifts);
        }
        else
        {
            gifts = mPerson.getGifts();
        }

        mAdapter = new GiftAdapter(gifts);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_person, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_new_gift)
        {
            Intent intent = new Intent(this, AddGift.class);
            startActivityForResult(intent, ADD_GIFT_REQUEST);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        data.putExtra("NAME", mPerson.getName());
        data.putExtra("PERSON", mPerson);
        setResult(ON_BACK_REQUEST, data);
        finish();
//        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_GIFT_REQUEST)
        {
            if(data != null)
            {
                String name = data.getStringExtra("NAME");
                String price = data.getStringExtra("PRICE");
                String store = data.getStringExtra("STORE");
                String pic = data.getStringExtra("URI");
                Uri picData = Uri.parse(pic);

                if(gifts != null)
                {
                    Gift product = new Gift();
                    product.setGiftName(name);
                    product.setGiftPrice(price);
                    product.setGiftStore(store);
                    product.setGiftPic(pic);
                    gifts.add(product);

                    mPerson.setGifts(gifts);

                    mAdapter.notifyDataSetChanged();
                }
                else
                {
                    gifts = new ArrayList<Gift>();
                    Gift product = new Gift();
                    product.setGiftName(name);
                    product.setGiftPrice(price);
                    product.setGiftStore(store);
                    product.setGiftPic(pic);
                    gifts.add(product);
                    mPerson.setGifts(gifts);
                    MainActivity.mAdapter.notifyDataSetChanged();
                    mAdapter.notifyDataSetChanged();
                }
            }
        }

    }
}
