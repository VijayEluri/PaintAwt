/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fenetres;

import javax.swing.JOptionPane;
import toolkit.LoadSave;

/**
 *4 juin 2010
 * @author agindre
 */
public class FenetDialogues {

    private FenetAffiche frame;
    private Object[] options = {"Oui", "Non"};

    public FenetDialogues(FenetAffiche frame) {
        this.frame = frame;
        int n = JOptionPane.showOptionDialog(frame,
                "Vous allez perdre tout ce qui n'est pas sauvegardé \n"
                + "Voulez-vous sauvegarder?",
                "Sauvegarde",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);

        if (n == 0) {
            LoadSave ls = new LoadSave(frame);
            ls.save();
        }

    }

    public FenetDialogues(FenetAffiche frame, String nom) {
        this.frame = frame;
        String s = (String) JOptionPane.showInputDialog(
                frame,
                "Veillez entrer le nom de la figure",
                "Nom figure",
                JOptionPane.PLAIN_MESSAGE,null, null, nom);

        //If a string was returned, say so.
        if ((s != null) && (s.length() > 0)) {
            frame.nom = s;
        }
    }
}
