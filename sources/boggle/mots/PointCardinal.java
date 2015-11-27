package boggle.mots;

/**
 * Enumère les différents points cardinaux
 */
public enum PointCardinal implements Coordonnees {
	
	NORD(0, -1), EST(1, 0), SUD(0, 1), OUEST(-1, 0), NORD_OUEST(-1, -1), NORD_EST(-1, 1), SUD_OUEST(1, -1), SUD_EST(1,1);
	
	private int x;
	private int y;
	
	private PointCardinal(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public Coordonnees ajoute(Coordonnees c) {
		return new CoordonneesCartesiennes(x + c.getX(), y + c.getY());
	}

	/* Les points cardinaux ne sont voisins de personnes */
	public boolean estVoisinDe(Coordonnees c) {
		return false;
	}

}
