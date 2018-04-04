package com.divyanshjain.snake;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by divyanshjain on 10/06/17.
 */

public class Mouse {

    private int x;
    private int y;
    ImageView imageView;


    public void addMouse() {
        int frameHeight = MainActivity.getInstance().getFrame().getHeight();
        int frameWidth = MainActivity.getInstance().getFrame().getWidth();

        System.out.println(frameHeight);
        imageView = new ImageView(MainActivity.getInstance());
        imageView.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(SnakeBodyBlock.WIDTH, SnakeBodyBlock.WIDTH); //height and width of image
        imageView.setLayoutParams(parms);
        imageView.setImageResource(R.drawable.orange);

        System.out.println(imageView.getWidth());

        x = (int) Math.floor(Math.random() * (frameWidth - imageView.getWidth()));
        y = (int) Math.floor(Math.random() * (frameHeight - imageView.getHeight()));

        System.out.println(x);
        System.out.println(y);
        System.out.println(frameWidth);
        System.out.println(frameHeight);

        imageView.setX(x);
        imageView.setY(y);
        MainActivity.getInstance().getFrame().addView(imageView);
    }

    public void removeMouse() {
        //imageView.setVisibility(View.GONE);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
