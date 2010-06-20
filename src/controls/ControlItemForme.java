package controls;

import fenetres.FenetAffiche;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Class chargée de récupérer la valeur du choix de l'utilisateur fait
 * dans la liste déroulante associée à la forme de la figure
 * courante
 */
public class ControlItemForme implements ItemListener {

    /**
     * Variable contenant la FenetAffiche associée au controleur
     */
    protected FenetAffiche frame;
    /**
     * Variable contenant les formes disponibles dans la liste déroulante
     */
    String formes[];

    /**
     * Constructeur de la class, prend en paramètre les formes disponibles et
     * la FenetAffiche associée 
     * @param formes String[]
     * @param frame FenetAffiche
     */
    public ControlItemForme(String formes[], FenetAffiche frame) {
        this.formes = formes;
        this.frame = frame;
    }

    /**
     * Méthode appelée lorsque l'utilisateur fait un changement dans la liste
     * des formes disponibles
     * @param e ItemEvent
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        Choice choice = (Choice) e.getSource();
        frame.choice = new String(formes[choice.getSelectedIndex()]);
    }
}
