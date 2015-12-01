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
package boggle.tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import boggle.jeu.CompteARebours;

public class JCompteur extends JLabel implements Observer {
	
	public JCompteur(CompteARebours car) {
		super(car.toString());
		car.addObserver(this);
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object arg) {
		setText(((CompteARebours) o).toString());
	}
	
	public static void main(String[] args) {
		final CompteARebours c = new CompteARebours(15);
		JFrame f = new JFrame();
		JButton stop = new JButton("Stop");
		final JCompteur compteur = new JCompteur(c);
		
		stop.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				c.shutdown();
			}
			
		});
		
		f.setContentPane(new JPanel());
		f.getContentPane().add(compteur);
		f.getContentPane().add(stop);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.run();
		System.out.println("fin main");
	}

}
