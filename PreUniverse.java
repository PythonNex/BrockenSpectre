import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PreUniverse here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PreUniverse extends World
{
    Actor tScreen = new TitleScreen();
    
    /**
     * Constructor for objects of class PreUniverse.
     * 
     */
    public PreUniverse()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 800, 1); 
        addObject(tScreen,400,400);
        Greenfoot.setSpeed(32);
        Greenfoot.start();
    }
    
    public void stopped(){
        SWorld uni = new Universe();
        Greenfoot.setWorld(uni);
        Greenfoot.start();
    }
}
