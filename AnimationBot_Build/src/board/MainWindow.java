package board;

import java.awt.EventQueue;

import javax.swing.JFrame;

import bot.IRCbot;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;


	public MainWindow(){
		initUI();
	}


	private void initUI() {
		add(new Window());
		setSize(800, 600);
		setResizable(false);
		setTitle("Move Sprite");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		(new IRCbot()).start();
	}


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				MainWindow ex = new MainWindow();
				ex.setVisible(true);	
			}
		});
	}

}
