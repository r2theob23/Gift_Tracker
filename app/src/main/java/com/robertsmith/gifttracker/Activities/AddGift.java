package com.robertsmith.gifttracker.Activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

public class AddGift extends Activity {

    private EditText productNameTF;
    private EditText productPriceTF;
    private EditText productStoreTF;
    private ImageView productIV;
    private Uri imageUri;


    public int CAMERA_REQUEST_CODE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gift);
        setTitle("Add Gift");

        productNameTF = (EditText) findViewById(R.id.giftName);
        productPriceTF = (EditText) findViewById(R.id.giftPrice);
        productStoreTF = (EditText) findViewById(R.id.giftStore);
        productIV = (ImageView) findViewById(R.id.giftPic);

        if (imageUri == null)
        {
            productIV.setImageResource(R.drawable.ic_action_picture);
        }


//        addPicButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), CAMERA_REQUEST_CODE);
//            }
//        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            if (requestCode == CAMERA_REQUEST_CODE)
            {
                imageUri = data.getData();
                Log.e("Image Path", imageUri + "--> " + imageUri.getPath());
                Picasso.with(this).load(imageUri).into(productIV);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_gift, menu);
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
        if (id == R.id.action_accept)
        {
            if(!productNameTF.getText().toString().equals("") && !productPriceTF.getText().toString().equals("") && imageUri != null)
            {
                String name = productNameTF.getText().toString();
                String budget = productPriceTF.getText().toString();
                String store = productStoreTF.getText().toString();
                Boolean purchased = false;

                Intent intent = new Intent();
                intent.putExtra("NAME", name);
                intent.putExtra("PRICE", budget);
                intent.putExtra("STORE", store);
                intent.putExtra("PURCHASED", purchased);
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
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), CAMERA_REQUEST_CODE);
        }
        return super.onOptionsItemSelected(item);
    }
}
