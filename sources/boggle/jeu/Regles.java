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
import java.util.Properties;

import boggle.BoggleException;

/**
 * Les Regles définissent la manière de jouer au Boggle.
 * 
 * Une instance de Partie est instanciée via une instance de Regles.
 */
public class Regles implements Cloneable {
	
	public static final Path CONFIG_FOLDER = Paths.get("config");
	public static final int DEFAULT_TAILLE_MIN = 3;
	public static final int DEFAULT_DUREE_SABLIER_MIN = 30;
	public static final int DEFAULT_NOMBRE_POINTS = 6;
	
	/**
	 * Cette énumération représente les différentes règles applicable à une partie de Boggle
	 */
	public enum Regle {
		
		TAILLE_MIN("taille-min") {
			public boolean verifier(String value) {
				return isInteger(value) && Integer.parseInt(value) >= 3;
			}

			public String getMessage() {
				return "La taille minimale d'un mot doit être de " + getDefaultValue() + " minimum";
			}
			
			public String getDefaultValue() {
				return "3";
			}
		},
		TOUR_MAX("tour-max") {
			public boolean verifier(String value) {
				return isInteger(value);
			}

			public String getMessage() {
				return "Le tour maximal doit être un entier";
			}
			
			public String getDefaultValue() {
				return "10";
			}
		},
		SCORE_CIBLE("score-cible") {
			public boolean verifier(String value) {
				return isInteger(value);
			}

			public String getMessage() {
				return "Le score à atteindre doit être un entier";
			}
			
			public String getDefaultValue() {
				return "100";
			}
		},
		DUREE_SABLIER("duree-cible") {
			public boolean verifier(String value) {
				return isInteger(value) && Integer.parseInt(value) >= DEFAULT_DUREE_SABLIER_MIN;
			}

			public String getMessage() {
				return "Le sablier ne peut pas avoir une durée < " + DEFAULT_DUREE_SABLIER_MIN + " sec";
			}
			
			public String getDefaultValue() {
				return "120";
			}
		},
		POINTS("points") {
			public boolean verifier(String value) {
				return isIntArray(value) && value.split(",").length == DEFAULT_NOMBRE_POINTS;
			}

			public String getMessage() {
				return "Les points doivent être un ensemble de " + DEFAULT_NOMBRE_POINTS + " entiers";
			}
			
			public String getDefaultValue() {
				return "1,1,2,3,5,11";
			}
		},
		FICHIER_DES("des") {
			public boolean verifier(String value) {
				return !isEmpty(value);
			}

			public String getMessage() {
				return "Le fichier de des ne peut pas être null";
			}
			
			public String getDefaultValue() {
				return Paths.get("config", "des-4x4.csv").toString();
			}
		},
		FICHIER_DICO("dictionnaire") {
			public boolean verifier(String value) {
				return !isEmpty(value);
			}

			public String getMessage() {
				return "Le fichier dictionnaire ne peut pas être null";
			}
			
			public String getDefaultValue() {
				return Paths.get("config", "dict-fr.txt").toString();
			}
		};
		
		private final String key;
		
		private Regle(String key) {
			this.key = key;
		}
		
		/**
		 * Vérifie si la valeur est compatible avec la règle
		 * 
		 * @param	value
		 * 			la valeur à vérifier
		 * 
		 * @return	<code>true</code> si la valeur est compatible, <code>false</code> sinon
		 */
		public abstract boolean verifier(String value);
		
		/**
		 * Le message d'indication des valeurs que peuvent prendre la règle
		 * 
		 * @return	le message d'indication
		 */
		public abstract String getMessage();
		
		/**
		 * Retourne la valeur par défaut de la règle
		 * 
		 * @return	la valeur par défaut de la règle
		 */
		public abstract String getDefaultValue();
		
		/**
		 * Retourne le nom de la règle
		 * 
		 * @return	le nom de la règle
		 */
		public String key() {
			return key;
		}
		
		/* Retire les espaces dans une chaîne de caractères */
		private static String noBlank(String str) {
			return str.replaceAll(" ", "");
		}
		
		/* Vérifie si une chaîne est un entier */
		private static boolean isInteger(String str) {
			return str != null && noBlank(str).matches("[+-]?\\d+(\\.\\d+)?");
		}
		
		/* Vérifie si une chaîne est un tableau d'entier */
		private static boolean isIntArray(String str) {
			return str != null && noBlank(str).matches("[0-9]+(,[0-9]+)*");
		}
		
		/* Vérifie si une chaîne est vide */
		private static boolean isEmpty(String str) {
			return str != null && noBlank(str).length() == 0;
		}
		
	}
	
	private String titre;
	private Properties prop;
	
	public Regles() {
		this("Nouvelle partie", new Properties());
	}
	
	public Regles(String title, Properties prop) {
		this.titre = title;
		this.prop = new Properties();
		String value;
		for (Regle regle : Regle.values()) {
			value = prop.getProperty(regle.key(), regle.getDefaultValue());
			this.setRegle(regle, value);
		}
	}
	
	/**
	 * Retourne le titre des règles
	 * 
	 * @return	le titre des règles
	 */
	public String getTitre() {
		return titre;
	}
	
