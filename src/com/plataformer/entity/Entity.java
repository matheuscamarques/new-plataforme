package com.plataformer.entity;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.awt.event.KeyAdapter;
import java.awt.Graphics2D;
import java.awt.Color;

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
	public  void draw(Graphics2D g){
        g.setColor(Color.green);
        var bottom = getBoundsBottom();
		g.draw(bottom);

        g.setColor(Color.green);
		var left = getBoundsLeft();
		g.draw(left);

        g.setColor(Color.green);
        var top = getBoundsTop();
		g.draw(top);

        g.setColor(Color.green);
		var right = getBoundsRight();
		g.draw(right);
    };	
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

