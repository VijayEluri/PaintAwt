package controls;

import fenetres.FenetAffiche;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import toolkit.LoadSave;

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
        LoadSave ls = new LoadSave(frame);
        ls.save();
    }
}
