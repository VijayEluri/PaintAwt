/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fenetres;

import exceptions.FileSecurity;
import exceptions.FilesCorrupted;
import exceptions.FilesNull;
import java.awt.PopupMenu;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import toolkit.LoadSave;

/**
 *4 juin 2010
 * @author agindre
 */
public class FenetDialogues {

    private Object[] options = {"Oui", "Non"};
    private Object[] optionsErreur = {"OK"};

    public FenetDialogues(FenetAffiche frame) {
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
            try {
                ls.save();
            } catch (FileSecurity ex) {
                new FenetDialogues(frame, ex);
            } catch (FilesNull ex) {
                new FenetDialogues(frame, ex);
            } catch (FilesCorrupted ex) {
                new FenetDialogues(frame, ex);
            }
        }

    }

    public FenetDialogues(FenetAffiche frame, String nom) {
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

    public FenetDialogues(FenetAffiche frame, Exception ex) {
         JOptionPane.showOptionDialog(frame,
                ex.toString(),
                "Erreur !",
                JOptionPane.ERROR_MESSAGE,
                JOptionPane.ERROR_MESSAGE,
                null,
                optionsErreur,
                optionsErreur[0]);
    }
}
