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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import boggle.BoggleException;

/**
 * Représente une grille de dés.
 */
public abstract class Grille extends Observable implements Observer {
	
	public static final int DIMENSION_MIN = 3;
	
	// La taille de la grille (dimension x dimension)
	private int dimension;
	
	// On utilise une liste (à contenu unique) pour stocker les mots fabriqués avec cette grille
	private List<String> mots;
	
	// On utilise une double file pour conserver les positions utilisée par le joueur
	// lors de la création d'un mot. Elle est vidée à chaque nouveau mot.
	private Deque<Coordonnees> deck;
	
	/**
	 * Constructeur d'une grille
	 * 
	 * @param	dimension
	 * 			la taille de la grille (dimension * dimension)
	 */
	public Grille(int dimension) {
		if (dimension < DIMENSION_MIN) {
			throw new BoggleException("La dimension minimale d'une grille est " + DIMENSION_MIN);
		}
		this.dimension = dimension;
		this.mots = new ArrayList<String>();
		this.deck = new ArrayDeque<Coordonnees>();
	}
	
	/**
	 * Retourne la dimension de la grille
	 * 
	 * @return	la dimension de la grille
	 */
	public int dimension() {
		return dimension;
	}
	
	/**
	 * Retourne la taille minimale d'un mot dans la grille
	 * 
	 * @return	la taille minimale d'un mot dans la grille
	 */
	public int tailleMinimale() {
		return dimension - 1;
	}
	
	/**
	 * Vérifie si les coordonnées (x,y) sont accessibles dans la grille
	 * 
	 * @param	c
	 * 			le couple de coordonnées (x,y)
	 * 
	 * @return	<code>true</code> si le couple (x,y) est contenu dans la grille
	 */
	public boolean contient(Coordonnees c) {
		int x = c.getX();
		int y = c.getY();
		return x >= 0 && x < dimension && y >= 0 && y < dimension;
	}
	
	/**
	 * Place un dé dans la grille
	 * 
	 * @param	d
	 * 			le dé à placer dans la grille
	 * @param	c
	 * 			les coordonnées du dé dans la grille
	 */
	public abstract void placer(De d, Coordonnees c);
	
	/**
	 * Retourne le dé de coordonnées (x,y)
	 * 
	 * @param	c
	 * 			les coordonnées du dé dans la grille
	 * 
	 * @return	une instance de dé
	 */
	public abstract De getDe(Coordonnees c);
	
	/**
	 * Vérifie si l'emplacement (x,y) contient un dé
	 * 
	 * @param	c
	 * 			les coordonnées du dé dans la grille
	 * 
	 * @return	<code>true</code> s'il y a un dé en (x,y), <code>false</code> sinon
	 */
	public boolean contientDe(Coordonnees c) {
		return getDe(c) != null;
	}
	
	/**
	 * Vérifie si le dé de coordonnées (x,y) est déjà utilisé
	 * 
	 * @param	c
	 * 			les coordonnées du dé dans la grille
	 * 
	 * @return	<code>true</code> si le dé est déjà utilisé, <code>false</code> sinon
	 */
	public boolean estUtilise(Coordonnees c) {
		return getDe(c).estUtilise();
	}
	
	/**
	 * Utilise le dé de coordonnées (x,y)
	 * 
	 * @param	c
	 * 			les coordonnées du dé dans la grille
	 */
	public void utiliserDe(Coordonnees c) {
		if (getDe(c).utiliser()) {
			deck.push(c);
		}
	}
	
	/**
	 * Vérifie si le dé de coordonnées (x,y) est le dernier utilisé
	 * 
	 * @param	c
	 * 			les coordonnées du dernier dé utilisé dans la grille
	 * 
	 * @return	<code>true</code> si le dé est le dernier utilisé, <code>false</code> sinon
	 */
	public boolean estDernierUtilise(Coordonnees c) {
		return c.equals(deck.peek());
	}
	
	/**
	 * Retourne la position du dernier dé utilisé par le joueur
	 * 
	 * @return	les coordonnées du dernier dé utilisé par le joueur
	 */
	public Coordonnees getDernierePosition() {
		return deck.peek();
	}
	
