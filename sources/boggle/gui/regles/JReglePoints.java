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

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import boggle.jeu.Regles;
import boggle.jeu.Regles.Regle;

/**
 * Composant permettant de saisir les points associés à la taille de chaque mot.
 */
public class JReglePoints extends JPanel {
	
	private static final long serialVersionUID = 4693612936983879311L;
	
	private int[] points;
	private List<JReglePointsSpinner> spinners;
	
	public JReglePoints(Regles regles) {
		super(new FlowLayout(FlowLayout.LEADING));
		this.points = new int[Regles.DEFAULT_NOMBRE_POINTS];
		this.spinners = new ArrayList<JReglePointsSpinner>();
		
		JReglePointsSpinner sp;
		for (int i=0; i < points.length; i++) {
			sp = new JReglePointsSpinner(regles, i);
			spinners.add(sp);
			this.add(sp);
		}
	}
	
	public void setValues(int[] values) {
		for (int i=0; i < points.length; i++) {
			spinners.get(i).setValue(values[i]);
		}
	}
	
	/**
	 * Chaque point est définit via un spinner personnalisé
	 */
	class JReglePointsSpinner extends JSpinner {

		private static final long serialVersionUID = 7237564411378262819L;
		
		public JReglePointsSpinner(final Regles regles, final int idxPoint) {
			super(new SpinnerNumberModel(1, 1, 20, 1));
			this.addChangeListener(new ChangeListener() {

				public void stateChanged(ChangeEvent e) {
					points[idxPoint] = (int) JReglePointsSpinner.this.getValue();
					regles.setRegle(Regle.POINTS, points);
				}
				
			});
		}
		
	}

}
