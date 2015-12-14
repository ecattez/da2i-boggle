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
package boggle.gui.partie;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import boggle.jeu.Sablier;

/**
 * Représente un sablier
 */
public class JSablier extends JLabel implements Observer {
	
	private static final long serialVersionUID = 7770571884846643267L;

	public JSablier(final Sablier sablier) {
		super(sablier.toString());
		this.setForeground(Color.RED);
		sablier.addObserver(this);
	}

	public void update(Observable obs, Object o) {
		this.setText(obs.toString());
	}


}
