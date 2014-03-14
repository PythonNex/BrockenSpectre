import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnergyFence here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnergyFence extends AnimatedActor
{
    double strength=200;
    public EnergyFence() {
        super("ef", ".png", 6);
    }

     /**
     * Act - do whatever the Tesseract wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        checkStatus();
    }
    
    public void updateStrength(double energy){
        strength-=energy;
    }
    
    private void checkStatus(){
        if (strength<=0)
            getWorld().removeObject(this);
    }
}
