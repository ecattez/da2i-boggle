package boggle.gui;

public class Coordonnees {
	
	private int x;
	private int y;
	
	public Coordonnees(int x, int y) {
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
		return x == c.x && y == c.y;
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
		int rx = Math.abs(x - c.x);
		int ry = Math.abs(y - c.y);
		return (rx == 0 || rx == 1 && ry == 0 && ry == 1);
	}
	
}
