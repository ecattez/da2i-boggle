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
package boggle.gui.classement;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import boggle.jeu.Classement;

/**
 * Représentation visuelle d'une instance de Classement
 */
public class JClassement extends JScrollPane implements Observer {
	
	private static final long serialVersionUID = 8994111109824949559L;
	
	private Classement classement;
	private TableClassementModel tableModel;
	
	public JClassement(Classement classement) {
		super();
		classement.addObserver(this);
		this.classement = classement;
		this.tableModel = new TableClassementModel();
		this.setViewportView(new JTable(tableModel));
	}
	
	/**
	 * Retourne le classement associé à la vue courante
	 * 
	 * @return	l'instance de Classement associée à une vue
	 */
	public Classement getClassement() {
		return this.classement;
	}
	
	/**
	 * Modèle de JTable pour la représentation graphique des instances de Classement
	 */
	private class TableClassementModel extends DefaultTableModel {
		
		private static final long serialVersionUID = -5742429842171045372L;

		public TableClassementModel() {
			super(classement.nbLignes(), 2);
			this.setColumnIdentifiers(new String[] { "Joueur", "Score" });
		}

		public Object getValueAt(int row, int col) {
			if (col == 0) {
				return classement.getJoueur(row).getNom();
			}
			return classement.getScore(row);
		}

	}
	
	// Le classement a changé, on met à jour la JTable en triant les joueurs
	public void update(Observable obs, Object o) {
		tableModel.fireTableDataChanged();
	}

}
