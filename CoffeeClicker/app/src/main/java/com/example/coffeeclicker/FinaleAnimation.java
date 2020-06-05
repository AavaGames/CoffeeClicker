package com.example.coffeeclicker;

import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
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

    private boolean fadeButtons = false;
    private boolean start = true;

    final float safeSmallestFloat = 0.001f;

    private float deltaTime;

    private boolean poemFinished = false;
    private float poemTimer = 0.0f;
    private float poemStartTime = 11.0f;
    private float poemLineInterval = 2f;
    private String poem[] = { "There was truth after all.\n",
        "And all had witnessed it.\n",
        "Everything became one with their creator.\n",
        "The petty insignificance of themselves melted into the void.\n",
        "There was meaning to the universe.\n",
        "One must imagine them happy." };

    public ImageButton coffeeImage;
    public ImageView backgroundImage;
    public ImageView happyImage;

    public FinaleAnimation(GameActivity game, ImageButton coffeeImageView, ImageView backgroundImageView, ImageView happyImageView)
    {
        gameActivity = game;
        coffeeImage = coffeeImageView;
        backgroundImage = backgroundImageView;
        happyImage = happyImageView;
    }

    public void Start()
    {
        fadeButtons = true;
        CoffeeAnimation();
        BackgroundAnimation();
        HappyAnimation();
    }

    public void Update(float deltaT)
    {
        deltaTime = deltaT;

        if (start)
        {
            Start();
            start = false;
        }

        if (fadeButtons)
        {
            FadeButtons();
        }

        if (!poemFinished)
        {
            poemTimer += deltaTime;
            if (poemTimer > poemStartTime)
            {
                Poem();
            }
        }
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

    private void CoffeeAnimation()
    {
        coffeeImage.setImageResource(R.drawable.coffee_animation);
        AnimationDrawable animation = (AnimationDrawable)coffeeImage.getDrawable();
        animation.start();
    }

    private void BackgroundAnimation()
    {
        backgroundImage.setImageResource(R.drawable.background_animation);
        AnimationDrawable animation = (AnimationDrawable)backgroundImage.getDrawable();
        animation.start();
    }

    private void HappyAnimation()
    {
        happyImage.setAlpha(1.0f);
        happyImage.setImageResource(R.drawable.happy_animation);
        AnimationDrawable animation = (AnimationDrawable)happyImage.getDrawable();
        animation.start();
    }

    private void Poem()
    {
        String text = "";

        float timer = poemTimer - poemStartTime;
        int lines = 1 + (int)(timer / poemLineInterval);

        for (int i = 0; i < lines; i++)
        {
            if (i >= poem.length)
            {
                Log.d("Test", "POEM FINISHED, END STATE");
                poemFinished = true;
                gameActivity.End();
                break;
            }
            text += poem[i];
        }

        gameActivity.poemText.setText(text);
    }
}
