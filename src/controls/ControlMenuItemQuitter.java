/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author grimm
 */
public class ControlMenuItemQuitter implements ActionListener {

    public ControlMenuItemQuitter () {};

    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

}
