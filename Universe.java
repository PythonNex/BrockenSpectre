import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Game Universe.
 * 
 * 
 */
public class Universe extends SWorld
{
    healthBar[] healthHearts = new healthBar[10];
    powerBar[] powerHearts = new powerBar[10];
    tessBar[] tesserSquares = new tessBar[10];
    Hero brocken  = new Hero();
    Actor dummy = new Dummy();
    
    
    int dx = 20;
    int x=580;
    int y1=50;
    int y2=85;
    int y3=130;
    
    int numSpecters=0;
    
    GreenfootSound levelOne = new GreenfootSound("lvOneST.mp3");
    GreenfootSound gameOverMusic = new GreenfootSound("gameOver.wav");
    GreenfootImage gameOver = new GreenfootImage("gameOverScreen.jpg");
    GreenfootImage win = new GreenfootImage("winScreen.jpg");
    GreenfootSound winMusic = new GreenfootSound("winMusic.mp3");
    
    long startMusicTime=0;
    long secondsElapsed=0;
    boolean isGameOver = false;
        
    /**
     * Constructor for objects of class Universe.
     * 
     */
    public Universe()
    {    
        // Create a new world with 1600x1600 cells with a cell size of 1x1 pixels.
        super(800, 800, 1,1600,1600); 
        setMainActor(brocken, 470, 470);
        GreenfootImage bg = new GreenfootImage("ValleyOfDreams2.jpg");
        setScrollingBackground(bg); // set the scolling background image
        Greenfoot.setSpeed(50);
        setupBars();
        setupLevelOne();
        levelOne.play();
        startMusicTime = System.nanoTime();
        removeObjects(getObjects(TitleScreen.class));
        
        
    }
    
    private void setupBars(){   
        
        for(int i = 0; i<10;i++){
            healthHearts[i] = new healthBar();
            addObject(healthHearts[i], x+i*dx, y1, false);  
            
           
        }
        addObject(new hText(), x-3*dx, y1-3, false);
        addObject(new pText(), x-3*dx, y2-3, false);
        addObject(new tText(), x-3*dx, y3-3, false);
    }
    
    private void updateBars(){
        int numHearts = brocken.getHealth() / 10;
        int numPower = brocken.getPower();
        int numTesseracts = brocken.getNumTesseracts();
                
        if(numHearts>0){
            for(int i=0;i<10;i++){
                if(i+1<=numHearts && healthHearts[i] == null){
                    healthHearts[i] = new healthBar();   
                    addObject(healthHearts[i], x+i*dx, y1, false);
                }
                else if(i+1>numHearts){
                    removeObject(healthHearts[i]);
                    healthHearts[i] = null;
                }
                    
            }
        }
        else if(numHearts<=0){
            int i=0;
            while(healthHearts[i] != null){
                removeObject(healthHearts[i]);
                healthHearts[i] = null;
                i++;
            }
        }
        
        if(numPower>0){
            for(int i=0;i<10;i++){
                if(i+1<=numPower && powerHearts[i] == null){
                    powerHearts[i] = new powerBar();
                    addObject(powerHearts[i], x+i*dx, y2, false); 
                }
                else if(i+1>numPower){
                    removeObject(powerHearts[i]);
                    powerHearts[i] = null;
                }
            }
        }
        else{
            removeObject(powerHearts[0]);
            powerHearts[0] = null;
        }
        
        if(numTesseracts>0){
            for(int i=0;i<10;i++){
                if(i+1<=numTesseracts && tesserSquares[i] == null){
                    tesserSquares[i] = new tessBar();
                    addObject(tesserSquares[i], x+i*dx, y3, false); 
                }
                else if(i+1>numTesseracts){
                    removeObject(tesserSquares[i]);
                    tesserSquares[i] = null;
                }
            }
        }
        else{
            removeObject(tesserSquares[0]);
            tesserSquares[0] = null;
        }
        
    }
    
