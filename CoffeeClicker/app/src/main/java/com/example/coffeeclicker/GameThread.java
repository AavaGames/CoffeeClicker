package com.example.coffeeclicker;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameThread extends Thread
{
    private boolean running;
    private boolean paused = false;
    GameActivity gameActivity;

    // This variable is used in case of extreme frame lengths - causes the game to slow down instead of skip.
    // The lower this is the high your frame rate must be.
    // A maxFrameTime of 0.1f means your target fps must be higher than 10.
    // final = constant
    private final float maxFrameTime = 0.1f;
    private long maxFrameTimeInMilliseconds = (long)(maxFrameTime * 1000);

    private float deltaTime;

    private long startOfFrameTime;
    private long endOfCodeTime;
    private long endOfFrameTime;

    public final int TARGET_FPS = 60;
    public static int ACTUAL_FPS = 0;

    public GameThread(GameActivity activity)
    {
        running = true;
        gameActivity = activity;
        this.start();
    }

    //Game code called every frame (e.g Update & Render)
    private void GameCode()
    {
        gameActivity.Update();
    }

    public void run()
    {
        while (running) {
            while (!paused) {
                startOfFrameTime = System.currentTimeMillis();

                GameCode();

                endOfCodeTime = System.currentTimeMillis();
                long codeExecutionTime = endOfCodeTime - startOfFrameTime;

                if (codeExecutionTime < maxFrameTimeInMilliseconds) {
                    long frameGoal = 1000 / TARGET_FPS;
                    long sleepTime = frameGoal - codeExecutionTime;

                    try {
                        Thread.sleep(sleepTime);
                    } catch (Exception ex) {
                        Log.e("Error", "EXCEPTION CAUGHT WHILE ATTEMPTING TO PUT THREAD TO SLEEP");
                        ex.printStackTrace();
                    }

                    endOfFrameTime = System.currentTimeMillis();

                    long frameTime = endOfFrameTime - startOfFrameTime;
                    deltaTime = (float) frameTime / 1000;
                } else {
                    deltaTime = maxFrameTime;
                    //ACTUAL_FPS = (int)(1000 / deltaTime);
                }

                ACTUAL_FPS = (int) (1 / deltaTime);
                //Log.d("Test", "FPS = " + ACTUAL_FPS + " - DeltaTime = " + deltaTime);
            }
        }
    }

    public void onPause()
    {
        paused = true;
        Log.d("Test", "Pause");
    }

    public void onResume()
    {
        paused = false;
        Log.d("Test", "Resume");
    }

    public float DeltaTime()
    {
        return deltaTime;
    }
}
