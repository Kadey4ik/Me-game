package ua.kadey.spacewar;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Rocket extends BaseObject{

	public Rocket(double x, double y)
    {
        super(x, y, 5);
    }

    /**
     * ������� ���� ����� �� ���� ���.
     */
    @Override
    public void move()
    {
        y-=2;
    }

}
