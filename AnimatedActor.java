import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * The AnimatedActor class serves as a superclass for other, more specific
 * Actor classes. It provides a mechanism to give actors an animated image.<p>
 * 
 * To use this class, you need a sequence of image files to be used for the
 * animation. Let's say we create four files named "cat0.png", "cat1.png",
 * "cat2.png" and "cat3.png". In this case, our base name would be "cat", the
 * suffix is ".png", and the number of images is 4.<p>
 * 
 * You can create an animated actor by subclassing this class, and calling this
 * class's constructor with the base name, suffix, and number of images as a 
 * parameter. So, to make an animated cat, I might have a subclass 'Cat' with 
 * the folling constructor"<p>
 * 
 * <pre>
 *   public Cat() {
 *       super("cat", ".png", 4);
 *   }
 * <pre>
 * 
 * The image files have to be numbered starting at 0, and they have to ve in the
 * scenario's 'image' folder.<p>
 * 
 * The image will change with every act step.
 * 
 * @author mik
 * @version 1.0
 */
public class AnimatedActor extends Mover
{
    private GreenfootImage[] images;
    private int currentImage = 0;
    
    /**
     * Construct an animated actor. This assumes that you have provided image
     * file (in the 'images' folder) named 'basenameN.suffix', where N is
     * [0..numberOfImages-1].
     * 
     * @param basename  The base name of the image files.
     * @param suffix  The suffix of the image files (must include the ".").
     * @param noOfImages  The number of images to be use din the animation.
     */
    public AnimatedActor(String basename, String suffix, int noOfImages)
    {
        images = new GreenfootImage[noOfImages];
        for(int i=0; i < noOfImages; i++) {
            images[i] = new GreenfootImage(basename + i + suffix);
        }
        setImage(images[currentImage]);
    }
    
    /**
     * Act - do whatever the AnimatedActor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        currentImage = (currentImage + 1) % images.length;
        setImage(images[currentImage]);
    }
}
