package controles;

import fenetres.FenetAffiche;
import java.awt.*;
import java.awt.event.*;

/**
 * Class chargée de récupérer la valeur du choix de l'utilisateur fait
 * dans la liste déroulante associée à la couleur de la figure
 * courante
 * 
 */
public class ControlItemCouleur implements ItemListener {

    /**
     * Variable contenant la FenetAffiche associée au controleur
     */
    protected FenetAffiche frame;
    /**
     * Variable contenant les couleurs disponible
     */
    protected Color couleurs[];

    /**
     * Constructeur du controleur, prend en paramètre les couleurs disponibles
     * et la FenetAffiche associée au controleur
     * @param couleurs Color[]
     * @param frame FenetAffiche
     */
    public ControlItemCouleur(Color couleurs[], FenetAffiche frame) {
        this.couleurs = couleurs;
        this.frame = frame;
    }

    /**
     * Méthode lorsque l'utilisateur fait un changement de couleur
     * @param e ItemEvent
     */
    public void itemStateChanged(ItemEvent e) {
        //$$7
        Choice choice = (Choice) e.getSource();
        frame.couranteCol = couleurs[choice.getSelectedIndex()];
    }
}
