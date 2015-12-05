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

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import boggle.BoggleException;
import boggle.mots.ArbreLexical;
import boggle.mots.De;

/**
 * L'objet Regles stocke en mémoire certaines règles de jeu administrable à une partie
 * via le constructeur de {@link Partie}
 */
public class Regles {
	
	public static final int DEFAULT_TAILLEMIN = 3;
	public static final int DEFAULT_DUREESABLIER = 60;
	public static final int DEFAULT_SCORECIBLE = 30;
	public static final int DEFAULT_TOURMAX = 5;
	public static final int[] DEFAULT_POINTS = { 1, 1, 2, 3, 5, 11 };
	
	private De[] des;
	private ArbreLexical dictionnaire;
	private int tailleMin;
	private int[] points;
	private int tourMax;
	private int scoreCible;
	private int dureeSablier;
	
	// Le constructeur avec l'utilisation d'un fichier
	public Regles(String fichierRegles) {
		Path regles = Paths.get("config", fichierRegles);
		if (!Files.isRegularFile(regles)) {
			throw new BoggleException("Le fichier " + fichierRegles + " n'est pas un fichier de configuration correct");
		}
		Properties prop = new Properties();
		try {
			prop.load(Files.newBufferedReader(regles));
			this.tailleMin = Integer.parseInt(prop.getProperty("taille-min", String.valueOf(DEFAULT_TAILLEMIN)));
			this.des = De.creerDes(prop.getProperty("des"));
			this.dictionnaire = ArbreLexical.lireMots(prop.getProperty("dictionnaire"));
			this.tourMax = Integer.parseInt(prop.getProperty("tour-max", String.valueOf(DEFAULT_TOURMAX)));
			this.scoreCible = Integer.parseInt(prop.getProperty("score-cible", String.valueOf(DEFAULT_SCORECIBLE)));
			this.dureeSablier = Integer.parseInt(prop.getProperty("duree-sablier", String.valueOf(DEFAULT_DUREESABLIER)));
			
			String[] pointsArray = prop.getProperty("points", String.valueOf(DEFAULT_POINTS)).split(",");
			if (pointsArray.length < 6) {
				throw new BoggleException("Il manque des points à attribuer pour la taille des mots");
			}
			this.points = new int[pointsArray.length];
			for (int i=0; i < pointsArray.length; i++) {
				points[i] = Integer.parseInt(pointsArray[i]);
			}
		} catch (Exception e) {
			throw new BoggleException("Impossible de charger une configuration à partir du fichier " + fichierRegles + "\n" + e);
		}
	}
	
	// Le constructeur avec chaque paramètre
	public Regles(De[] des, ArbreLexical dictionnaire, int tailleMin, int[] points, int tourMax, int scoreCible, int tempsSablier) {
		this.des = des;
		this.dictionnaire = dictionnaire;
		this.tailleMin = tailleMin;
		this.points = points;
		this.tourMax = tourMax;
		this.scoreCible = scoreCible;
		this.dureeSablier = tempsSablier;
	}
	
	// Le constructeur de chaque paramètre simplifé avec les valeurs par défaut
	public Regles(De[] des, ArbreLexical dictionnaire, int tailleMin) {
		this(des, dictionnaire, tailleMin, DEFAULT_POINTS, DEFAULT_TOURMAX, DEFAULT_SCORECIBLE, DEFAULT_DUREESABLIER);
	}

	/**
	 * Retourne les dés définis dans ces règles de jeu
	 * 
	 * @return	les dés (De[]) utilisés
	 */
	public De[] getDes() {
		return des;
	}

	/**
	 * Change les dés définis dans ces règles de jeu
	 * 
	 * @param	des
	 * 			les nouveaux dés utilisés
	 */
	public void setDes(De[] des) {
		this.des = des;
	}

	/**
	 * Retourne le dictionnaire défini dans ces règles de jeu
	 * 
	 * @return	le dictionnaire (ArbreLexical) utilisé
	 */
	public ArbreLexical getDictionnaire() {
		return dictionnaire;
	}

	/**
	 * Change le dictionnaire défini dans ces règles de jeu
	 * 
	 * @param	dictionnaire
	 * 			le nouveau dictionnaire utilisé
	 */
	public void setDictionnaire(ArbreLexical dictionnaire) {
		this.dictionnaire = dictionnaire;
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
		this.dureeSablier = dureeSablier;
	}
	
	/**
	 * Une représentation succinte d'une instance de Regles
	 */
	public String toString() {
		return String.format("des: %s\ndictionnaire: %s\ntaille-min: %d\npoints: %s\ntour-max: %d\nscore-cible: %d\nduree-sablier: %d",
				des, dictionnaire, tailleMin, points, tourMax, scoreCible, dureeSablier);
	}
	
}
