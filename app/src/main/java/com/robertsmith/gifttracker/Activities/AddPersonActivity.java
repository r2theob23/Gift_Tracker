package com.robertsmith.gifttracker.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;

public class AddPersonActivity extends Activity
{
    private Context context;
    private EditText nameTF;
    private EditText budgetTF;
    private ImageView pic;
    private Button cameraButton;
    private Uri imageUri;
    private String selectedImagePath;

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
        pic = (ImageView) findViewById(R.id.imageView);

        cameraButton = (Button) findViewById(R.id.camera);

        cameraButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST_CODE);
            }
        });


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
            if(!nameTF.getText().toString().equals("") && !budgetTF.getText().toString().equals(""))
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
        return super.onOptionsItemSelected(item);
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

    public String getPath(Uri uri)
    {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null )
        {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return uri.getPath();
    }

}
