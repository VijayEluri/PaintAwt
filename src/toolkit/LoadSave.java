/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * GIT ÇA ROXXE
 */
package toolkit;

import exceptions.*;
import fenetres.FenetAffiche;
import fenetres.FenetDialogues;
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
 *5 juin 2010
 * @author agindre
 */
public class LoadSave {

    private FenetAffiche frame;

    public LoadSave(FenetAffiche frame) {
        this.frame = frame;
    }

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
