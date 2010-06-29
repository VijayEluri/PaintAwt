package fenetres;

import exceptions.PolygoneConcave;
import gestions.GestionDeplacementSouris;
import figures.Point_2D;
import figures.FigureGraphique;
import figures.Cercle;
import figures.Rectangle;
import controles.*;
import exceptions.CreateFigureCancelled;
import figures.Polygone;
import figures.Triangle;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Class principale de l'application, elle s'occupe de faire l'appel aux
 * différentes classes composants l'application
 * 
 */
public class FenetAffiche extends Frame {

    /**
     * Variable contenant la ZoneDessin de l'application
     */
    public ZoneDessin zd;
    /**
     * Variable contenant le Graphics associée à la ZoneDessin zd
     */
    public Graphics gx;
    // Variable contenant toutes figures déjà déssinées
    private Vector<FigureGraphique> figs;
    // Variables contenant les coordonnées du point cliqué par l'utilisateur
    private int xEnfonce, yEnfonce;
    // Variable contenant les points cliqués consécutivement par l'utilisateur
    private ArrayList<Point_2D> listePoints = new ArrayList();
    /**
     * Variable contenant la couleur courante
     */
    public Color couranteCol;
    /**
     * Variable contenant la couleur de fond courante
     */
    public Color couranteFgCol;
    /**
     * Variable contenant la liste des figures sélectionnées par l'utilisateur
     */
    public ArrayList<FigureGraphique> save = new ArrayList();
    /**
     * Variable contenant le type de forme sélectionné
     */
    public String choice;
    /**
     * Variable utilisé pour savoir si l'utilisateur fait de la sélection mutliple
     */
    public Boolean saisie = false;
    /**
     * Variable contenant la nom de la figure 
     */
    public String nom = null;
    /**
     * Vriable permettant de savoir si une suppression est prévu par l'utilisateur
     */
    public Boolean suppr = false;
    /**
     * Variable pour stocker le vecteur de déplacement dû à la translation
     */
    private Point_2D diffr = new Point_2D();
    /**
     * Variable pour stocker l'écart entre le centre du cercle et la taille du cercle déssiné par drawOval.
     */
    private Point_2D ecart = new Point_2D();
    //Le mouseMotionListener pour le pseudo drag and drop
    GestionDeplacementSouris motionListener = new GestionDeplacementSouris(this);

