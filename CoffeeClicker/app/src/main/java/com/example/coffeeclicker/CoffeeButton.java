package com.example.coffeeclicker;

import android.graphics.Color;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.ColorInt;

public class CoffeeButton
{
    private Coffee coffee;

    public Button button;
    public CoffeeButton coffeeButtonChild;

    public String title;

    public long cost;
    public float costPercentageIncrement;

    public int increasePer;
    private static int laborCapacity = 0;

    public enum ButtonType { PerTap, PerSecond, MultiplyTap, MultiplySecond, Sweatshop, Slave, Horror }
    public ButtonType buttonType;

    public CoffeeButton(Coffee cof, Button butto, ButtonType type, int variableIncrease,
                        int price, float pricePercentageIncrement, String name)
    {
        coffee = cof;
        button = butto;
        buttonType = type;

        cost = price;
        costPercentageIncrement = pricePercentageIncrement;
        title = name;

        increasePer = variableIncrease;

        UpdateText();
    }

    private void UpdateButton()
    {
        UpdateText();
    }

    private void UpdateText()
    {
        String text = title + "\n";
        if (buttonType == ButtonType.Sweatshop)
        {
            text = text + "Purchase " + increasePer + " Labor\n";
        }
        else if (!(buttonType == ButtonType.Horror))
        {
            if (buttonType == ButtonType.MultiplySecond || buttonType == ButtonType.MultiplyTap)
            {
                text = text + "x";
            }
            else
            {
                text = text + "+";
            }

            text = text + increasePer + " Coffee/";

            if (buttonType == ButtonType.PerTap  || buttonType == ButtonType.MultiplyTap)
            {
                text = text + "tap\n";
            }
            else
            {
                text = text + "sec\n";
            }
        }
        text = text + "Cost: " + cost;

        button.setText(text);
    }

    private void IncrementCost()
    {
        cost = (long)((float)cost * costPercentageIncrement);
    }

    public void ButtonClick()
    {
        if (buttonType == ButtonType.Slave && laborCapacity < 1)
        {
            Log.d("Test", "Not enough capacity for " + title);
            //Play Error Sound
            return;
        }

        if (Coffee.coffee >= cost)
        {
            Buy();
            UpdateButton();

            Log.d("Test", "Purchased " + title);
        }
        else
        {
            Log.d("Test", "Not enough money for " + title);
            //Play Error Sound
        }
    }

    private void Buy()
    {
        if (buttonType == ButtonType.PerTap)
        {
            coffee.AddPerTap(increasePer);
        }
        else if (buttonType == ButtonType.PerSecond)
        {
            coffee.AddPerSecond(increasePer);
        }
        else if (buttonType == ButtonType.MultiplyTap)
        {
            coffee.MultiplyPerTap(increasePer);
        }
        else if (buttonType == ButtonType.MultiplySecond)
        {
            coffee.MultiplyPerSecond(increasePer);
        }
        else if (buttonType == ButtonType.Sweatshop)
        {
            laborCapacity += increasePer;
            //Reference to slave button
            coffeeButtonChild.ButtonAvailable(true);
        }
        else if (buttonType == ButtonType.Slave)
        {
            Log.i("Slave", "Cap - " + laborCapacity);
            if (laborCapacity != 0)
            {
                laborCapacity--;
                coffee.AddPerSecond(increasePer);
            }
            Log.i("Slave", "After - " + laborCapacity);
            if (laborCapacity < 1)
            {
                ButtonAvailable(false);
            }
        }
        else if (buttonType == ButtonType.Horror)
        {

        }

        coffee.Subtract(cost);
        IncrementCost();
    }

    public void ButtonAvailable(boolean show)
    {
        if (show)
        {
            button.setTextColor(0xFFFFFFFF);
        }
        else
        {
            button.setTextColor(0x4DFFFFFF);
        }
    }
}
