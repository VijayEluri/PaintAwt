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
 *18 juin 2010
 * @author agindre
 */
public class ControlMenuItemAbout implements ActionListener {

     private FenetAffiche frame;

    public ControlMenuItemAbout(FenetAffiche frame) {
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
        new FenetDialogues(frame, 1);
    }
}
