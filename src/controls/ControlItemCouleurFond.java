package controls;

import fenetres.FenetAffiche;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class ControlItemCouleurFond implements ItemListener {
	protected FenetAffiche frame;
	Color couleurs[];
	
	public ControlItemCouleurFond (Color couleurs[], FenetAffiche frame) {
		this.couleurs = couleurs;
		this.frame = frame;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Choice choice = (Choice) e.getSource();
		frame.couranteFgCol = couleurs[choice.getSelectedIndex()];
	}
	
}
