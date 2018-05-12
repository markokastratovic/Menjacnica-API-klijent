package gui;

import java.awt.EventQueue;
import util.Menjacnica;

public class GUIKontroler {
	public static GlavniProzor gp;
	public static Menjacnica m;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					m=new Menjacnica();
					GUIKontroler.gp = new GlavniProzor();
					GUIKontroler.gp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


}
