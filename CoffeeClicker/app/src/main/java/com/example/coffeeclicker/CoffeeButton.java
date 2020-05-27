package com.example.coffeeclicker;

import android.util.Log;
import android.widget.Button;

public class CoffeeButton
{
    private Coffee coffee;

    public Button button;

    public String text1;
    public String text2;

    public int cost;
    public int costIncrement;

    public int increasePerTap;
    public int increasePerSecond;

    public enum ButtonType { PerTap, PerSecond }
    public ButtonType buttonType;

    public CoffeeButton(Coffee cof, Button butto, ButtonType type, int variableIncrease, int price, int priceIncrement, String preCostText, String postCostText)
    {
        coffee = cof;
        button = butto;
        buttonType = type;

        cost = price;
        costIncrement = priceIncrement;
        text1 = preCostText;
        text2 = postCostText;

        if (buttonType == ButtonType.PerTap)
        {
            increasePerTap = variableIncrease;
        }
        else
        {
            increasePerSecond = variableIncrease;
        }

        UpdateText();
    }

    private void UpdateButton()
    {
        IncrementCost();
        UpdateText();
    }

    private void UpdateText()
    {
        button.setText(text1 + cost + text2);
    }

    private void IncrementCost()
    {
        cost += costIncrement;
    }

    public void ButtonClick()
    {
        Log.d("Test", "ButtonClicked");
        if (Coffee.coffee >= cost)
        {
            Log.d("Test", "Button Purchase");
            if (buttonType == ButtonType.PerTap)
            {
                BuyTap();
            }
            else if (buttonType == ButtonType.PerSecond)
            {
                BuySecond();
            }

            UpdateButton();
        }
    }

    private void BuyTap()
    {
        coffee.AddPerTap(increasePerTap);
        coffee.Subtract(cost);
    }

    private void BuySecond()
    {
        coffee.AddPerSecond(increasePerSecond);
        coffee.Subtract(cost);
    }
}
