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
package boggle.mots ;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.List;
import java.util.Scanner;

import boggle.BoggleException;

/**
 * La classe ArbreLexical permet de stocker de façon compacte et d'accéder rapidement à un ensemble de mots.
 */
public class ArbreLexical {

	public static final int TAILLE_ALPHABET = 26;
	
	private boolean estMot; // vrai si le noeud courant est la fin d'un mot valide
	private ArbreLexical[] fils = new ArbreLexical[TAILLE_ALPHABET]; // les sous-arbres

	/**
	 * Crée un arbre vide (sans aucun mot)
	 */
	public ArbreLexical() {}
	
	/**
	 * Retourne le véritable indice d'une majuscule
	 * 
	 * @param	lettre
	 * 			la lettre pour laquelle il faut trouver l'indice
	 * 
	 * @return	l'indice de la lettre passé en paramètre
	 */
	private int indexOf(char lettre) {
		return lettre - 'A';
	}
	
	/**
	 * Retourne la véritable lettre à l'indice en paramètre (et non sa valeur ASCII)
	 * 
	 * @param	i
	 * 			l'indice de la lettre à retrouver
	 * 
	 * @return	le caractère d'indice passé en paramètre
	 */
	private char charAt(int i) {
		return (char) (i + 'A');
	}

	/**
	 * Indique si le noeud courante est situé à l'extrémité d'un mot valide
	 */
	public boolean estMot() {
		return estMot ;
	}
	
	/**
	 * Place le mot en paramètre dans l'arbre
	 * 
	 * @param	word
	 * 			le mot à ajouter dans l'arbre
	 * 
	 * @return	<code>true</code> si le mot a été ajouté, <code>false</code> sinon
	 */
	public boolean ajouter(String word) {
		word = Normalizer.normalize(word, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toUpperCase();
		return word.matches("[A-Z]+") && ajouterNorme(word);
	}

	/**
	 * Place le mot préalablement normé (ASCII en majuscule) dans l'arbre
	 * 
	 * @param	word
	 * 			le mot à ajouter dans l'arbre
	 * 
	 * @return	<code>true</code> si le mot a été ajouté, <code>false</code> sinon
	 */
	private boolean ajouterNorme(String word) {
		if (word.length() == 0) {
			estMot = true;
			return true;
		}
		char c = word.charAt(0);
		int i = indexOf(c);
		if (fils[i] == null) {
			fils[i] = new ArbreLexical();
		}
		return fils[i].ajouterNorme(word.substring(1));
	}

	/**
	 * Teste si l'arbre lexical contient le mot spécifié.
	 * 
	 * @param	word
	 * 			le mot qui peut se trouver dans l'arbre
	 * 
	 * @return	<code>true</code> si <code>word</code> est un mot (String) contenu dans l'arbre, <code>false</code> sinon.
	 */
	public boolean contient(String word) {
		if (word.length() == 0) {
			return estMot();
		}
		char c = Character.toUpperCase(word.charAt(0));
		ArbreLexical noeud = fils[indexOf(c)];
		return noeud != null && noeud.contient(word.substring(1));
	}
	
	/**
	 * Ajoute à la liste <code>resultat<code> tous les mots de l'arbre commençant par le préfixe spécifié.
	 * 
	 * @param	prefixe
	 * 			le préfixe du mot
	 * @param	resultat
	 * 			la liste des mots qui commence par le préfixe
	 *  
	 * @return	<code>true</code> si <code>resultat</code> a été modifié, <code>false</code> sinon.
	 */
	public boolean motsCommencantPar(String prefixe, List<String> resultat) {
		return motsCommencantPar(prefixe, 0, resultat);
	}

	/**
	 * Ajoute à la liste <code>resultat<code> tous les mots de l'arbre commençant par le préfixe spécifié.
	 * 
	 * @param	prefixe
	 * 			le préfixe du mot
	 * @param	niveau
	 * 			le niveau de profondeur courant de l'arbre
	 * @param	resultat
	 * 			la liste des mots qui commence par le préfixe
	 *  
	 * @return	<code>true</code> si <code>resultat</code> a été modifié, <code>false</code> sinon.
	 */
	private boolean motsCommencantPar(String prefixe, int niveau, List<String> resultat) {
		if (niveau < prefixe.length()) {
			ArbreLexical suivant = fils[indexOf(prefixe.charAt(niveau))];
			if (suivant == null) {
				return false;
			}
			if (suivant.estMot()) {
				resultat.add(prefixe);
			}
			suivant.motsCommencantPar(prefixe, niveau + 1, resultat);
		}
		else {
			for (int i=0; i < fils.length; i++) {
				if (fils[i] == null) {
					continue;
				}
				if (fils[i].estMot()) {
					resultat.add(prefixe + charAt(i));
				}
				fils[i].motsCommencantPar(prefixe + charAt(i), niveau + 1, resultat);
			}
		}
		return resultat.size() > 0;
	}
	
	/**
	 * Crée un arbre lexical qui contient tous les mots du fichier spécifié.
	 * 
	 * @param	fichier
	 * 			le nom du fichier à charger
	 */
	public static ArbreLexical creerArbre(String fichier) {
		ArbreLexical root = new ArbreLexical();
		Path path = Paths.get("config", fichier);
		try (Scanner sc = new Scanner(path)) {
			while (sc.hasNextLine()) {
				root.ajouter(sc.nextLine());
			}
		} catch (IOException e) {
			throw new BoggleException("Impossible de créer un arbre lexical avec le fichier " + fichier + "\n" + e);
		}
		return root;
	}
	
}
