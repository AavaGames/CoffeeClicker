package com.example.coffeeclicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class CanvasLevel extends SurfaceView implements SurfaceHolder.Callback {
    //Declare thread
    com.example.coffeeclicker.GameThread gameThread;

    //Declare gameState
    com.example.coffeeclicker.GameStateManager gameState;

    //Declare ui
    UI ui;

    //Declare game objects


    Bitmap spriteSheet;
    com.example.coffeeclicker.GameActivity gameActivity;

    public CanvasLevel(Context context, int screenLength, int screenHeight, com.example.coffeeclicker.GameActivity _gameActivity) {
        super(context);
        //Constructor - Defaults will be useful here
        gameActivity = _gameActivity;

        //Instantiate the sprite sheet for the game.
        //spriteSheet = BitmapFactory.decodeResource(context.getResources(), R.drawable.spritesheet);
        getHolder().addCallback(this);

        ui = new UI(context);

        //Instantiate game objects here
        //gameObject = new GameObject();
        //gameObject.size = 50;
        //...
    }

    public void update(float deltaTime) {
        //Use for game object collision and update

        //gameObject.update(deltaTime);

        //if(gameObject.isColliding(enemy)){
            //gameObject.onCollisionEnter(enemy);
            //Log.i("Collision", "Collision Detected" + enemy);
        //}
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        //Set background to blue
        canvas.drawColor(Color.BLUE);

        //Draw UI
        ui.UpdateUI();

        //Draw Object
        //object.draw(canvas);
        //...
    }

    public void reset(){
        //Reset Objects
        //gameObject.restartObject();
    }

    public Boolean isCompleted(){
        return false;
    }

    public void onPause(){
        if(gameThread != null){
            gameThread.onPause();
        }
    }

    public void onResume(){
        if(gameThread != null){
            gameThread.onResume();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //Initialize thread
        gameThread = new com.example.coffeeclicker.GameThread();
        //Initialize gameState
        gameState = new com.example.coffeeclicker.GameStateManager(this);
        //Start thread
        gameThread.activateGameThread(this.getHolder(), gameState);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        //FINGER DOWN OR MOVE
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_MOVE){
/*            //if (motionEvent.getX() < paddle.getPosition().x + (paddle.getLength()/2))
            {
                //Log.i("Log", "Move Left");
                //paddle.move(-1, (int)motionEvent.getX());
            }
            else
                {
                //Log.i("Log", "Move Right");
                //paddle.move(1, (int)motionEvent.getX());
            }*/
        }
        //FINGER OFF
        else if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
            //paddle.move(0, (int)motionEvent.getX());
            //Log.i("Log", "Stop Movement");
        }

        return true;
    }
}
