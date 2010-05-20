package controls;

import fenetres.FenetAffiche;
import java.awt.*;
import java.awt.event.*;

public class ControlItemCouleur implements ItemListener 	{
	protected FenetAffiche frame;
	Color couleurs[];
	public ControlItemCouleur (Color couleurs[], FenetAffiche frame)	{
		this.couleurs = couleurs;
		this.frame = frame;
	}
	public void itemStateChanged( ItemEvent e)	{
	//$$7
		Choice choice = (Choice) e.getSource();
		frame.couranteCol = couleurs[choice.getSelectedIndex()];
	}
}
