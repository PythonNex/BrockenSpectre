import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends Actor
{
    int current = 0;
    GreenfootSound tsloop = new GreenfootSound("tScreen.wav");
    GreenfootSound select = new GreenfootSound("selectSound.mp3");
    GreenfootImage zero = new GreenfootImage("titleScreen0.jpg");
    GreenfootImage one = new GreenfootImage("titleScreen1.jpg");
    GreenfootImage two = new GreenfootImage("gameGuide.jpg");
    GreenfootImage three = new GreenfootImage("gameGuide2.jpg");

    public TitleScreen()
    {    
        
        select.setVolume(50);
        tsloop.setVolume(100);
        tsloop.playLoop();
        
    }

    /**
     * Act - do whatever the TitleScreen wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        String key = Greenfoot.getKey();
        if(current == 0){
            if("up".equals(key) || "down".equals(key)){
                setImage(one);    
                current = 1;
                select.play();
            }
            else if("enter".equals(key)) {
                setImage(two);                
                current = 2;
                Greenfoot.delay(5);
            }
        }
        else if(current == 1 && (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down")) )
        {
            setImage(zero);
            current = 0;
            select.play();
        }
        else if(current == 1 && Greenfoot.isKeyDown("enter") )
        {
            fade(tsloop);
            Greenfoot.stop();
            getWorld().removeObject(this);
            
        }
        else if(current == 2 && (Greenfoot.isKeyDown("enter"))) {

            setImage(three);
            current = 3;
        }
        else if(current == 3 && (Greenfoot.isKeyDown("enter"))) {
            setImage(zero);
            current = 0; 
        }
    }

    private void fade(GreenfootSound x){
        int i=x.getVolume();
        int j=1;
        while(i>0){
            i=i-j;
            j+=2;
            x.setVolume(i);
        }
        x.stop();        
    }
}    

