package boggle.jeu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Scores {
	
	private static Path scores = Paths.get("scores");
	
	/**
	 * Empêche l'instanciation de la classe
	 */
	private Scores() {}
	
	public static boolean ajouterScore(int n, Joueur joueur) {
		Path path = scores.resolve("grille-" + n + ".txt");
		if (!Files.isDirectory(scores)) {
			try {
				Files.createDirectory(scores);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!Files.exists(path)) {
			try {
				Files.createFile(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		Scores.ajouterScore(3, new Humain("Edouard"));
	}

}
