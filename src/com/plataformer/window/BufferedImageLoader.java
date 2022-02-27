package com.plataformer.window;
import java.awt.image.BufferedImage;
public class BufferedImageLoader {
    private BufferedImage image;

    public BufferedImage loadImage(String path){
        try{
            System.out.println("Loading image: " + getClass().getResource(path));
            image = javax.imageio.ImageIO.read(getClass().getResource(path));
        }catch(Exception e){
            e.printStackTrace();
        }
        return image;
    }
}
