package ua.kadey.spacewar;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Bomb extends BaseObject{
	
	public Bomb(double x, double y)
    {
        super(x, y, 5);
    }

    /**
     * Двигаем себя вниз на один ход.
     */
    @Override
    public void move()
    {
        y++;
    }
    
    public Image getImg() {
		return img;
	}
}
