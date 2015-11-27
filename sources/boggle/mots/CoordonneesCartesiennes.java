package boggle.mots;

public class CoordonneesCartesiennes implements Coordonnees {
	
	private int x;
	private int y;
	
	public CoordonneesCartesiennes(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean equals(Object o) {
		Coordonnees c = (Coordonnees) o;
		return x == c.getX() && y == c.getY();
	}
	
	/**
	 * Additionne un couple de coordonnées et l'instance courante
	 * 
	 * @param	c
	 * 			le couple a additionner avec l'instance courante
	 * 
	 * @return	une nouvelle instance de Coordonnees représentant l'ajout des coordonnées entres-elles
	 */
	public Coordonnees ajoute(Coordonnees c) {
		return new CoordonneesCartesiennes(x + c.getX(), y + c.getY());
	}
	
	/**
	 * Vérifie si deux coordonnées sont voisines
	 * 
	 * @param	c
	 * 			la coordonnées pour la comparaison
	 * 
	 * @return	<code>true</code> si les coordonnées sont voisines, <code>false</code> sinon
	 */
	public boolean estVoisinDe(Coordonnees c) {
		int rx = Math.abs(x - c.getX());
		int ry = Math.abs(y - c.getY());
		return (rx == 0 || rx == 1 && ry == 0 && ry == 1);
	}
	
	public String toString() {
		return "Coordonnees(" + x + "," + y + ")";
	}
	
}
