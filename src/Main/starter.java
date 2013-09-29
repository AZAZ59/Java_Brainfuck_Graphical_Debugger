package Main;
import java.awt.AWTException;

import javax.swing.SwingUtilities;

import GUI.Main_Window;

public class starter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main_Window();
			}
		});

	}

}
