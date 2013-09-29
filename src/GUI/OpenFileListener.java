package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

final class OpenFileListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent arg0) {

		final JFileChooser fileChooser = new JFileChooser();

		final JFrame fileFrame = new JFrame();
		fileFrame.add(fileChooser);
		fileFrame.pack();
		fileFrame.setVisible(true);

		fileChooser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switch (e.getActionCommand()) {
				case "CancelSelection":
					fileFrame.setVisible(false);
					break;
				case "ApproveSelection":
					BufferedReader r = null;
					try {
						r = new BufferedReader(new FileReader(fileChooser
								.getSelectedFile()));
						StringBuilder builder = new StringBuilder();
						String s;
						while ((s = r.readLine()) != null) {
							builder.append(s);
						}
						Main_Window.source_code.setText(builder.toString());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				default:
					break;
				}

			}
		});

	}
}