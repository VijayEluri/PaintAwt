package controls;

import fenetres.FenetAffiche;
import fenetres.FenetDialogues;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class chargée de controler la remise à zéro de l'application lorsque l'utlisateur
 * clique sur nouveau
 */
public class ControlMenuItemNew implements ActionListener {

    /**
     * Variable contenant la FenetAffiche associée au controleur
     */
    private FenetAffiche frame;

    /**
     * Constructeur de la class, prend en paramètre la FenetAffiche associée
     * @param frame FenetAffiche
     */
    public ControlMenuItemNew(FenetAffiche frame) {
        this.frame = frame;
    }

    /**
     * Méthode appelée lorsque l'utilisateur clique sur nouveau
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!frame.getFigs().isEmpty()) {
            FenetDialogues fd = new FenetDialogues(frame);
            frame.getFigs().removeAllElements();
            frame.zd.repaint();
        }
    }
}
