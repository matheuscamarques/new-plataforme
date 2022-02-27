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
	private ArrayList<Enemy> inimigos = new ArrayList<>();
	private ArrayList<Entity> colidiveis = new ArrayList<>();
	private ArrayList<Entity> notColidiveis = new ArrayList<>();
	private Player player;
	private Camera camera;

	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void init() {
		for (int i = 0; i < TestLevel.instance.blocosList.size(); i++) {
			Block bloco = TestLevel.instance.blocosList.get(i);
			if (bloco.colide) {
				colidiveis.add(bloco);
			} else {
				notColidiveis.add(bloco);
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// System.out.println("Thread is started.");
		this.init();
		player.init();
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
			this.createBufferStrategy(3);
			return;
		}

		g = (Graphics2D) bs.getDrawGraphics();
		Rectangle2D.Float background = new Rectangle2D.Float(0, 0, getWidth(), getHeight());
		g.setColor(Color.green);
		g.fill(background);
		g.draw(background);
		g.translate(camera.x, camera.y);
		// player colide with blocks
		for (int i = 0; i < TestLevel.instance.blocosList.size(); i++) {
			Block bloco = TestLevel.instance.blocosList.get(i);
			bloco.draw(g);
			if (bloco.colide)
				player.colide(bloco);

			if (bloco.valueOf == -1) {
				if (inimigos.size() < 5) {
					Enemy inimigo = new Enemy(bloco.x, bloco.y);
					inimigos.add(inimigo);
					System.out.println(" " + bloco.x + " " + bloco.y + " Inimigo adicionado: " + inimigos.size());
				}
			}
		}

		
		player.draw(this, g);
		player.update();
		player.gravity();

		// enemy colide with blocks
		for (int i = 0; i < inimigos.size(); i++) {
			Enemy inimigo = inimigos.get(i);
			inimigos.get(i).draw(g);
			inimigos.get(i).update();
			inimigos.get(i).gravity();

			for (int j = 0; j < TestLevel.instance.blocos.length; j++) {
				for (int k = 0; k < TestLevel.instance.blocos[j].length; k++) {
					Block bloco = TestLevel.instance.blocos[j][k];
					if (bloco.colide) {
						inimigo.colide(bloco);
					}
				}
			}

			// colide others enemies
			for (int j = 0; j < inimigos.size(); j++) {
				if (i != j) {
					Enemy inimigoOther = inimigos.get(j);
					inimigo.colide(inimigoOther);
					System.out.println("Colidiu com: " + j);
				}
			}

			player.colide(inimigo);
			inimigo.colide(player);

			if (inimigo.died) {
				inimigos.remove(i);
			}
		}

		g.translate(-camera.x, -camera.y);
		g.dispose();
		bs.show();
	}

	private void tick() {
		// TODO Auto-generated method stub
		camera.tick(player, this);
		player.update();
		player.gravity();

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
