package controles;

import fenetres.FenetAffiche;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Class chargée de récupérer la valeur du choix de l'utilisateur fait
 * dans la liste déroulante associée à la couleur de fond de la figure
 * courante
 */
public class ControlItemCouleurFond implements ItemListener {

    /**
     * Variable contenant la FenetAffiche associée au controleur
     */
    protected FenetAffiche frame;
    /**
     * Variable contenant les couleurs de fond disponibles
     */
    Color couleurs[];

    /**
     * Constructeur de la class, prend en paramètre les couleurs de fond disponible
     * et la FenetAffiche associée
     * @param couleurs Coulor[]
     * @param frame FenetAffiche
     */
    public ControlItemCouleurFond(Color couleurs[], FenetAffiche frame) {
        this.couleurs = couleurs;
        this.frame = frame;
    }

    /**
     * Méthode appelée lorsque l'utilisateur fait un changement de couleur de fond
     * dans la liste associée.
     * @param e ItemEvent
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        Choice choice = (Choice) e.getSource();
        frame.couranteFgCol = couleurs[choice.getSelectedIndex()];
    }
}
