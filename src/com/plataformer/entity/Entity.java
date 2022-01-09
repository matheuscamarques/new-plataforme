package com.plataformer.entity;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.awt.event.KeyAdapter;
import java.awt.Graphics2D;


public abstract class Entity extends Rectangle2D.Float{
	
	private static final long serialVersionUID = 1L;
	public float velx = 0.0f; 
	public String name = "name is not defined";
    private HashMap<String,Rectangle2D.Float> bounds    = new HashMap<>();

	public Entity(float x,float y,float w,float h) {
		super(x,y,w,h);
        var boundBottom = new Rectangle2D.Float(x+(width/2)-((width/2)/2),y+(height/2),width/2,height/2);
		
        bounds.put("bottom", boundBottom);
        System.out.println(name);
	}

    public Entity(String name,float x,float y,float w,float h) {
		super(x,y,w,h);
        var boundBottom = new Rectangle2D.Float(x+(width/2)-((width/2)/2),y+(height/2),width/2,height/2);
		this.name = name;
        bounds.put("bottom", boundBottom);
        System.out.println(name);
	}
	public abstract void update();
	public abstract void draw(Graphics2D g);	
	public abstract KeyAdapter getKeyAdapter();
    public abstract void gravity();


    public Rectangle2D.Float getBoundsBottom() {
        var boundBottom = bounds.get("bottom");
        boundBottom.x = x+(width/2)-((width/2)/2);
        boundBottom.y = y+(height/2);
        return boundBottom;
    }
    
    public Rectangle2D.Float getBoundsTop() {
        return new Rectangle2D.Float(x+(width/2)-((width/2)/2),y,width/2,height/2);
    }
    
    public Rectangle2D.Float getBoundsRight() {
        return new Rectangle2D.Float(x+width-5,y+5,5,height-10);
    }
    public Rectangle2D.Float getBoundsLeft() {
        return new Rectangle2D.Float(x,y+5,5,height-10);
    }
}

