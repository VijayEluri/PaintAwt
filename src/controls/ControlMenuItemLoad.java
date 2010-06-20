package controls;

import exceptions.FileSecurity;
import exceptions.FilesCorrupted;
import exceptions.FilesNull;
import fenetres.FenetAffiche;
import fenetres.FenetDialogues;
import figures.FigureGraphique;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import toolkit.LoadSave;

/**
 * Class chargée de controler le chargement du contenu de l'application lorsque l'utlisateur
 * clique sur charger
 */
public class ControlMenuItemLoad implements ActionListener {

    /**
     * Variable contenant la FenetAffiche associée au controleur
     */
    private FenetAffiche frame;

    /**
     * Constructeur de la class, prend en paramètre la FenetAffiche associée
     * @param frame FenetAffiche
     */
    public ControlMenuItemLoad(FenetAffiche frame) {
        this.frame = frame;
    }

    /**
     * Méthode appelée lorsque l'utilisateur clique sur charger
     * @param ae ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        LoadSave ls = new LoadSave(frame);
        try {
            ls.load();
        } catch (FileSecurity ex) {
            new FenetDialogues(frame, ex);
        } catch (FilesNull ex) {
            new FenetDialogues(frame, ex);
        } catch (FilesCorrupted ex) {
            new FenetDialogues(frame, ex);
        }
        redessiner();
    }

    private void redessiner() {
        Graphics g = frame.zd.getGraphics();
        for (FigureGraphique current : frame.getFigs()) {
            current.dessineToi(g);
        }
        frame.zd.repaint();
    }
}
