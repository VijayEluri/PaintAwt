/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package toolkit;

import controls.ControlMenuItemLoad;
import controls.ControlMenuItemSave;
import fenetres.FenetAffiche;
import figures.FigureGraphique;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public void save() {
        JFileChooser fc = new JFileChooser();
        fc.showSaveDialog(frame);
        try {
            ObjectOutputStream fluxObjEcrit = new ObjectOutputStream(new FileOutputStream(fc.getSelectedFile()));
            fluxObjEcrit.writeObject(frame.figs);
        } catch (Exception ex) {
            Logger.getLogger(ControlMenuItemSave.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void load() {
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(frame);
        try {
            ObjectInputStream fluxObjLect = new ObjectInputStream(new FileInputStream(fc.getSelectedFile()));
            Object o = fluxObjLect.readObject();
            frame.figs = (Vector <FigureGraphique>) o;
        } catch (Exception ex) {
            Logger.getLogger(ControlMenuItemLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
