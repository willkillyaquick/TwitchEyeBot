package board;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.json.JSONException;
import org.json.JSONObject;

import bot.IRCbot;
import bot.VarKeeper;
import sprite.Notification;
import urlUtil.TwitchImageJSON;

public class Window extends JPanel implements ActionListener {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private Notification notifications;
	private final int DELAY = 10;
	private int painterTimer = 1000;
	private int painterTimerLim = 1000;

	public Window() {
		initBoard();
	}

	private void initBoard() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.GREEN);

		notifications = new Notification("", 40, 60);

		timer = new Timer(DELAY, this);
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
		Toolkit.getDefaultToolkit().sync();
	}

	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(notifications.getImage(), notifications.getX(), notifications.getY(), this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (VarKeeper.isWaitingProcess()) {
			// Local Emotes
			if (painterTimer >= painterTimerLim) {
				if (VarKeeper.getId() != -1) {
					try {
						notifications.setImage(VarKeeper.getfJSON().getJsonFile().getJSONArray("emotions")
								.getJSONObject(VarKeeper.getId()).getString("location"));
						painterTimer = VarKeeper.getfJSON().getJsonFile().getJSONArray("emotions")
								.getJSONObject(VarKeeper.getId()).getInt("timer");
						VarKeeper.setId(-1);
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				// Twitch Emotes
				else if (VarKeeper.getProcessRoot() != null) {
					notifications.getTwitchImage(VarKeeper.getProcessKey());
					painterTimerLim = 700;
					VarKeeper.setProcessRoot(null);
				}
				VarKeeper.setWaitingProcess(false);
				painterTimer = 0;
				System.out.println("rested");
				notifications.setRandPos();
			}
		}
		if (painterTimer <= painterTimerLim) {
			painterTimer += 1;
			notifications.moveTo();
		} else {
			notifications.setImage("");
			notifications.setX(40);
			notifications.setY(60);
		}
		repaint();
	}

	private class TAdapter extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("KeyReleased");
		}

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println(VarKeeper.isWaitingProcess());
			VarKeeper.setWaitingProcess(false);
			System.out.println(VarKeeper.isWaitingProcess());

		}
	}
}
