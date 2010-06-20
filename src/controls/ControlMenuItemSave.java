package controls;

import exceptions.FileSecurity;
import exceptions.FilesCorrupted;
import exceptions.FilesNull;
import fenetres.FenetAffiche;
import fenetres.FenetDialogues;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import toolkit.LoadSave;

/**
 *Class chargée de controler la sauvegarde de l'application lorsque l'utlisateur
 * clique sur sauvegarder
 */
public class ControlMenuItemSave implements ActionListener {

    /**
     * Variable contenant la FenetAffiche associée au controleur
     */
    private FenetAffiche frame;

    /**
     * Constructeur de la class, prend en paramètre la FenetAffiche associée
     * @param frame FenetAffiche
     */
    public ControlMenuItemSave(FenetAffiche frame) {
        this.frame = frame;
    }

    /**
     * Méthode appelée lorsque l'utilisateur clique sur sauvegarder
     * @param ae ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
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
