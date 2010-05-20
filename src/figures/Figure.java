package figures;

import java.awt.*;

public interface Figure {
	public abstract double surface();

	public abstract Point_2D getCentre();

	public abstract void deplace(int dx, int dy);

	public abstract boolean contient(Point_2D p);

	public abstract String toString();

	public abstract void dessineToi(Graphics gx);
}	