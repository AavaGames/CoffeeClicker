package com.example.coffeeclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coffeeclicker.R;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    public static GameActivity gameActivity;
    private GameThread gameThread;
    private Coffee coffee;

    TextView coffeeText;

    ImageButton coffeeButton;

    ImageView backgroundImage;

    float secondTimer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeFullScreen();

        if(gameActivity != null)
        {
            onDestroy();
        }
        gameActivity = this;

        setContentView(R.layout.activity_game);

        coffee = new Coffee();

        //Texts
        coffeeText = (TextView)findViewById(R.id.coffeeText);

        //Buttons
        coffeeButton = (ImageButton)findViewById(R.id.coffeeButton);

        //Images
        backgroundImage = (ImageView)findViewById(R.id.backgroundImage);
        Bitmap bmp = BitmapFactory.decodeResource(this.getResources(), R.drawable.background);
        bmp = Bitmap.createScaledBitmap(bmp, bmp.getWidth()*5, bmp.getHeight()*5, false);
        backgroundImage.setImageBitmap(bmp);

        //Listeners
        coffeeButton.setOnClickListener(this);

        gameThread = new GameThread(gameActivity);
    }

    @Override
    protected void onPause(){
        super.onPause();

        if(gameThread != null){
            gameThread.onPause();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();

        if(gameThread != null){
            gameThread.onResume();
        }

        makeFullScreen();
    }

    public void Update()
    {
        Log.d("CoffeeError", "Update - " + secondTimer);
        UpdateUI();
        CoffeeOverTime();
    }

    private void makeFullScreen(){
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    private void UpdateUI()
    {
        coffeeText.setText("Coffee - " + coffee.GetCoffee());
    }

    private void CoffeeOverTime() {
        secondTimer += gameThread.DeltaTime();
        if (secondTimer > 1) {
            coffee.OverTime();
            secondTimer = 0;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.coffeeButton:
                coffee.Tap();
                break;
            default:
                break;
        }
    }
}
