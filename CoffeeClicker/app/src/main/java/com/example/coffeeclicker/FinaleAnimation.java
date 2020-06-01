package com.example.coffeeclicker;

import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class FinaleAnimation
{
    GameActivity gameActivity;

    //private enum AnimationState { FadingButtons, Intro, Poem, End }
    //private AnimationState state = AnimationState.FadingButtons;

    private boolean fadeButtons = true;
    private boolean meltCoffee = true;

    final float safeSmallestFloat = 0.001f;

    private float deltaTime;

    public FinaleAnimation(GameActivity game)
    {
        gameActivity = game;
    }

    public void Update(float deltaT)
    {
        deltaTime = deltaT;

        if (fadeButtons)
        {
            FadeButtons();
        }
        if (meltCoffee)
        {
            MeltCoffee();
        }
    }

    public void Start()
    {
        //On animation start
    }

    private float fadingTimer = 0;
    private float timeToFade = 2;

    private void FadeButtons()
    {
        fadingTimer += deltaTime;
        float alpha = 1 - (fadingTimer / timeToFade);

        for (TextView text : gameActivity.texts)
        {
            text.setAlpha((alpha));
        }

        for (Button button : gameActivity.buyButtons)
        {
            button.setAlpha(alpha);
        }

        for (ImageView image : gameActivity.buttonImages)
        {
            image.setAlpha(alpha);
        }

        if (alpha < safeSmallestFloat)
        {
            fadeButtons = false;
        }
    }

    private void MeltCoffee()
    {

    }

}
