package controls;

import fenetres.FenetAffiche;
import figures.FigureGraphique;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import toolkit.LoadSave;

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
        LoadSave ls = new LoadSave(frame);
        ls.load();
        redessiner();
    }

    private void redessiner() {
        Graphics g = frame.zd.getGraphics();
        for (FigureGraphique current : frame.getFigs()) {
                current.dessineToi(g);
        }
        frame.zd.repaint();
    }
}
