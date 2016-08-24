package ua.kadey.spacewar;

import java.awt.Canvas;
import java.awt.Image;

import javax.swing.ImageIcon;

public class SpaceShip extends BaseObject{
    //вектор движения (-1 влево,+1 вправо)
    private double dx = 0;

    public SpaceShip(int x, int y)
    {
        super(x, y, 15);
    }

    /**
     * Устанавливаем вектор движения влево
     */
    public void moveLeft()
    {
        dx = -2;
    }

    /**
     * Устанавливаем вектор движения вправо
     */
    public void moveRight()
    {
        dx = 2;
    }
    
    public void moveNull(){
    	dx = 0;
    }

    /**
     * Двигаем себя на один ход.
     * Проверяем столкновение с границами.
     */
    @Override
    public void move()
    {
        x = x + dx;

        checkBorders(radius, Space.game.getWidth() - radius + 1, 1, Space.game.getHeight() + 1);
    }

    /**
     * Стреляем.
     * Создаем две ракеты: слева и справа от корабля.
     */
    public void fire()
    {
        Space.game.getRockets().add(new Rocket(x, y));
        Space.game.getRockets().add(new Rocket(x + 20, y));
    }
}
