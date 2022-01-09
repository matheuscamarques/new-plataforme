package com.plataformer.camera;

import com.plataformer.entity.Player;
import com.plataformer.window.Game;

public class Camera {
    public float x,y;
    public Camera(float x,float y){
        this.x=x;
        this.y=y;
    }

    public void tick(Player player, Game game){
        x = -player.x + game.getWidth()/2;
        y = -player.y + game.getHeight()/2;
    }
}
