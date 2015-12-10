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
package boggle.gui.ecran;

import boggle.gui.Bucket;
import boggle.gui.NouvellePartie;
import boggle.gui.ecran.EcranManager.Bouton;
import boggle.jeu.Partie;
import boggle.jeu.Regles;
import boggle.jeu.joueur.IAHardcore;
import boggle.jeu.joueur.Joueur;

public class EcranNouvellePartie extends AbstractEcran {

	private static final long serialVersionUID = 4790691387769043379L;

	public EcranNouvellePartie() {
		this.add(new NouvellePartie());
	}
	
	public void recharger() {
		cacherBoutons();
		afficherBoutons(Bouton.MENU_PRINCIPAL, Bouton.DEMARRER_PARTIE);
		Partie partie = Bucket.getInstance().getPartie();
		if (partie != null && !partie.estTerminee()) {
			partie.forcerArret();
		}
		if (partie == null || partie.estTerminee()) {
			Joueur[] joueurs = { new IAHardcore("Picault"), new IAHardcore("Beaufils") };
			Regles regles = new Regles("regles-4x4.config");
			partie = new Partie(regles, joueurs);
			
			Bucket.getInstance().push(joueurs);
			Bucket.getInstance().push(regles);
			Bucket.getInstance().push(partie);
			
			System.out.println(joueurs);
			System.out.println(regles);
		}
	}

}
