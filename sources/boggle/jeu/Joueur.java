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
package boggle.jeu;

import java.util.Stack;

import boggle.mots.Coordonnees;
import boggle.mots.Grille;

/**
 * Représentation générique d'un joueur de Boggle
 */
public abstract class Joueur {
	
	private String name;
	private int score;
	
	public Joueur(String name) {
		this.name = name;
		this.score = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
	
	public void resetScore() {
		this.score = 0;
	}
	
	public void inc(int n) {
		this.score += n;
	}
	
	public void joue(Grille g, Coordonnees c, Stack<Coordonnees> coordUtilisees) {
		Coordonnees tete = coordUtilisees.peek();
		if (c.estVoisinDe(tete)) {
			if (!g.estUtilise(c)) {
				g.utiliserDe(c);
				coordUtilisees.push(c);
			}
		}
		else if (c.equals(tete)) {
			g.rendreDe(c);
			coordUtilisees.pop();
		}
	}
	
}
