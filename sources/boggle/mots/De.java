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

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import boggle.Ascii;
import boggle.BoggleException;

/**
 * Représente un dé à NB_FACES faces.
 */
public class De extends Observable {
	
	public static final int NB_FACES = 6;
	
	private String[] faces;
	private int faceVisible;
	private boolean utilise;
	
	public De(String[] faces) {
		this.faces = new String[faces.length];
		for (int i=0; i < faces.length; i++) {
			this.faces[i] = Ascii.normalizeUpper(faces[i]);
		}
		this.faceVisible = 0;
		this.utilise = false;
	}
	
	// Notifie les observeurs que le dé à changé
	private void update() {
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Informe que l'on utilise le dé
	 * 
	 * @return <code>true</code> si le dé s'utilise, <code>false</code> s'il est déjà utilisé
	 */
	public boolean utiliser() {
		if (!utilise) {
			utilise = true;
			update();
			return true;
		}
		return false;
	}
	
	/**
	 * Informe que l'on a fini d'utiliser le dé
	 * 
	 * @return <code>true</code> si le dé à été rendu, <code>false</code> sinon
	 */
	public boolean rendre() {
		if (utilise) {
			utilise = false;
			update();
			return true;
		}
		return false;
	}
	
	/**
	 * Vérifie si le dé est déjà utilisé
	 * 
	 * @return	<code>true</code> si le dé est utilisé, <code>false</code> sinon
	 */
	public boolean estUtilise() {
		return utilise;
	}
	
	/**
	 * Choisi la face visible du dé
	 * 
	 * @param	i
	 * 			l'indice entre 0 et NB_FACES exclu
	 */
	public void setFaceVisible(int i) {
		if (i < 0 || i >= faces.length) {
			throw new BoggleException("Il n'y a pas de face n°" + i + " à ce dé."); 
		}
		faceVisible = i;
		update();
	}
	
	/**
	 * Retourne la valeur de la face visible du dé
	 * 
	 * @return	la face visible du dé
	 */
	public String getFaceVisible() {
		return faces[faceVisible];
	}

	/**
	 * Simule un lancé du dé courant ce qui peut changer la face visible du dé
	 */
	public void lancer() {
		setFaceVisible((int) (Math.random() * faces.length));
	}
	
	/**
	 * Représentation textuelle d'un dé
	 */
	public String toString() {
		String str = "";
		String face;
		for (int i=0; i < faces.length; i++) {
			face = faces[i];
			if (i == faceVisible) {
				str += "(" + face + ")";
			}
			else {
				str += face;
			}
			if (i < faces.length - 1) {
				str += ", ";
			}
		}
		return "Faces: " + str;
	}
	
	/**
	 * Crée des dés à partir d'un fichier de configuration
	 *  
	 * @param	fichier
	 *			le chemin du fichier qui contient les différentes faces de chaque dé
	 *
	 * @return	un tableau de dés
	 */
	public static De[] creerDes(String fichier) {
		return creerDes(Paths.get(fichier));
	}
	
	/**
	 * Crée des dés à partir d'un fichier de configuration
	 *  
	 * @param	fichier
	 *			le chemin du fichier qui contient les différentes faces de chaque dé
	 *
	 * @return	un tableau de dés
	 */
	public static De[] creerDes(Path fichier) {
		List<De> des = new ArrayList<De>();
		String[] faces;
		
		try (Scanner sc = new Scanner(fichier)) {
			while(sc.hasNextLine()) {
				faces = sc.nextLine().split(";");
				if (faces.length == NB_FACES) {
					des.add(new De(faces));
				}
			}
		} catch (IOException e) {
			throw new BoggleException("Impossible de créer de dés avec le fichier " + fichier + "\n" + e);
		}
		return des.toArray(new De[des.size()]);		
	}
	
	/**
	 * Mélange un tableau de dés
	 * 
	 * @param	des
	 * 			les dés à mélanger
	 * 
	 * @return	le tableau de dés mélangé
	 */
	public static De[] melange(De[] des) {
		Collections.shuffle(Arrays.asList(des));
		return des;
	}
	
}
