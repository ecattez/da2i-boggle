package boggle.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class NouvellePartie extends JPanel {

	private JPanel ouest;
	
	public NouvellePartie(){
		super(new BorderLayout());
		this.setBackground(Color.GRAY);
		this.setPreferredSize(new Dimension(500,500));
		this.ouest= new JPanel();
		ouest.setLayout(new BoxLayout(ouest, BoxLayout.PAGE_AXIS));
		ouest.add(new JoueurPanel());
		
		this.add(ouest,BorderLayout.WEST);

	}

	public class JoueurPanel extends JPanel {

		public JoueurPanel(){
		
	
		JPanel choixListe = new JPanel();
		JButton plus = new JButton("+");
		JButton moins = new JButton("-");
		choixListe.add(moins);
		choixListe.add(plus);
		choixListe.add(new JTextField("Joueur 1"));
		String[] selection = {"humain" , "AI easy" , "AI hard"};
		JComboBox typeJoueur = new JComboBox(selection);
		choixListe.add(typeJoueur);

		this.add(choixListe); 
		moins.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(ouest.getComponentCount()>1) {
				ouest.remove(JoueurPanel.this);
				ouest.validate();
				}
			}
		});
		
		plus.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				ouest.add(new JoueurPanel());
				ouest.validate();
				
			}
		});
		}
	}
}
