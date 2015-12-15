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
package boggle.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import boggle.gui.ecran.EcranManager;

/**
 * Classe contenant la méthode main qui exécute tout le programme
 */
public class Lanceur {
	
	public static JFrame f;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable()  {
			public void run() {
				final JFrame f = EcranManager.getInstance();
				f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				f.pack();
				f.setLocationRelativeTo(null);
				f.setVisible(true);
				f.addWindowListener(new WindowAdapter() {
					
					public void windowClosing(WindowEvent e) {
						int option = JOptionPane.showConfirmDialog(f.getContentPane(),
								"Etes-vous sur de vouloir quitter le jeu du Boggle ?",
								"Quitter",
								JOptionPane.YES_OPTION);
						if (option == JOptionPane.YES_OPTION) {
							System.exit(0);
						}
					}
					
				});
			}
		});
	}

}
