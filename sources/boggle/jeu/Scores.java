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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import boggle.BoggleException;
import boggle.jeu.joueur.Joueur;

/**
 * Cette classe permet d'accéder aux meilleurs scores des joueurs ou d'ajouter leur meilleur score selon la taille grille.
 * 
 * Les scores sont relatifs à la dimension de la grille, c'est à dire qu'un joueur peut avoir un score différent
 * selon si la grille est de dimensions 4x4, 5x5 ... NxN.
 */
public final class Scores {
	
	private static final Path SCORES_FOLDER = Paths.get("scores");
	private static final String DEFAULT_FILENAME = "scores-grille-";
	
	/**
	 * Empêche l'instanciation de la classe
	 */
	private Scores() {}
	
	/**
	 * Charge les meilleurs scores par rapport à une taille de grille
	 * 
	 * @param	n
	 * 			la dimension de la grille
	 * 
	 * @return	les meilleurs scores sous la forme nomDuJoueur/scoreDuJoueur pour les grilles de taille n
	 */
	public static List<Score> chargerScores(int n) {
		Properties prop = chargerProperties(n);
		List<Score> scores = new ArrayList<Score>();
		String key;
		String value;
		for (Entry<Object, Object> entry : prop.entrySet()) {
			key = (String) entry.getKey();
			value = (String) entry.getValue();
			
			// Si la valeur est un nombre positif, on ajoute le score à la liste
			if (value.matches("[0-9]+")) {
				scores.add(new Score(key, Integer.parseInt(value)));
			}
		}
		Collections.sort(scores);
		return scores;
	}
	
	/**
	 * Ajoute le (meilleur) score d'un joueur dans le fichier correspondant
	 * 
	 * @param	n
	 * 			la taille des grilles résolues par le joueur, lui valant son score
	 * @param	joueur
	 * 			le joueur pour lequel il faut sauvegarder le score
	 */
	public static void ajouterScore(int n, Joueur joueur) {
		Properties prop = chargerProperties(n);
		Path scoreFile = getPathByDimension(n);
		mkdirScores();
		prop.setProperty(joueur.getNom(), String.valueOf(joueur.getScore()));
		try (BufferedWriter out = Files.newBufferedWriter(scoreFile, StandardOpenOption.CREATE)) {
			prop.store(out, "Scores pour les grilles de taille " + n);
		} catch (IOException e) {
			throw new BoggleException("Une erreur s'est produite lors de l'écriture du fichier de score " + scoreFile + "\n" + e);
		}
	}
	
	/**
	 * Charge les meilleurs scores sous la forme de clés/valeurs avec la clé le nom du joueur et la valeur son score
	 * 
	 * @param	n
	 * 			la dimension de la grille
	 * 
	 * @return	les meilleurs scores sous la forme nomDuJoueur/scoreDuJoueur pour les grilles de taille n
	 */
	private static Properties chargerProperties(int n) {
		Properties prop = new Properties();
		Path scoreFile = getPathByDimension(n);
		try (BufferedReader in = Files.newBufferedReader(scoreFile)) {
			prop.load(in);
		} catch (IOException e) {
			throw new BoggleException("Une erreur s'est produite lors de l'ouverture du fichier de score " + scoreFile + "\n" + e);
		}
		return prop;
	}
	
	/**
	 * Crée le dossier des meilleurs scores s'il n'existe pas
	 *
	 * @return	<code>true</code> si le dossier a été créé, <code>false</code> sinon
	 */
	private static boolean mkdirScores() {
		if (!Files.isDirectory(SCORES_FOLDER)) {
			try {
				Files.createDirectory(SCORES_FOLDER);
				return true;
			} catch (IOException e) {
				throw new BoggleException("Le dossier des scores n'a pas pu être créé\n" + e);
			}
		}
		return false;
	}
	
	/**
	 * Concatène le chemin d'accès aux scores relatif aux grilles de taille n passé en paramètre
	 * 
	 * @param	n
	 * 			la dimension de la grille
	 * 
	 * @return	le chemin d'accès au fichier des meilleurs scores relatif aux grilles de taille n
	 */
	private static Path getPathByDimension(int n) {
		return SCORES_FOLDER.resolve(DEFAULT_FILENAME + n + ".txt");
	}

}
