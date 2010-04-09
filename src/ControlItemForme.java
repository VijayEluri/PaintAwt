import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class ControlItemForme implements ItemListener {
	protected FenetAffiche frame;
	String formes[];
	
	public ControlItemForme(String formes[], FenetAffiche frame) {
		this.formes = formes;
		this.frame = frame;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Choice choice = (Choice) e.getSource();
		frame.choice = new String(formes[choice.getSelectedIndex()]);
	}

}
