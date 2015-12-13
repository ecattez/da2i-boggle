package boggle.gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import boggle.gui.ecran.EcranManager;

public class Lanceur {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable()  {
			public void run() {
				JFrame f = new JFrame("Boggle - Edouard CATTEZ & Alexandre VASTRA");
				f.setContentPane(EcranManager.getInstance());
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.pack();
				f.setLocationRelativeTo(null);
				f.setVisible(true);
			}
		});
	}

}
