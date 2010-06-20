package figures;

import java.awt.*;

public interface Figure {
    public abstract Point_2D getCentre();

    public abstract void deplace(int dx, int dy);

    public abstract boolean contient(Point_2D p);

    @Override
    public abstract String toString();

    public abstract void dessineToi(Graphics gx);
}	