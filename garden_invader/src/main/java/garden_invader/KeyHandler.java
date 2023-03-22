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

        if(code== KeyEvent.VK_Q) {
            leftPressed = true;
            //System.out.println("KeyH : gauche pressé");
        }
        if(code== KeyEvent.VK_D) {
            rightPressed = true;
            //System.out.println("KeyH : droite pressé");

        }
        if(code== KeyEvent.VK_SPACE) {
            spacePressed = true;
            //System.out.println("KeyH : espace pressé");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code== KeyEvent.VK_Q) {
            leftPressed = false;
        }
        if(code== KeyEvent.VK_D) {
            rightPressed = false;
        }
        if(code== KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
    }
}
