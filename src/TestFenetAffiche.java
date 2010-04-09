import java.awt.*;	
import java.util.Vector;
class TestFenetAffiche {
	public static void main (String args[]){
		Vector<FigureGraphique> vec = new Vector<FigureGraphique>(10,1);
		Rectangle rec1 = new Rectangle ("rec1", Color.blue, Color.red, 20,20,50,40);
		 vec.addElement(rec1);
		Rectangle rec2 = new Rectangle ("rec2", Color.blue, Color.yellow, 70,60,50,40);
		 vec.addElement(rec2);
		Rectangle rec3 = new Rectangle ("rec2", Color.blue, Color.green, 120,100,50,40);
		 vec.addElement(rec3);
		 Cercle c1 = new Cercle("c1",Color.green, Color.yellow, 215,170,50);
		 vec.addElement(c1);
		 new FenetAffiche(vec);
	}
}