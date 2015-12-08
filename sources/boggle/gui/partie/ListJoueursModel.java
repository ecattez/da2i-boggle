package boggle.gui.partie;

import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractListModel;

import boggle.jeu.joueur.Joueur;

/**
 * Modèle pour l'objet JList afin d'afficher les joueurs d'une partie
 */
public class ListJoueursModel extends AbstractListModel<Joueur> implements Observer {
	
	private static final long serialVersionUID = -6285952535980327348L;
	
	private Joueur[] joueurs;
	
	public ListJoueursModel(Joueur[] joueurs) {
		for (int i=0; i < joueurs.length; i++) {
			joueurs[i].addObserver(this);
		}
		this.joueurs = joueurs;
	}

	public Joueur getElementAt(int i) {
		return joueurs[i];
	}

	public int getSize() {
		return joueurs.length;
	}
	
	public void update(Observable obs, Object o) {
		this.fireContentsChanged(this, 0, getSize() - 1);
	}


}
