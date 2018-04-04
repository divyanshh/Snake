package com.divyanshjain.snake;

import android.util.Log;
import android.widget.ImageView;

/**
 * Created by divyanshjain on 31/05/17.
 */

public class SnakeBodyBlock {

    public static final int WIDTH = 60;
    public static final int WIDTH_BUFFER = 70;

    private Direction direction;
    private final ImageView imageView;

    public SnakeBodyBlock(Direction direction, ImageView imageView) {
        this.direction = direction;
        this.imageView = imageView;
    }

    public void changePos() {

        int frameHeight = MainActivity.getInstance().getFrame().getHeight();
        int frameWidth = MainActivity.getInstance().getFrame().getWidth();

        int x = (int) imageView.getX();
        int y = (int) imageView.getY();
        int flag = 0;
        int duration = 125;

        //Log.d("DIBBI", "before " + x + " " + y);

        if (direction == Direction.RIGHT) {

            x = x + SnakeBodyBlock.WIDTH;
            if (x > frameWidth) {
                x = -SnakeBodyBlock.WIDTH_BUFFER;
                flag = 1;
            }

        } else if (direction == Direction.LEFT) {

            x = x - SnakeBodyBlock.WIDTH;
            if (x < -SnakeBodyBlock.WIDTH_BUFFER) {
                x = frameWidth + 1;
                flag = 1;
            }

        } else if (direction == Direction.DOWN) {

            y = y + SnakeBodyBlock.WIDTH;
            if (y > frameHeight) {
                y = -SnakeBodyBlock.WIDTH_BUFFER;
                flag = 1;
            }

        } else {

            y = y - SnakeBodyBlock.WIDTH;
            if (y < -SnakeBodyBlock.WIDTH_BUFFER) {
                y = frameHeight + 1;
                flag = 1;
            }

        }

        //Log.d("DIBBI", x + " " + y);

        if (flag == 1) {
            duration = 0;
        }

        imageView.animate().translationX(x).setDuration(duration).start();
        imageView.animate().translationY(y).setDuration(duration).start();

    }

    public Direction getDirection() {
        return direction;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getX() {
        return (int) imageView.getX();
    }

    public int getY() {
        return (int) imageView.getY();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setX(int x) {
        this.imageView.setX(x);
    }

    public void setY(int y) {
        this.imageView.setY(y);
    }

}
