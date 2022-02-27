package com.plataformer.entity;

import com.plataformer.config.GameController;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;

import com.plataformer.window.BufferedImageLoader;
import com.plataformer.window.Game;
import com.plataformer.window.SpriteSheet;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

class PlayerAnimation {
	ArrayList<BufferedImage> idle = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> leftWalk = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> rightWalk = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> attack = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> jump = new ArrayList<BufferedImage>();

	int imageIndex = 0;
	int maxIndex = 10;

	public int nextIndex() {
		imageIndex = (imageIndex + 1) % maxIndex;
		return imageIndex;
	}

	public void init() {
		BufferedImageLoader loader = new BufferedImageLoader();

		attack.add(
				loader.loadImage("/freeknight/png/Attack (1).png"));
		attack.add(
				loader.loadImage("/freeknight/png/Attack (2).png"));

		attack.add(
				loader.loadImage("/freeknight/png/Attack (3).png"));

		attack.add(
				loader.loadImage("/freeknight/png/Attack (4).png"));

		attack.add(
				loader.loadImage("/freeknight/png/Attack (5).png"));

		attack.add(
				loader.loadImage("/freeknight/png/Attack (6).png"));

		attack.add(
				loader.loadImage("/freeknight/png/Attack (7).png"));

		attack.add(
				loader.loadImage("/freeknight/png/Attack (8).png"));

		attack.add(
				loader.loadImage("/freeknight/png/Attack (9).png"));

		attack.add(
				loader.loadImage("/freeknight/png/Attack (10).png"));

		rightWalk.add(
				loader.loadImage("/freeknight/png/Walk (1).png"));

		rightWalk.add(
				loader.loadImage("/freeknight/png/Walk (2).png"));

		rightWalk.add(
				loader.loadImage("/freeknight/png/Walk (3).png"));

		rightWalk.add(
				loader.loadImage("/freeknight/png/Walk (4).png"));

		rightWalk.add(
				loader.loadImage("/freeknight/png/Walk (5).png"));

		rightWalk.add(
				loader.loadImage("/freeknight/png/Walk (6).png"));

		rightWalk.add(
				loader.loadImage("/freeknight/png/Walk (7).png"));

		rightWalk.add(
				loader.loadImage("/freeknight/png/Walk (8).png"));

		rightWalk.add(
				loader.loadImage("/freeknight/png/Walk (9).png"));

		rightWalk.add(
				loader.loadImage("/freeknight/png/Walk (10).png"));

		for (int i = 0; i < rightWalk.size(); i++) {
			var image = this.rightWalk.get(i);
			// Flip the image horizontally
			var tx = AffineTransform.getScaleInstance(-1, 1);
			tx.translate(-image.getWidth(null), 0);
			var op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			image = op.filter(image, null);
			this.leftWalk.add(image);
		}

		idle.add(
				loader.loadImage("/freeknight/png/Idle (1).png"));

		idle.add(
				loader.loadImage("/freeknight/png/Idle (2).png"));

		idle.add(
				loader.loadImage("/freeknight/png/Idle (3).png"));

		idle.add(
				loader.loadImage("/freeknight/png/Idle (4).png"));

		idle.add(
				loader.loadImage("/freeknight/png/Idle (5).png"));

		idle.add(
				loader.loadImage("/freeknight/png/Idle (6).png"));

		idle.add(
				loader.loadImage("/freeknight/png/Idle (7).png"));

		idle.add(
				loader.loadImage("/freeknight/png/Idle (8).png"));

		idle.add(
				loader.loadImage("/freeknight/png/Idle (9).png"));

		idle.add(
				loader.loadImage("/freeknight/png/Idle (10).png"));

	}
}

public class Player extends Entity {

	private static final long serialVersionUID = 1L;
	private Color color;
	boolean moveLeft = false, moveRight = false, moveUp = false, moveDown = true;
	boolean jumping = false;
	float jumpingRecharge = 0.0f;

	private PlayerAnimation animation = new PlayerAnimation();

	public void init() {
		animation.init();
	}

	public Player(float x, float y) {
		super("Player 1", x, y, 40, 70);
		color = Color.red;
	}

