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
package boggle;

import java.text.Normalizer;

/**
 * Cette classe permet de normaliser n'importe quelle chaîne de caractères au format ASCII
 */
public final class Ascii {
	
	/**
	 * Empêche l'instanciation de la classe
	 */
	private Ascii() {}
	
	/**
	 * Normalise une chaîne de caractères au format ASCII
	 * 
	 * @param	str
	 * 			la chaîne à normaliser
	 * 
	 * @return	la chaîne normalisée au format ASCII
	 */
	public static String normalize(String str) {
		return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}
	
	/**
	 * Normalise une chaîne de caractères au format ASCII et la retourne en majuscules
	 * 
	 * @param	str
	 * 			la châine à normaliser
	 * 
	 * @return	la chaîne normalisée au format ASCII et en majuscules
	 */
	public static String normalizeUpper(String str) {
		return normalize(str).toUpperCase();
	}

}
