package com.robertsmith.gifttracker.Activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.robertsmith.gifttracker.R;
import com.squareup.picasso.Picasso;

//Robert Smith
//Gift Tracker
//12/2014

public class AddPersonActivity extends Activity
{
    private EditText nameTF;
    private EditText budgetTF;
    private ImageView pic;
    private Uri imageUri;

    public int GALLERY_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        setTitle("Add Person");

        //Text Fields that gather person data
        nameTF = (EditText) findViewById(R.id.nameTF);
        budgetTF = (EditText) findViewById(R.id.budgetTF);
        pic = (ImageView) findViewById(R.id.giftPic);

        if (imageUri == null)
        {
            pic.setImageResource(R.drawable.ic_action_picture);
        }

//        cameraButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST_CODE);
//            }
//        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            if (requestCode == GALLERY_REQUEST_CODE)
            {
                imageUri = data.getData();
                Log.e("Image Path",imageUri+ "--> "+imageUri.getPath());
                Picasso.with(this).load(imageUri).into(pic);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_person, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_accept)
        {
            if(!nameTF.getText().toString().equals("") && !budgetTF.getText().toString().equals("") && imageUri != null)
            {
                String name = nameTF.getText().toString();
                String budget = budgetTF.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("NAME", name);
                intent.putExtra("BUDGET", budget);
                if (imageUri != null)
                {
                    intent.putExtra("URI", imageUri.toString());
                }

                setResult(0, intent);

                finish();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Error, Please Enter All Required Information!!", Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.action_gallery)
        {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST_CODE);
        }
        return super.onOptionsItemSelected(item);
    }
}