	/**
	 * Retourne le dernier dé utilisé par le joueur
	 * 
	 * @return	le dernier dé utilisé par le joueur
	 */
	public De getDernierDe() {
		return getDe(getDernierePosition());
	}
	
	/**
	 * Rend disponible le dé de coordonnées (x,y)
	 * 
	 * @param	c
	 * 			les coordonnées du dé dans la grille
	 */
	public void rendreDe(Coordonnees c) {
		if (getDe(c).rendre()) {
			deck.remove(c);
		}
	}
	
	/**
	 * Rend disponible le dernier dé utilisé par le joueur
	 */
	public void rendreDernierDeUtilise() {
		if (deck.size() > 0) {
			getDe(deck.pop()).rendre();
		}
	}
	
	/**
	 * Rend disponible tous les dés de la grille
	 */
	public void rendreTout() {
		while (!deck.isEmpty()) {
			getDe(deck.pop()).rendre();
		}
	}
	
	/**
	 * Retourne les lettres actuellement utilisée par l'utilisateur dans leur ordre d'utilisation
	 * 
	 * @return	les lettres utilisée par l'utilisateur dans l'ordre d'utilisation
	 */
	public String getLettresUtilisees() {
		if (deck.size() == 0) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		for (Coordonnees c : deck) {
			// On utilise un second StringBuilder au cas où la face d'un dé est un ensemble de lettres
			builder.append(new StringBuilder(getFaceVisible(c)).reverse());
		}
		return builder.reverse().toString();
	}
	
	/**
	 * Enregistre un mot obtenu avec cette grille
	 * 
	 * @return	<code>true</code> si le mot a bien été enregistré, <code>false</code> sinon
	 */
	public boolean stockerMot() {
		String mot = getLettresUtilisees();
		return !mots.contains(mot) && mot.length() >= tailleMinimale() && mots.add(mot);
	}
	
	/**
	 * Vide la liste de tous les mots obtenus avec cette grille
	 */
	public void viderMotsStockes() {
		mots.clear();
	}
	
	/**
	 * Retourne la liste des mots obtenus avec cette grille
	 * 
	 * @return	la liste de tous les mots obtenus par le joueur avec cette grille
	 */
	public List<String> getMots() {
		return mots;
	}
	
	/**
	 * Retourne la face visible du dé de coordonnées (x,y)
	 * 
	 * @param	c
	 * 			les coordonnées du dé dans la grille
	 * 
	 * @return	la face visible (String) du dé
	 */
	public String getFaceVisible(Coordonnees c) {
		return getDe(c).getFaceVisible();
	}
	
	/**
	 * Retourne un couple de coordonnées aléatoire compris dans la grille
	 * 
	 * @return	une instance de Coordonnees
	 */
	private Coordonnees getCoordonneesAleatoirement() {
		int x = (int) (Math.random() * dimension);
		int y = (int) (Math.random() * dimension);
		return new CoordonneesCartesiennes(x, y);
	}
	
	/**
	 * Secoue la grille pour mélanger les dés
	 */
	public void secouer() {
		int r = (int) (Math.random() * 500);
		De d1, d2;
		Coordonnees c1, c2;
		// On efface tous les mots qui ont été produits avec cette grille
		viderMotsStockes();
		// On rend disponible tous les dés de la grille qui ne l'étaient pas
		rendreTout();
		// On prend des dé aléatoirement et on échange leur position
		// puis on les lance pour mélanger les faces visibles
		for (int i=0; i < r; i++) {
			c1 = getCoordonneesAleatoirement();
			c2 = getCoordonneesAleatoirement();
			d1 = getDe(c1);
			d2 = getDe(c2);
			d1.lancer();
			d2.lancer();
			placer(d2, c1);
			placer(d1, c2);
		}
	}
	
	/**
	 * Vérifie si deux couples de coordonnées sont voisines
	 * 
	 * @param	c1
	 * 			le premier couple de coordonnées
	 * @param	c2
	 * 			le second couple de coordonnées
	 * 
	 * @return	<code>true</code> si les deux coordonnées sont voisines, <code>false</code> sinon
	 */
	public boolean sontVoisins(Coordonnees c1, Coordonnees c2) {
		return c1.estVoisinDe(c2);
	}
	
