package com.example.coffeeclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    Button Play;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        makeFullScreen();
        setContentView(R.layout.activity_main);

        Play = (Button)findViewById(R.id.buttonPlay);
        Play.setOnClickListener(this);
        Play.setVisibility(View.VISIBLE);

        imageView = (ImageView)findViewById(R.id.backgroundImage);
        Bitmap bmp = BitmapFactory.decodeResource(this.getResources(), R.drawable.background);
        bmp = Bitmap.createScaledBitmap(bmp, bmp.getWidth(), bmp.getHeight(), false);
        imageView.setImageBitmap(bmp);
    }

    @Override
    public void onClick(View view)
    {
        //If we clicked the Play button - Activate the GameActivity
        if (view.getId() == Play.getId())
        {
            Intent i;
            i = new Intent(this, com.example.coffeeclicker.GameActivity.class);
            startActivity(i);
        }
    }
    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        makeFullScreen();
    }

    public void  makeFullScreen()
    {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                         | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}