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
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Properties;

import boggle.BoggleException;

/**
 * L'objet Regles stocke en mémoire certaines règles de jeu administrable à une partie
 * via le constructeur de {@link Partie}
 */
public class Regles {
	
	public static final int DEFAULT_TAILLEMIN = 3;
	public static final int DEFAULT_DUREESABLIER = 60;
	public static final int DEFAULT_SCORECIBLE = 50;
	public static final int DEFAULT_TOURMAX = 10;
	public static final int[] DEFAULT_POINTS = { 1, 1, 2, 3, 5, 11 };
	
	private String titre;
	private Path fichierDes;
	private Path fichierDico;
	private int tailleMin;
	private int[] points;
	private int tourMax;
	private int scoreCible;
	private int dureeSablier;
	
	// Le constructeur avec chaque paramètre
	public Regles(String titre, Path fichierDes, Path fichierDico, int tailleMin, int[] points, int tourMax, int scoreCible, int dureeSablier) {
		this.titre = titre;
		setTailleMin(tailleMin);
		setTourMax(tourMax);
		setScoreCible(scoreCible);
		setDureeSablier(dureeSablier);
		setFichierDes(fichierDes);
		setFichierDictionnaire(fichierDico);
		setPoints(this.points = points);
	}
	
	// Le constructeur de chaque paramètre simplifé avec les valeurs par défaut
	public Regles(String titre, Path fichierDes, Path fichierDico, int tailleMin) {
		this(titre, fichierDes, fichierDico, tailleMin, DEFAULT_POINTS, DEFAULT_TOURMAX, DEFAULT_SCORECIBLE, DEFAULT_DUREESABLIER);
	}
	
	// Le constructeur sans paramètres
	public Regles() {
		this("regles", Paths.get("des-4x4.csv"), Paths.get("dict-fr.txt"), DEFAULT_TAILLEMIN);
	}
	
	/**
	 * Vérifie si la chaîne passée en paramètre est un entier (positif ou négatif)
	 * 
	 * @param	str
	 * 			la chaîne à vérifier
	 * 
	 * @return	<code>true</code> si la chaîne représente un entier, <code>false</code> sinon
	 */
	private static boolean isInteger(String str) {
		return str != null && str.matches("[+-]?\\d+(\\.\\d+)?");
	}
	
	/**
	 * Change le titre personnalisé des règles
	 * 
	 * @param	titre
	 * 			le nouveau titre des règles
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	/**
	 * Retourne le chemin (Path) du fichier de dés
	 * 
	 * @return	le chemin du fichier de dés
	 */
	public Path getFichierDes() {
		return fichierDes;
	}
	
	/**
	 * Change le chemin (Path) du fichier de dés
	 * 
	 * @param	fichierDes
	 * 			le nouveau chemin du fichier de dés
	 */
	public void setFichierDes(Path fichierDes) {
		this.fichierDes = fichierDes;
	}
	
	/**
	 * Change le chemin (Path) du fichier de dés
	 * 
	 * @param	fichierDes
	 * 			le nouveau chemin du fichier de dés
	 */
	public void setFichierDes(String fichierDes) {
		setFichierDes(Paths.get(fichierDes));
	}
	
	/**
	 * Retourne le chemin (Path) du fichier dictionnaire
	 * 
	 * @return	le chemin du dictionnaire
	 */
	public Path getFichierDictionnaire() {
		return fichierDico;
	}
	
	/**
	 * Change le chemin (Path) du fichier dictionnaire
	 * 
	 * @param	fichierDico
	 * 			le nouveau chemin du fichier dictionnaire
	 */
	public void setFichierDictionnaire(Path fichierDico) {
		this.fichierDico = fichierDico;
	}
	
	/**
	 * Change le chemin (Path) du fichier dictionnaire
	 * 
	 * @param	fichierDico
	 * 			le nouveau chemin du fichier dictionnaire
	 */
	public void setFichierDictionnaire(String fichierDico) {
		setFichierDictionnaire(Paths.get(fichierDico));
	}

