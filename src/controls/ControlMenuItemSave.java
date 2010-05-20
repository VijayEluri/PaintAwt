package controls;

import fenetres.FenetAffiche;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author grimm
 */
public class ControlMenuItemSave implements ActionListener {

    private FenetAffiche frame;

    public ControlMenuItemSave(FenetAffiche frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JFileChooser fc = new JFileChooser();
        fc.showSaveDialog(frame);
        try {
            ObjectOutputStream fluxObjEcrit = new ObjectOutputStream(new FileOutputStream(fc.getSelectedFile()));
            fluxObjEcrit.writeObject(frame.figs);
        } catch (Exception ex) {
            Logger.getLogger(ControlMenuItemSave.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
