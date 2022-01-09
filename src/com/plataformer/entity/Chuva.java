package com.plataformer.entity;

import java.awt.geom.Rectangle2D;
import java.awt.event.KeyAdapter;
import java.awt.Graphics2D;
import java.awt.Color;

public class Chuva extends Entity {
	private static final long serialVersionUID = 1L;
	public float velx = 0.0f; 
	public String name = "";
	public Chuva(float x,float y) {
		super(x,y,0.5f,5.5f);
	}
	@Override
	public void update() {
		this.y += 10f;
		this.x += velx;
	}
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.blue);
		g.fill(this);
		g.draw(this);
	}
	@Override
	public KeyAdapter getKeyAdapter() {
		// TODO Auto-generated method stub
		return null;
	}
    @Override
    public void gravity() {
        // TODO Auto-generated method stub
        
    }
}
