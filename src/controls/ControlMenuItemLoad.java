package controls;

import fenetres.FenetAffiche;
import figures.FigureGraphique;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author grimm
 */
public class ControlMenuItemLoad implements ActionListener {

    private FenetAffiche frame;
    
    public ControlMenuItemLoad(FenetAffiche frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(frame);
        try {
            ObjectInputStream fluxObjLect = new ObjectInputStream(new FileInputStream(fc.getSelectedFile()));
            Object o = fluxObjLect.readObject();
            frame.figs = (Vector <FigureGraphique>) o;
            redessiner();
        } catch (Exception ex) {
            Logger.getLogger(ControlMenuItemLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void redessiner() {
        Graphics g = frame.zd.getGraphics();
        for (FigureGraphique current : frame.figs) {
                current.dessineToi(g);
        }
        frame.zd.repaint();
    }
}