	/**
	 * Retourne la taille minimale des mots défini dans ces règles de jeu
	 * 
	 * @return	la taille minimale des mots
	 */
	public int getTailleMin() {
		return tailleMin;
	}

	/**
	 * Change la taille minimale des mots définie dans ces règles de jeu
	 * 
	 * @param	tailleMin
	 * 			la nouvelle taille minimale des mots
	 */
	public void setTailleMin(int tailleMin) {
		if (tailleMin < 1) {
			throw new BoggleException("La taille minimale d'un mot ne peut être < 0");
		}
		this.tailleMin = tailleMin;
	}

	/**
	 * Retourne les points attribués à chaque mot définis dans ces règles de jeu.
	 * Les points sont attribués à partir de la taille minimale jusqu'à la taille minimale + 4
	 * 
	 * @return	les points (int[]) attribués à chaque mot
	 */
	public int[] getPoints() {
		return points;
	}

	/**
	 * Change les points attribués à chaque mot définis dans ces règles de jeu
	 * 
	 * @param	points
	 * 			les nouveaux points à attribuer à chaque mot
	 */
	public void setPoints(int[] points) {
		if (points == null || points.length < 6) {
			throw new BoggleException("Il manque des points à attribuer pour la taille des mots");
		}
		this.points = points;
	}

	/**
	 * Retourne le tour maximal atteignable défini dans ces règles de jeu
	 * 
	 * @return	le tour maximal atteignable
	 */
	public int getTourMax() {
		return tourMax;
	}

	/**
	 * Change le tour maximal atteignable défini dans ces règles de jeu
	 * 
	 * @param	tourMax
	 * 			le nouveau tour maximal atteignable
	 */
	public void setTourMax(int tourMax) {
		if (tourMax < 1 && scoreCible < 1) {
			throw new BoggleException("Les règles doivent définir un tour maximal et/ou un score cible à atteindre");
		}
		this.tourMax = tourMax;
	}
	
	/**
	 * Retourne le score à atteindre pour gagner défini dans ces règles de jeu
	 * 
	 * @return	le score à atteindre pour gagner
	 */
	public int getScoreCible() {
		return scoreCible;
	}

	/**
	 * Change le score à atteindre pour gagner défini dans ces règles de jeu
	 * 
	 * @param	scoreCible
	 * 			le nouveau score à atteindre
	 */
	public void setScoreCible(int scoreCible) {
		if (tourMax < 1 && scoreCible < 1) {
			throw new BoggleException("Les règles doivent définir un tour maximal et/ou un score cible à atteindre");
		}
		this.scoreCible = scoreCible;
	}

	/**
	 * Retourne la durée totale du sablier définie dans ces règles de jeu
	 * 
	 * @return	la durée totale du sablier
	 */
	public int getDureeSablier() {
		return dureeSablier;
	}

	/**
	 * Change la durée totale du sablier définie dans ces règles de jeu
	 * 
	 * @param	dureeSablier
	 * 			la nouvelle durée totale du sablier
	 */
	public void setDureeSablier(int dureeSablier) {
		if (dureeSablier < 1) {
			throw new BoggleException("Le sablier ne peut pas avoir une durée < 1 sec");
		}
		this.dureeSablier = dureeSablier;
	}
	
