import java.awt.event.*;
class GestionBoutonsSouris extends MouseAdapter 	{
        protected FenetAffiche frame;
		
        public GestionBoutonsSouris (FenetAffiche frame)	{
                this.frame = frame;
        }

        public void mousePressed( MouseEvent e)	{
                frame.boutonSourisEnfonce(e.getX(),e.getY());
        }

        public void mouseReleased( MouseEvent e)	{
                frame.boutonSourisRelache(e.getX(),e.getY());
        }
}
