package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    //be sure to add the 3 unimplemented methods
    @Override
    public void keyTyped(KeyEvent e) {
        //not using this one
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); //will return the number of the key pressed
        if(code == KeyEvent.VK_W){ // W key
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){ // S key
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){ // A key
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){ // D key
            rightPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){ // W key
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){ // S key
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){ // A key
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){ // D key
            rightPressed = false;
        }
    }
}
