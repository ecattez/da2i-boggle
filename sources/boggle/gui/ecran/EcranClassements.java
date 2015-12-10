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
import boggle.gui.classement.JClassement;
import boggle.gui.ecran.EcranManager.Bouton;
import boggle.jeu.Classement;

public class EcranClassements extends AbstractEcran {
	
	private static final long serialVersionUID = 599000474435628090L;
	
	private Classement classement;
	
	public EcranClassements() {}

	public void recharger() {
		cacherBoutons();
		afficherBoutons(Bouton.MENU_PRINCIPAL, Bouton.NOUVELLE_PARTIE);
		Classement classement = Bucket.getInstance().getClassement();
		if (classement != null && classement != this.classement) {
			this.removeAll();
			this.classement = classement;
			this.add(new JClassement(classement));
		}
	}

}
