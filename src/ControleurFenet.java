import java.awt.event.*;

class ControleurFenet extends WindowAdapter  	{

	FenetAffiche fenet;

	public ControleurFenet (FenetAffiche fenet){
	this.fenet = fenet;
	};

	public void windowClosing (WindowEvent e)	{
		System.exit(0);
	}
}
