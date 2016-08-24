package ua.kadey.spacewar;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Space {
	//Ширина и высота игрового поля
    private int width;
    private int height;
    
    ///*
    public final Image img_bomb = new ImageIcon(getClass().getClassLoader()
			.getResource("res/bomb.png")).getImage();
    public final Image img_rocket = new ImageIcon(getClass().getClassLoader()
			.getResource("res/rocket.png")).getImage(); 
    public final Image img_badship = new ImageIcon(getClass().getClassLoader()
			.getResource("res/badship.png")).getImage(); 
    public final Image img_spaceship = new ImageIcon(getClass().getClassLoader()
			.getResource("res/spaceship.png")).getImage(); 
    //*/
     /*
    public final Image img_bomb = new ImageIcon("res/bomb.png").getImage();
    public final Image img_rocket = new ImageIcon("res/rocket.png").getImage(); 
    public final Image img_badship = new ImageIcon("res/badship.png").getImage(); 
    public final Image img_spaceship = new ImageIcon("res/spaceship.png").getImage(); 
     */
    public int count = 0;

    //Космический корабль
    private SpaceShip ship;
    //Список НЛО
    private ArrayList<Ufo> ufos = new ArrayList<Ufo>();
    //Список бомб
    private ArrayList<Bomb> bombs = new ArrayList<Bomb>();
    //Список ракет
    private ArrayList<Rocket> rockets = new ArrayList<Rocket>();

    public Space(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    /**
     * Двигаем все объекты игры
     */
    public void moveAllItems()
    {
        for (BaseObject object : getAllItems())
        {
            object.move();
        }
    }

    /**
     * Метод возвращает общий список, который содержит все объекты игры
     */
    public List<BaseObject> getAllItems()
    {
        ArrayList<BaseObject> list = new ArrayList<BaseObject>(ufos);
        list.add(ship);
        list.addAll(bombs);
        list.addAll(rockets);
        return list;
    }

    /**
     * Создаем новый НЛО. 1 раз на 10 вызовов.
     */
    public void createUfo()
    {
        
        int random10 = (int) (Math.random() * 300);
        if (random10 == 0)
        {
            double x = new Random().nextInt(game.getHeight());
            double y = new Random().nextInt(50);
            ufos.add(new Ufo(x, y));
        }
    }

    /**
     * Проверяем бомбы.
     * а) столкновение с кораблем (бомба и корабль умирают)
     * б) падение ниже края игрового поля (бомба умирает)
     */
    public void checkBombs()
    {
    	ArrayList<Bomb> dies_bombs = new ArrayList<>();
 
        for (Bomb bomb : game.bombs){
            if (ship.isIntersec(bomb)){
                ship.die();
                dies_bombs.add(bomb);
            }

            if (bomb.getY() >= height)
                dies_bombs.add(bomb);
        }
        
        for(Bomb bomb : dies_bombs){
        	game.bombs.remove(bomb);
        }
    }

    /**
     * Проверяем ракеты.
     * а) столкновение с НЛО (ракета и НЛО умирают)
     * б) вылет выше края игрового поля (ракета умирает)
     */
    public void checkRockets()
    {
    	ArrayList<Rocket> dies_rockets = new ArrayList<>();
        for (Rocket rocket : game.rockets)
        {
        	ArrayList<Ufo> dies_ufos = new ArrayList<>();
            for (Ufo ufo : game.ufos)
            {
                if (ufo.isIntersec(rocket))
                {
                    dies_ufos.add(ufo);
                    dies_rockets.add(rocket);
                    count++;
                }
            }
            
            for(Ufo ufo : dies_ufos){
            	game.ufos.remove(ufo);
            }
            
            ArrayList<Bomb> dies_bombs = new ArrayList<>();
            for(Bomb bomb : game.bombs){
            	if(bomb.isIntersec(rocket)){
            		dies_bombs.add(bomb);
            		dies_rockets.add(rocket);
            	}
            }
            
            for(Bomb bomb : dies_bombs){
            	game.bombs.remove(bomb);
            }

            if (rocket.getY() <= 0)
                dies_rockets.add(rocket);
        }
        
        for(Rocket rocker : dies_rockets){
        	game.rockets.remove(rocker);
        }
    }

    /**
     * Удаляем умерсшие объекты (бомбы, ракеты, НЛО) из списков
     */
    
    /*
    public void removeDead()
    {
    	Iterator<BaseObject> iterator = game.getAllItems().iterator();
    	
        while (iterator.hasNext())
        {
        	BaseObject o = (BaseObject) iterator.next();
        		if(!o.isAlive())
        			if (o instanceof Bomb)
        				bombs.remove(o);
        			else if(o instanceof Rocket)
        				rockets.remove(o);
        			else if(o instanceof Ufo)
        				ufos.remove(o);
        }

    }

    */
    
    public SpaceShip getShip()
    {
        return ship;
    }

    public void setShip(SpaceShip ship)
    {
        this.ship = ship;
    }

    public ArrayList<Ufo> getUfos()
    {
        return ufos;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public ArrayList<Bomb> getBombs()
    {
        return bombs;
    }

    public ArrayList<Rocket> getRockets()
    {
        return rockets;
    }

    public static Space game;

    public static void main(String[] args) throws Exception
    {
    	game = new Space(300, 400);
    	JFrame f = new JFrame("Space War");
    	f.setSize(game.width+30, game.height+50);
    	f.setResizable(false);
    	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	f.setLocationRelativeTo(null);
    	f.add(new Panel());
    	
    	game.ship = new SpaceShip(game.getWidth()/2,game.getHeight()-15-20);
    	
    	f.setVisible(true);
    	
    	while(game.getShip().isAlive()){
    		game.createUfo();
    		game.moveAllItems();
    		game.checkRockets();
    		game.checkBombs();
    		//game.removeDead();
    		f.repaint();
    		//System.out.println(game.getAllItems().size());
    		game.sleep(10);
    	}
    	
    	JOptionPane.showMessageDialog(null, "Игра окончена! Ваш счет: "+game.count);
    	
    } 

    /**
     * Метод делает паузу длинной delay миллисекунд.
     */
    public static void sleep(int delay){
        try{
            Thread.sleep(delay);
        }catch (InterruptedException e){}
    }
}
