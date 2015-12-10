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
	
	public class TableMotsModel extends DefaultTableModel {
	
		private static final long serialVersionUID = -4104833440035573086L;
		
		public TableMotsModel() {
			super();
			this.setRowCount(mots.size());
			this.setColumnIdentifiers(new String[] { "Liste des mots" });
		}
		
		public Object getValueAt(int row, int col) {
			return row >= 0 && row < mots.size() ? mots.get(row) : null;
		}
	
		public int getSize() {
			return mots.size();
		}
		
	}
	
	// La grille a changé, on met à jour la JTable des mots
	public void update(Observable obs, Object o) {
		tableModel.setRowCount(mots.size());
		tableModel.fireTableDataChanged();
	}

}
