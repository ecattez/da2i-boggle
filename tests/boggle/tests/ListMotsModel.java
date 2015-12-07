package boggle.tests;

import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractListModel;

import boggle.mots.Grille;

public class ListMotsModel extends AbstractListModel<String> implements Observer {
	
	private Grille grille;
	
	public ListMotsModel(Grille grille) {
		this.grille = grille;
		grille.addObserver(this);
	}
	
	public String getElementAt(int i) {
		return grille.getMots().get(i);
	}

	public int getSize() {
		return grille.getMots().size();
	}
	
	public void update(Observable arg0, Object arg1) {
		this.fireContentsChanged(this, 0, getSize() - 1);
	}

}
