/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controls;

import fenetres.FenetAffiche;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *3 juin 2010
 * @author agindre
 */
public class ControlClavier implements KeyListener {

    private FenetAffiche frame;

    public ControlClavier(FenetAffiche frame) {
        this.frame = frame;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet.");
        System.out.println("Bon ok!");
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_CONTROL) {
            frame.saisie = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //row new UnsupportedOperationException("Not supported yet.");
        frame.saisie = false;
    }

}
