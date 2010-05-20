package gestions;

import fenetres.FenetAffiche;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


public class GestionDeplacementSouris extends MouseMotionAdapter {
	protected FenetAffiche frame;
	
	public GestionDeplacementSouris(FenetAffiche frame) {
		this.frame = frame;
	}
	
	public void mouseDragged(MouseEvent e) {
		frame.boutonSourisDeplace(e.getX(), e.getY());
	}
}
