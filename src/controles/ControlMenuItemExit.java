/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import fenetres.FenetAffiche;
import fenetres.FenetDialogues;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class chargée de controler la fermeture de l'application lorsque l'utlisateur
 * clique sur quitter
 */
public class ControlMenuItemExit implements ActionListener {

    /**
     * Variable contenant la FenetAffiche associée au controleur
     */
    private FenetAffiche frame;

    /**
     * Controleur de la class, prend en paramètre le FenetAffiche associée
     * @param frame FenetAffiche
     */
    public ControlMenuItemExit(FenetAffiche frame) {
        this.frame = frame;
    }

    /**
     * Méthode appelée lorsque l'utilisateur clique sur quitter
     * @param e ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        if (!frame.getFigs().isEmpty()) {
            FenetDialogues fd = new FenetDialogues(frame);
        }
        System.exit(0);
    }
}
