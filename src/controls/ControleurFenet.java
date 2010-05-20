package controls;

import java.awt.event.*;
import fenetres.*;

public class ControleurFenet extends WindowAdapter {

    public FenetAffiche fenet;

    public ControleurFenet(FenetAffiche fenet) {
        this.fenet = fenet;
    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}
