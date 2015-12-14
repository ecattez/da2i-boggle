/**
 * This file is part of da2i-boggle.
 *
 * da2i-boggle is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * da2i-boggle is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.				 
 * 
 * You should have received a copy of the GNU General Public License
 * along with da2i-boggle.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * @author Edouard CATTEZ <edouard.cattez@sfr.fr> (La 7 Production)
 */
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
