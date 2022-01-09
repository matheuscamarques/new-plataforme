package com.plataformer.entity;

import com.plataformer.config.GameController;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;

public class Player extends Entity{

	private static final long serialVersionUID = 1L;
    private Color color;
    boolean moveLeft=true,moveRight=true,moveUp=true,moveDown=true;

	public Player(float x, float y) {
		super("Player 1",x, y, 50, 100);
        color = Color.red;
	}

	@Override
	public void update() {
		color = Color.red;
        //moveLeft=moveRight=moveUp=moveDown=true;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fill(this);
		g.draw(this);

		super.draw(g);
	}



    @Override
	public KeyAdapter getKeyAdapter() {
		return new KeyAdapter() {
			@Override 
			public void keyPressed (KeyEvent e) {
	        	GameController ctrl = GameController.getInstance();
	        	Integer keyCode     = e.getKeyCode();
	        	
	        	if(ctrl.isUpKey(keyCode) && moveUp) {
	        		y = y - 75f;
	        	}
	        	
	        	if(ctrl.isDownKey(keyCode) && moveDown) {
	        		y = y + 10.8f;
	        	}
	        	
	        	if(ctrl.isLeftKey(keyCode) && moveLeft) {
	        		x = x - 10.8f;
	        	}
	        	if(ctrl.isRightKey(keyCode) && moveRight) { 
	        		x = x + 10.8f;
	        	}

	        }
			
			// @Override
			// public void keyTyped (KeyEvent e) {
			// 	GameController ctrl = GameController.getInstance();
	        // 	Integer keyCode     = e.getKeyCode();
	        	
	        // 	if(ctrl.isUpKey(keyCode) && moveUp) {
	        // 		y = y - 100f;
	        // 	}
	        	
	        // 	if(ctrl.isDownKey(keyCode) && moveDown) {
	        // 		y = y + 10.8f;
	        // 	}
	        	
	        // 	if(ctrl.isLeftKey(keyCode) && moveLeft) {
	        // 		x = x - 10.8f;
	        // 	}
	        // 	if(ctrl.isRightKey(keyCode) && moveRight) { 
	        // 		x = x + 10.8f;
	        // 	}
			// }
			
			// @Override
			// public void keyReleased (KeyEvent e) {
				
			// }
			
		};
	}

    public void colide(){
        color = Color.blue;
    }

    public void colide(Block bloco) {
        if(getBoundsTop().intersects(bloco.getBounds())){
            y = (float)bloco.getY() + height;
            //velY = 0;
        }


        if(getBoundsBottom().intersects(bloco.getBounds())){
            y = (float)bloco.getY() - height;
            moveDown = false;
            //moveUp = false;
        }else 
            moveDown = true;

        if(getBoundsRight().intersects(bloco.getBounds())){
            x = (float)bloco.getX() - width;
        }


        if(getBoundsLeft().intersects(bloco.getBounds())){
            x = (float)bloco.getX() + width;
        }
        
    }

    @Override
    public void gravity() {
        if(moveDown) this.y += 0.5f;
    }
	
	
}

