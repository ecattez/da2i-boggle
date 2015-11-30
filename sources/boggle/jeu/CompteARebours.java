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
package boggle.jeu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Le compte à rebours donne un temps imparti à chaque joueur
 * pour trouver des mots dans une grille.
 */
public class CompteARebours extends Thread {
	
	public static final int ONE_SECOND = 1000;
	
	// Le temps en secondes du compte à rebours
	private int delay;
	// Indique si le compte à rebours est arrêté ou non
	private boolean stop;
	
	public CompteARebours(int sec) {
		this.delay = sec;
		super.start();
	}
	
	/**
	 * Démarre le compte à rebours
	 */
	public synchronized void start() {
		this.countDown();
		this.notifyAll();
		try {
			this.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Arrête le compte à rebours
	 */
	public synchronized void shutdown() {
		this.stop = true;
	}
	
	/**
	 * Vérifie si le compte à rebours est arrivé à son terme
	 * 
	 * @return <code>true</code> si le compte à rebours est à 0, <code>false</code> sinon
	 */
	public synchronized boolean isOver() {
		return stop;
	}
	
	/**
	 * Effectue un décompte d'une seconde
	 */
	private synchronized void countDown() {
		try {
			if (!stop) {
				stop = (--delay == 0);
			}
			Thread.sleep(ONE_SECOND);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Le compte à rebours continue tant qu'il n'est pas arrivé à 0
	 */
	public void run() {
		while (!isOver()) {
			countDown();
		}
	}
	
	/**
	 * Représentation textuelle du compte à rebours
	 */
	public String toString() {
		int min = (delay % 3600) / 60;
		int sec = delay % 60;
		return String.format("%02d:%02d", min, sec);
	}
	
	public static void main(String[] args) {
		CompteARebours c = new CompteARebours(5);
		JFrame f = new JFrame();
		JButton stop = new JButton("Stop");
		JLabel label = new JLabel();
		
		new Thread() {
			public void run() {
				while (true) {
					label.setText(c.toString());
				}
			}
		}.start();
		
		stop.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				c.shutdown();
			}
			
		});
		
		f.setContentPane(new JPanel());
		f.getContentPane().add(label);
		f.getContentPane().add(stop);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.start();
		System.out.println("fin main");
	}

}
