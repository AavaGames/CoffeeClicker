package com.example.coffeeclicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coffeeclicker.R;

public class UI extends AppCompatActivity {
    static int coffee;

    TextView coffeeText;

    public UI(Context context)
    {
        SetCoffee(0);

        coffeeText = (TextView)findViewById(R.id.coffeeText);

        UpdateUI();
    }

    public void UpdateUI()
    {
        coffeeText.setText("Coffee - " + coffee);
    }

    public void SetCoffee(int amount)
    {
        coffee = amount;
    }

    public void SddCoffee(int amount)
    {
        coffee += amount;
    }

    public void SubtractCoffee(int amount)
    {
        coffee -= amount;
    }
}