	/**
	 * Récupère la liste des positions des voisins d'un dé dans la grille
	 * 
	 * @param	c
	 * 			le couple de coordonnées pour lequel il faut trouver les voisins
	 * 
	 * @return	la liste des positions voisines d'un dé de la grille
	 */
	public List<Coordonnees> voisins(Coordonnees c) {
		List<Coordonnees> coords = new ArrayList<Coordonnees>();
		Coordonnees tmp;
		for (Coordonnees points : PointCardinal.values()) {
			tmp = c.ajoute(points);
			if (contient(tmp)) {
				coords.add(tmp);
			}
		}
		return coords;
	}
	
	/**
	 * Récupère la liste des positions des voisins non utilisés d'un dé dans la grille
	 * 
	 * @param	c
	 * 			le couple de coordonnées pour lequel il faut trouver les voisins
	 * 
	 * @return	la liste des positions voisines d'un dé de la grille
	 */
	public List<Coordonnees> voisinsDisponibles(Coordonnees c) {
		List<Coordonnees> coords = new ArrayList<Coordonnees>();
		Coordonnees tmp;
		for (Coordonnees points : PointCardinal.values()) {
			tmp = c.ajoute(points);
			if (contient(tmp) && !estUtilise(tmp)) {
				coords.add(tmp);
			}
		}
		return coords;
	}
	
	/**
	 * Vérifie si le mot passé en paramètre existe dans la grille en respectant les règles du jeu
	 * et l'écrit comme si l'utilisateur avait directement utiliser les dés de la grille
	 * 
	 * @param	mot
	 * 			le mot à chercher dans la grille
	 * 
	 * @return	<code>true</code> si le mot est écrit à partir de la grille, <code>false</code> sinon
	 */
	public synchronized boolean ecrire(String mot) {
		rendreTout();
		if (mot.length() == 0) {
			return true;
		}
		Coordonnees courant;
		// On parcourt la grille pour trouver la première lettre du mot
		for (int y=0; y < dimension; y++) {
			for (int x=0; x < dimension; x++) {
				courant = new CoordonneesCartesiennes(x, y);
				if (ecrire(mot, courant)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Vérifie de manière récursive si le mot passé en paramètre existe dans la grille en respectant les règles du jeu
	 * et l'écrit comme si l'utilisateur avait directement utiliser les dés de la grille
	 * 
	 * @param	mot
	 * 			le mot à chercher dans la grille
	 * @param	courant
	 * 			le couple de coordonnées du dé courant
	 * 
	 * @return	<code>true</code> si le mot est écrit à partir de la grille, <code>false</code> sinon
	 */
	private boolean ecrire(String mot, Coordonnees courant) {
		String face = getFaceVisible(courant);
		// Si la face visible du dé a plus de lettres que le mot (dé de syllabes par exemple)
		// On retourne directement false
		if (face.length() > mot.length()) {
			return false;
		}
		// Si on peut construire le mot avec la face
		if (face.equals(mot.substring(0, face.length()))) {
			utiliserDe(courant);
			mot = mot.substring(face.length());
			// Si le mot est vide, on a réussi à le fabriquer complétement avec la grille
			if (mot.length() == 0) {
				return true;
			}
			// Dans le cas contraire, on réitère récursivement la méthode avec les voisins disponibles du dé courant
			for (Coordonnees voisin : voisinsDisponibles(courant)) {
				if (ecrire(mot, voisin)) {
					return true;
				}
			}
			rendreDe(courant);
		}
		return false;
	}
	
	/**
	 * Retourne un affichage au format texte de la grille
	 */
	public String toString() {
		String str = "";
		for (int y=0; y < dimension; y++) {
			for (int x=0; x < dimension; x++) {
				str += getDe(new CoordonneesCartesiennes(x, y)).getFaceVisible();
				if (x < dimension - 1) {
					str += "\t";
				}
			}
			str += "\n";
		}
		return str;
	}
	
	/**
	 * La grille observe les dés, c'est à dire que cette méthode est appelée
	 * lorsqu'un dé appartenant à la grille change d'état.
	 */
	public void update(Observable obs, Object o) {
		// On indique également aux observeurs de la grille qu'elle a changé
		// En plus de la grille, on envoie le dé qui a bougé (obs) aux observeurs
		setChanged();
		notifyObservers(obs);
	}

}
