package com.plataformer.entity;

import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.Color;
public class Block extends Entity{
    public Color color ;
    public boolean colide = false;
    
    public Block(float x, float y,Color color,boolean colide) {
        super("Block Map",x, y, 50, 50);
        this.color = color;
        this.colide = colide;
        //TODO Auto-generated constructor stub
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
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
