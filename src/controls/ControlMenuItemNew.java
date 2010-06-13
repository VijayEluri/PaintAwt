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
        if (!frame.getFigs().isEmpty()) {
            FenetDialogues fd = new FenetDialogues(frame);
            frame.getFigs().removeAllElements();
            frame.zd.repaint();
        }
    }
}