	/**
	 * Change le titre des règles
	 * 
	 * @param	titre
	 * 			le nouveau titre des règles
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	/**
	 * Change la valeur d'une règle par une chaîne de caractères
	 * 
	 * @param	regle
	 * 			la règle à changer
	 * @param	value
	 * 			la nouvelle valeur de la règle
	 */
	public void setRegle(Regle regle, String value) {
		if (regle.verifier(value)) {
			prop.setProperty(regle.key(), value);
		}
		else {
			throw new BoggleException(regle.getMessage());
		}
	}
	
	/**
	 * Change la valeur d'une règle par un chemin de fichier
	 * 
	 * @param	regle
	 * 			la règle à changer
	 * @param	value
	 * 			la nouvelle valeur de la règle
	 */
	public void setRegle(Regle regle, Path value) {
		setRegle(regle, value.toString());
	}
	
	/**
	 * Change la valeur d'une règle par un entier
	 * 
	 * @param	regle
	 * 			la règle à changer
	 * @param	value
	 * 			la nouvelle valeur de la règle
	 */
	public void setRegle(Regle regle, int value) {
		setRegle(regle, String.valueOf(value));
	}
	
	/**
	 * Change la valeur d'une règle par un tableau d'entier
	 * 
	 * @param	regle
	 * 			la règle à changer
	 * @param	values
	 * 			les nouvelles valeurs de la règle
	 */
	public void setRegle(Regle regle, int[] values) {
		String value = "";
		for (int i=0; i < values.length; i++) {
			value += values[i];
			if (i < values.length - 1) {
				value += ",";
			}
		}
		setRegle(regle, value);
	}
	
	/**
	 * Récupère la valeur textuelle d'une règle
	 * 
	 * @param	regle
	 * 			la règle pour laquelle il faut récupérer la valeur
	 * 
	 * @return	une chaîne de caractères représentant les valeurs de la règle
	 */
	public String getString(Regle regle) {
		String value = prop.getProperty(regle.key()).replaceAll(" ", "");
		if (regle.verifier(value)) {
			return value;
		}
		throw new BoggleException(regle.getMessage());
	}
	
	/**
	 * Récupère la valeur entière d'une règle
	 * 
	 * @param	regle
	 * 			la règle pour laquelle il faut récupérer la valeur
	 * 
	 * @return	un entier représentant la valeur de la règle
	 */
	public int getInt(Regle regle) {
		String value = getString(regle);
		if (regle.verifier(value)) {
			return Integer.parseInt(value);
		}
		throw new BoggleException(regle.getMessage());
	}
	
	/**
	 * Récupère les valeurs d'une règle sous la forme d'un tableau d'entier
	 * 
	 * @param	regle
	 * 			la règle pour laquelle il faut récupérer les valeurs
	 * 
	 * @return	un tableau d'entier représentant les valeurs de la règle
	 */
	public int[] getIntArray(Regle regle) {
		String value = getString(regle);
		if (regle.verifier(value)) {
			String[] array = value.split(",");
			int[] values = new int[array.length];
			for (int i=0; i < values.length; i++) {
				values[i] = Integer.parseInt(array[i]);
			}
			return values;
		}
		throw new BoggleException(regle.getMessage());
	}
	
	/**
	 * Sauvegarde les règles au format "titre.config"
	 */
	public void sauvegarder() {
		Path path = Paths.get(titre);
		if (path.getParent() == null) {
			path = CONFIG_FOLDER.resolve(path);
		}
		try (BufferedWriter out = Files.newBufferedWriter(path, Charset.forName("UTF-8"))) {
			prop.store(out, null);
		} catch (IOException e) {
			throw new BoggleException("Une erreur s'est produite à l'écriture du fichier " + path + "\n" + e);
		}
	}

	/**
	 * Retourne le titre des règles
	 */
	public String toString() {
		return titre;
	}
	
	/**
	 * Représentation textuelle des règles
	 * 
	 * @return	la description des règles d'une instance de Regles
	 */
	public String describe() {
		return prop.toString();
	}
	
	/**
	 * Crée une instance de Regles à partir de l'instance courante
	 */
	public Regles clone() {
		Regles regles = new Regles();
		regles.setTitre(titre);
		for (Regle regle : Regle.values()) {
			regles.setRegle(regle, this.getString(regle));
		}
		return regles;
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
		Properties prop = new Properties();
		try (BufferedReader in = Files.newBufferedReader(fichier, Charset.forName("UTF-8"))) {
			prop.load(in);
		} catch (IOException e) {
			throw new BoggleException("Une erreur s'est produite à l'ouverture du fichier " + fichier + "\n" + e);
		}
		return new Regles(fichier.getFileName().toString(), prop);
	}
	
	/**
	 * Charge des règles à partir d'un fichier
	 * 
	 * @param	fichier
	 * 			le chemin du fichier
	 * 
	 * @return	une instance de Regles si tout c'est bien passé, une <code>BoggleException</code> sinon
	 */
	public static Regles chargerRegles(String fichier) {
		return chargerRegles(Paths.get(fichier));
	}
	
}
