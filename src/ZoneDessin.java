import java.awt.*;
class ZoneDessin extends Canvas	{
	private int xinit, yinit;
	private int x=20,y=20,width=20,height=20;
	private Color color = Color.black;
	private FenetAffiche frame;

	public ZoneDessin(FenetAffiche frame)	{
		this.frame = frame;
		setBackground(Color.white);
		//$$3   
		addMouseListener(new GestionBoutonsSouris(frame));
	}

	public void paint (Graphics g) {
	//$$2
		frame.dessineFigs(g);
	}

}
