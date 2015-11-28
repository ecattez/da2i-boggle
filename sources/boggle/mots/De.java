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
import java.util.Scanner;

import boggle.BoggleException;

/**
 * Représente un dé à NB_FACES faces.
 */
public class De {
	
	public static final int NB_FACES = 6;
	
	private String[] faces;
	private int faceVisible;
	private boolean utilise;
	
	public De(String[] faces) {
		this.faces = faces;
		this.faceVisible = 0;
		this.utilise = false;
	}
	
	/**
	 * Informe que l'on utilise le dé
	 */
	public void utiliser() {
		utilise = true;
	}
	
	/**
	 * Informe que l'on a fini d'utiliser le dé
	 */
	public void rendre() {
		utilise = false;
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
		if (i < 0 || i >= NB_FACES) {
			throw new BoggleException("Il n'y a pas de face n°" + i + " à ce dé."); 
		}
		faceVisible = i;
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
		setFaceVisible((int) (Math.random()*NB_FACES));
	}
	
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
	 *			le fichier qui contient les différentes faces de chaque dé
	 *
	 * @return	un tableau de dés
	 */
	public static De[] creerDes(String fichier) {
		List<De> des = new ArrayList<De>();
		Path path = Paths.get("config", fichier);
		String[] faces;
		
		try (Scanner sc = new Scanner(path)) {
			while(sc.hasNextLine()) {
				faces = sc.nextLine().split(";");
				if (faces.length == NB_FACES) {
					des.add(new De(faces));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
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
	
	public static void main(String[] args) {
		De[] des = De.creerDes("des-4x4.csv");
		for (De de : des) {
			de.lancer();
			System.out.println(de);
		}
	}
	
}
