package controls;

import fenetres.FenetAffiche;
import fenetres.FenetDialogues;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Grimm
 */
public class ControlMenuItemNew implements ActionListener {

    private FenetAffiche frame;

    public ControlMenuItemNew(FenetAffiche frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FenetDialogues fd = new FenetDialogues(frame);
        frame.figs.removeAllElements();
        frame.zd.repaint();
    }
}
