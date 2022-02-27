package com.plataformer.entity;

import java.awt.event.KeyAdapter;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.Color;
enum Direction{
    UP(1), DOWN(2), LEFT(3), RIGHT(4);

    private Integer value;

    Direction(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}

public class Enemy extends Entity {
    public Integer movingTo = Direction.LEFT.getValue();
    boolean moveLeft=true,moveRight=true,moveUp=true,moveDown=true;
    private Color color;
    public boolean died = false;

    public Enemy(float x, float y) {
        super("Enemy", x, y, 50, 50);
        color = Color.yellow;

        // random 0,1 
        Random r = new Random();
        int random = r.nextInt(2);
        if(random == 0) {
            movingTo = Direction.LEFT.getValue();
        } else {
            movingTo = Direction.RIGHT.getValue();
        }
    }

    public void movingLeft() {
        x = x - 1.5f;
    }

    public void movingRight() {
        x = x + 1.5f;
    }

    private void move() {
        //System.out.println(this.movingTo+" "+Direction.LEFT.getValue());
        if (movingTo == (Direction.LEFT.getValue())) {
            movingLeft();
        }
        if (movingTo == (Direction.RIGHT.getValue())) {
            movingRight();
        }
    }

    public void changeDirections() {
        if (movingTo == Direction.LEFT.getValue() ) {
            this.movingTo = Direction.RIGHT.getValue();

        }
        else if(movingTo == Direction.RIGHT.getValue()) {
            this.movingTo = Direction.LEFT.getValue();
        }
    }

    @Override
    public void update() {
        move();
        // TODO Auto-generated method stub

    }

    @Override
    public KeyAdapter getKeyAdapter() {
        // TODO Auto-generated method stub
        return null;
    }

    public void colide(Entity bloco) {
        if (getBoundsTop().intersects(bloco.getBounds())) {
            y = (float) bloco.getY() + height;
            // velY = 0;
        }

        if (getBoundsBottom().intersects(bloco.getBounds())) {
            y = (float) bloco.getY() - height;
            moveDown = false;
            // moveUp = false;
        } else
            moveDown = true;

        if (getBoundsRight().intersects(bloco.getBounds())) {
            x = (float) bloco.getX() - width;
            changeDirections();
           // System.out.println(movingTo.toString());
        }

        if (getBoundsLeft().intersects(bloco.getBounds())) {
            x = (float) bloco.getX() + width;
            changeDirections();
           // System.out.println(movingTo.toString());
        }

    }

    @Override
    public void gravity() {
        if (moveDown)
            this.y += 0.5f;
    }

    @Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fill(this);
		g.draw(this);

		super.draw(g);
	}

   
}
