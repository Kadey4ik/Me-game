package ua.kadey.spacewar;

import java.awt.Canvas;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Ufo extends BaseObject{
    public Ufo(double x, double y)
    {
        super(x, y, 15);
    }

    /**
     * Двигаем себя на один ход в случайном направлении.
     */
    @Override
    public void move()
    {
        double dx = Math.random() * 2-1;
        double dy = Math.random() * 2-1;
        
        if(dx<0){
        	dx+=-1;
        }else{
        	dx+=1;
        }
        
        if(dy<0){
        	dy+=-1;
        }else{
        	dy+=1;
        }

        x += dx;
        y += dy;

        checkBorders(radius, Space.game.getWidth() - radius + 1, 1, Space.game.getHeight() / 2);

        int random10 = (int) (Math.random() * 100);
        if (random10 == 0)
            fire();
    }

    /**
     * Стреляем.
     * Сбрасываем(создаем) одну бомбу прямо под собой.
     */
    public void fire()
    {
        Space.game.getBombs().add(new Bomb(x, y + 3));
    }

}
