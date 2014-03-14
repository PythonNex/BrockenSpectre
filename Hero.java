import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hero extends SmoothMover
{
    double ySpeed = 0, xSpeed = 0; // the initial vertical and horizontal speeds
    int health = 100;
    int power = 0;
    int tesseracts = 0;
    
    private GreenfootImage def = new GreenfootImage("mc.png");
    private GreenfootImage rt = new GreenfootImage("mcSR.png");    
    private GreenfootImage lt = new GreenfootImage("mcSL.png");
    private GreenfootImage up = new GreenfootImage("mcSU.png");    
    private GreenfootImage dw = new GreenfootImage("mcSD.png");
    private GreenfootImage flare = new GreenfootImage("flare.png");
    
    GreenfootSound tG = new GreenfootSound("tesseractGet.wav");
    GreenfootSound pG = new GreenfootSound("potionGet.mp3");
    GreenfootSound sG = new GreenfootSound("starGet.mp3");
    GreenfootSound eF = new GreenfootSound("efBump.wav");
    GreenfootSound gB = new GreenfootSound("ghostBump.wav");
    GreenfootSound hH = new GreenfootSound("hithard.wav");
    
    /**
     *Constructor for our hero. 
     */
    public Hero(){
        setImage(def);
    }
    
    
    /**
     * Act - do whatever the Hero wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        getOrientation();
        mover();
        getIcon();
        checkCollision();
    }
    
    /**
     * Determines any changes in horizontal and vertical speeds for the actor.
     */
    private void getOrientation()
    {
        // sets requested direction of move, or continues in current direction
        
        if (Greenfoot.isKeyDown("left")) xSpeed--; //acceleration
        if (Greenfoot.isKeyDown("right")) xSpeed++; 
        if (Greenfoot.isKeyDown("up")) ySpeed--;
        if (Greenfoot.isKeyDown("down")) ySpeed++;
        if (Greenfoot.isKeyDown("a")) setRotation(getRotation()-20);//pitch and yaw
        if (Greenfoot.isKeyDown("d")) setRotation(getRotation()+20);
        if (Greenfoot.isKeyDown("f")) setRotation(0);
        if (Greenfoot.isKeyDown("w")){
            xSpeed*=1.5;
            ySpeed*=1.5;
        }
        if (Greenfoot.isKeyDown("s")){ //brakes
            xSpeed/=2;
            ySpeed/=2;
        }
        
        
    }
    
    private void getIcon(){
        int x = flare.getWidth();
        int y = flare.getHeight();
        GreenfootImage temp = new GreenfootImage(flare);
        if(xSpeed>15 && Greenfoot.isKeyDown("right")){
            setImage(rt);
        }else if(xSpeed<-15 && Greenfoot.isKeyDown("left")){
            setImage(lt);
        }else if(ySpeed<-15 && Greenfoot.isKeyDown("up")){
            setImage(up);
        }else if(ySpeed>15 && Greenfoot.isKeyDown("down")){
            setImage(dw);
        }else setImage(def);
    }
    
    
    /**
     * Moves the actor with appropriate image.  
     */
    private void mover()
    {
        
        setLocation(getExactX()+xSpeed, getExactY()+ySpeed);
        if(getX()<=0 || getX()>=800)
            xSpeed= -xSpeed*.6;
        if(getY()<=0 || getY()>=800)
            ySpeed= -ySpeed*.6;
    }
    
    /**
     * Checks for obstacles and adjusts the position and attributes of the actor accordingly.
     */
    private void checkCollision(){
        EnergyFence fence = (EnergyFence) getOneIntersectingObject(EnergyFence.class);
        Tesseract tesseract = (Tesseract) getOneIntersectingObject(Tesseract.class);
        Health health = (Health) getOneIntersectingObject(Health.class);
        StartBeat star = (StartBeat) getOneIntersectingObject(StartBeat.class);
        Specter specter = (Specter) getOneIntersectingObject(Specter.class);
        Potion potion = (Potion) getOneIntersectingObject(Potion.class);
        Cannon cannon = (Cannon) getOneIntersectingObject(Cannon.class);
        
        //Energy Fence logic
        double kineticEnergy = Math.sqrt((xSpeed*xSpeed)+(ySpeed*ySpeed));
        if(fence != null){
            this.xSpeed = -xSpeed*.8;
            this.ySpeed= -ySpeed*.8;
            fence.updateStrength(kineticEnergy);
            eF.setVolume(80);
            eF.play();
        }
        
        //Tesseract logic
        if(tesseract != null){
            this.tesseracts+=1;
            getWorld().removeObject(tesseract);
            tG.setVolume(88);
            tG.play();
        }
        
        //Health logic
        if(health != null){
            this.health+=10;
            getWorld().removeObject(health);
            pG.setVolume(70);
            pG.play();
        }
        
        //StartBeat logic
        if(star != null){
            this.health=100;
            this.power +=2;
            getWorld().removeObject(star);
            pG.setVolume(75);
            sG.play();
        }
        //Potion logic
        if(potion != null){
            this.power +=1;
            getWorld().removeObject(potion);
            pG.setVolume(70);
            pG.play();
        }
        
        //Specter logic
        if(specter != null){
            if(this.power > 0){
                this.health -= 5;
                this.power-=1;
                this.xSpeed = xSpeed * -1 *Greenfoot.getRandomNumber(5);
                this.ySpeed = ySpeed * -1 *Greenfoot.getRandomNumber(5);
            }
            else{
                this.health -= 25;
                this.xSpeed = xSpeed * -1 *Greenfoot.getRandomNumber(12);
                this.ySpeed = ySpeed * -1 *Greenfoot.getRandomNumber(12);
            }
            gB.setVolume(75);
            gB.play();
        }
        
        //Cannon logic
        if(cannon != null){
            if(this.power > 0){
                this.health -= 5;
                this.power-=1;
                this.xSpeed = xSpeed * -1 *Greenfoot.getRandomNumber(4);
                this.ySpeed = ySpeed * -1 *Greenfoot.getRandomNumber(4);
            }
            else{
                this.health -= 15;
                this.xSpeed = xSpeed * -1 *Greenfoot.getRandomNumber(7);
                this.ySpeed = ySpeed * -1 *Greenfoot.getRandomNumber(7);
            }
            
            getWorld().removeObject(cannon);
            hH.setVolume(88);
            hH.play();
        }
    }
    
    public int getHealth(){
        return health;
    }
    
    public int getPower(){
        return power;
    }
    
    public int getNumTesseracts(){
        return tesseracts;
    }
}
