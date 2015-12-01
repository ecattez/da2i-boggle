package boggle.gui;

import javax.swing.JFrame;

public class Lanceur {
	
	public static void main(String[] args) {
		JFrame f = new JFrame("pipou");
		f.setContentPane(new ConteneurPrincipal());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1000, 1000);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

}
