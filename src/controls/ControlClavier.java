/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controls;

import fenetres.FenetAffiche;
import figures.FigureGraphique;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 *3 juin 2010
 * @author agindre
 */
public class ControlClavier implements KeyListener {

    private FenetAffiche frame;

    /**
     *
     * @param frame
     */
    public ControlClavier(FenetAffiche frame) {
        this.frame = frame;
    }

    /**
     *
     * @param ke
     */
    @Override
    public void keyTyped(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Giga de la mort qui tue sa mère
     * @param ke KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_CONTROL) {
            frame.saisie = true;
        }

        if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
            Vector<FigureGraphique> figs = frame.getFigs();
            for (FigureGraphique current : frame.save) {
                figs.removeElement(current);
            }
            frame.zd.repaint();
            frame.save.clear();
        }
    }

    /**
     *
     * @param ke
     */
    @Override
    public void keyReleased(KeyEvent ke) {
        //row new UnsupportedOperationException("Not supported yet.");
        frame.saisie = false;
    }

}
