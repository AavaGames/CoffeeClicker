package com.example.coffeeclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    private FinaleAnimation finale;

    public enum GameState { active, finale, end }
    public GameState gameState = GameState.active;

    TextView coffeeText;
    TextView tapText;
    TextView avgTapText;
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

    public TextView poemText;
    public TextView texts[] = new TextView[4];
    public Button buyButtons[] = new Button[9];
    public ImageView buttonImages[] = new ImageView[9];

    float secondTimer = 0;

    private long totalTaps = 0;
    private double totalTime = 0;
    private long averageTaps = 0;
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
        avgTapText = (TextView)findViewById(R.id.avgTapText);
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
                CoffeeButton.ButtonType.PerSecond, 10,
                10, 1.5f,
                "Coffee Maker");
        coffeeButton2 = new CoffeeButton(coffee, buyButton2,
                CoffeeButton.ButtonType.PerSecond, 50,
                50, 1.3f,
                "Motivated Worker");
        coffeeButton5 = new CoffeeButton(coffee, buyButton5,
                CoffeeButton.ButtonType.Sweatshop, 10,
                10000, 1.25f,
                "Sweatshop");
        coffeeButton6 = new CoffeeButton(coffee, buyButton6,
                CoffeeButton.ButtonType.Slave, 300,
                10, 1f,
                "Slave Labor");

        coffeeButton5.coffeeButtonChild = coffeeButton6;

        coffeeButton3 = new CoffeeButton(coffee, buyButton3,
                CoffeeButton.ButtonType.PerTap, 10,
                100, 1.75f,
                "Extra Arm");
        coffeeButton7 = new CoffeeButton(coffee, buyButton7,
                CoffeeButton.ButtonType.PerTap, 150,
                700, 1.2f,
                "Hard Amphetamines");

        coffeeButton4 = new CoffeeButton(coffee, buyButton4,
                CoffeeButton.ButtonType.MultiplySecond, 2,
                5000, 3.0f,
                "Rent a timeline");
        coffeeButton8 = new CoffeeButton(coffee, buyButton8,
                CoffeeButton.ButtonType.MultiplyTap, 2,
                4500, 1.5f,
                "Clone Thyself");

        coffeeButton9 = new CoffeeButton(coffee, buyButton9,
                CoffeeButton.ButtonType.Horror, 20,
                100000000, 0,
                "Release the Eldrich Horror");
        coffeeButton9.gameActivity = this;

        //References FinaleAnimation
        poemText = (TextView) findViewById(R.id.poemText);
        texts[0] = (TextView) findViewById(R.id.coffeeText);
        texts[1] = (TextView) findViewById(R.id.tapText);
        texts[2] = (TextView) findViewById(R.id.avgTapText);
        texts[3] = (TextView) findViewById(R.id.secondText);

        buyButtons[0] = buyButton1;
        buyButtons[1] = buyButton2;
        buyButtons[2] = buyButton3;
        buyButtons[3] = buyButton4;
        buyButtons[4] = buyButton5;
        buyButtons[5] = buyButton6;
        buyButtons[6] = buyButton7;
        buyButtons[7] = buyButton8;
        buyButtons[8] = buyButton9;

        for (int i = 1; i < 10; i++) {
            int id = getResources().getIdentifier("buyImage"+i, "id", getPackageName());
            buttonImages[i-1] = (ImageView)findViewById(id);
        }

        gameThread = new GameThread(gameActivity);

        //ImageView coffeeImage = (ImageView)findViewById(R.id.coffeeImage);
        ImageView happyImage = (ImageView)findViewById(R.id.happyImage);
        ImageView backgroundImage = (ImageView)findViewById(R.id.backgroundImage);
        finale = new FinaleAnimation(gameActivity, coffeeAddButton, backgroundImage, happyImage);
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

    private void makeFullScreen(){
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    public void Update()
    {
        runOnUiThread(
            new Runnable() {
                @Override
                public void run()
                {
                    totalTime += gameThread.DeltaTime();

                    if (gameState == GameState.active)
                    {
                        AverageTaps();
                        SecondCounter();
                        UpdateUI();
                    }
                    else if (gameState == GameState.finale)
                    {
                        finale.Update(gameThread.DeltaTime());
                    }
                    //Log.d("Test", "Update - " + secondTimer);
                }
            }
        );
    }

    private void AverageTaps()
    {
        averageTaps = (long)((double)totalTaps / totalTime);
    }

    private void SecondCounter()
    {
        secondTimer += gameThread.DeltaTime();
        if (secondTimer > 1) {

            //Code that happens once a second
            coffee.OverTime();
            if (coffee.GetCoffee() >= coffeeButton9.cost)
            {
                coffeeButton9.ButtonAvailable(true);
            }
            else
            {
                coffeeButton9.ButtonAvailable(false);
            }

            secondTimer = 0;
        }
    }

    private void UpdateUI()
    {
        coffeeText.setText("Coffee - " + coffee.GetCoffee());
        tapText.setText(coffee.coffeePerTap + "/tap");
        avgTapText.setText(averageTaps + " taps/s");
        secondText.setText(coffee.coffeePerSecond + "/sec");
    }

    @Override
    public void onClick(View v)
    {
        if (gameState == GameState.active)
        {
            switch (v.getId())
            {
                case R.id.coffeeButton:
                    totalTaps++;
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
        else if (gameState == GameState.end)
        {
            if (v.getId() == R.id.buyButton9)
            {
                Runtime.getRuntime().exit(0);
            }
        }
    }

    public void StartFinale()
    {
        UpdateUI();
        gameState = GameState.finale;
    }

    public void End()
    {
        buyButton9.setAlpha(1.0f);
        buttonImages[8].setAlpha(1.0f);
        buyButton9.setText("Enter Bliss");

        gameState = GameState.end;
    }
}
