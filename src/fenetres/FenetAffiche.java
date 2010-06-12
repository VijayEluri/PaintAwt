package fenetres;

import gestions.GestionDeplacementSouris;
import figures.Point_2D;
import figures.FigureGraphique;
import figures.Cercle;
import figures.Rectangle;
import controls.*;
import figures.Polygone;
import figures.Triangle;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Vector;

public class FenetAffiche extends Frame {

    public ZoneDessin zd;
    public Graphics gx;
    public Vector<FigureGraphique> figs;
    private int xEnfonce, yEnfonce;
    private ArrayList<Point_2D> listePoints = new ArrayList();
    public Color couranteCol;
    public Color couranteFgCol;
    //Variable de sauvegarde de la figure sélectionnée
    public ArrayList<FigureGraphique> save = new ArrayList();
    //Variable pour stocker le type de dessin à produire
    public String choice;
    //connaitre si l'utilisateur en à fini avec la sasie des points du polynome
    public Boolean saisie = false;
    public String nom = null;
    //Le mouseMotionListener pour le pseudo drag and drop
    GestionDeplacementSouris motionListener = new GestionDeplacementSouris(this);

    public FenetAffiche(Vector<FigureGraphique> vec) {
        setSize(700, 500);
        setTitle("Affichage de Figures Graphiques");
        setForeground(Color.black);
        setBackground(Color.GRAY);
        couranteCol = Color.blue;
        couranteFgCol = Color.blue;
        choice = "rectangle";
        figs = vec;
        add(zd = new ZoneDessin(this), "Center");
        //$$1
        addWindowListener(new ControlFenet(this));
        //$$6
        add(new BarreOutils(this), "North");
        setMenuBar(new BarreMenu(this));
        setVisible(true);
        setFocusable(true);
        requestFocus();
        zd.addKeyListener(new ControlClavier(this));
        Graphics gx = zd.getGraphics();
        dessineFigs(gx);
    }

    public void dessineFigs(Graphics g) {
        for (int i = 0; i < figs.size(); i++) {
            figs.get(i).dessineToi(g);
        }
    }

    public void changeCouleur(Color color) {
        // faire le changement de couleur
        couranteCol = color;
    }

    public void boutonSourisEnfonce(int x, int y) {
        xEnfonce = x;
        yEnfonce = y;

        //Test de la gestion de déplacement de figure
        //Je met une variable FigureGraphique pour save la derniere figure pouvant abriter les bons parametre
        //Pour l'instant c'est pour test avec la boucle for
        Point_2D p = new Point_2D(x, y);
        for (FigureGraphique current : figs) {
            if (current.contient(p)) {
                save.add(current);
            }
        }

        if (!save.isEmpty()) {
            zd.addMouseMotionListener(motionListener);
        }
        //$$3
		/*System.out.println("bouton gauche enfoncé x:" + x + " y: "+ y);
        Graphics g = zd.getGraphics();
        g.drawString("<xE : "+x+", yE : "+y+">",x,y);*/
        listePoints.add(new Point_2D(x, y));
    }

    public void boutonSourisRelache(int x, int y) {
        Graphics g = zd.getGraphics();
        //$$3
        //System.out.println("bouton gauche relaché x:" + x + " y: "+ y);
        //$$4
        Point_2D vect = new Point_2D(Math.abs(x - xEnfonce), Math.abs(y - yEnfonce));
        if (!save.isEmpty() && saisie == false) {
            for (FigureGraphique current : save) {
                if (save.size() == 1) {
                    current.deplace(x, y);
                } else {
                   current.translate(vect);
                }
                current.dessineToi(g);
            }
            save.clear();
            zd.removeMouseMotionListener(motionListener);
        } else if (save.isEmpty()) {
            g.setColor(couranteCol);
            if (choice.compareTo("cercle") == 0) {
                saisirNom(1);
                //g.drawOval(xEnfonce, yEnfonce, Math.abs(x - xEnfonce), Math.abs(y - yEnfonce));
                //$$5
                int rayon = Math.min(Math.abs(Math.abs(x - xEnfonce) / 2), Math.abs(y - yEnfonce));
                Cercle cercle = new Cercle(nom, couranteCol, couranteFgCol, xEnfonce, yEnfonce, rayon);
                cercle.dessineToi(g);
                figs.add(cercle);
                listePoints = new ArrayList();
            } else if (choice.compareTo("rectangle") == 0) {
                saisirNom(2);
                Point_2D p = new Point_2D(Math.abs(x - xEnfonce), Math.abs(y - yEnfonce));
                //g.drawRect(xEnfonce, yEnfonce, p.x, p.y);
                Rectangle rectangle = new Rectangle(nom, couranteCol, couranteFgCol, xEnfonce, yEnfonce, p.x, p.y);
                rectangle.dessineToi(g);
                figs.add(rectangle);
                listePoints = new ArrayList();
            } else if (choice.compareTo("triangle") == 0) {
                if (listePoints.size() == 3) {
                    saisirNom(3);
                    Point_2D[] points = new Point_2D[3];
                    for (int i = 0; i < 3; i++) {
                        points[i] = listePoints.get(i);
                    }
                    Triangle triangle = new Triangle(nom, couranteCol, couranteFgCol, points);
                    //g.drawPolygon(triangle.getXTab(), triangle.getYTab(), 3);
                    triangle.dessineToi(g);
                    figs.add(triangle);
                    listePoints = new ArrayList();
                }
            } else {
                if (saisie == false && listePoints.size() >= 3) {
                    saisirNom(4);
//                    Point_2D[] points = new Point_2D[listePoints.size()];
//                    for (int i = 0; i < listePoints.size(); i++) {
//                        points[i] = listePoints.get(i);
//                    }
                    Polygone polygone = new Polygone(nom, couranteCol, couranteFgCol, listePoints, listePoints.size());
                    polygone.dessineToi(g);
                    figs.add(polygone);
                    listePoints = new ArrayList();
                }
            }
        }
        zd.repaint();
    }

    public void boutonSourisDeplace(int x, int y) {
        Graphics g = zd.getGraphics();
        Point_2D vect = new Point_2D(Math.abs(x - xEnfonce), Math.abs(y - yEnfonce));
        if (!save.isEmpty()) {
            for (FigureGraphique current : save) {
                if (save.size() == 1) {
                    current.deplace(x, y);
                } else {
                   current.translate(vect);
                }
                current.dessineToi(g);
            }
            listePoints = new ArrayList();
        } else {
            if (choice.compareTo("cercle") == 0) {
                g.drawOval(xEnfonce, yEnfonce, Math.abs(x - xEnfonce), Math.abs(y - yEnfonce));
                System.out.println("cer" + x + " " + y);
            } else {
                g.drawRect(xEnfonce, yEnfonce, x, y);
                System.out.println("rect" + x + " " + y);
            }
        }
        zd.repaint();
    }

    private void saisirNom(int figure) {

        switch (figure) {
            case 1:
                nom = "c" + Cercle.nbCercle;
                new FenetDialogues(this, nom);
                break;

            case 2:
                nom = "r" + Rectangle.nbRect;
                new FenetDialogues(this, nom);
                break;

            case 3:
                nom = "t" + Triangle.nbTri;
                new FenetDialogues(this, nom);
                break;

            default:
                nom = "p" + Polygone.nbPoly;
                new FenetDialogues(this, nom);
                break;
        }
    }
}
