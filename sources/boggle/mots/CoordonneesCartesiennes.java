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
 * Implémentation des coordonnées cartésiennes (x,y)
 */
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
	
	/**
	 * Deux coordonnées sont égales si leur abscisse et leur ordonnées sont égales
	 */
	public boolean equals(Object o) {
		Coordonnees c = (Coordonnees) o;
		return c != null && x == c.getX() && y == c.getY();
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
		return (rx == 0 || rx == 1) && (ry == 0 || ry == 1);
	}
	
	/**
	 * Représentation textuelle des coordonnées cartésiennes
	 */
	public String toString() {
		return "Coordonnees(" + x + "," + y + ")";
	}
	
}
