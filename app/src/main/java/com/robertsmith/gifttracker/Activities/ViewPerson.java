package com.robertsmith.gifttracker.Activities;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.robertsmith.gifttracker.R;
import com.squareup.picasso.Picasso;


public class ViewPerson extends Activity
{
    private ImageView imageView;
    private TextView nameTV;
    private TextView budgetTV;

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

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            String name = extras.getString("NAME");
            String budget = extras.getString("BUDGET");
            String pic = extras.getString("PIC");
            Uri picData = Uri.parse(pic);

            Log.e("Image Path", picData + "--> " + picData.getPath());

            nameTV.setText(name);
            budgetTV.setText(budget);
            Picasso.with(this).load(picData).into(imageView);

        }

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
