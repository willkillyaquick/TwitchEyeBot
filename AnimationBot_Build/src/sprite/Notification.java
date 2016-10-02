package sprite;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Notification {
	private int dx;
	private int dy;
	private int x;
	private int y;
	private Image image;
	private ImageIcon ii;
	Random ran = new Random();
	private int[] boundries;

	public Notification(String loc, int x, int y) {
		initCraft(loc, x, y);
	}

	private void initCraft(String loc, int x, int y) {
		ii = new ImageIcon(loc);
		image = ii.getImage();
		this.x = x;
		this.y = y;
	}
	public void move(){
		dx = ran.nextInt(2) - ran.nextInt(2);
		dy = ran.nextInt(2) - ran.nextInt(2);
		System.out.println(dx);
		x += dx;
		y += dy;
	}
	public void moveTo(){
		//System.out.println("x=" + x + " y=" + y + " dx=" + dx + " dy" + dy);
		if (x > dx){
			x -= 1;
		}
		else if (x < dx){
			x += 1;
		}
		if (y > dy){
			y -= 1;
		}
		else if (y < dy){
			y += 1;
		}
		if (x == dx && y == dy){
			setRandPos();
		}
		
	}
	public void setRandPos(){
		dx = ran.nextInt(800);
		dy = ran.nextInt(600);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;	
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public Image getImage(){
		return image;
	}
	
	public void setImage(String image) {
		ii = new ImageIcon(image);
		int[] arr = {getImageXMin(),ii.getIconWidth(), ii.getIconHeight()};
		setBoundries(arr);
		this.image = ii.getImage();
	}
	
	public void getTwitchImage(String key){
		try {
			URL url = new URL("https://static-cdn.jtvnw.net/emoticons/v1/" + key + "/3.0");
			BufferedImage img = ImageIO.read(url);
			ImageIcon imgR = new ImageIcon(img);
			this.image = imgR.getImage();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int[] getBoundries() {
		return boundries;
	}

	public void setBoundries(int[] boundries) {
		this.boundries = boundries;
	}
	public int getImageXMin(){
		return ii.getIconWidth()/2 - x;
	}
	public int getImageYMins(){
		return ii.getIconHeight()/2 - y;
	}
}