    private void setupLevelOne(){
        addObject(new Tesseract(), -70, -150, true);
        addObject(new EnergyFence(), -70, -150, true);
        
        addObject(new Tesseract(), 1000, 150, true);
        addObject(new EnergyFence(), 1000, 150, true);
        
        addObject(new Tesseract(), -77, 1100, true);
        addObject(new EnergyFence(), -77, 1100, true);
        
        addObject(new Tesseract(), 1000, 1150, true);
        addObject(new EnergyFence(), 1000, 1150, true);
        
        addObject(new Tesseract(), 700, 550, true);
        addObject(new EnergyFence(), 700, 550, true);
        
        addObject(new Tesseract(), 0, 800, true);
        addObject(new Tesseract(), 450, 137, true);
        addObject(new Tesseract(), 986, 243, true);
        addObject(new Tesseract(), 109, 698, true);
        addObject(new Tesseract(), 999, 836, true);
        
        
    }
    
    private void newBullet(){
    }
    
    private void newCannon(){
        Cannon c = new Cannon(brocken);
        int x = Greenfoot.getRandomNumber(750)+25;
        int y = Greenfoot.getRandomNumber(2)*700+25;
        addObject (c, x, y);
        
    }
    
    private void newPotion(){
        int x = Greenfoot.getRandomNumber(900);
        int y = Greenfoot.getRandomNumber(900);
        addObject(new Potion(), x-100, y, true);
    }
    private void newStar(){
        int x = Greenfoot.getRandomNumber(900);
        int y = Greenfoot.getRandomNumber(900);
        addObject(new StartBeat(), x-100, y, true);
    }
    private void newHealth(){
        int x = Greenfoot.getRandomNumber(900);
        int y = Greenfoot.getRandomNumber(900);
        addObject(new Health(), x-100, y, true);
    }
    
    private void releaseSpecter(){
        addObject(new Specter(brocken), 800, 800, true);
    }
    
    
    private void giveGoodies(){
        int dice = Greenfoot.getRandomNumber(4000);
        if(dice<10)
            newHealth();
        if(dice>10 && dice<20)
            newPotion();
        if(dice>=72 && dice<=77)
            newStar();
            
    }
    
    public void act(){
        super.act();
        updateBars();
        giveGoodies();
        getGameStatus();
        if(!isGameOver){
            secondsElapsed = (System.nanoTime() - startMusicTime)/100000000;
        }
        if(secondsElapsed == 162 || secondsElapsed ==569 || secondsElapsed ==830 ||
            secondsElapsed ==960 || secondsElapsed ==1370 || secondsElapsed ==1630 || 
            secondsElapsed ==1760 || secondsElapsed ==1890 || secondsElapsed ==1930 ||
            secondsElapsed ==1960 ||secondsElapsed == 1990){
            newCannon();
        }
        
        if(secondsElapsed == 100){
            if(numSpecters==0){
                releaseSpecter();
                numSpecters++;
            }
        }
    }
    
    public void getGameStatus(){
        if(brocken.getHealth()<=0){
            isGameOver = true;
            fade(levelOne);
            gameOverMusic.playLoop();
            gameOver.setTransparency(0);
            removeObjects(this.getObjects(null));
            removeObjects(getObjects(Cannon.class));
            dummy.setImage(gameOver);
            addObject(dummy,400,400);
            int i = 0;
            while(gameOver.getTransparency()<255){
                if(Greenfoot.getRandomNumber(100000)<10)
                    gameOver.setTransparency(++i);
            }
            
            removeObjects(getObjects(Cannon.class));
            
        }
        if(brocken.getNumTesseracts()==10){
            isGameOver = true;
            fade(levelOne);
            winMusic.play();
            win.setTransparency(0);
            removeObjects(this.getObjects(null));
            removeObjects(getObjects(Cannon.class));
            dummy.setImage(win);
            addObject(dummy,400,400);
            int i = 0;
            while(win.getTransparency()<255){
                if(Greenfoot.getRandomNumber(100000)<10)
                    win.setTransparency(++i);
            }
            removeObjects(getObjects(Cannon.class));
        }
    }
    
    public void started(){
        
        
        
    }
    
    public void stopped(){
        levelOne.stop();
        gameOverMusic.stop();
        winMusic.stop();
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
