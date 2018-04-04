package com.divyanshjain.snake;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by divyanshjain on 08/06/17.
 */

public class SnakeBody {

    private List<SnakeBodyBlock> snake;

    public SnakeBody() {
        snake = new ArrayList<>();

        addSnakeHead(120, 0, Direction.RIGHT);
        addSnakeBody();
        addSnakeBody();
    }

    private void addSnakeBody() {
        SnakeBodyBlock currentTail = snake.get(snake.size() - 1);
        ImageView imageView = new ImageView(MainActivity.getInstance());
        imageView.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(SnakeBodyBlock.WIDTH, SnakeBodyBlock.WIDTH); //height and width of image
        imageView.setLayoutParams(parms);
        imageView.setImageResource(R.drawable.orange);
        if (currentTail.getDirection() == Direction.DOWN) {
            imageView.setX(currentTail.getX());
            imageView.setY(currentTail.getY() - SnakeBodyBlock.WIDTH);
        } else if (currentTail.getDirection() == Direction.TOP) {
            imageView.setX(currentTail.getX());
            imageView.setY(currentTail.getY() + SnakeBodyBlock.WIDTH);
        } else if (currentTail.getDirection() == Direction.LEFT) {
            imageView.setX(currentTail.getX() + SnakeBodyBlock.WIDTH);
            imageView.setY(currentTail.getY());
        } else {
            imageView.setX(currentTail.getX() - SnakeBodyBlock.WIDTH);
            imageView.setY(currentTail.getY());
        }
        MainActivity.getInstance().getFrame().addView(imageView);

        this.snake.add(new SnakeBodyBlock(currentTail.getDirection(), imageView));
    }

    private void addSnakeHead(int x, int y, Direction direction) {
        ImageView imageView = new ImageView(MainActivity.getInstance());
        imageView.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(SnakeBodyBlock.WIDTH, SnakeBodyBlock.WIDTH); //height and width of image
        imageView.setLayoutParams(parms);
        imageView.setImageResource(R.drawable.orange);
        imageView.setX(x);
        imageView.setY(y);
        MainActivity.getInstance().getFrame().addView(imageView);
        this.snake.add(new SnakeBodyBlock(direction, imageView));
    }


    public void changeSnakeBodyPos(Direction direction) {
        Direction prevDirection = direction;

        for (int i = 0; i < snake.size(); i++) {
            SnakeBodyBlock block = snake.get(i);
            Direction tempPrevDirection = block.getDirection();
            block.setDirection(prevDirection);
            prevDirection = tempPrevDirection;
            //Log.d("DIBBI", "i = " + i);
            block.changePos();
        }
    }

    public void detectCollision() {

        SnakeBodyBlock currentHead = snake.get(1);
        Mouse mouse = new Mouse();

        if (currentHead.getDirection() == Direction.DOWN) {
            int headX = currentHead.getX();
            int headY = currentHead.getY() + 60;
            int mouseX = mouse.getX() + 30;
            int mouseY = mouse.getY();

            if (mouseY == headY && mouseX > headX && mouseX < (headX+60)) {
                addSnakeBody();
                mouse.removeMouse();
                mouse.addMouse();
            }
        }

        else if (currentHead.getDirection() == Direction.TOP) {
            int headX = currentHead.getX();
            int headY = currentHead.getY();
            int mouseX = mouse.getX() + 30;
            int mouseY = mouse.getY() + 60;

            if (mouseY == headY && mouseX > headX && mouseX < (headX+60)) {
                addSnakeBody();
                mouse.removeMouse();
                mouse.addMouse();
            }
        }

        else if (currentHead.getDirection() == Direction.LEFT) {
            int headX = currentHead.getX() + 60;
            int headY = currentHead.getY();
            int mouseX = mouse.getX();
            int mouseY = mouse.getY() + 30;

            if (mouseX == headX && mouseY > headY && mouseY < (headY+60)) {
                addSnakeBody();
                mouse.removeMouse();
                mouse.addMouse();
            }
        }

        else if (currentHead.getDirection() == Direction.RIGHT) {
            int headX = currentHead.getX();
            int headY = currentHead.getY();
            int mouseX = mouse.getX() + 60;
            int mouseY = mouse.getY() + 30;

            if (mouseX == headX && mouseY > headY && mouseY < (headY+60)) {
                addSnakeBody();
                mouse.removeMouse();
                mouse.addMouse();
            }
        }

    }

    public Direction getDirection() {
        return snake.get(0).getDirection();
    }
}
