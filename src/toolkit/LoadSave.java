package toolkit;

import exceptions.*;
import fenetres.FenetAffiche;
import figures.FigureGraphique;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.Vector;
import javax.swing.JFileChooser;

/**
 * Class qui gère la sauvegarde et le chargement de l'application
 */
public class LoadSave {

    /**
     * Variable contenant la FenetAffiche associée à la classs
     */
    private FenetAffiche frame;

    /**
     * Constructeur de la class, prend en paramètre la FenetAffiche associée
     * @param frame
     */
    public LoadSave(FenetAffiche frame) {
        this.frame = frame;
    }

    /**
     * Méthode permettant de sauvegarder l'application
     * @throws FileSecurity
     * @throws FilesNull
     * @throws FilesCorrupted
     */
    public void save() throws FileSecurity, FilesNull, FilesCorrupted {
        JFileChooser fc = new JFileChooser();
        fc.showSaveDialog(frame);
        try {
            ObjectOutputStream fluxObjEcrit = new ObjectOutputStream(new FileOutputStream(fc.getSelectedFile()));
            fluxObjEcrit.writeObject(frame.getFigs());
            fluxObjEcrit.close();
        } catch (Exception ex) {
            if (ex instanceof SecurityException) {
                throw new FileSecurity();
            }

            if (ex instanceof IOException) {
                throw new FilesNull();
            }

            if (ex instanceof StreamCorruptedException) {
                throw new FilesCorrupted();
            }
        }
    }

    /**
     * Méthode permettant de charger l'application
     * @throws FileSecurity
     * @throws FilesNull
     * @throws FilesCorrupted
     */
    public void load() throws FileSecurity, FilesNull, FilesCorrupted {
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(frame);
        try {
            ObjectInputStream fluxObjLect = new ObjectInputStream(new FileInputStream(fc.getSelectedFile()));
            Object o = fluxObjLect.readObject();
            frame.setFigs((Vector<FigureGraphique>) o);
            fluxObjLect.close();
        } catch (Exception ex) {
            if (ex instanceof SecurityException) {
                throw new FileSecurity();
            }

            if (ex instanceof IOException) {
                throw new FilesNull();
            }

            if (ex instanceof StreamCorruptedException) {
                throw new FilesCorrupted();
            }
        }
    }
}
