import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cannon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cannon extends Mover
{
    boolean hasTurned = false;
    Hero temp;
    public Cannon(Hero hero) {
        temp=hero;
        this.turnTowards(hero.getX(),hero.getY());
    }
    
    /**
     * Act - do whatever the Cannon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
       super.act();       
       move(30.0);
       
       if(!hasTurned){
           this.turnTowards(temp.getX(),temp.getY());
           hasTurned = true;
       }
       
       if(atWorldEdge()){
           getWorld().removeObject(this);
       }
    }    
}
