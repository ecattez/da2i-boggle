package boggle.tests;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import boggle.jeu.Humain;
import boggle.jeu.Joueur;
import boggle.jeu.Partie;
import boggle.jeu.Piko;
import boggle.jeu.Regles;
import boggle.mots.Grille;

public class PartieTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Joueur[] joueur = { new Humain("Edouard"), new Piko() };
		Regles regles = new Regles("regles-4x4.config");
		
		System.out.println(regles);
		
		final Partie partie = new Partie(regles, joueur);
		final Grille grille = partie.getGrille();
		
		final JPanel container = new JPanel(new BorderLayout());
		final JPanel bottom = new JPanel();
		final JObserver obs = new JObserver(grille);
		final JTextField text = new JTextField();
		final JButton ajouter = new JButton("ajouter");
		final JButton terminer = new JButton("terminer");
		
		text.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent e) {
				grille.rendreTout();
				grille.ecrire(text.getText().toUpperCase());
			}
			
		});
		
		ajouter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (grille.getLettresUtilisees().length() >= grille.tailleMinimale()) {
					grille.stockerMot();
					grille.rendreTout();
					text.setText("");
				}
			}
			
		});
		
		terminer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				partie.stopperSablier();
			}
			
		});
		
		container.add(obs, BorderLayout.CENTER);
		container.add(text, BorderLayout.SOUTH);
		container.add(bottom, BorderLayout.EAST);
		
		bottom.add(ajouter);
		bottom.add(terminer);
		
		JFrame f = new JFrame();
		f.setContentPane(container);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		new Thread(partie).start();
	}

}
