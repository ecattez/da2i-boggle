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
package boggle.gui.decorateur;

import java.awt.Color;

import javax.swing.JButton;

/**
 * Définition d'un bouton plat type Material Design.
 * 
 * Ce bouton ne retient pas le focus lorsque l'on clique dessus.
 * Nous privilégions ce bouton pour "Ajouter", "Vider", "Terminer" dans la vue de partie,
 * afin de pouvoir continuer à écrire des mots sans devoir cliquer sur la zone de texte.
 */
public class BoutonPlatSansFocus extends DecorateurBouton {

	private static final long serialVersionUID = 8813538990842453141L;

	public BoutonPlatSansFocus(JButton bouton) {
		super(bouton);
		this.setBackground(Color.WHITE);
		this.setForeground(Color.BLACK);
		this.setFocusable(false);
	}

}
