package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Brainfuck_runner.BF_Runner;

public class Main_Window {
	static final JEditorPane source_code = new JEditorPane();
	static 		 BF_Runner program;
	static JButton start_button;
	static int cur_pos=-1;
	public Main_Window() {
		// TODO Auto-generated constructor stub
		final JFrame main_frame = new JFrame("stt");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JScrollPane content = new JScrollPane();
		//content.setLayout(new FlowLayout());
		main_frame.add(content);

		source_code.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		source_code.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (arg0.getModifiers() == 2 && arg0.getKeyChar() == 10) {// 2
																			// ->ctrl_mod
																			// 10->
																			// Enter_key_code
					run_compile(source_code.getText());
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		content.add(source_code);

		start_button = new JButton("Run");
		start_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String src_code = source_code.getText();
				program.main(src_code);
				run_compile(src_code);
			}
		});
		start_button.setSize(10, 10);
		content.add(start_button);

		JButton start_debug = new JButton("Step_into");
		start_debug.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String src_code = source_code.getText();
				start_button.setEnabled(false);
				if(cur_pos==-1){
					program.main(src_code);
					cur_pos=0;
				}
				run_debug(src_code);
			}
		});
		
		start_debug.setSize(10, 10);
		content.add(start_debug);

		JButton openFile = new JButton("Open");
		openFile.addActionListener(new OpenFileListener());
		content.add(openFile);

		main_frame.pack();
		main_frame.setVisible(true);
		
		
		source_code.setText("+++[>++[!-]<-]!");

	}

	private static void run_debug(String src_code) {
		System.out.println(program.start(cur_pos, 1)+" current char is "+(cur_pos+1));
		cur_pos++;
	}

	private static void run_compile(String src_code) {
		System.out.println("RUN");
		
		System.out.println(program.start(0,0));
	}
	public static void EndOfProgramm(){
		start_button.setEnabled(true);
		cur_pos=-1;
	}
	
}
