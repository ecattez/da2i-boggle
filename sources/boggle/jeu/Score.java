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

import java.util.Map;

import boggle.jeu.joueur.Joueur;

/**
 * Un score est une implémentation de {@link Map.Entry} avec comme clé le nom d'un joueur et comme valeur son score
 */
public class Score implements Map.Entry<String, Integer>, Comparable<Score> {
	
	private String nomJoueur;
	private Integer scoreJoueur;
	
	public Score(String nomJoueur, Integer scoreJoueur) {
		this.nomJoueur = nomJoueur;
		this.scoreJoueur = scoreJoueur;
	}
	
	public Score(Joueur joueur) {
		this(joueur.getNom(), joueur.getScore());
	}

	public String getKey() {
		return nomJoueur;
	}

	public Integer getValue() {
		return scoreJoueur;
	}

	public Integer setValue(Integer value) {
		int v = scoreJoueur;
		scoreJoueur = value;
		return v;
	}
	
	/* La comparaison se fait dans l'ordre décroissant */
	public int compareTo(Score o) {
		return o.scoreJoueur.compareTo(scoreJoueur);
	}
	
	/**
	 * Représentation textuelle d'un score
	 */
	public String toString() {
		return nomJoueur + "=" + scoreJoueur;
	}

}
