package boggle.mots;

import java.util.ArrayList;
import java.util.List;

import boggle.BoggleException;


/**
 * Implémente une grille de dés.
 */
public class GrilleLettres implements Grille {
	
	private int dimension;
	private De[][] grille;
	
	/* 
	 * Représentation la grille sous la forme d'une seule dimension
	 * Ce tableau est initialisé par le constructeur
	 * et est utilisé notammenent par la méthode secouer()
	 */
	private De[] des;
	
	/**
	 * Constructeur pour instancier une grille de lettres
	 * 
	 * @param	dimension
	 * 			la taille de la grille (dimension x dimension)
	 * @param	des
	 * 			une liste de dés (ordonnée ou non) à ajouter à la grille
	 * 
	 * Note:
	 * On ajoute uniquement le nombre de dés qui peuvent être ajoutés dans la grille
	 * S'il y a moins de dé qu'il n'en faut dans la grille, on retourne une erreur
	 */
	public GrilleLettres(int dimension, De[] des) {
		if (dimension < DIMENSION_MIN) {
			throw new BoggleException("La dimension minimale d'une grille est " + DIMENSION_MIN);
		}
		if (des == null) {
			throw new BoggleException("Il n'y a pas aucun dé à placer dans la grille.");
		}
		if (des.length < dimension * dimension) {
			throw new BoggleException("Il n'y a pas suffisamment de dé pour cette grille.");
		}
		
		this.des = des;
		this.dimension = dimension;
		this.grille = new De[dimension][dimension];
		this.secouer();
	}
	
	public int dimension() {
		return this.dimension;
	}
	
	public boolean contient(int x, int y) {
		return x >= 0 && x < dimension && y >= 0 && y < dimension;
	}
	
	public boolean contient(Coordonnees c) {
		return contient(c.getX(), c.getY());
	}
	
	public void placer(De d, int x, int y) {
		grille[y][x] = d;
	}
	
	public void placer(De de, Coordonnees c) {
		placer(de, c.getX(), c.getY());
	}

	public De getDe(int x, int y) {
		return grille[y][x];
	}
	
	public De getDe(Coordonnees c) {
		return getDe(c.getX(), c.getY());
	}
	
	public boolean contientDe(int x, int y) {
		return getDe(x, y) != null;
	}
	
	public boolean contientDe(Coordonnees c) {
		return contientDe(c.getX(), c.getY());
	}

	public boolean estUtilise(int x, int y) {
		return getDe(x, y).estUtilise();
	}
	
	public boolean estUtilise(Coordonnees c) {
		return estUtilise(c.getX(), c.getY());
	}
	
	public String getFaceVisible(int x, int y) {
		return getDe(x, y).getFaceVisible();
	}
	
	public String getFaceVisible(Coordonnees c) {
		return getFaceVisible(c.getX(), c.getY());
	}

	public void secouer() {
		De.melange(des);
		int n = 0;
		for (int y=0; y < dimension; y++) {
			for (int x=0; x < dimension; x++) {
				placer(des[n], x, y);
				des[n].lancer();
				n++;
			}
		}
	}

	public boolean sontVoisins(Coordonnees c1, Coordonnees c2) {
		return c1.estVoisinDe(c2);
	}
	
	public List<De> voisins(int x, int y) {
		List<De> des = new ArrayList<>();
		Coordonnees tmp;
		for (int vy=-1; vy <= 1; vy++) {
			for (int vx=-1; vx <= 1; vx++) {
				if (vx == 0 && vy == 0) {
					continue;
				}
				tmp = new Coordonnees(x + vx, y + vy);
				if (contient(tmp)) {
					des.add(getDe(tmp));
				}
			}
		}
		return des;
	}

	public List<De> voisins(Coordonnees c) {
		return voisins(c.getX(), c.getY());
	}
	
	public String toString() {
		String str = "";
		for (int y=0; y < dimension; y++) {
			for (int x=0; x < dimension; x++) {
				str += grille[y][x].getFaceVisible();
				if (x < dimension - 1) {
					str += "\t";
				}
			}
			str += "\n";
		}
		return str;
	}
	
	public static void main(String[] args) {
		GrilleLettres g = new GrilleLettres(4, De.creerDes("des-4x4.csv"));
		System.out.println(g);
		System.out.println("On secoue...");
		g.secouer();
		System.out.println();
		System.out.println(g);
	}
	
}
