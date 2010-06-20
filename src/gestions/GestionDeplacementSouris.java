package gestions;

import fenetres.FenetAffiche;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Class qui gère les déplacements de la souris 
 */
public class GestionDeplacementSouris extends MouseMotionAdapter {

    /**
     * Variable contenant la FenetAffiche associée à la gestion de la souris
     */
    protected FenetAffiche frame;

    /**
     * Constructeur de la class, prend en paramètre la FenetAffiche associée
     * @param frame FenetAffiche
     */
    public GestionDeplacementSouris(FenetAffiche frame) {
        this.frame = frame;
    }

    /**
     * Méthode appelée lorsque l'utilisateur déplace la souris 
     * @param e MouseEvent
     */
    public void mouseDragged(MouseEvent e) {
        frame.boutonSourisDeplace(e.getX(), e.getY());
    }
}
