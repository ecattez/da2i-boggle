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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Deprecated
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
