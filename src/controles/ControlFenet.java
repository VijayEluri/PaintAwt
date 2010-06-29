package controles;

import java.awt.event.*;
import fenetres.*;

/**
 * Class chargée du controle de la fermeture de la FenetreAffiche associée
 *
 */

public class ControlFenet extends WindowAdapter {

    /**
     * Variable dans laquelle est stockée le FenetAffiche
     */
    public FenetAffiche fenet;

    /**
     * Constructeur de la class, prend en paramètre la FenetAffiche associée au
     * controleur
     * @param fenet FenetAffiche
     */
    public ControlFenet(FenetAffiche fenet) {
        this.fenet = fenet;
    }

    /**
     * Méthode appelée lorsque l'utilisateur fait une demande de fermeture de
     * la FenetAffiche
     * @param e WindowEvent
     */
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}
