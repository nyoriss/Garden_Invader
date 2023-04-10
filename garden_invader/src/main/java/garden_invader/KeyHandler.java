package garden_invader;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean leftPressed, rightPressed, spacePressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //Si Q est pressé
        if(code== KeyEvent.VK_Q) {
            leftPressed = true;
        }
        //Si D est pressé
        if(code== KeyEvent.VK_D) {
            rightPressed = true;
        }
        //Si la barre espace est pressée
        if(code== KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        //Si Q est relaché
        if(code== KeyEvent.VK_Q) {
            leftPressed = false;
        }
        //Si D est relaché
        if(code== KeyEvent.VK_D) {
            rightPressed = false;
        }
        //Si la barre espace est relachée
        if(code== KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
    }
}
