package com.plataformer.config;
import java.util.HashMap;
import java.awt.event.KeyEvent;

public final class GameController {
    private static volatile GameController instance;
   
    private HashMap<Integer,Integer> UpKeys    = new HashMap<>();
    private HashMap<Integer,Integer> DownKeys  = new HashMap<>();
    private HashMap<Integer,Integer> LeftKeys  = new HashMap<>();
    private HashMap<Integer,Integer> RightKeys = new HashMap<>();



    private GameController() {
        UpKeys.put(KeyEvent.VK_PAGE_UP,1);
        UpKeys.put(KeyEvent.VK_KP_UP,1);
        UpKeys.put(KeyEvent.VK_UP,1);
        
        DownKeys.put(KeyEvent.VK_DOWN,1);
        DownKeys.put(KeyEvent.VK_KP_DOWN,1);
        DownKeys.put(KeyEvent.VK_PAGE_DOWN,1);
        
        LeftKeys.put(KeyEvent.VK_LEFT, 1);
        LeftKeys.put(KeyEvent.VK_KP_LEFT, 1);
        LeftKeys.put(KeyEvent.VK_END,1);
        
        RightKeys.put(KeyEvent.VK_RIGHT, 1);
        RightKeys.put(KeyEvent.VK_KP_RIGHT, 1);
        RightKeys.put(KeyEvent.VK_HOME,1);
    }

    public static GameController getInstance() {
    	GameController result = instance;
        if (result != null) {
            return result;
        }
        synchronized(GameController.class) {
            if (instance == null) {
                instance = new GameController();
            }
            return instance;
        }
    }

    public boolean isUpKey(int keyCode) {
    	return UpKeys.get( keyCode ) != null;
    }
    
    public boolean isDownKey(int keyCode) {
    	return DownKeys.get( keyCode ) != null;
    }
    
    public boolean isLeftKey(int keyCode) {
    	return LeftKeys.get( keyCode ) != null;
    }
    
    public boolean isRightKey(int keyCode) {
    	return RightKeys.get( keyCode ) != null;
    }
    
}
    




