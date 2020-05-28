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
    TextView tapText;
    TextView secondText;

    ImageButton coffeeAddButton;
    Button buyButton1;
    Button buyButton2;
    Button buyButton3;
    Button buyButton4;
    Button buyButton5;
    Button buyButton6;
    Button buyButton7;
    Button buyButton8;
    Button buyButton9;

    CoffeeButton coffeeButton1;
    CoffeeButton coffeeButton2;
    CoffeeButton coffeeButton3;
    CoffeeButton coffeeButton4;
    CoffeeButton coffeeButton5;
    CoffeeButton coffeeButton6;
    CoffeeButton coffeeButton7;
    CoffeeButton coffeeButton8;
    CoffeeButton coffeeButton9;

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
        tapText = (TextView)findViewById(R.id.tapText);
        secondText = (TextView)findViewById(R.id.secondText);

        //Buttons
        coffeeAddButton = (ImageButton)findViewById(R.id.coffeeButton);
        buyButton1 = (Button)findViewById(R.id.buyButton1);
        buyButton2 = (Button)findViewById(R.id.buyButton2);
        buyButton3 = (Button)findViewById(R.id.buyButton3);
        buyButton4 = (Button)findViewById(R.id.buyButton4);
        buyButton5 = (Button)findViewById(R.id.buyButton5);
        buyButton6 = (Button)findViewById(R.id.buyButton6);
        buyButton7 = (Button)findViewById(R.id.buyButton7);
        buyButton8 = (Button)findViewById(R.id.buyButton8);
        buyButton9 = (Button)findViewById(R.id.buyButton9);

        //Images
        backgroundImage = (ImageView)findViewById(R.id.backgroundImage);
        Bitmap bmp = BitmapFactory.decodeResource(this.getResources(), R.drawable.background);
        bmp = Bitmap.createScaledBitmap(bmp, bmp.getWidth(), bmp.getHeight(), false);
        backgroundImage.setImageBitmap(bmp);

        //Listeners
        coffeeAddButton.setOnClickListener(this);
        buyButton1.setOnClickListener(this);
        buyButton2.setOnClickListener(this);
        buyButton3.setOnClickListener(this);
        buyButton4.setOnClickListener(this);
        buyButton5.setOnClickListener(this);
        buyButton6.setOnClickListener(this);
        buyButton7.setOnClickListener(this);
        buyButton8.setOnClickListener(this);
        buyButton9.setOnClickListener(this);

        //CoffeeButton Setup
        coffeeButton1 = new CoffeeButton(coffee, buyButton1,
                CoffeeButton.ButtonType.PerSecond, 1,
                10, 8,
                "Coffee Maker");
        coffeeButton2 = new CoffeeButton(coffee, buyButton2,
                CoffeeButton.ButtonType.PerSecond, 5,
                100, 43,
                "Motivated Worker");
        coffeeButton5 = new CoffeeButton(coffee, buyButton5,
                CoffeeButton.ButtonType.Sweatshop, 10,
                10000, 2500,
                "Sweatshop");
        coffeeButton6 = new CoffeeButton(coffee, buyButton6,
                CoffeeButton.ButtonType.Slave, 30,
                10, 1,
                "Slave Labor");

        coffeeButton5.coffeeButtonChild = coffeeButton6;

        coffeeButton3 = new CoffeeButton(coffee, buyButton3,
                CoffeeButton.ButtonType.PerTap, 1,
                50, 75,
                "Extra Arm");
        coffeeButton7 = new CoffeeButton(coffee, buyButton7,
                CoffeeButton.ButtonType.PerTap, 15,
                700, 628,
                "Hard Amphetamines");

        coffeeButton4 = new CoffeeButton(coffee, buyButton4,
                CoffeeButton.ButtonType.MultiplySecond, 2,
                1000, 1358,
                "Rent a timeline");
        coffeeButton8 = new CoffeeButton(coffee, buyButton8,
                CoffeeButton.ButtonType.MultiplyTap, 2,
                500, 628,
                "Clone Thyself");

        coffeeButton9 = new CoffeeButton(coffee, buyButton9,
                CoffeeButton.ButtonType.Horror, 20,
                1000000000, 0,
                "Release the Eldrich Horror");

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
        //Log.d("CoffeeError", "Update - " + secondTimer);
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
        tapText.setText(coffee.coffeePerTap + "/tap");
        secondText.setText(coffee.coffeePerSecond + "/sec");
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
            case R.id.buyButton1:
                coffeeButton1.ButtonClick();
                break;
            case R.id.buyButton2:
                coffeeButton2.ButtonClick();
                break;
            case R.id.buyButton3:
                coffeeButton3.ButtonClick();
                break;
            case R.id.buyButton4:
                coffeeButton4.ButtonClick();
                break;
            case R.id.buyButton5:
                coffeeButton5.ButtonClick();
                break;
            case R.id.buyButton6:
                coffeeButton6.ButtonClick();
                break;
            case R.id.buyButton7:
                coffeeButton7.ButtonClick();
                break;
            case R.id.buyButton8:
                coffeeButton8.ButtonClick();
                break;
            case R.id.buyButton9:
                coffeeButton9.ButtonClick();
                break;
        }
    }
}
