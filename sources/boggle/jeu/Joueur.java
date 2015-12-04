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

import java.util.Observable;

import boggle.mots.ArbreLexical;
import boggle.mots.GrilleLettres;

/**
 * Représentation générique d'un joueur de Boggle
 */
public abstract class Joueur extends Observable {
	
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
	
	public void incScore(int n) {
		this.score += n;
	}
	
	/**
	 * Le joueur effectue une action dans le Boggle
	 * 
	 * @param	grille
	 * 			la grille utilisée pour la partie du joueur
	 * @param	arbre
	 * 			l'arbre lexical utilisé pour la partie du joueur
	 * @param	partie
	 * 			la partie dans laquelle joue le joueur
	 */
	public abstract void joue(GrilleLettres grille, ArbreLexical arbre, Partie partie);
	
}
