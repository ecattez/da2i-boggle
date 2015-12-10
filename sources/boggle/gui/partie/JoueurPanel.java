package boggle.gui.partie;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import boggle.gui.decorateur.DecorateurBoutonPlat;

/**
 * 
 */
public class JoueurPanel extends JPanel {
	
	private JTextField textField;
	private JComboBox<String> comboBox;
	private String[] type = { "Humain", "IA" };
	
	public JoueurPanel() {
		super();
		final Container parent = this.getParent();
		JButton moins = new DecorateurBoutonPlat(new JButton("-"));
		JButton plus = new DecorateurBoutonPlat(new JButton("+"));
		textField = new JTextField("Nom du joueur");
		comboBox = new JComboBox<String>();
		for (String str : type) {
			comboBox.addItem(str);
		}
		
		moins.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (parent != null && parent.getComponentCount() > 1) {
					parent.remove(JoueurPanel.this);
					parent.validate();
				}
			}				
			
		});
		
		plus.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (parent != null) {
					parent.add(new JoueurPanel());
					parent.validate();
				}
			}
			
		});
		
		this.add(moins);
		this.add(plus);
		this.add(textField);
		this.add(comboBox);
	}
	
	public boolean estHumain() {
		return getType().equals("Humain");
	}
	
	public String getNomJoueur() {
		return textField.getText();
	}
	
	public String getType() {
		return (String) comboBox.getSelectedItem();
	}
	
}
