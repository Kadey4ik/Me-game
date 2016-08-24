package ua.kadey.spacewar;

import java.awt.Image;

public class BaseObject {
	
	protected Image img;
	//координаты
    protected double x;
    protected double y;
    //радиус объекта
    protected double radius;
    //состояние объект - жив ли объект
    private boolean isAlive;

    public BaseObject(double x, double y, double radius)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.isAlive = true;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public double getRadius()
    {
        return radius;
    }

    public void setRadius(double radius)
    {
        this.radius = radius;
    }

    /**
     * Двигаем себя на один ход.
     */
    public void move()
    {
        //do nothing
    }

    /**
     * Проверяем - не выходит ли (x,y) за границы.
     */
    public void checkBorders(double minx, double maxx, double miny, double maxy)
    {
        if (x < minx) x = minx;
        if (x > maxx) x = maxx;
        if (y < miny) y = miny;
        if (y > maxy) y = maxy;
    }

    public boolean isAlive()
    {
        return isAlive;
    }

    public void setAlive(boolean alive)
    {
        isAlive = alive;
    }

    public void die()
    {
        isAlive = false;
    }

	/**
     * Проверяем - пересекаются ли переданный(o) и наш(this) объекты.
     */
    public boolean isIntersec(BaseObject o)
    {
        double dx = x - o.x;
        double dy = y - o.y;
        double destination = Math.sqrt(dx * dx + dy * dy);
        double destination2 = Math.max(radius, o.radius);
        return destination <= destination2;
    }
}
