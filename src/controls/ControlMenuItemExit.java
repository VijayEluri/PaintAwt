/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

import fenetres.FenetAffiche;
import fenetres.FenetDialogues;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author grimm
 */
public class ControlMenuItemExit implements ActionListener {

    private FenetAffiche frame;

    public ControlMenuItemExit(FenetAffiche frame) {
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
        if (!frame.getFigs().isEmpty()) {
            FenetDialogues fd = new FenetDialogues(frame);
        }
        System.exit(0);
    }
}
