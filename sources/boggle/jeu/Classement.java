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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import boggle.BoggleException;
import boggle.jeu.joueur.Humain;
import boggle.jeu.joueur.Joueur;

/**
 * Représentation d'un classement de différents joueurs, triés dans l'ordre décroissant de leurs scores.
 */
public class Classement extends Observable implements Observer {
	
	private static final String CLASSEMENT_FOLDER = "classement";

	private int dimensionGrille;
	private Joueur[] joueurs;
	
	public Classement(int n, Joueur[] joueurs) {
		Arrays.sort(joueurs);
		this.dimensionGrille = n;
		this.joueurs = joueurs;
		for (int i = 0; i < joueurs.length; i++) {
			joueurs[i].addObserver(this);
		}
	}
	
	/**
	 * Retourne tous les joueurs du classement
	 * 
	 * @return	le tableau des joueurs classés
	 */
	public Joueur[] getJoueurs() {
		return this.joueurs;
	}
	
	/**
	 * Retourne le joueur à la ligne i passée en paramètre
	 * 
	 * @param	i
	 * 			l'indice de la ligne dans le classement
	 * 
	 * @return	le joueur d'indice i dans le classement
	 */
	public Joueur getJoueur(int i) {
		if (i >= 0 && i < joueurs.length) {
			return joueurs[i];
		}
		return null;
	}
	
	/**
	 * Retourne le score du joueur à la ligne i passée en paramètre
	 * 
	 * @param	i
	 * 			l'indice de la ligne dans le classement
	 * 
	 * @return	le score du joueur d'indice i dans le classement
	 */
	public int getScore(int i) {
		Joueur joueur = getJoueur(i);
		return joueur == null ? -1 : joueur.getScore();
	}
	
	/**
	 * Retourne le nombre de lignes (de joueurs) dans le classement
	 * 
	 * @return	un entier représentant le nombre d'entrées dans le classement
	 */
	public int nbLignes() {
		return joueurs.length;
	}
	
	/**
	 * Lorsque les joueurs changent, le classement changent aussi.
	 * (C'est le même principe qu'avec la Grille et les Dés)
	 */
	public void update(Observable obs, Object o) {
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Représentation textuelle du classement
	 */
	public String toString() {
		Arrays.sort(joueurs);
		return "Classement: " + Arrays.toString(joueurs);
	}
	
	/**
	 * Sauvegarde les meilleurs scores des joueurs dans un fichier.
	 * Si le fichier existe et que le score des joueurs est supérieur dans le fichier,
	 * aucune modification n'est apportée dans le fichier
	 */
	public void sauvegarderMeilleursScores() {
		creerDossier();
		Path classementPath = Paths.get(CLASSEMENT_FOLDER, "classement-" + dimensionGrille + ".txt");
		Properties prop = charger(classementPath);
		String nom;
		String value;
		int score;
		for (Joueur joueur : joueurs) {
			// On ne sauvegarde les scores que des joueurs humains
			if (joueur.estHumain()) {
				nom = joueur.getNom();
				score = joueur.getScore();
				value = prop.getProperty(nom);
				if (value == null || !value.matches("[0-9]+")) {
					prop.setProperty(nom, String.valueOf(score));
				}
				else if (value != null && value.matches("[0-9]+")) {
					if (score > Integer.parseInt(value)) {					
						prop.setProperty(nom, String.valueOf(score));
					}
					else {
						prop.setProperty(nom, value);
					}
					System.out.println("2)" + value);
				}
			}
		}
		try (BufferedWriter out = Files.newBufferedWriter(classementPath, Charset.forName("UTF-8"))) {
			prop.store(out, null);
		} catch (IOException e) {
			throw new BoggleException("Une erreur s'est produite lors de l'écriture du fichier " + classementPath + "\n" + e);
		}
	}
	
	/**
	 * Charge le classement des meilleurs joueurs selon la dimension de la grille voulue
	 * 
	 * @param	n
	 * 			la dimension de la grille
	 * 
	 * @return	une instance de Classement représentant les meilleurs scores par rapport à la dimension d'une grille
	 */
	public static Classement depuisDimension(int n) {
		Properties prop = charger(Paths.get(CLASSEMENT_FOLDER, "classement-" + n + ".txt"));
		Joueur[] joueurs = new Joueur[prop.size()];
		String value;
		int score;
		int i = 0;
		for (String key : prop.stringPropertyNames()) {
			value = prop.getProperty(key);
			score = 0;
			if (value != null && value.matches("[0-9]+")) {
				score = Integer.parseInt(value);
			}
			joueurs[i++] = new Humain(key, score);
		}
		return new Classement(n, joueurs);
	}
	
	/**
	 * Charge un fichier de classement depuis son chemin d'accès
	 * 
	 * @param	path
	 * 			le chemin d'accès du fichier de classement
	 * 
	 * @return	une instance de Properties représentant le fichier de classement
	 */
	private static Properties charger(Path path) {
		Properties prop = new Properties();
		if (Files.isRegularFile(path)) {
			try (BufferedReader in = Files.newBufferedReader(path, Charset.forName("UTF-8"))){
				prop.load(in);
			} catch (IOException e) {
				throw new BoggleException("Une erreur s'est produite lors de l'ouverture du fichier " + path + "\n" + e);
			}
		}
		return prop;
	}
	
	/**
	 * Crée le dossier des meilleurs scores s'il n'existe pas
	 *
	 * @return	<code>true</code> si le dossier a été créé, <code>false</code> sinon
	 */
	private static boolean creerDossier() {
		Path classement = Paths.get(CLASSEMENT_FOLDER);
		if (!Files.isDirectory(classement)) {
			try {
				Files.createDirectory(classement);
				return true;
			} catch (IOException e) {
				throw new BoggleException("Le dossier des scores n'a pas pu être créé\n" + e);
			}
		}
		return false;
	}

}
