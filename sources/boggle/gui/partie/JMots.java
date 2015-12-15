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

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import boggle.mots.Grille;

/**
 * Modèle pour l'objet JList afin d'afficher les mots écrits par les joueurs à partir d'une grille
 */
public class JMots extends JScrollPane implements Observer {
	
	private static final long serialVersionUID = -2781525014383977608L;
	
	private List<String> mots;
	private TableMotsModel tableModel;
	
	public JMots(Grille grille) {
		grille.addObserver(this);
		this.mots = grille.getMots();
		this.tableModel = new TableMotsModel();
		this.setViewportView(new JTable(tableModel));
	}
	
	/**
	 * Redéfinition d'un TableModel pour le composant JMots
	 */
	private class TableMotsModel extends DefaultTableModel {
	
		private static final long serialVersionUID = -4104833440035573086L;
		
		public TableMotsModel() {
			super();
			this.setRowCount(mots.size());
			this.setColumnIdentifiers(new String[] { "Liste des mots" });
		}
		
		public Object getValueAt(int row, int col) {
			return row >= 0 && row < mots.size() ? mots.get(row) : null;
		}
		
	}
	
	// La grille a changé, on met à jour la JTable des mots
	public void update(Observable obs, Object o) {
		tableModel.setRowCount(mots.size());
		tableModel.fireTableDataChanged();
	}

}
