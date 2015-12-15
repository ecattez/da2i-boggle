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

import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import boggle.gui.ecran.EcranManager.Bouton;
import boggle.gui.ecran.EcranManager.Ecran;

/**
 * Représentation d'un écran
 */
public abstract class AbstractEcran extends JPanel {
	
	private static final long serialVersionUID = 4088889425334087954L;
	
	public AbstractEcran() {
		this(new FlowLayout());
	}
	
	public AbstractEcran(LayoutManager layoutManager) {
		super(layoutManager);
	}
	
	/**
	 * Recharge l'écran à son affichage
	 */
	public abstract void recharger();
	
	/**
	 * Nettoie l'écran lorsque l'on change d'écran
	 */
	public abstract void nettoyer();
	
	/**
	 * Reçoit un objet passé en paramètre et effectue des traitements en conséquence
	 * 
	 * @param	o
	 * 			l'objet reçu
	 */
	public abstract void recevoir(Object o);
	
	/**
	 * Envoie un objet à un écran
	 * 
	 * @param	o
	 * 			l'objet à envoyer
	 * @param	e
	 * 			l'écran qui va recevoir l'objet
	 */
	public void envoyer(Object o, Ecran e) {
		e.recevoir(o);
	}
	
	/**
	 * Redéfinit la méthode de visibilité pour pouvoir recharger/nettoyer
	 * l'écran selon s'il est affiché ou non
	 */
	public void setVisible(boolean visible)	{
		super.setVisible(visible);
		if (visible) {
			recharger();
		}
		else {
			nettoyer();
		}
	}
	
	/**
	 * Cache tous les boutons du menu
	 */
	public void cacherBoutons() {
		EcranManager.getInstance().cacherBoutons();
	}
	
	/**
	 * Cache tous les boutons voulus du menu
	 * 
	 * @param	boutons
	 * 			les boutons à cacher
	 */
	public void cacherBoutons(Bouton... boutons) {
		EcranManager.getInstance().cacherBoutons(boutons);
	}
	
	/**
	 * Affiche tous les boutons du menu
	 */
	public void afficherBoutons() {
		EcranManager.getInstance().afficherBoutons();
	}
	
	/**
	 * Affiche tous les boutons voulus du menu
	 * 
	 * @param	boutons
	 * 			les boutons à afficher
	 */
	public void afficherBoutons(Bouton... boutons) {
		EcranManager.getInstance().afficherBoutons(boutons);
	}
	
	/**
	 * Passe à un autre écran
	 * 
	 * @param	ecran
	 * 			l'écran à afficher
	 */
	public void switchTo(Ecran ecran) {
		EcranManager.getInstance().show(ecran);
	}
	
	/**
	 * Passe à un autre écran en lui envoyant de plus un objet
	 * 
	 * @param	ecran
	 * 			l'écran à afficher
	 * @param	o
	 * 			l'objet à envoyer à l'écran
	 */
	public void switchTo(Ecran ecran, Object o) {
		ecran.envoyer(o, ecran);
		switchTo(ecran);
	}

}
