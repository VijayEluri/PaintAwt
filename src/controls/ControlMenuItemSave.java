package controls;

import fenetres.FenetAffiche;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        try {
            ObjectOutputStream fluxObjEcrit = new ObjectOutputStream(new FileOutputStream(new File("/home/grimm/perso/test")));
            fluxObjEcrit.writeObject(frame.figs);
        } catch (Exception ex) {
            Logger.getLogger(ControlMenuItemSave.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
