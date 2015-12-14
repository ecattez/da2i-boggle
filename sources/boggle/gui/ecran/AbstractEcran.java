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
	
	public abstract void recharger();
	
	public void setVisible(boolean visible)	{
		super.setVisible(visible);
		if (visible) {
			recharger();
		}
	}
	
	public void cacherBoutons() {
		EcranManager.getInstance().cacherBoutons();
	}
	
	public void cacherBoutons(Bouton... boutons) {
		EcranManager.getInstance().cacherBoutons(boutons);
	}
	
	public void afficherBoutons() {
		EcranManager.getInstance().afficherBoutons();
	}
	
	public void afficherBoutons(Bouton... boutons) {
		EcranManager.getInstance().afficherBoutons(boutons);
	}
	
	public void switchTo(Ecran ecran) {
		EcranManager.getInstance().show(ecran);
	}
	
	public void repack() {
		EcranManager.getInstance().repack();
	}

}
