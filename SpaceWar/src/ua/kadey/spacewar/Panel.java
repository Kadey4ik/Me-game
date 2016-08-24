package ua.kadey.spacewar;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;


public class Panel extends JPanel{

	@Override
	public void paint(Graphics g) {
		g = (Graphics2D) g;
		Image img = null;
		
		for(BaseObject o : Space.game.getAllItems()){
			int x = 10;
			if(o instanceof SpaceShip){
				x = 30;
				img = Space.game.img_spaceship;
			}
			if(o instanceof Ufo){
				x = 30;
				img = Space.game.img_badship;
			}
			if(o instanceof Rocket){
				x = 10;
				img = Space.game.img_rocket;
			}
			if(o instanceof Bomb){
				x = 10;
				img = Space.game.img_bomb;
			}
			g.drawImage(img, (int)o.getX(), (int)o.getY(), x, x, null);
		}
	}
	
	public Panel(){
		addKeyListener(new myKeyAdapter());
		setFocusable(true);
	}
	
	private class myKeyAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			int key = e.getKeyCode();
			if(key == 37){
				Space.game.getShip().moveLeft();
			}
			if(key == 39){
				Space.game.getShip().moveRight();
			}
			if(key == KeyEvent.VK_SPACE){
				if(Space.game.getRockets().size()<50)
					Space.game.getShip().fire();
			}
		}
		public void keyReleased(KeyEvent e){
			int key = e.getKeyCode();
			if(key == 37||key == 39)
				Space.game.getShip().moveNull();
		}
	}
	
}
