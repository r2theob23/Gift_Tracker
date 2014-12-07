package com.robertsmith.gifttracker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.robertsmith.gifttracker.R;

import java.io.File;

public class AddPersonActivity extends Activity
{
    private EditText nameTF;
    private EditText budgetTF;
    private Button cameraButton;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        //Text Fields that gather person data
        nameTF = (EditText) findViewById(R.id.nameTF);
        budgetTF = (EditText) findViewById(R.id.budgetTF);

        cameraButton = (Button) findViewById(R.id.camera);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photo));
                imageUri = Uri.fromFile(photo);
                startActivityForResult(intent, 1);
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
            if(nameTF.getText() != null && budgetTF.getText() != null)
            {
                String name = nameTF.getText().toString();
                String budget = budgetTF.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("NAME", name);
                intent.putExtra("BUDGET", budget);

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
        if(requestCode == 1)
        {
            if (resultCode == Activity.RESULT_OK)
            {

            }
        }
    }
}
