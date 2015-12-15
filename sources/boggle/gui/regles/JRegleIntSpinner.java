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

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import boggle.jeu.Regles;
import boggle.jeu.Regles.Regle;

/**
 * Représentation d'un JSpinner numérique pour les règles d'une nouvelle partie.
 * 
 * Les instances de cette classe sont associées à des règles d'une instance de Regles, dont la valeur est un entier.
 */
public class JRegleIntSpinner extends JSpinner {

	private static final long serialVersionUID = -1321115021038002758L;
	
	public JRegleIntSpinner(final Regles regles, final Regle regle, int min, int max) {
		super(new SpinnerNumberModel(regles.getInt(regle), min, max, 1));
		this.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				regles.setRegle(regle, (int) JRegleIntSpinner.this.getValue());
			}
			
		});
	}

}
