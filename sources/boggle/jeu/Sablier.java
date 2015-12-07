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

/**
 * Le sablier donne un temps imparti à chaque joueur
 * pour trouver des mots dans une grille.
 */
public class Sablier extends Observable implements Runnable {
	
	public static final int DEFAULT_DUREE = 60 * 5;
	public static final int ONE_SECOND = 1000;
	
	// La durée totale que peut atteindre le sablier
	private int dureeMax;
	// Le temps courant en secondes du sablier
	private int duree;
	// Indique si le sablier est arrêté ou non
	private boolean stop;
	
	public Sablier(int sec) {
		this.dureeMax = sec + 1;
		this.duree = dureeMax;
	}
	
	public Sablier() {
		this(DEFAULT_DUREE);
	}

	/**
	 * Arrête le sablier
	 * 
	 * Note: Pour pouvoir l'arrêter à tout moment, il est essentil qu'il n'y est pas le mot clé "synchronized"
	 */
	public void shutdown() {
		this.stop = true;
	}
	
	/**
	 * Remet le sablier à son état initial
	 */
	public void reset() {
		this.duree = dureeMax;
		this.stop = false;
	}
	
	/**
	 * Vérifie si le sablier est arrivé à son terme
	 * 
	 * @return <code>true</code> si le sablier est à 0, <code>false</code> sinon
	 */
	public boolean isOver() {
		return stop;
	}
	
	/**
	 * Le sablier continue tant qu'il n'est pas arrivé à 0 ou qu'il est arrêté par l'utilisateur
	 */
	public void run() {
		while (!isOver()) {
			if (!isOver()) {
				stop = (--duree <= 0);
				setChanged();
				notifyObservers();
			}
			try {
				Thread.sleep(ONE_SECOND);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Représentation textuelle du sablier
	 */
	public String toString() {
		int min = (duree % 3600) / 60;
		int sec = duree % 60;
		return String.format("%02d:%02d", min, sec);
	}

}
