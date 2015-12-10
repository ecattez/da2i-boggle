package boggle.gui.ecran;

import java.util.Observable;

import boggle.gui.Bucket;
import boggle.gui.ecran.EcranManager.Bouton;
import boggle.gui.partie.JPartie;
import boggle.jeu.Partie;

public class EcranJeu extends AbstractEcran {
	
	private static final long serialVersionUID = -2902887394692862062L;
	
	private Partie partie;

	public EcranJeu() {}

	public void recharger() {
		cacherBoutons();
		afficherBoutons(Bouton.MENU_PRINCIPAL, Bouton.NOUVELLE_PARTIE);
		if (partie == null || partie.estTerminee()) {
			partie = Bucket.getInstance().getPartie();
			this.removeAll();
			this.add(new JPartie(partie));
			new Thread(partie).start();
		}
	}

}
