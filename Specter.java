import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Specter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Specter extends AnimatedActor
{
    int xGoal, yGoal;
    Hero temp;
    
    public Specter(Hero hero) {
        super("Specter", ".png", 10);
        temp = hero;
        int xGoal = hero.getX();
        int yGoal = hero.getY();
    }
    
    /**
     * Act - do whatever the Specter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        int xGoal = temp.getX();
        int yGoal = temp.getY();
        turnTowards(xGoal,yGoal);
        move(5);
        if(atWorldEdge()){
            move(10.0);    
        }
    }    
}
