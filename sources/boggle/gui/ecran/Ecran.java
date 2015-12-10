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

import java.awt.LayoutManager;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import boggle.gui.Bucket;
import boggle.gui.ConteneurPrincipal;
import boggle.jeu.Partie;

/**
 * Représentation d'un écran
 */
public abstract class Ecran extends JPanel implements Observer {
	
	private static final long serialVersionUID = 4088889425334087954L;
	
	protected ConteneurPrincipal mainPanel;
	
	public Ecran(ConteneurPrincipal mainPanel) {
		super();
		this.mainPanel = mainPanel;
		Bucket.getInstance().addObserver(this);
	}
	
	public Ecran(ConteneurPrincipal mainPanel, LayoutManager layout) {
		super(layout);
		this.mainPanel = mainPanel;
		this.recharger();
	}
	
	public abstract void recharger();

	public void setVisible(boolean visible)	{
		super.setVisible(visible);
		if (visible) {
			recharger();
		}
	}
	
	public void update(Observable obs, Object o) {
		Bucket bucket = ((Bucket) obs);
		Partie partie = bucket.getPartie();
		
		// Si l'écran est visible et qu'une partie est en cours, on la termine
		if (isVisible() && partie != null && !partie.estTerminee()) {
			partie.forcerArret();
		}
	}

}
