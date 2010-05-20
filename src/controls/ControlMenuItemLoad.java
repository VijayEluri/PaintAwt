package controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author grimm
 */
public class ControlMenuItemLoad  implements ActionListener{

    public ControlMenuItemLoad() {}
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        ObjectInputStream fluxObjLect;
        try {
            fluxObjLect = new ObjectInputStream(new FileInputStream(new File("/home/grimm/perso/test")));
            Object o = fluxObjLect.readObject();
        } catch (Exception ex) {
            Logger.getLogger(ControlMenuItemLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
