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
package boggle.gui.regles;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JComboBox;

import boggle.BoggleException;
import boggle.jeu.Regles;
import boggle.jeu.Regles.Regle;

/**
 * Menu déroulant qui liste des fichiers à partir du dossier Regles.CONFIG_FOLDER.
 */
public class JReglesComboPath extends JComboBox<Path> {

	private static final long serialVersionUID = -3052670413780436311L;
	
	private String prefixe;
	
	public JReglesComboPath(final Regles regles, final Regle regle, String prefixe) {
		super();
		this.prefixe = prefixe;
		this.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				regles.setRegle(regle, (Path) e.getItem());
			}
			
		});
		recharger();
	}
	
	/**
	 * Recharge le menu déroulant
	 */
	private void recharger() {
		this.removeAllItems();
		Path root = Regles.CONFIG_FOLDER;
		Path chemin;
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(root)) {
			for (Path path : directoryStream) {
				chemin = path.getFileName();
				if (chemin.toString().startsWith(prefixe)) {
					this.addItem(chemin);
				}
			}
		} catch (IOException e) {
			throw new BoggleException("Une erreur s'est produite à la lecture du dossier " + root + "\n" + e);
		}	
	}

}
