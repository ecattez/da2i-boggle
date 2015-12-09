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
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * Définition d'un bouton plat type Material Design.
 */
public class DecorateurBoutonMenu extends DecorateurBouton {

	private static final long serialVersionUID = 8813538990842453141L;

	public DecorateurBoutonMenu(JButton bouton) {
		super(bouton);
		this.setBorder(BorderFactory.createLineBorder(new Color(206,42,13)));
		this.setPreferredSize(new Dimension(100, 30));
		this.setBackground(Color.WHITE);
		this.setForeground(Color.BLACK);
	}

}