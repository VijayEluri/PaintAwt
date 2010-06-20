package controls;

import fenetres.FenetAffiche;
import fenetres.FenetDialogues;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class chargée de l'interaction avec le menu About
 */
public class ControlMenuItemAbout implements ActionListener {

    /**
     * Variable contenant la FenetAffiche associée
     */
    private FenetAffiche frame;

    /**
     * Constructeur de la classe, prend en paramètre le FenetAffiche associée
     * @param frame
     */
    public ControlMenuItemAbout(FenetAffiche frame) {
        this.frame = frame;
    }

    /**
     * Méthode appelée lorsque l'utlisateur clique sur About
     * @param e ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        new FenetDialogues(frame, 1);
    }
}
