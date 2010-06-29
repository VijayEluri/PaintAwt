package controles;

import fenetres.FenetAffiche;
import figures.FigureGraphique;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * Class chargée de la gestion des touches clavier
 * comme la suppression (suppr) ou choix mutliple (ctrl)
 */
public class ControlClavier implements KeyListener {

    /**
     * Variable contenant la FenetAffiche associée au controleur
     */
    private FenetAffiche frame;

    /**
     * Contructeur de la class ControlClavier
     * @param frame FenetAffiche
     * on passe en paramètre la FenetAffiche associée au controleur
     */
    public ControlClavier(FenetAffiche frame) {
        this.frame = frame;
    }

    /**
     * Méthode non implementée
     * @param ke KeyEvent
     */
    @Override
    public void keyTyped(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Méthode appelée lors qu'une touche est pressée.
     * Prend en paramètre le KeyEvent générer par la pression de la touche
     * @param ke KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent ke) {
        //si la touche pressée est la touche ctrl
        if (ke.getKeyCode() == KeyEvent.VK_CONTROL) {
            frame.saisie = true;
        }

        //si la touche pressée est la touche suppr
        if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
            Vector<FigureGraphique> figs = frame.getFigs();
            for (FigureGraphique current : frame.save) {
                figs.removeElement(current);
            }
            frame.zd.repaint();
            frame.save.clear();
            frame.suppr = true;
        }
    }

    /**
     * Méthode appelée lors du relachement de la touche précedement pressée
     * @param ke KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent ke) {
        frame.saisie = false;
    }

}