	/**
	 * Sauvegarde les règles dans un fichier
	 * 
	 * @param	path
	 * 			le chemin du fichier
	 */
	public void sauvegarder(Path path) {
		Properties prop = new Properties();
		prop.setProperty("taille-min", String.valueOf(tailleMin));
		prop.setProperty("tour-max", String.valueOf(tourMax));
		prop.setProperty("score-cible", String.valueOf(scoreCible));
		prop.setProperty("duree-sablier", String.valueOf(dureeSablier));
		prop.setProperty("points", Arrays.toString(points).replace(" ", "").replace("[", "").replace("]", ""));
		prop.setProperty("des", fichierDes.getFileName().toString());
		prop.setProperty("dictionnaire", fichierDico.getFileName().toString());
		try (BufferedWriter out = Files.newBufferedWriter(path, Charset.forName("UTF-8"), StandardOpenOption.CREATE)) {
			prop.store(out, null);
		} catch (IOException e) {
			throw new BoggleException("Une erreur s'est produite à l'enregistrement du fichier " + path + "\n" + e);
		}
	}
	
	/**
	 * Sauvegarde les règles dans un fichier
	 * 
	 * @param	fichier
	 * 			le chemin du fichier
	 */
	public void sauvegarder(String fichier) {
		sauvegarder(Paths.get(fichier));
	}
	
	/**
	 * Une représentation succinte d'une instance de Regles
	 */
	public String toString() {
		return titre;
	}
	
	/**
	 * Charge des règles à partir d'un fichier
	 * 
	 * @param	fichier
	 * 			le chemin du fichier
	 * 
	 * @return	une instance de Regles si tout c'est bien passé, une <code>BoggleException</code> sinon
	 */
	public static Regles chargerRegles(Path fichier) {
		if (!Files.exists(fichier)) {
			throw new BoggleException("Le fichier " + fichier + " n'existe pas");
		}
		Properties prop = new Properties();
		try (BufferedReader in = Files.newBufferedReader(fichier, Charset.forName("UTF-8"))) {
			prop.load(in);
		} catch (Exception e) {
			throw new BoggleException("Une erreur s'est produite à l'ouverture du fichier " + fichier + "\n" + e);
		}
		
		String sTailleMin = prop.getProperty("taille-min");
		String sTourMax = prop.getProperty("tour-max");
		String sScoreCible = prop.getProperty("score-cible");
		String sDureeSablier = prop.getProperty("duree-sablier");
		String sDes = prop.getProperty("des");
		String sDico = prop.getProperty("dictionnaire");
		String sPoints = prop.getProperty("points");
		
		String titre = fichier.getFileName().toString();
		int tailleMin = (isInteger(sTailleMin) ? Integer.parseInt(sTailleMin) : DEFAULT_TAILLEMIN);
		int tourMax = (isInteger(sTourMax) ? Integer.parseInt(sTourMax) : DEFAULT_TOURMAX);
		int scoreCible = (isInteger(sScoreCible) ? Integer.parseInt(sScoreCible) : DEFAULT_SCORECIBLE);
		int dureeSablier = (isInteger(sDureeSablier) ? Integer.parseInt(sDureeSablier) : DEFAULT_DUREESABLIER);
		int[] points = Regles.DEFAULT_POINTS;
		
		if (sPoints != null && sPoints.contains(",")) {
			String[] pointsArray = sPoints.split(",");
			for (int i=0; i < pointsArray.length; i++) {
				points[i] = Integer.parseInt(pointsArray[i]);
			}
		}
		
		Path fichierDes = Paths.get(sDes);
		Path fichierDico = Paths.get(sDico);
		
		Path parent = fichier.getParent();
		if (parent != null) {
			if (fichierDes.getParent() == null) {
				fichierDes = parent.resolve(fichierDes);
			}
			if (fichierDico.getParent() == null) {
				fichierDico = parent.resolve(fichierDico);
			}
		}
		
		return new Regles(titre, fichierDes, fichierDico, tailleMin, points, tourMax, scoreCible, dureeSablier);
	}
	
	/**
	 * Charge des règles à partir d'un fichier
	 * 
	 * @param	fichier
	 * 			le chemin du fichier
	 * 
	 * @return	une instance de Regles si tout c'est bien passé, une <code>BoggleException</code> sinon
	 */
	public static Regles chargerRegles(String regles) {
		return chargerRegles(Paths.get(regles));
	}
	
}