	@Override
	public void update() {
		color = Color.red;
		// moveLeft=moveRight=moveUp=moveDown=true;
		if (moveUp && jumping) {
			y = y - height * 0.5f / 2;

			jumpingRecharge += height * 0.5/ 2 ;
			System.out.println(jumpingRecharge + " " + height * 5);
			if (jumpingRecharge > height * 5) {
				jumping = false;
				jumpingRecharge = 0.0f;
				moveUp = false;
			}
		}

		if (moveLeft) {
			System.out.println("moving left: " + x);
			x = x - 5f;
		}

		if (moveRight) {
			System.out.println("moving right: " + x);
			x = x + 5f;
		}

	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fill(this);
		g.draw(this);

		super.draw(g);
	}

	public void draw(Game game, Graphics2D g) {
		if (moveLeft || moveRight) {
			if (moveRight) {
				g.drawImage(
						animation.rightWalk.get(animation.nextIndex()),
						(int) x - 5,
						(int) y,
						(int) (width + (width * 0.6)),
						(int) (height + (height * 0.1)),
						game);
			}

			if (moveLeft) {
				g.drawImage(
						animation.leftWalk.get(animation.nextIndex()),
						(int) x - 5,
						(int) y,
						(int) (width + (width * 0.6)),
						(int) (height + (height * 0.1)),
						game);
			}
		} else {
			g.drawImage(
					animation.idle.get(animation.nextIndex()),
					(int) x - 5,
					(int) y,
					(int) (width + (width * 0.6)),
					(int) (height + (height * 0.1)),
					game);
		}

		super.draw(g);
	}

	@Override
	public KeyAdapter getKeyAdapter() {
		return new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				GameController ctrl = GameController.getInstance();
				Integer keyCode = e.getKeyCode();

				if (ctrl.isUpKey(keyCode)) {
					// System.out.print("UP");
					moveUp = true;
				}

				if (ctrl.isDownKey(keyCode)) {
					moveDown = true;
				}

				if (ctrl.isLeftKey(keyCode)) {
					moveLeft = true;
				}
				if (ctrl.isRightKey(keyCode)) {
					moveRight = true;
				}

			}

			public void keyReleased(KeyEvent e) {
				GameController ctrl = GameController.getInstance();
				Integer keyCode = e.getKeyCode();

				if (ctrl.isUpKey(keyCode)) {
					moveUp = false;
				}

				if (ctrl.isDownKey(keyCode)) {
					moveDown = false;
				}

				if (ctrl.isLeftKey(keyCode)) {
					System.out.println("left");
					moveLeft = false;
				}

				if (ctrl.isRightKey(keyCode)) {
					System.out.println("right");
					moveRight = false;
				}

			}

		};
	}

	public void colide() {
		color = Color.blue;
	}

	public void colide(Block bloco) {
		if (getBoundsTop().intersects(bloco.getBoundsBottom())) {
			y = (float) bloco.getY() + height;
			// velY = 0;
		}

		if (getBoundsBottom().intersects(bloco.getBounds())) {
			y = (float) bloco.getY() - height;
			moveDown = false;
			jumping = true;
		} else
			moveDown = true;

		if (getBoundsRight().intersects(bloco.getBounds())) {
			x = (float) bloco.getX() - width;
		}

		if (getBoundsLeft().intersects(bloco.getBounds())) {
			x = (float) bloco.getX() + width;
			System.out.println("colidiur");
		}

	}

	@Override
	public void gravity() {
		y += 9.8f /2;
	}

	public void colide(Enemy inimigo) {
		// enemy top colide with player bottom
		if (getBoundsBottom().intersects(inimigo.getBoundsTop())) {
			inimigo.died = true;
		}

		if (getBoundsTop().intersects(inimigo.getBounds())) {
			y = (float) inimigo.getY() + height;
			// velY = 0;
		}

		if (getBoundsBottom().intersects(inimigo.getBounds())) {
			y = (float) inimigo.getY() - height;
			moveDown = false;
			// moveUp = false;
		} else
			moveDown = true;

		if (getBoundsRight().intersects(inimigo.getBounds())) {
			x = (float) inimigo.getX() - width;
		}

		if (getBoundsLeft().intersects(inimigo.getBounds())) {
			x = (float) inimigo.getX() + width;
		}
	}

}
