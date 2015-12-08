package boggle.gui.partie;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractListModel;

import boggle.mots.Grille;

/**
 * Modèle pour l'objet JList afin d'afficher les mots écrits par les joueurs à partir d'une grille
 */
public class ListMotsModel extends AbstractListModel<String> implements Observer {
	
	private static final long serialVersionUID = -4104833440035573086L;
	
	private List<String> mots;
	
	public ListMotsModel(Grille grille) {
		grille.addObserver(this);
		this.mots = grille.getMots();
	}
	
	public String getElementAt(int i) {
		return i >= 0 && i < mots.size() ? mots.get(i) : null;
	}

	public int getSize() {
		return mots.size();
	}
	
	public void update(Observable arg0, Object arg1) {
		this.fireContentsChanged(this, 0, getSize() - 1);
	}

}
