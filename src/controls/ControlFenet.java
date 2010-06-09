package controls;

import java.awt.event.*;
import fenetres.*;

public class ControlFenet extends WindowAdapter {

    public FenetAffiche fenet;

    public ControlFenet(FenetAffiche fenet) {
        this.fenet = fenet;
    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}
