package boggle.tests;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import boggle.jeu.Partie;
import boggle.jeu.Regles;
import boggle.jeu.joueur.Humain;
import boggle.jeu.joueur.IAHardcore;
import boggle.jeu.joueur.Joueur;
import boggle.mots.Grille;

public class PartieTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Joueur[] joueur = { new Humain("Edouard"), new IAHardcore("Picault"), new IAHardcore("Beaufils") };
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
		final ListMotsModel model = new ListMotsModel(grille);
		
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
		
		JList<String> jlist = new JList<String>(model);
		jlist.setPreferredSize(new Dimension(100,300));
		obs.setPreferredSize(new Dimension(300,300));
		
		container.add(jlist, BorderLayout.WEST);
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
