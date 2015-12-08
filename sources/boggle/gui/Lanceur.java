package boggle.gui;

import javax.swing.JFrame;

public class Lanceur {
	
	public static void main(String[] args) {
		JFrame f = new JFrame("pipou");
		f.setContentPane(ConteneurPrincipal.getInstance());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

}
