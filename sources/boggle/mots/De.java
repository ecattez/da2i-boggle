package boggle.mots;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
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
		
		try (Scanner sc = new Scanner(path)) {
			while(sc.hasNextLine()) {
				des.add(new De(sc.nextLine().split(";")));
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
	
	public static final int NB_FACES = 6;
	
	private String[] faces;
	private String faceVisible;
	private boolean utilise;
	
	public De(String[] faces) {
		this.faces = faces;
		this.faceVisible = faces[0];
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
		faceVisible = faces[i];
	}
	
	/**
	 * Retourne la valeur de la face visible du dé
	 * 
	 * @return	la face visible du dé
	 */
	public String getFaceVisible() {
		return faceVisible;
	}

	/**
	 * Simule un lancé du dé courant ce qui peut changer la face visible du dé
	 */
	public void lancer() {
		setFaceVisible((int) (Math.random()*NB_FACES));
	}
	
	public String toString() {
		return "Face visible: " + faceVisible + "\tFaces: " + Arrays.asList(faces);
	}
	
	public static void main(String[] args) {
		De[] des = De.creerDes("des-4x4.csv");
		for (De de : des) {
			de.lancer();
			System.out.println(de);
		}
	}
	
}
