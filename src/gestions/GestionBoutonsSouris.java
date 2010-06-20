package gestions;

import fenetres.FenetAffiche;
import java.awt.event.*;

/**
 * Class qui gère la souris
 */
public class GestionBoutonsSouris extends MouseAdapter {

    /**
     * Variable contenant la FenetAffiche associée à la gestion de la souris
     */
    protected FenetAffiche frame;

    /**
     * Constructeur de la class, prend en paramètre la FenetAffiche associée
     * @param frame FenetAffiche
     */
    public GestionBoutonsSouris(FenetAffiche frame) {
        this.frame = frame;
    }

    /**
     * Méthode appelée lorsque l'utilisateur clique dans l'application
     * @param e MouseEvent
     */
    public void mousePressed(MouseEvent e) {
        frame.boutonSourisEnfonce(e.getX(), e.getY());
    }

    /**
     * Méthode appelée lorsque l'utilisateur relâche le clique de sa souris
     * @param e MouseEvent
     */
    public void mouseReleased(MouseEvent e) {
        frame.boutonSourisRelache(e.getX(), e.getY());
    }
}
