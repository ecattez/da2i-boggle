package boggle.gui.partie;

import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractListModel;

import boggle.jeu.Partie;
import boggle.jeu.joueur.Joueur;

/**
 * Modèle pour l'objet JList afin d'afficher les joueurs d'une partie
 */
public class ListJoueursModel extends AbstractListModel<Joueur> implements Observer {
	
	private Joueur[] joueurs;
	
	public ListJoueursModel(Partie partie) {
		this.joueurs = partie.getJoueur();
		partie.addObserver(this);
	}

	public Joueur getElementAt(int i) {
		return joueurs[i];
	}

	@Override
	public int getSize() {
		return joueurs.length;
	}

	@Override
	public void update(Observable obs, Object o) {
		this.fireContentsChanged(this, 0, getSize() - 1);
	}


}