    /**
     * Constructeur de l'application, prend en paramètre le Vector contenant les
     * figures à afficher sur la ZoneDessin zd
     * @param vec Vector<FigureGraphique>
     */
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
        Graphics gx = zd.getGraphics();
        zd.addMouseMotionListener(motionListener);
        dessineFigs(gx);
    }

    /**
     * Méthode permettant de dessiner toues les figures, prend paramètre le
     * Graphique pour dessiner sur la ZoneDessin zd
     * @param g
     */
    public void dessineFigs(Graphics g) {
        for (int i = 0; i < figs.size(); i++) {
            figs.get(i).dessineToi(g);
        }
    }

    /**
     * Méthode qui gère le changement de couleur
     * @param color Color
     */
    public void changeCouleur(Color color) {
        couranteCol = color;
    }

    /**
     * Méthode traitant les actions pour le clique de la souris, prend en
     * paramètre les coordonnés du clique
     * @param x int
     * @param y int
     */
    public void boutonSourisEnfonce(int x, int y) {
        xEnfonce = x;
        yEnfonce = y;

        Point_2D p = new Point_2D(x, y);
        for (FigureGraphique current : figs) {
            if (current.contient(p)) {
                save.add(current);
            }
        }
        listePoints.add(new Point_2D(x, y));
    }

    /**
     * Méthode permettant les actions lors du relachement du clic de la souris, prend en
     * parmètre les coordonnées du point de relâchement de la souris
     * @param x int
     * @param y int
     */
    public void boutonSourisRelache(int x, int y) {
        Graphics g = zd.getGraphics();
        
        Point_2D vect = new Point_2D(Math.abs(x - xEnfonce), Math.abs(y - yEnfonce));
        if (!save.isEmpty() && saisie == false) {
            for (FigureGraphique current : save) {
                if (save.size() == 1) {
                    current.deplace(x, y);
                } else {
                    current.translate(diffr);
                }
                current.dessineToi(g);
            }
            save.clear();
            
        } else if (save.isEmpty() && suppr != true) {
            g.setColor(couranteCol);
            try {
                if (choice.compareTo("cercle") == 0) {
                    saisirNom(1);
                    int rayon = Math.min(Math.abs(Math.abs(x - xEnfonce) / 2), Math.abs(y - yEnfonce));
                    Cercle cercle = new Cercle(nom, couranteCol, couranteFgCol, xEnfonce + (ecart.x / 2), yEnfonce + (ecart.y / 2), rayon);
                    cercle.dessineToi(g);
                    figs.add(cercle);
                    listePoints = new ArrayList();
                } else if (choice.compareTo("rectangle") == 0) {
                    saisirNom(2);
                    Point_2D p = new Point_2D(Math.abs(x - xEnfonce), Math.abs(y - yEnfonce));
                    Point_2D pgh = calculeBonSens(x, y);
                    Rectangle rectangle = new Rectangle(nom, couranteCol, couranteFgCol, pgh.x, pgh.y, p.x, p.y);
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
                        triangle.dessineToi(g);
                        figs.add(triangle);
                        listePoints = new ArrayList();
                    }
                } else {
                    if (saisie == false && listePoints.size() >= 3) {
                        saisirNom(4);
                        Polygone polygone;
                        try {
                            polygone = new Polygone(nom, couranteCol, couranteFgCol, listePoints, listePoints.size());
                            polygone.dessineToi(g);
                            figs.add(polygone);
                        } catch (PolygoneConcave ex) {
                            new FenetDialogues(this, ex);

                        }
                        listePoints = new ArrayList();
                    }
                }
            } catch (CreateFigureCancelled e) {
            }
        }
        suppr = false;
        zd.paint(g);
    }

    /**
     * Méthode gérant les actions avec le déplacement de la souris
     * @param x int
     * @param y int
     */
    public void boutonSourisDeplace(int x, int y) {
        Graphics g = zd.getGraphics();

        if (!save.isEmpty()) {
            trie();
            for (FigureGraphique current : save) {
                if (save.size() == 1) {
                    current.deplace(x, y);
                } else {
                    diffr = new Point_2D((x - xEnfonce) / 2, (y - yEnfonce) / 2);
                    current.translate(diffr);
                }
                current.dessineToi(g);
            }
            if (save.size() > 1) {
                xEnfonce = save.get(save.size() - 1).getCentre().x;
                yEnfonce = save.get(save.size() - 1).getCentre().x;
            }
            listePoints = new ArrayList();
        } else {
            if (choice.compareTo("cercle") == 0) {
                //ecart = new Point_2D(Math.abs(x - xEnfonce), Math.abs(y - yEnfonce));
                ecart = calculeBonSens(x - xEnfonce, y - yEnfonce);
                Point_2D pgh = calculeBonSens(x, y);
                g.drawOval(pgh.x, pgh.y, Math.abs(ecart.x), Math.abs(ecart.y));
                
            } else {
                Point_2D p = new Point_2D(Math.abs(x - xEnfonce), Math.abs(y - yEnfonce));
                Point_2D pgh = calculeBonSens(x, y);
                g.drawRect(pgh.x, pgh.y, p.x, p.y);
                
            }
        }
        zd.paint(g);
        zd.repaint();
    }

    //Méthode permettant la saisie du nom de la figure courante 
    private void saisirNom(int figure) throws exceptions.CreateFigureCancelled {

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

    //Méthode permettant de mettre au premier plan les figures sélectionnées
    private void trie() {
        Vector<FigureGraphique> temp = new Vector<FigureGraphique>(figs);
        for (FigureGraphique current : save) {
            temp.remove(current);
        }
        figs.clear();
        figs.addAll(0, temp);
        figs.addAll(temp.size(), save);
    }

    //Méthode renvoyant le bon Point_2D pour le dessin d'une figure
    private Point_2D calculeBonSens(int x, int y) {
        Point_2D point = new Point_2D(xEnfonce, yEnfonce);

        if (x < xEnfonce) {
            point = new Point_2D(x, y);
            if (y > yEnfonce) {
                point = new Point_2D(x, yEnfonce);
            }
        } else if (y < yEnfonce) {
            point = new Point_2D(xEnfonce, y);
        }
        return point;
    }

    /**
     * Getter du Vector figs
     * @return Vector<FigureGraphique>
     */
    public Vector<FigureGraphique> getFigs() {
        return figs;
    }

    /**
     * Setter du Vector figs, prend en paramètre le Vector a associer
     * @param figs Vector<FigureGraphique>
     */
    public void setFigs(Vector<FigureGraphique> figs) {
        this.figs = figs;
    }
}
