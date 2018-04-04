package com.divyanshjain.snake;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static MainActivity _mainActtivity = null;

    private Timer timer = new Timer();
    private Handler handler = new Handler();
    private Direction direction = Direction.RIGHT;
    private FrameLayout frame;

    private int timerStart = 1;
    private SnakeBody snakebody;
    private Mouse mouse;

    float x1, x2;
    float y1, y2;

    public static MainActivity getInstance() {
        return _mainActtivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        _mainActtivity = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frame = (FrameLayout) findViewById(R.id.frameLayout);

        snakebody = new SnakeBody();
        mouse = new Mouse();
        mouse.addMouse();
        direction = snakebody.getDirection();

        MobileAds.initialize(getApplicationContext(), " ca-app-pub-4527272160231444~5516063211");
        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("B3831012E69C2476CA3042F94FB057AE").build();
        adView.loadAd(adRequest);
    }

    public FrameLayout getFrame() {
        return frame;
    }

    // onTouchEvent () method gets called when User performs any touch event on screen
    // Method to handle touch event like left to right swap and right to left swap
    public boolean onTouchEvent(MotionEvent touchevent) {

        switch (touchevent.getAction()) {
            // when user first touches the screen we get x and y coordinate
            case MotionEvent.ACTION_DOWN: {
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            }
            case MotionEvent.ACTION_UP: {
                x2 = touchevent.getX();
                y2 = touchevent.getY();

                //if left to right sweep event on screen
                if (x1 < x2 && (x2 - x1) > 100 && direction != Direction.LEFT) {
                    //Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_SHORT).show();
                    direction = Direction.RIGHT;
                }

                // if right to left sweep event on screen
                if (x1 > x2 && (x1 - x2) > 100 && direction != Direction.RIGHT) {
                    //Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_SHORT).show();
                    direction = Direction.LEFT;
                }

                // if UP to Down sweep event on screen
                if (y1 < y2 && (y2 - y1) > 100 && direction != Direction.TOP) {
                    //Toast.makeText(this, "UP to Down Swap Performed", Toast.LENGTH_SHORT).show();
                    direction = Direction.DOWN;
                }

                //if Down to UP sweep event on screen
                if (y1 > y2 && (y1 - y2) > 100 && direction != Direction.DOWN) {
                    //Toast.makeText(this, "Down to UP Swap Performed", Toast.LENGTH_SHORT).show();
                    direction = Direction.TOP;
                }

                if (timerStart == 1) { // if not used then it is movesnake() is called every time we touch the screen
                    moveSnake();
                    timerStart = 0;
                }

                break;
            }
        }
        return false;
    }

    private void moveSnake() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        snakebody.changeSnakeBodyPos(direction);
                        snakebody.detectCollision();
                    }
                });
            }
        }, 0, 250);
    }

}
