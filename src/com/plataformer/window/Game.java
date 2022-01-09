package com.plataformer.window;

import java.awt.Canvas;

import com.plataformer.camera.*;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import com.plataformer.entity.*;
import com.plataformer.level.TestLevel;
import com.plataformer.camera.Camera;
import com.plataformer.config.Config;
import java.awt.Graphics2D;
import java.awt.Color;

public class Game extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5190140607513575498L;

	private boolean running = false;
	private Thread thread;
	private BufferStrategy bs;
	private Graphics2D g;
	private ArrayList<Entity> entidades = new ArrayList<>();
	private Player player;
	private Camera camera;

	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// System.out.println("Thread is started.");

		long lastTime = System.nanoTime();
		double amountOfTicks = 30.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		@SuppressWarnings("unused")
		int updates = 0;
		@SuppressWarnings("unused")
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	private void render() {
		bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(9);
			return;
		}

		g = (Graphics2D) bs.getDrawGraphics();
		Rectangle2D.Float background = new Rectangle2D.Float(0, 0, getWidth(), getHeight());
		g.setColor(Color.green);
		g.fill(background);
		g.draw(background);

		g.translate(camera.x, camera.y);
		for (int i = 0; i < TestLevel.instance.blocos.length; i++) {
			for (int j = 0; j < TestLevel.instance.blocos[i].length; j++) {
				TestLevel.instance.blocos[i][j].draw(g);
			}
		}

		for (int i = 0; i < entidades.size(); i++) {
			entidades.get(i).draw(g);
		}

		for (int i = 0; i < entidades.size(); i++) {
			entidades.get(i).update();
		}

		for (int i = 0; i < TestLevel.instance.blocos.length; i++) {
			for (int j = 0; j < TestLevel.instance.blocos[i].length; j++) {
				Block bloco = TestLevel.instance.blocos[i][j];
				if(bloco.colide)
						player.colide(bloco);
				
				
			}
		}

		for (int i = 0; i < entidades.size(); i++) {
			Entity entidade = entidades.get(i);
			if (entidade.y < getHeight() - entidade.height) {
				entidade.gravity();
			}
		}

		g.translate(-camera.x, -camera.y);
		g.dispose();
		bs.show();
	}

	private void tick() {
		// TODO Auto-generated method stub
		camera.tick(player, this);

	}

	public static void main(String args[]) {
		System.setProperty("sun.java2d.opengl", "true");
		var game = new Game();
		var player = new Player(game.getWidth() / 2, game.getHeight() / 2);
		var camera = new Camera(0, 0);
		game.addKeyListener(player.getKeyAdapter());
		game.entidades.add(player);
		game.player = player;
		game.camera = camera;
		new Window(
				Config.WIDTH.getValue(),
				Config.HEIGHT.getValue(),
				"Plataformer Game",
				game);

	}
}
